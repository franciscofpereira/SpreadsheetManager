package xxl.core;

public class Mul extends BinaryFunction {
    
    private Content _arg1;
    private Content _arg2;

    public Mul(Content arg1, Content arg2){
        super(arg1,arg2);
        _arg1 = arg1;
        _arg2 = arg2;     
    }

    // public Literal compute()
}
