package xxl.core;

public abstract class Literal<T> extends Content {

    protected T _value;
    
    public Literal(T value){
        if (value instanceof String || value instanceof Integer) {
            _value = value;
        } else {
            throw new IllegalArgumentException("Literals must be either String or Integer");
        }
    }
    
    @Override
    Literal<?> value(){
        return this;
    }

    protected T getValue(){
        return _value;
    }
}
