package xxl.core;

import xxl.core.exception.UnsupportedConversionException;

public class LiteralString extends Literal {
    
    private String _value;

    public LiteralString(String value){
        _value = value;
    }

    @Override
    public String toString(){
        return _value;
    }

    @Override
    public String asString(){
        return _value.substring(1);
    }

    @Override
    public String asArg(){
        return _value;
    }
    
    public Literal compute(){
        return this;
    }

    // FIXME isto vai dar throw a uma exception
    public int asInt() throws UnsupportedConversionException{
        throw new UnsupportedConversionException("Cannot convert a LiteralString to an integer");
    }
   
}
