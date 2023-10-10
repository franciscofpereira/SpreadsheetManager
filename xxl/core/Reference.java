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
        return _row + ";" + _column;
    }

    Literal<?> value(){
        
        try{
            return _spreadsheet.getCell(_row, _column).value();
        }catch (InvalidCellException ice){
            System.err.println("Error in Reference class: Invalid cell referenced.");    
        }
        
        return null;
    }
}
