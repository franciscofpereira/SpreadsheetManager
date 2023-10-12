package xxl.core;

public abstract class BinaryFunction extends Function {
    
    protected Content _arg1;
    protected Content _arg2;

    public BinaryFunction(Content arg1, Content arg2) {
        
        if ((arg1 instanceof Reference || arg1 instanceof Literal<?>) &&
            (arg2 instanceof Reference || arg2 instanceof Literal<?>)) {
            // Arguments are of the correct type, proceed.
            _arg1 = arg1;
            _arg2 = arg2;
        } else {
            this.toString();
            //throw new IllegalArgumentException("Binary functions require literals or references as arguments.");
        }
    }

    public String toString(){
        return "#VALUE";
    }
}
