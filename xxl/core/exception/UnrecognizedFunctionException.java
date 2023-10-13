package xxl.core.exception;

public class UnrecognizedFunctionException extends Exception{
    

        public UnrecognizedFunctionException() {
            super("Unrecognized function.");
        }

        public UnrecognizedFunctionException(String message){
            super(message);
        }
}
