package xxl.core.exception;

/**
 * Exception for attempting to access unknown Cell.
 */

public class InvalidCellException extends Exception {
    public InvalidCellException() {
        super("Invalid cell access.");
    }

    public InvalidCellException(String message) {
        super(message);
    }
}