package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Calculator;
import xxl.core.Spreadsheet;

/**
 * Open a new file.
 */
class DoNew extends Command<Calculator> {

  DoNew(Calculator receiver) {
    super(Label.NEW, receiver);
    addIntegerField("Rows", Message.lines());
    addIntegerField("Columns", Message.columns());
  }
  
  @Override
  protected final void execute() throws CommandException {
    Spreadsheet spreadsheet = _receiver.createSpreadsheet(integerField("Rows"),integerField("Columns") );
  }
}
