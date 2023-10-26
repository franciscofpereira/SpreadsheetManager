package xxl.app.edit;

import java.util.List;

import pt.tecnico.uilib.menus.Command;
import xxl.core.Spreadsheet;
// FIXME import classes

/**
 * Show cut buffer command.
 */
class DoShowCutBuffer extends Command<Spreadsheet> {

  DoShowCutBuffer(Spreadsheet receiver) {
    super(Label.SHOW_CUT_BUFFER, receiver);
  }
  
  @Override
  protected final void execute() {
    // FIXME implement command
    
    List<String> cutBufferContent = _receiver.getCutBuffer().cutBufferViewerBuilder();

    for(String content: cutBufferContent){
      _display.popup(content);
    }
  }
}
