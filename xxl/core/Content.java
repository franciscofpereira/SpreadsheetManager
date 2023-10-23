package xxl.core;

import java.io.Serial;
import java.io.Serializable;
import xxl.core.exception.UnsupportedConversionException;


public abstract class Content implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 9876543210L;
    
    abstract Literal value();

    public abstract String toString();

    public String asString() throws UnsupportedConversionException{
        return value().asString();   //FIXME maybe its just ""
    }
    
    public int asInt() throws UnsupportedConversionException{
        return value().asInt();
    }

    public String asArg() {
        return null;
    }
}
