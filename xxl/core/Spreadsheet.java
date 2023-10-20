package xxl.core;

// FIXME import classes

import java.io.Serial;
import java.io.Serializable;

import xxl.app.exception.InvalidCellRangeException;
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
  //private boolean _changed;
  private Cell[][] _cells;
  //private User _user;

  public Spreadsheet( int rows, int columns){     // Spreadsheet Constructor
    _numRows = rows;
    _numColumns = columns;
    //_changed = false;                             // Initializes 'changed' state to false
    _cells = new Cell[rows][columns];             // Initializes cells array
    createCells();                                // Invokes the method that creates the Spreadsheet's Cell objects
  }

  // Creates the spreadsheet's cells objects
  public void createCells(){                      
    for(int row = 1; row <= _numRows; row++) {
      for(int column = 1; column <= _numColumns; column++) {
        _cells[row - 1][column - 1] = new Cell(row, column);    
      }
    }
  }

  /**
   * Getter for a Cell object. Returns a Cell when given its coordinates.
   *
   * @param row row of Cell object
   * @param column column of Cell object
   * @throws InvalidCellException when specified coordinates are out of bounds of the spreadsheet
   * @returns Cell object 
   */
  // Returns an object cell when given its coordinates
  public Cell getCell(int row, int column) throws InvalidCellException{
    
    if (isValidCell(row, column)){
      return _cells[row - 1][column - 1];   // Returns the cell when valid coordinates are provided
    }
    else{
      throw new InvalidCellException("Invalid cell coordinates: (" + row + "," + column +").");   
    }
  }
    
  /**
   * Checks if a given Cell is within the bounds of the Spreadsheet
   * 
   * @param row cell's row
   * @param column cell's column
   * @return boolean: true if cell is valid, false otherwise
   */
  public boolean isValidCell(int row, int column){
    return row >= 1 && row <= _numRows && column >= 1 && column <= _numColumns;
  }
  
  
  /**
   * Insert specified content in specified address.
   *
   * @param row the row of the cell to change 
   * param column the column of the cell to change
   * @param contentSpecification the specification in a string format of the content to put
   *        in the specified cell.
   */
  public void insertContent(int row, int column, Content contentSpecification){ //throws UnrecognizedEntryException /* FIXME maybe add exceptions */ {
    
    try{
      getCell(row, column).setContent(contentSpecification);
    } catch (InvalidCellException ice){
      System.err.println("Failed to insert content. Invalid cell coordinates: " + row + ";" + column);
    }
  }

  /**
   * Creates a Range object from a string description of it.
   * 
   * @param range String that specifies the range.
   * @return Range object.
   * @throws InvalidCellRangeException
   */
  public Range createRange(String range) throws InvalidCellRangeException  {
    String[] rangeCoordinates;
    int firstRow, firstColumn, lastRow, lastColumn;
    
    if (range.indexOf(':') != -1) {
      rangeCoordinates = range.split("[:;]");
      firstRow = Integer.parseInt(rangeCoordinates[0]);
      firstColumn = Integer.parseInt(rangeCoordinates[1]);
      lastRow = Integer.parseInt(rangeCoordinates[2]);
      lastColumn = Integer.parseInt(rangeCoordinates[3]);
    } else {
      rangeCoordinates = range.split(";");
      firstRow = lastRow = Integer.parseInt(rangeCoordinates[0]);
      firstColumn = lastColumn = Integer.parseInt(rangeCoordinates[1]);
    }

    if( isRangeValid(firstRow, lastColumn, lastRow, lastColumn))
      return new Range(firstColumn, lastColumn, lastRow, lastColumn, this);
    
    else{
      throw new InvalidCellRangeException("Specified range is invalid.");
    }
  }


  /**
   * Checks if a given Range is valid when given its starting and ending cell coordinates
   * (essencially it checks if the range is either horizontal or vertical)
   * 
   * @param beginRow r
   * @param beginColumn 
   * @param endRow
   * @param endColumn
   * @return boolean: true if range is valid, false otherwise
   */
  // Returns true if specified Range is valid, else returns false.
  public boolean isRangeValid(int beginRow, int beginColumn, int endRow, int endColumn){

    if( isValidCell(beginRow, beginColumn) && isValidCell(endRow, endColumn) ) {

      // horizontal range
      if(beginRow == endRow && beginColumn != endColumn){
        return true;
      }

      // vertical range
      if(beginRow != endRow && beginColumn == endColumn){
        return true;
      }

      if(beginRow == endRow && beginColumn == endColumn){
        return true;
      }
  
    }

    // range is a singular cell
    else if( isValidCell(beginRow, beginColumn) && (endRow == 0 && endColumn == 0)){
        return true;
      }
    
    return false; 
  }
}
