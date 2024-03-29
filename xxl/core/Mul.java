package xxl.core;

import xxl.core.exception.UnsupportedConversionException;

/**
 * Class that represents the MUL function.
 */
public class Mul extends BinaryFunction {

    public Mul(Content arg1, Content arg2){
        super(arg1,arg2,"MUL");    
    }
    
    @Override
    protected Literal compute(){
        
        try{
            return new LiteralInteger(super._arg1.value().asInt() * super._arg2.value().asInt());
        } catch(UnsupportedConversionException| NullPointerException e){
            return new LiteralString("#VALUE");
        }     
    }

    @Override
    public Content copy() {
        return new Mul(super._arg1, super._arg2);
    }   
}

