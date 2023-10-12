package xxl.core;

public class LiteralString extends Literal<String> {
    


    public LiteralString(String value){
        super(value);
    }

    public String toString(){
        String value = getValue();
        return value;
    }

    //public Integer asInt(){
    //    Integer value = getValue();
    //    return value;
    //}

    //public String asString(){
    //}

   
}
