package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Calculator;
import xxl.app.exception.FileOpenFailedException;
import xxl.core.exception.UnavailableFileException;

// FIXME import classes

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
    
      try {
        _receiver.load(stringField("file"));
      } catch (UnavailableFileException e) {
        throw new FileOpenFailedException(e);
      }
  }
}
