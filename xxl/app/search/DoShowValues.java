package xxl.app.search;

import java.util.ArrayList;
import java.util.Collection;

import pt.tecnico.uilib.menus.Command;
import xxl.core.Cell;
import xxl.core.SearchCriteria;
import xxl.core.Spreadsheet;
import xxl.core.ValueSearchCriteria;

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
    SearchCriteria criteria = new ValueSearchCriteria(stringField("Value"));
    Collection<Cell> foundCells = _receiver.getStorageStrategy().lookUp(criteria);
    for(Cell c: foundCells){
      _display.popup(c);
    };
  }
}
