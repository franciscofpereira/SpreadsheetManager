package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import xxl.core.Range;
import xxl.core.Spreadsheet;

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
    
    Range range = _receiver.createRange(stringField("range"));
    range.insertRangeContent(stringField("content"));
    
    }   
       
}
