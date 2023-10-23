package xxl.core;

import xxl.core.exception.UnsupportedConversionException;

public class LiteralInteger extends Literal {
    
    private int _value;
    
    public LiteralInteger(int value){
        _value = value;                           
    }

    
    @Override
    public String toString(){
        return "" + _value;
    }

    @Override
    public String asString() throws UnsupportedConversionException{
        throw new UnsupportedConversionException("Cannot convert a LiteralInteger to a String");
    }
    
    @Override
    public String asArg(){
        return "" + _value;
    }

    @Override
    public int asInt(){
        return _value;
    }

    public Literal compute(){
        return this;
    }
    
    
    

    
}
