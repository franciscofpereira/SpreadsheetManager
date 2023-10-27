package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Calculator;
import xxl.core.Spreadsheet;
import xxl.app.exception.FileOpenFailedException;
import xxl.core.exception.UnavailableFileException;


/**
 * Open existing file.
 */
class DoOpen extends Command<Calculator> {

  DoOpen(Calculator receiver) {
    super(Label.OPEN, receiver);
    addStringField("file", Message.openFile());
  }
  
  @Override
  protected final void execute() throws CommandException {
       
    Spreadsheet spreadsheet = _receiver.getSpreadsheet();
    
    // When opening a file, we should ask the user if he wants to save the changes made to the active Spreadsheet
    if(spreadsheet != null && spreadsheet.isUnsaved() && Form.confirm(Message.saveBeforeExit())){
      DoSave ds = new DoSave(_receiver);
      ds.performCommand();
    }

    // Tries to load the file, catches exception if something goes wrong and throws FileOpenFailedException
    try {
      _receiver.load(stringField("file"));
    } catch (UnavailableFileException e) {
      throw new FileOpenFailedException(e);
    }
  

  
  }
}
