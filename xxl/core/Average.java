package xxl.core;

import xxl.core.exception.UnsupportedConversionException;

public class Average extends RangeFunction {
    
    public Average(Range range){
        super(range, "AVERAGE");
    }

    @Override
    protected Literal compute() {
        int sum = 0;
        int count = 0;
        
        try{
            for(Cell cell: _range.getCellList()){
                sum += cell.value().asInt();
                count++;
            }

            int average = sum/count;

            return new LiteralInteger(average);
        } catch(UnsupportedConversionException| NullPointerException e){
            return new LiteralString("#VALUE");
        }
    }

    @Override
    public Content copy() {
        return new Average(_range);
    }
}
