package xxl.core;

// FIXME import classes

import java.io.Serial;
import java.io.Serializable;

import xxl.core.exception.InvalidCellException;
import xxl.core.exception.UnrecognizedEntryException;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {
  @Serial
  private static final long serialVersionUID = 202308312359L;
  
  private int _numRows;
  private int _numColumns;
  private boolean _changed;
  private Cell[][] _cells;

  public Spreadsheet( int rows, int columns){     // Spreadsheet Constructor
    _numRows = rows;
    _numColumns = columns;
    //_changed = false;                             // Initializes 'changed' state to false
    _cells = new Cell[rows][columns];             // Initializes cells array
    createCells();                                // Invokes the method that creates the Spreadsheet's Cell objects
  }

  // Creates the spreadsheet's cells
  public void createCells(){                      
    for(int row = 1; row <= _numRows; row++) {
      for(int column = 1; column <= _numColumns; column++) {
        _cells[row - 1][column - 1] = new Cell(row, column);    
      }
    }
  }


  // Returns an object cell when given its coordinates
  public Cell getCell(int row, int column) throws InvalidCellException{
    
    if (isValidCell(row, column)){
      return _cells[row - 1][column - 1];   // Returns the cell when valid coordinates are provided
    }
    else{
      throw new InvalidCellException("Invalid cell coordinates: (" + row + "," + column +").");   
    }
  }
    

  // Returns true if coordinates are valid, else returns false
  private boolean isValidCell(int row, int column){
    return row >= 1 && row <= _numRows && column >= 1 && column <= _numColumns;
  }
  // FIXME define attributes
  // FIXME define contructor(s)
  // FIXME define methods
  

  /**
   * Insert specified content in specified address.
   *
   * @param row the row of the cell to change 
   * param column the column of the cell to change
   * @param contentSpecification the specification in a string format of the content to put
   *        in the specified cell.
   */
  public void insertContent(int row, int column, String contentSpecification) throws UnrecognizedEntryException /* FIXME maybe add exceptions */ {
    //FIXME implement method
  }
}
