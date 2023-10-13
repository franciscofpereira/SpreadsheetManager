package xxl.core.exception;

/**
 * Exception for attempting to create invalid Range.
 */

public class InvalidRangeException extends Exception {
    public InvalidRangeException() {
        super("Invalid cell access.");
    }

    public InvalidRangeException(String message) {
        super(message);
    }

    public String getMessage( int beginRow, int beginColumn, int endRow, int endColumn ){
        return "The specified range is invalid: " + beginRow + ";" + beginColumn + ":" + endRow + ";" + endColumn;
    }
}