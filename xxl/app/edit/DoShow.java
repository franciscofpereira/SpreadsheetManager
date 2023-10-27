package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Cell;
import xxl.core.Range;
import xxl.core.Spreadsheet;
import xxl.core.exception.UnrecognizedEntryException;

/**
 * Class for searching functions.
 */
class DoShow extends Command<Spreadsheet> {

  DoShow(Spreadsheet receiver) {
    super(Label.SHOW, receiver);
    addStringField("range", Message.address()); 
  }
  
  @Override
protected final void execute() throws CommandException {
    
    try {
        Range range = _receiver.createRange(stringField("range"));
        
        for(Cell cell: range.getCellList()){
            _display.popup(cell);
        }     
    }catch(UnrecognizedEntryException uee){
        throw new InvalidCellRangeException(stringField("range"));
    }
}

}