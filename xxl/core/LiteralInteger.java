package xxl.core;

public class LiteralInteger extends Literal {
    
    private int _value;
    
    public LiteralInteger(int value){
        _value = value;                           
    }

    public String toString(){
        return "" + _value;
    }

    public int asInt(){
        return _value;
    }

    // FIXME isto vai dar throw a uma exception
    //public String asString(){
    //}
    
    
}
