package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.app.exception.UnknownFunctionException;
import xxl.core.Range;
import xxl.core.Spreadsheet;
import xxl.core.exception.UnrecognizedEntryException;
import xxl.core.exception.UnrecognizedFunctionException;

// FIXME import classes

/**
 * Class for inserting data.
 */
class DoInsert extends Command<Spreadsheet> {

  DoInsert(Spreadsheet receiver) {
    super(Label.INSERT, receiver);
    addStringField("range", Message.address()); 
    addStringField("content", Message.contents());   
    
  }
  
  @Override
  protected final void execute() throws CommandException{  
    
    try {
        // Attempts to create the specified range
        Range range = _receiver.createRange(stringField("range"));
        
        // Now that the range is successfully created, tries to insert the content
        try {
            range.insertRangeContent(stringField("content"));
        } catch (UnrecognizedFunctionException ufe) {
            // If the content is an inexistent function, throws UnknownFunctionException
            throw new UnknownFunctionException(stringField("content"));
        } catch(UnrecognizedEntryException uee ){
          // If the content is not a function, but it is still invalid, prints error message on the screen.
            System.err.println("Contéudo especificado é desconhecido: " + stringField("content"));
        }
    } catch (UnrecognizedEntryException uee) {
        // If the range is not valid, throw InvalidCellRangeException
        throw new InvalidCellRangeException(stringField("range"));
    } 
  }
  
     
}


