package xxl.core;

import xxl.core.exception.UnsupportedConversionException;

public class Mul extends BinaryFunction {
    
    private static final String _functionName = "MUL";

    public Mul(Content arg1, Content arg2){
        super(arg1,arg2);    
    }

    public String getFunctionName(){
        return _functionName;
    }
    
    protected Literal compute(){
        
        try{
            return new LiteralInteger(super._arg1.value().asInt() * super._arg2.value().asInt());
        } catch(UnsupportedConversionException| NullPointerException e){
            return new LiteralString("#VALUE");
        }     
    }   
}

