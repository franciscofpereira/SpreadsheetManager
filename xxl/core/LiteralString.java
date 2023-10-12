package xxl.core;

public class LiteralString extends Literal {
    
    private String _value;

    public LiteralString(String value){
        _value = value;
    }

    public String toString(){
        return _value;
    }

    public String asString(){
        return "'" + _value;
    }
    
    // FIXME isto vai dar throw a uma exception
    //public Integer asInt(){
    //    return value;
    //}

    

   
}
