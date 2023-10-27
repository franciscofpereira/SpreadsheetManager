package xxl.core;

import xxl.core.exception.UnsupportedConversionException;

/*
 * Class that represents the DIV function.
 */
public class Div extends BinaryFunction{
    
    public Div(Content arg1, Content arg2){
        super(arg1,arg2,"DIV"); 
    }
  
    @Override
    protected Literal compute(){
        
        try{
            return new LiteralInteger(super._arg1.value().asInt() / super._arg2.value().asInt());
        } catch(UnsupportedConversionException| NullPointerException e){
            return new LiteralString("#VALUE");
        }     
    }

    @Override
    public Content copy() {
        return new Div(super._arg1, super._arg2);
    }   
}

