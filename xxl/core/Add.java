package xxl.core;


public class Add extends BinaryFunction {
    
    private Content _arg1;
    private Content _arg2;

    public Add(Content arg1, Content arg2){
        super(arg1,arg2);
        _arg1 = arg1;
        _arg2 = arg2;     
    }
 
    protected Literal compute() {
        return null;                // FIXME implement method
        }
    
}


