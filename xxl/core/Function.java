package xxl.core;

import xxl.core.exception.UnsupportedConversionException;

public abstract class Function extends Content {
   
    protected String _functionName;

    public Function(String functionName){
        _functionName = functionName;
    }

    // Computes the result of the function
    protected abstract Literal compute(); 
    
    @Override
    public String asString() throws UnsupportedConversionException{
        return compute().asString();        
    }

    @Override
    public int asInt() throws UnsupportedConversionException{
        return compute().asInt();
    }

    @Override
    public Literal value(){
        return compute();        
    }

    public String getFunctionName() {
        return _functionName;
    }

    public void accept(FunctionVisitor visitor){
        visitor.visit(this);
    }
}
