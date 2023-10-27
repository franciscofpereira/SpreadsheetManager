package xxl.core;

import java.io.Serial;
import java.io.Serializable;

/*
 * Class that represents a cell in a spreadsheet.
 */
public class Cell implements Serializable{

    @Serial
    private static final long serialVersionUID = 1234567890L;
    
    private int _row;
    private int _column;
    private Content _content;
    
    public Cell( int row, int column){
        _row = row;
        _column = column;
        _content = null;
    }

    public int getRow(){
        return _row;
    }

    public int getColumn(){
        return _column;
    }
    
    public Content getContent(){
        return _content;
    }
    
    void setContent( Content c){
        _content = c;
    }

    Literal value(){
        
        if(_content == null)
            return null;

        return _content.value();
    }
    
    public String toString(){

        if(_content == null){
            return _row + ";" + _column + "|";
        }
        
        return _row + ";" + _column + "|" + _content.toString();
    }

    public String viewInCutBuffer(){
        
        if(_content == null){
            return "";
        }
        return _content.toString();

    }
    public Cell copy(){
        
        Cell copiedCell = new Cell(_row, _column);
        
        // If the content of this cell is null, the content of copiedCell will also be null
        if(_content == null){
            copiedCell.setContent(null);
        }
        else{
            Content copiedContent = _content.copy();
            copiedCell.setContent(copiedContent);
        }
        return copiedCell;
    }
}

