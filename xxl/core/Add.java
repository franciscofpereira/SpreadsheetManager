package xxl.core;

import xxl.core.exception.UnsupportedConversionException;

/*
 * Class that represents the ADD function.
 */
public class Add extends BinaryFunction {
    
    public Add(Content arg1, Content arg2){
        super(arg1,arg2,"ADD");     
    }
 

    @Override
    protected Literal compute(){
        
        try{
            return new LiteralInteger(super._arg1.value().asInt() + super._arg2.value().asInt());
        } catch(UnsupportedConversionException| NullPointerException e){
            return new LiteralString("#VALUE");
        }  
    }


    @Override
    public Content copy() {
        return new Add(super._arg1, super._arg2); 
    }   
}


