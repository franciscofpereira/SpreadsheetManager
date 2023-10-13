package xxl.core;

public abstract class Function extends Content {
   
    //private String _functionName;
    
    protected abstract Literal compute();    // Computes the result of the function

    public String asString(){
        return null;        // FIXME
    }

    //public int asInt(){

    //}

    public Literal value(){
        return null;        // FIXME
    }
}
