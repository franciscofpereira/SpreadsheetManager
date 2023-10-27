package xxl.core;

import java.io.Serial;
import java.io.Serializable;
import xxl.core.exception.UnsupportedConversionException;

/**
 * Class that represents the content of a cell.
 */
public abstract class Content implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 9876543210L;
    
    abstract Literal value();

    public abstract String toString();

    public abstract Content copy(); 

    public String asString() throws UnsupportedConversionException{
        return value().asString();   
    }
    
    public int asInt() throws UnsupportedConversionException{
        return value().asInt();
    }

    public String asArg() {
        return null;
    }
}
