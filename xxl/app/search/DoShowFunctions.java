package xxl.app.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Comparator;

import pt.tecnico.uilib.menus.Command;
import xxl.core.Cell;
import xxl.core.CellComparator;
import xxl.core.FunctionSearchCriteria;
import xxl.core.SearchCriteria;
import xxl.core.Spreadsheet;

/**
 * Command for searching function names.
 */
class DoShowFunctions extends Command<Spreadsheet> {

  DoShowFunctions(Spreadsheet receiver) {
    super(Label.SEARCH_FUNCTIONS, receiver);
    addStringField("Function", Message.searchFunction());
  }

  @Override
  protected final void execute() {
    
    SearchCriteria criteria = new FunctionSearchCriteria(stringField("Function"));
    Collection<Cell> foundCells = _receiver.getStorageStrategy().lookUp(criteria);
    
    List<Cell> cellList = new ArrayList<>(foundCells); // Convert to a List

    // Sorts by function name
    Comparator<Cell> functionNameComparator = new CellComparator();
    Collections.sort(cellList, functionNameComparator);
    
    for(Cell c: cellList){
      _display.popup(c);
    }
  }
}
