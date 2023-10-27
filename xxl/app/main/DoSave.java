package xxl.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import xxl.core.Calculator;
import xxl.core.exception.MissingFileAssociationException;

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSave extends Command<Calculator> {

  DoSave(Calculator receiver) {
    super(Label.SAVE, receiver, xxl -> xxl.getSpreadsheet() != null);
  }
  
  @Override
  protected final void execute() {
    
    // If the receiver is already saved, no need to save it again.
    if(!_receiver.getSpreadsheet().isUnsaved())
      return;
    
    _receiver.getSpreadsheet().saved();    // updates receiver's _unsaved field to false

    try{
        try{
          
          // attempts to save
          _receiver.save();

        } catch(MissingFileAssociationException mfae1){
          
          // If there isn't a file associated with the receiver, attempts to saveAs to a file 
          try{
              _receiver.saveAs(Form.requestString(Message.newSaveAs()));
            } catch( MissingFileAssociationException mfae2){
              
              // The file we attempted to save to is either missing or does not exist
              // saving operation failed so we keep receiver's _unsaved field as true
              _receiver.getSpreadsheet().saveHasFailed();  
            }
        }
    } catch ( IOException ioe){
      _receiver.getSpreadsheet().saveHasFailed();
    }  
  }
}
