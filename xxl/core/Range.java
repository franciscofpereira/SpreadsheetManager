package xxl.core;

import java.util.ArrayList;
import java.util.List;

import xxl.core.exception.InvalidCellException;
import xxl.core.exception.InvalidRangeException;

public class Range {
    private int _beginRow;
    private int _beginColumn;
    private int _endRow;
    private int _endColumn;
    private List<Cell> cellList = new ArrayList<>();
    private Spreadsheet _spreadsheet;

    public Range(int beginRow, int beginColumn, int endRow, int endColumn, Spreadsheet spreadsheet){
        _beginRow = beginRow;
        _beginColumn = beginColumn;
        _endRow = endRow;
        _endColumn = endColumn;
        _spreadsheet = spreadsheet;
    }
    
    List<Cell> getCells() throws InvalidCellException{

            // Case of horizontal Range
            if(_beginRow == _endRow && _beginColumn != _endColumn){
                for ( int col = _beginColumn; col < _endColumn; col++){
                    Cell cell = _spreadsheet.getCell(_beginRow,col);
                    cellList.add(cell);   
                }
                return cellList;
            }
            // Case of vertical Range
            if(_beginRow != _endRow && _beginColumn == _endColumn){
                for ( int row = _beginRow; row < _endRow; row++){
                    Cell cell = _spreadsheet.getCell(row, _beginColumn);
                    cellList.add(cell);   
                }
                return cellList;
            }

            throw new InvalidCellException("Invalid cell accessed in Range.");

        }   

    }
        

