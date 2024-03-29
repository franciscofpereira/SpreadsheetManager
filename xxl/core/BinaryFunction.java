package xxl.core;

/**
 * Class that represents binary functions.
 * These functions take Reference and Literal objects as arguments and produce int values in the form of LiteralInteger
 */
public abstract class BinaryFunction extends Function {
    
    protected Content _arg1;
    protected Content _arg2;

    public BinaryFunction(Content arg1, Content arg2, String functionName){
        
        // Creates Function object with given name
        super(functionName);
        
        // Check if arg1 is either a LiteralInteger or a Reference, if not throws exception.
        if (!(arg1 instanceof Literal || arg1 instanceof Reference)) {
            throw new IllegalArgumentException("arg1 must be a Literal or Reference.");
        }
        
        // Check if arg2 is neither a LiteralInteger nor a Reference
        if (!(arg2 instanceof Literal || arg2 instanceof Reference)) {
            throw new IllegalArgumentException("arg2 must be a Literal or Reference.");
        }
    
        _arg1 = arg1;
        _arg2 = arg2;
        
    }
    
    @Override
    public String toString(){
        return "" + compute() + "=" + super._functionName + "(" + _arg1.asArg() + "," + _arg2.asArg() + ")";
    }
}
