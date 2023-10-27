package xxl.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

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
  private CellStorageStrategy _storageStrategy;
  private boolean _changed;
  private boolean _unsaved;
  private CutBuffer _cutBuffer = new CutBuffer();
  //private User _user;

  public Spreadsheet( int rows, int columns){     
    _numRows = rows;
    _numColumns = columns;
    _changed = false;
    _unsaved = true;                                               
    setStorageStrategy(new TwoDimensionArrayStrategy(rows, columns)); // By default, 2D array strategy is used
    createCells();                                                        
  }

  /**
   * Setter for the Spreadsheet's cells storage strategy.
   * Allows changing the desired storage strategy.
   * @param storageStrategy
   */
  public void setStorageStrategy(CellStorageStrategy storageStrategy){
    _storageStrategy = storageStrategy;
  }

 
  /**
   * Getter for the Spreadsheet's cells storage strategy.
   * @return _storageStrategy
   */
  public CellStorageStrategy getStorageStrategy(){
    return _storageStrategy;
  }


  /**
   * Initializes the Spreadsheet's cell objects.
   */
  public void createCells(){                      
    _storageStrategy.createCells(_numRows, _numColumns);
  }

  
  /**
   * Getter for a Cell object. Returns a Cell when given its coordinates.
   *
   * @param row row of Cell object
   * @param column column of Cell object
   * @throws InvalidCellException when specified coordinates are out of bounds of the spreadsheet
   * @returns Cell object 
   */
  public Cell getCell(int row, int column) throws InvalidCellException{
    
    if (isValidCell(row, column)){
      return _storageStrategy.getCell(row, column);   // Returns the cell when valid coordinates are provided
    }
    else{
      throw new InvalidCellException("Invalid cell coordinates: (" + row + "," + column +").");   
    }
  }
  

  /**
   * Getter for the Spreadsheet's cut buffer
   * @return CutBuffer object
   */
  public CutBuffer getCutBuffer(){
    return _cutBuffer;
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
   * @param column the column of the cell to change
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
  public Range createRange(String range) throws UnrecognizedEntryException{
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

    if( isRangeValid(firstRow, firstColumn, lastRow, lastColumn))
      return new Range(firstRow, firstColumn, lastRow, lastColumn, range, this);

    else{
      throw new UnrecognizedEntryException("Specified range cannot be created.");
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

      // singular cell
      if(beginRow == endRow && beginColumn == endColumn){
        return true;
      }
  
    }
    return false; 
  }

  // State variable. Registers that something was changed
    public void changed(){
      _changed = true;
      _unsaved = true;
  }

  // State variable. Registers that everything was saved
    public void saved() {
      _changed = false;
      _unsaved = false;
  }
  
  // Getter for _changed state variable
  public boolean isUnsaved(){
    return _unsaved;
  }

  // If an error occurrs while saving, object remains unsaved
  public void saveHasFailed(){
    _changed = true;
    _unsaved = true;
  }


  /**
   * Copies the contents of the range onto the Spreadsheet's cut buffer
   * @param range
   * @throws UnrecognizedEntryException
   */
  public void copy(String range) throws UnrecognizedEntryException{

    // Each time we call the cut method, the previous cutBuffer content is destroyed
    if(_cutBuffer.getCells()!=null){_cutBuffer.getCells().clear();}

    Range cutBufferRange = createRange(range);

    // Copies the cells over to the cutBuffer
    for(Cell c: cutBufferRange.getCellList()){
      List<Cell> cutBufferList = _cutBuffer.getCells();
      cutBufferList.add(c.copy());
    }
    
    // Communicates to the cutbuffer which type of range was selected and its direction
    _cutBuffer.setCutBufferRangeType(cutBufferRange.getRangeType());
    _cutBuffer.setDirection(cutBufferRange.getRangeDirection());
  }



  /**
   * Copies the contents of the range onto the Spreadsheet's cut buffer 
   * and destroys the content of the original cell.
   * @param range
   * @throws UnrecognizedEntryException
   */
  public void cut(String range) throws UnrecognizedEntryException{

    // Each time we call the cut method, the previous cutBuffer content is destroyed
    if(_cutBuffer.getCells()!=null){_cutBuffer.getCells().clear();}
    
    Range cutBufferRange = createRange(range);

    // Copies the cells over to the cutBuffer and destroys the content at the original cell
    for(Cell c: cutBufferRange.getCellList()){
      List<Cell> cutBufferList = _cutBuffer.getCells();
      cutBufferList.add(c.copy());
      c.setContent(null);
    }

    // Communicates to the cutbuffer which type of range was selected and its direction
    _cutBuffer.setCutBufferRangeType(cutBufferRange.getRangeType());
    _cutBuffer.setDirection(cutBufferRange.getRangeDirection());
  }



  /**
   * Deletes the contents of cells within the given range. 
   * @param range
   * @throws UnrecognizedEntryException
   */
  public void delete(String range) throws UnrecognizedEntryException{
    Range cutBufferRange = createRange(range);
    for(Cell c: cutBufferRange.getCellList()){
      c.setContent(null);
    }
  }

  
  
  /**
   * Pastes the contents from the cutBuffer to the given range.
   * @param range
   * @throws UnrecognizedEntryException
   * @throws InvalidCellException
   */
  public void paste(String range) throws UnrecognizedEntryException, InvalidCellException{
    
    Range destinationRange = createRange(range);

    // If the cutBuffer hasn't yet been initialized, or it is empty, nothing happens
    if(_cutBuffer == null || _cutBuffer.getCells().size() == 0){
      return;
    }

    // Pasting a range into a singular cell
    if(destinationRange.getCellList().size() == 1){
      
      // Gets direction of the cutBuffer range. +1 for ascending, -1 for descending
      int direction = _cutBuffer.getDirection();

      // Gets cutBuffer range type (HORIZONTAL, VERTICAL, SINGULAR_CELL)
      RangeType type = _cutBuffer.getCutBufferRangeType();
         
      // limit: Represents the Spreadsheet limit condition for which we will be checking while pasting
      // i and z: Represent the coordinates of the cell we are pasting to.  
      int limit = 0, i = 0, z = 0;

      if (type == RangeType.VERTICAL) {
          limit = _numRows;
          i = destinationRange.getCellList().get(0).getRow();
          z = destinationRange.getCellList().get(0).getColumn();
      } else if (type == RangeType.HORIZONTAL) {
          limit = _numColumns;
          i = destinationRange.getCellList().get(0).getColumn();
          z = destinationRange.getCellList().get(0).getRow();
      } else if (type == RangeType.SINGULAR_CELL) {
          limit = _numRows;  
          i = destinationRange.getCellList().get(0).getRow();  
      }

      Iterator<Cell> cutBuffer = _cutBuffer.getCells().iterator();
      
      // j is the iterator counter for the while loop
      int j = 0;

      // The baseline condition is that the cutBuffer still has content to paste and we are within the Spreadsheet's limits
      while(cutBuffer.hasNext() && 1<= i && i<= limit ){

        // We iterate through the cutBuffer and get the contentToPaste
        Content contentToPaste = _cutBuffer.getCells().get(j).getContent();
        
        // Pastes to the cell specified by the user.
        if(j==0){
          destinationRange.getCellList().get(0).setContent(contentToPaste);
        }
        
        // Keeps pasting to the adjacent cells to the one specified (in the same orientation and direction as the copied range)
        if(j>=1){
          if (type == RangeType.VERTICAL){
            this.getCell(i, z).setContent(contentToPaste);
          }
          else{
            this.getCell(z, i).setContent(contentToPaste);
          }
        }
        
        // Moves to the next element in the cutBuffer iterator
        cutBuffer.next();

        // Moves to the next adjacent cell in the same direction as the copied Range
        i += direction;
        j++;
      }
    }
    
    // Pasting between ranges
    if(destinationRange.getCellList().size() > 1 &&_cutBuffer.getCells().size() > 1){

      // Case in which the copied range and destination range dimensions differ (nothing happens)
      if((_cutBuffer.getCells().size() != destinationRange.getCellList().size()))
        return;

      // If they are the same, performs the pasting.
      else{
        for(int i=0; i<destinationRange.getCellList().size(); i++){
            Cell destinationCell = destinationRange.getCellList().get(i);
            Cell copiedCell = _cutBuffer.getCells().get(i);
            // copies the content over to the destinationCell
            destinationCell.setContent(copiedCell.getContent());
          }  
      }
    }
  }
}

