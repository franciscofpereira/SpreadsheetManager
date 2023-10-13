package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Spreadsheet;
import xxl.core.exception.InvalidCellException;
// FIXME import classes
import xxl.core.exception.UnrecognizedEntryException;

/**
 * Class for searching functions.
 */
class DoShow extends Command<Spreadsheet> {

  DoShow(Spreadsheet receiver) {
    super(Label.SHOW, receiver);
    addStringField("range", Message.address()); 
    // FIXME add fields
  }
  
  @Override
protected final void execute() throws CommandException {
    try {
        String gama = stringField("range");
        String adress[] = gama.split(":");

        if (adress.length != 1 && adress.length != 2) {
            throw new UnrecognizedEntryException("Invalid input. Please enter a valid cell or range.");
        }

        // This is a cell
        if (adress.length == 1) {
            String[] coordinates = adress[0].split(";");
            int row = Integer.parseInt(coordinates[0]);
            int col = Integer.parseInt(coordinates[1]);
            try {
                _display.popup(_receiver.getCell(row, col));
            } catch (InvalidCellException ice) {
                System.err.println("Attempt to view an invalid cell.");
            }
        }

        // This is a range
        if (adress.length == 2) {
            String[] startCoordinates = adress[0].split(";");
            String[] endCoordinates = adress[1].split(";");
            int startRow = Integer.parseInt(startCoordinates[0]);
            int startCol = Integer.parseInt(startCoordinates[1]);
            int endRow = Integer.parseInt(endCoordinates[0]);
            int endCol = Integer.parseInt(endCoordinates[1]);

            if (_receiver.isRangeValid(startRow, startCol, endRow, endCol)) {
                
              // Horizontal range
              if (startRow == endRow && startCol != endCol) {

                    if (startCol < endCol) {
                        for (int col = startCol; col <= endCol; col++) {
                            try {
                                _display.popup(_receiver.getCell(startRow, col));
                            } catch (InvalidCellException ice) {
                                System.err.println("Attempt to view an invalid cell.");
                            }
                        }
                    } else {
                        for (int col = startCol; col >= endCol; col--) {
                            try {
                                _display.popup(_receiver.getCell(startRow, col));
                            } catch (InvalidCellException ice) {
                                System.err.println("Attempt to view an invalid cell.");
                            }
                        }
                    }

                // Vertical Range    
                } else {
                    if (startRow < endRow) {
                        for (int row = startRow; row <= endRow; row++) {
                            try {
                                _display.popup(_receiver.getCell(row, startCol));
                            } catch (InvalidCellException ice) {
                                System.err.println("Attempt to view an invalid cell.");
                            }
                        }
                    } else {
                        for (int row = startRow; row >= endRow; row--) {
                            try {
                                _display.popup(_receiver.getCell(row, startCol));
                            } catch (InvalidCellException ice) {
                                System.err.println("Attempt to view an invalid cell.");
                            }
                        }
                    }
                }
            }
        }
    } catch (UnrecognizedEntryException uee) {
        System.err.println("The specified gama is not recognizable.");
    }
  }
}
