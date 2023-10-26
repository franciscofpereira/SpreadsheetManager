package xxl.core;

import java.util.ArrayList;
import java.util.List;

public class CutBuffer {
    private List<Cell> _cellList;
    private List<String> _cutBufferViewer;
    private RangeType _type;

    public CutBuffer(){
        _cellList  = new ArrayList<>();
        _cutBufferViewer = new ArrayList<>();
    }

    public void setCutBufferRangeType(RangeType type){
        _type = type;
    }
    
    public List<Cell> getCells(){
        return _cellList;
    }

    public List<String> cutBufferViewerBuilder(){
        int row = 1;
        int col = 1;

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
