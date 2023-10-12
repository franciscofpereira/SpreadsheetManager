package xxl.core;

import java.security.DrbgParameters.Reseed;

public class Add extends BinaryFunction {
    
    private Content _arg1;
    private Content _arg2;

    public Add(Content arg1, Content arg2){
        super(arg1,arg2);
        _arg1 = arg1;
        _arg2 = arg2;     
    }
 
    /* 
    protected Literal compute() {
        
        if (_arg1 instanceof LiteralInteger && _arg2 instanceof LiteralInteger) { 
            // Perform the addition      
        } else {
            // Handles the case where the arguments are not integers
            toString();
        } 
        
    }
    */
}


