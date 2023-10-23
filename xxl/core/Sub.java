package xxl.core;

import xxl.core.exception.UnsupportedConversionException;

public class Sub extends BinaryFunction{

    public Sub(Content arg1, Content arg2){
        super(arg1,arg2,"SUB");
    }

    @Override
    protected Literal compute(){
        
        try{
            return new LiteralInteger(super._arg1.value().asInt() - super._arg2.value().asInt());
        } catch(UnsupportedConversionException| NullPointerException e){
            return new LiteralString("#VALUE");
        }  
    }
}
