package xxl.core;

import xxl.core.exception.UnsupportedConversionException;

public class MaiorInt extends RangeFunction {
   
    public MaiorInt(Range range){
        super(range, "MAIORINT");
    }

    @Override
    protected Literal compute() {
        
        int max = 0;
        
        try{
            
            for(Cell cell: _range.getCellList()){
                
                if(cell.value().asInt() > max)
                    max = cell.value().asInt();
            }

            if(max != 0)
                 return new LiteralInteger(max);
            
        } catch(UnsupportedConversionException| NullPointerException e){
            // ignores cells that do not contain LiteralInteger content
        }
        
        return new LiteralString("#VALUE");
    }


    @Override
    public Content copy() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'copy'");
    }
}
