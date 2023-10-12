package xxl.core;

public class LiteralInteger extends Literal<Integer> {
    
    
    public LiteralInteger(Integer value){
        super(value);                           // calls superclass constructor
    }

    public String toString(){
        Integer value = getValue();
        return "" + value;
    }

    public int asInt(){
        int value = getValue().intValue();
        return value;
    }

    //public String asString(){
    //}
    
    
}
