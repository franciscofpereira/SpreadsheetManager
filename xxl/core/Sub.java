package xxl.core;

public class Sub extends BinaryFunction {

    private static final String _functionName = "SUB";

    public Sub(Content arg1, Content arg2){
        super(arg1,arg2);
    }

    public String getFunctionName(){
        return _functionName;
    }
    
    protected Literal compute(){
        
        Literal result1 = null;
        Literal result2 = null;

        // If super._arg1 is a Reference we compute its value
        if(super._arg1 instanceof Reference){
            result1 = ((Reference)super._arg1).compute();
            // If the computed value is a LiteralString it means the value is a String and we can't compute the SUB function.
            if(result1 instanceof LiteralString)
                return new LiteralString("#VALUE");
        }
        // If super._arg1 is a LiteralString we can't compute the SUB function
        else if(super._arg1 instanceof LiteralString){
            return new LiteralString("#VALUE");
        }
        else if(super._arg1 instanceof LiteralInteger){
            result1 = ((LiteralInteger)super._arg1).compute();
        }
        

        // Repeats the same process for super._arg2
        if(super._arg2 instanceof Reference){
            result2 = ((Reference)super._arg2).compute();
            if(result2 instanceof LiteralString)
                return new LiteralString("#VALUE");
        else if(super._arg2 instanceof LiteralString){
            return new LiteralString("#VALUE");
        }
        } else if(super._arg2 instanceof LiteralInteger){
            result2 = ((LiteralInteger)super._arg2).compute();
        }
        
        if (result1 == null || result2 == null) {
            return new LiteralString("#VALUE");
        }

        return new LiteralInteger(result1.asInt() - result2.asInt());   
        }   
}

