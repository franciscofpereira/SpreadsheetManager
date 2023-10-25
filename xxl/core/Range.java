package xxl.core;

import java.util.ArrayList;
import java.util.List;

import xxl.core.exception.InvalidCellException;
import xxl.core.exception.UnrecognizedEntryException;
import xxl.core.exception.UnrecognizedFunctionException;


public class Range {
    private int _beginRow;
    private int _beginColumn;
    private int _endRow;
    private int _endColumn;
    private List<Cell> _cellList = new ArrayList<>();
    private Spreadsheet _spreadsheet;
    private String _rangeDescriptioString;

    public Range(int beginRow, int beginColumn, int endRow, int endColumn, String range, Spreadsheet spreadsheet){
        _beginRow = beginRow;
        _beginColumn = beginColumn;
        _endRow = endRow;
        _endColumn = endColumn;
        _rangeDescriptioString = range;
        _spreadsheet = spreadsheet;
        initializeRangeCellList();
    }
    
    public String asArg(){
        return _rangeDescriptioString;
    }
    
    List<Cell> initializeRangeCellList(){
        
        try{
            // Case of horizontal Range
            if(_beginRow == _endRow && _beginColumn != _endColumn){
                // Ascending order
                if(_beginColumn < _endColumn){
                    for ( int col = _beginColumn; col <= _endColumn; col++){
                        Cell cell = _spreadsheet.getCell(_beginRow,col);
                        _cellList.add(cell);   
                    }
                }
                // Descending order
                else{
                    for( int col = _beginColumn; col>=_endColumn; col--){
                        Cell cell = _spreadsheet.getCell(_beginRow,col);
                        _cellList.add(cell); 
                    }
                } 
                return _cellList;
                }
                
            // Case of vertical Range
            if(_beginRow != _endRow && _beginColumn == _endColumn){
                // Ascending order
                if(_beginRow < _endRow){
                    for ( int row = _beginRow; row <= _endRow; row++){
                        Cell cell = _spreadsheet.getCell(row, _beginColumn);
                        _cellList.add(cell);   
                    }
                }
                // Descending order
                else{
                    for( int row = _beginRow; row >= _endRow; row--){
                        Cell cell = _spreadsheet.getCell(row, _beginColumn);
                        _cellList.add(cell);
                    }
                }
                return _cellList;
            }

            // Case of singular cell
            if((_beginRow == _endRow && _beginRow == _endRow)){
                Cell cell = _spreadsheet.getCell(_beginRow, _beginColumn);
                _cellList.add(cell);
                return _cellList;
            }
        } catch(InvalidCellException ice){
            System.err.println("Attempt to add invalid cell to Range _cellList.");
        }    
        
        return null;
    }   

    // Getter for the _cellList field
    public List<Cell> getCellList(){
        return _cellList;
    }

    // Inserts content in the given Range
    public void insertRangeContent(String contentSpecification) throws UnrecognizedEntryException,UnrecognizedFunctionException{
        
        Parser p = new Parser(_spreadsheet);
        
        Content content = p.parseContent(contentSpecification);

        for(Cell cell: _cellList){
            _spreadsheet.insertContent(cell.getRow(), cell.getColumn(), content);
        }  
    }
}