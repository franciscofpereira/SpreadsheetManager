package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Range;
import xxl.core.Spreadsheet;
import xxl.core.exception.UnrecognizedEntryException;

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
    
    try{
      Range range = _receiver.createRange(stringField("range"));
      range.insertRangeContent(stringField("content"));
    } catch( UnrecognizedEntryException uee){
        throw new InvalidCellRangeException(stringField("range"));
    } 
  }   
       
}
