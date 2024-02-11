package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Calculator;
import xxl.core.Spreadsheet;
import xxl.core.exception.UnrecognizedEntryException;

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
  protected final void execute() throws CommandException{

    Spreadsheet spreadsheet = _receiver.getSpreadsheet();
    
    // When opening a file, we should ask the user if he wants to save the changes made to the active Spreadsheet
    if(spreadsheet != null && spreadsheet.isUnsaved() && Form.confirm(Message.saveBeforeExit())){
      DoSave ds = new DoSave(_receiver);
      ds.performCommand();
    }
    
    try{
      Spreadsheet createsSpreadsheet = _receiver.createSpreadsheet(integerField("Rows"),integerField("Columns") );
    } catch( UnrecognizedEntryException e){
      throw new InvalidCellRangeException(integerField("Rows") + ";" + integerField("Columns"));
    }
    
  }
}
