package xxl.core;

import xxl.core.exception.UnsupportedConversionException;

public class Product extends RangeFunction{

    public Product(Range range){
        super(range,"PRODUCT");
    }

    @Override
    protected Literal compute(){
        int result = 1;
        try{
            for(Cell cell: _range.getCellList()){
                result *= cell.value().asInt();
            }

            return new LiteralInteger(result);
        } catch(UnsupportedConversionException| NullPointerException e){
            return new LiteralString("#VALUE");
        }
    }

    @Override
    public Content copy() {
        return new Product(_range);
    }
    
}
