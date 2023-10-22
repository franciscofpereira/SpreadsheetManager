package xxl.core;

import xxl.core.exception.UnrecognizedEntryException;
import xxl.core.exception.UnrecognizedFunctionException;
import xxl.core.exception.UnsupportedConversionException;

public abstract class Function extends Content {
   
    //protected String _functionName;

    //public Function(String functionName){
    //    _functionName = functionName;
    //}

    protected abstract Literal compute(); // Computes the result of the function

    public String asString(){
        return null;        // FIXME
    }

    public int asInt() throws UnsupportedConversionException{
        return compute().asInt();
    }

    public Literal value(){
        return compute();        // FIXME
    }
}
