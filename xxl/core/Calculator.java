package xxl.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import xxl.core.exception.ImportFileException;
import xxl.core.exception.MissingFileAssociationException;
import xxl.core.exception.UnavailableFileException;
import xxl.core.exception.UnrecognizedEntryException;

// FIXME import classes

/**
 * Class representing a spreadsheet application.
 */
public class Calculator implements Serializable {
  /** The current spreadsheet. */
  
  private Spreadsheet _spreadsheet;
  private String _currentFile = "";
  //private Calculator _calculator = this;
  
  // Something was changed since last save
  private boolean _unsaved = false;
  
  
  // FIXME add more fields and methods if needed
  
  /**
   * Return the current spreadsheet.
   *
   * @returns the current spreadsheet of this application. This reference can be null.
   */
  public final Spreadsheet getSpreadsheet() {
    return _spreadsheet;
  }

  /**
   * Saves the serialized application's state into the file associated to the current network.
   *
   * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   */
  public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
    
    // Throws exception if the current instance of the application is not associated with a file
    if(!isFileAssociated())
      throw new MissingFileAssociationException();

      try (ObjectOutputStream oos = new ObjectOutputStream( new BufferedOutputStream(new FileOutputStream(_currentFile)))) {
        oos.writeObject(_spreadsheet);
        }  
    // FIXME implement serialization method
  }
  
  /**
   * Saves the serialized application's state into the specified file. The current network is
   * associated to this file.
   *
   * @param filename the name of the file.
   * @throws FileNotFoundException if for some reason the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   */
  public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
    _currentFile = filename;
    save(); 
    // FIXME implement serialization method
  }
  
  /**
   * @param filename name of the file containing the serialized application's state
   *        to load.
   * @throws UnavailableFileException if the specified file does not exist or there is
   *         an error while processing this file.
   */
  public void load(String filename) throws UnavailableFileException {
    
    try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
          
      Calculator loadedCalculator = (Calculator) ois.readObject();
      
        this._spreadsheet = loadedCalculator._spreadsheet;
        this._currentFile = loadedCalculator._currentFile;

        } catch (IOException | ClassNotFoundException e) {
            throw new UnavailableFileException(filename);
        }  
    // FIXME implement serialization method
  }
  
  /**
   * Read text input file and create domain entities.
   *
   * @param filename name of the text input file
   * @throws ImportFileException
   */
  public void importFile(String filename) throws ImportFileException {
  //  try {
      // FIXME open import file and feed entries to new spreadsheet (in a cycle)
      //       each entry is inserted using insertContent of Spreadsheet. Set new
      // spreadsheet as the active one.
      // ....
   // } catch (IOException | UnrecognizedEntryException /* FIXME maybe other exceptions */ e) {
   //   throw new ImportFileException(filename, e);
    //}
  } 

  public Spreadsheet createSpreadsheet(int rows, int columns){
    _spreadsheet = new Spreadsheet(rows, columns);
    return _spreadsheet;
  //FIXME implement stuff regarding User
  }

  
  /**
  * Checks if a file has been associated with the current instance.
  * @return True if a file has been associated, false otherwise.
   */
  public boolean isFileAssociated(){
    return !_currentFile.equals("");
    }

  
  // Registers that something was changed
    public void changed(){
    _unsaved = true;
  }

  // Registers that everything was saved
    public void saved() {
      _unsaved = false;
  }
  
  // Getter for _unsaved field
  public boolean isUnsaved(){
    return _unsaved;
  }

  // If an error occurrs while saving, object remains unsaved
  public void saveHasFailed(){
    _unsaved = true;
  }
}


