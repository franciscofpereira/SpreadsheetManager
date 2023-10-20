package xxl.core;

public abstract class BinaryFunction extends Function {
    
    protected Content _arg1;
    protected Content _arg2;

    public BinaryFunction(Content arg1, Content arg2) {
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
    
    public abstract String getFunctionName();

    public String toString(){
        return "" + compute() + "=" + getFunctionName() + "(" + _arg1.asString() + "," + _arg2.asString() + ")";
    }
}
