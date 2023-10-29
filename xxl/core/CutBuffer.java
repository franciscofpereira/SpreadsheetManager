package xxl.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * Class that represents the cut buffer of a spreadsheet.
 */
public class CutBuffer implements Serializable{
    
    @Serial
    private static final long serialVersionUID = 202358312359L;
    private List<Cell> _cellList;
    private List<String> _cutBufferViewer;
    private RangeType _type;
    private int _direction;

    public CutBuffer(){
        _cellList  = new ArrayList<>();
        _cutBufferViewer = new ArrayList<>();
    }

    public void setCutBufferRangeType(RangeType type){
        _type = type;
    }

    public RangeType getCutBufferRangeType(){
        return _type;
    }

    public void setDirection(int direction){
        _direction = direction;
    }

    public int getDirection(){
        return _direction;
    }

    public List<Cell> getCells(){
        return _cellList;
    }

    public void clearCutBufferViewer(){
        _cutBufferViewer.clear();
    }

    public List<String> cutBufferViewerBuilder(){
        int row = 1;
        int col = 1;

        clearCutBufferViewer();

        if(_type == RangeType.SINGULAR_CELL){
            _cutBufferViewer.add(row + ";" + col + "|" + _cellList.get(0).viewInCutBuffer());
        }

        if(_type == RangeType.HORIZONTAL){
            for(Cell c: _cellList){
                _cutBufferViewer.add(row + ";" + col + "|" + c.viewInCutBuffer());
                col++;
            }
        }

        if(_type == RangeType.VERTICAL){
            for(Cell c: _cellList){
                _cutBufferViewer.add(row + ";" + col + "|" + c.viewInCutBuffer());
                row++;
            }
        }

        return _cutBufferViewer;
    }

}
