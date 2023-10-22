package xxl.core;

public abstract class Literal extends Content {

    @Override
    Literal value(){
        return this;
    }

    public abstract Literal compute();

    }

