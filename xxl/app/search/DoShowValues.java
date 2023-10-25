package xxl.app.search;

import java.util.ArrayList;
import java.util.Collection;

import pt.tecnico.uilib.menus.Command;
import xxl.core.Cell;
import xxl.core.Spreadsheet;
// FIXME import classes

/**
 * Command for searching content values.
 */
class DoShowValues extends Command<Spreadsheet> {

  DoShowValues(Spreadsheet receiver) {
    super(Label.SEARCH_VALUES, receiver);
    addStringField("Value", Message.searchValue());
    // FIXME add fields
  }
  
  @Override
  protected final void execute() {
    // FIXME implement command

    Collection<Cell> foundCells = _receiver.getStorageStrategy().lookUpValue(stringField("Value"));
    for(Cell c: foundCells){
      _display.popup(c);
    };
  }
}
