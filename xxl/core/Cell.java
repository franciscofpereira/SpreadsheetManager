package xxl.core;

import java.io.Serial;
import java.io.Serializable;


public class Cell implements Serializable{

    @Serial
    private static final long serialVersionUID = 1234567890L;
    
    private int _row;
    private int _column;
    private Content _content;
    
    public Cell( int row, int column){
        _row = row;
        _column = column;
    }

    void setContent( Content c){
        _content = c;
    }

    // FIXME implement value()
    
    public String toString(){
        return _row + ";" + _column + "|" + _content;
    }

}

// ola o meu nome é francisco