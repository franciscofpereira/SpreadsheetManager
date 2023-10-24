package xxl.core;

import java.io.Serial;
import java.io.Serializable;

import xxl.core.exception.InvalidCellException;

/**
 * Class that contains the implementation of the methods for the 2D array storage strategy
 * 
 */
public class TwoDimensionArrayStrategy implements CellStorageStrategy, Serializable{
    
    private Cell[][] _cells;

    @Serial
    private static final long serialVersionUID = 202908312759L;

    public TwoDimensionArrayStrategy(int numRows, int numColumns){
        _cells = new Cell[numRows][numColumns];
    }
    
    @Override
    public void createCells(int numRows, int numColumns) {
        for(int row = 1; row <= numRows; row++) {
            for(int column = 1; column <= numColumns; column++) {
              _cells[row - 1][column - 1] = new Cell(row, column);    
            }
          }
    }

    @Override
    public Cell getCell(int row, int column) throws InvalidCellException {
        return _cells[row - 1][column - 1];   
    }

}
