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
 
    protected Literal<Integer> compute() {
        
        
        if (_arg1 instanceof LiteralInteger && _arg2 instanceof LiteralInteger) {
            Literal<Integer> intArg1 = (LiteralInteger) _arg1;
            Literal<Integer> intArg2 = (LiteralInteger) _arg2;
            // Perform the addition
            int result = intArg1.asInt() + intArg2.asInt();
            return result;
        } else {
            // Handles the case where the arguments are not integers
            toString();
        } 
        
    }
}


