package xxl.app.edit;

import java.util.List;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import xxl.core.Range;
import xxl.core.Spreadsheet;
import xxl.core.exception.InvalidCellException;
import xxl.core.exception.UnrecognizedEntryException;
import xxl.core.exception.UnrecognizedFunctionException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Cell;
import xxl.core.Content;
//import xxl.core.Parser;
// FIXME import classes

/**
 * Class for inserting data.
 */
class DoInsert extends Command<Spreadsheet> {

  DoInsert(Spreadsheet receiver) {
    super(Label.INSERT, receiver);
    addStringField("range", Message.address()); 
    addStringField("content", Message.contents());   
    
  }
  
  @Override
  protected final void execute() throws CommandException{
    
    //FIXME implement method 
    
    
    /* 
      try{
        
        Range range = _receiver.createRange(stringField("range"));
        Parser p = new Parser(_receiver);
        Content content = p.parseContent(stringField("range"));
        
        List<Cell> cellList = range.getCells();
      
        for( Cell cell: cellList){
          _receiver.insertContent(cell.getRow(), cell.getColumn(), content);
        } 

      }catch(InvalidCellRangeException| InvalidCellException| UnrecognizedEntryException | UnrecognizedFunctionException e ){
        System.err.println("Something went wrong inserting content.");
      }
      */
    }   
       
}
