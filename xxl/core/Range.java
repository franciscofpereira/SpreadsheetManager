package xxl.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import xxl.core.exception.InvalidCellException;
import xxl.core.exception.UnrecognizedEntryException;
import xxl.core.exception.UnrecognizedFunctionException;

public class Range implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 9876543210L;

    private int _beginRow;
    private int _beginColumn;
    private int _endRow;
    private int _endColumn;
    private List<Cell> _cellList = new ArrayList<>();
    private Spreadsheet _spreadsheet;
    private String _rangeDescriptioString;
    private RangeType _type;
    private int _direction;
    
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
                
                _type = RangeType.HORIZONTAL;
                // Ascending order
                if(_beginColumn < _endColumn){
                    _direction = 1;
                    for ( int col = _beginColumn; col <= _endColumn; col++){
                        Cell cell = _spreadsheet.getCell(_beginRow,col);
                        _cellList.add(cell);   
                    }
                }
                // Descending order
                else{
                    _direction = -1;
                    for( int col = _beginColumn; col>=_endColumn; col--){
                        Cell cell = _spreadsheet.getCell(_beginRow,col);
                        _cellList.add(cell); 
                    }
                } 
                return _cellList;
                }
                
            // Case of vertical Range
            if(_beginRow != _endRow && _beginColumn == _endColumn){

                _type = RangeType.VERTICAL;
                // Ascending order
                if(_beginRow < _endRow){
                    _direction = 1;
                    for ( int row = _beginRow; row <= _endRow; row++){
                        Cell cell = _spreadsheet.getCell(row, _beginColumn);
                        _cellList.add(cell);   
                    }
                }
                // Descending order
                else{
                    _direction = -1;
                    for( int row = _beginRow; row >= _endRow; row--){
                        Cell cell = _spreadsheet.getCell(row, _beginColumn);
                        _cellList.add(cell);
                    }
                }
                return _cellList;
            }

            // Case of singular cell
            if((_beginRow == _endRow && _beginRow == _endRow)){
                _type = RangeType.SINGULAR_CELL;
                _direction = 0;
                Cell cell = _spreadsheet.getCell(_beginRow, _beginColumn);
                _cellList.add(cell);
                return _cellList;
            }
        } catch(InvalidCellException ice){
            System.err.println("Attempt to add invalid cell to Range _cellList.");
        }    
        
        return null;
    }   

    public int getRangeDirection(){
        return _direction;
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
    
    public RangeType getRangeType(){
        return _type;
    }
}

