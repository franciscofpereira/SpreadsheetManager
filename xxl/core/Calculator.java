package xxl.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import xxl.core.exception.ImportFileException;
import xxl.core.exception.MissingFileAssociationException;
import xxl.core.exception.UnavailableFileException;
import xxl.core.exception.UnrecognizedEntryException;
import xxl.core.exception.UnrecognizedFunctionException;


/**
 * Class representing a spreadsheet application.
 */

public class Calculator implements Serializable {
  
  // The current spreadsheet
  private Spreadsheet _spreadsheet;
  
  // The current associated file (if it's an empty string then it means there isn't an associated file)
  private String _currentFile = "";
  
  // Root user, active user, and list of users
  private User _root = new User("root");
  private User _activeUser;
  private List<User> _users = new ArrayList<>();
  
  // Unsaved state variable
  private boolean _unsaved = true;
  

  /**
   * Returns the current spreadsheet.
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
        oos.writeObject(this);
        }  
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
  }
  
  /**
   * Read text input file and create domain entities.
   *
   * @param filename name of the text input file
   * @throws ImportFileException
   */
  public void importFile(String filename) throws ImportFileException {
    try {
      
      Parser p = new Parser();
      _spreadsheet = p.parseFile(filename);
       
   } catch (IOException | UnrecognizedEntryException | UnrecognizedFunctionException/* FIXME maybe other exceptions */ e) {
      throw new ImportFileException(filename, e);
    }
  } 

  /**
   * Creates a new Spreadsheet object when given the number of rows and columns.
   * This method is used in DoNew Command to create a new Spreadsheet object with user input data.
   *
   * @param rows number of rows of the spreadsheet
   * @param columns number of columns of the spreadsheet
   */
  public Spreadsheet createSpreadsheet(int rows, int columns){
    _spreadsheet = new Spreadsheet(rows, columns);
    return _spreadsheet;
  //FIXME implement stuff regarding User
  }

  
  /**
  * Checks if a file has been associated with the current instance.
  * @returns True if a file has been associated, false otherwise.
  */
  public boolean isFileAssociated(){
    return !_currentFile.equals("");
    }

  
  // State variable. Registers that something was changed
    public void changed(){
    _unsaved = true;
  }

  // State variable. Registers that everything was saved
    public void saved() {
      _unsaved = false;
  }
  
  // Getter for _unsaved state variable
  public boolean isUnsaved(){
    return _unsaved;
  }

  // If an error occurrs while saving, object remains unsaved
  public void saveHasFailed(){
    _unsaved = true;
  }
}


