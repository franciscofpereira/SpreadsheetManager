package xxl.core;

/**
 * Class that represents range functions. 
 * These functions take in a range and operate over it, 
 * producing integer values or string values in the form of LiteralInteger or LiteralString, respectively
 */
public abstract class RangeFunction extends Function {

    protected Range _range;

    public RangeFunction(Range range, String functionName){
        super(functionName);

        if(!(range instanceof Range)){
            throw new IllegalArgumentException("Argument must be of type Range.");
        }
        _range = range;
    }
    
    @Override
    public String toString(){
        return "" + compute() + "=" + super._functionName + "(" +  _range.asArg() + ")";
    }
}
