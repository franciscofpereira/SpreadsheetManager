package xxl.core;

import xxl.core.exception.InvalidCellException;
import xxl.core.exception.UnrecognizedEntryException;
import xxl.core.exception.UnrecognizedFunctionException;

public class Reference extends Content{
    
    private int _row;
    private int _column;
    private Spreadsheet _spreadsheet;


    public Reference(int row, int column, Spreadsheet spreadsheet){
        _row = row;
        _column = column;
        _spreadsheet = spreadsheet;
    }
    
    public String toString(){
        
        if(value() == null){
            return "#VALUE=" + _row + ";" + _column ;
        }
        
        return value() + "=" + _row + ";" + _column;
    }

    public String asString(){
        return _row + ";" + _column;
    }
    // Getter for Literal value stored in referenced cell
    Literal value(){
        
        try{
            return _spreadsheet.getCell(_row, _column).value();
        }catch (InvalidCellException ice){
            System.err.println("Error in Reference class. Invalid cell referenced: " + _row + ";" + _column);    
        }
        
        return null;
    }

    
    // Computes the value of the referenced value
    public Literal compute(){

        Literal value = value();
        
        if(value() == null){
            return new LiteralString("#VALUE");
        }
        
        // If it's a LiteralInteger we just return its value
        if( value instanceof LiteralInteger ){
            return value;
        }

        // It means it is a LiteralString
        else{

            // We try parsing it to see if it is either a Function or another Reference
            try{
                Parser parser = new Parser(_spreadsheet);
                String expression = value.toString();
                Content var = parser.parseContent(expression);
                
                // If it is another Reference we call this method recursively on that Reference
                if( var instanceof Reference ){
                    return ((Reference)var).compute();
                }
                
                // If it is a Function we compute its value
                if( var instanceof Function ){
                    return ((Function)var).compute();
                }
            
                // If something goes wrong while parsing it means the value is just a String
            }catch(UnrecognizedEntryException | UnrecognizedFunctionException e){
                System.err.println("Something went wrong computing reference value at: " + _row + ";" + _column);

            }    
        }
        
        // It means this is a LiteralString and the value is neither a Reference or Function
        return value;
    }
}
