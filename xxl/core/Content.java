package xxl.core;

import java.io.Serial;
import java.io.Serializable;

public abstract class Content implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 9876543210L;
    
    
    public abstract String toString();

    //abstract value();
}
