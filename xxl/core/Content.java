package xxl.core;

import java.io.Serial;
import java.io.Serializable;

public abstract class Content implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 9876543210L;
    
    abstract Literal value();

    public abstract String toString(); 

    public String asString(){
        return value().asString();   //FIXME maybe its just ""
    }

    public int asInt(){
        return value().asInt();
    }
}
