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

    public Range(int beginRow, int beginColumn, int endRow, int endColumn, Spreadsheet spreadsheet){
        _beginRow = beginRow;
        _beginColumn = beginColumn;
        _endRow = endRow;
        _endColumn = endColumn;
        _spreadsheet = spreadsheet;
    }
    
    List<Cell> getRangeCells() throws InvalidCellException{

            // Case of horizontal Range
            if(_beginRow == _endRow && _beginColumn != _endColumn){
                for ( int col = _beginColumn; col < _endColumn; col++){
                    Cell cell = _spreadsheet.getCell(_beginRow,col);
                    _cellList.add(cell);   
                }
                return _cellList;
            }
            // Case of vertical Range
            if(_beginRow != _endRow && _beginColumn == _endColumn){
                for ( int row = _beginRow; row < _endRow; row++){
                    Cell cell = _spreadsheet.getCell(row, _beginColumn);
                    _cellList.add(cell);   
                }
                return _cellList;
            }

            // Case of singular cell
            if((_beginRow == _endRow && _beginRow == _endRow) || (_endRow == 0 && _endColumn == 0)){
                Cell cell = _spreadsheet.getCell(_beginRow, _beginColumn);
                _cellList.add(cell);
                return _cellList;
            }
            
            throw new InvalidCellException("Invalid cell accessed in Range.");

        }   

    
        
    public void insertRangeContent(String contentSpecification){
        
        Parser p = new Parser();
        
        try{

            Content content = p.parseContent(contentSpecification);

            for(Cell cell: _cellList){
                _spreadsheet.insertContent(cell.getRow(), cell.getColumn(), content);
            }
        } catch (UnrecognizedEntryException | UnrecognizedFunctionException e) {
            System.err.println("The specified content is not valid.");
        }
    }
}