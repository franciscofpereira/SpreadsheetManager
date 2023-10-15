package xxl.core;


public class Add extends BinaryFunction {
    
    private Content _arg1;
    private Content _arg2;
    private String _functionName;

    public Add(Content arg1, Content arg2){
        super(arg1,arg2);
        _arg1 = arg1;
        _arg2 = arg2;     
        _functionName = "ADD";
    }
 
    public String getFunctionName(){
        return _functionName;
    }

    protected Literal compute(){
        
        Literal result1 = null;
        Literal result2 = null;

        // If _arg1 is a Reference we compute its value
        if(_arg1 instanceof Reference){
            result1 = ((Reference)_arg1).compute();
            
            // If the computed value is a LiteralString it means the value is a String and we can't compute the ADD function.
            if(result1 instanceof LiteralString)
                return new LiteralString("#VALUE");
        }
        else if(_arg1 instanceof LiteralInteger){
            result1 = ((LiteralInteger)_arg1).compute();
        }
        

        // Repeats the same process for _arg2
        if(_arg2 instanceof Reference){
            result2 = ((Reference)_arg2).compute();
            if(result2 instanceof LiteralString)
                return new LiteralString("#VALUE");
        } else if(_arg2 instanceof LiteralInteger){
            result2 = ((LiteralInteger)_arg2).compute();
        }
        
        
        if (result1 == null || result2 == null) {
            return new LiteralString("#VALUE");
        }

        return new LiteralInteger(result1.asInt() + result2.asInt());   
        }   
}


