package xxl.app.search;

import java.util.Collection;

import pt.tecnico.uilib.menus.Command;
import xxl.core.Cell;
import xxl.core.Spreadsheet;
// FIXME import classes

/**
 * Command for searching function names.
 */
class DoShowFunctions extends Command<Spreadsheet> {

  DoShowFunctions(Spreadsheet receiver) {
    super(Label.SEARCH_FUNCTIONS, receiver);
    addStringField("Function", Message.searchFunction());
    // FIXME add fields
  }

  @Override
  protected final void execute() {
    
    Collection<Cell> foundCells = _receiver.getStorageStrategy().lookUpFunction(stringField("Function"));
    for(Cell c: foundCells){
      _display.popup(c);
    };
    // FIXME implement command
  }
}
