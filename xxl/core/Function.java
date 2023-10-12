package xxl.core;

public abstract class Function extends Content {
   
    private String _functionName;

    protected abstract Literal compute();    // Computes the result of the function

    public String asString(){
    }

    //public int asInt(){

    //}

    public Literal value(){   
    }
}
