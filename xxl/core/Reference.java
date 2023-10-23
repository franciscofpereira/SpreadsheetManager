package xxl.core;

import xxl.core.exception.InvalidCellException;

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
        return compute() + "=" + _row + ";" + _column;
    }

    public String asArg(){
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
        
        if(value() == null){
            return new LiteralString("#VALUE");
        }
        
        return value();
    }
}
