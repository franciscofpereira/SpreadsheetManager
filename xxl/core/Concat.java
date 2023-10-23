package xxl.core;

import xxl.core.exception.UnsupportedConversionException;

public class Concat extends RangeFunction{

    public Concat(Range range){
        super(range, "CONCAT");
    }

    
    @Override
    protected Literal compute(){
        
        String res = "'";

        for (Cell cell : _range.getCellList()) {
            
            try {
                // The #VALUE is the value of functions or references that can't be computed
                // It is not meant to concatenate this value with the rest of the LiteralStrings
                if( !(cell.value().asString().equals("VALUE")) )
                    res += cell.value().asString();
            } catch (UnsupportedConversionException | NullPointerException e) {
                // Ignores cells that do not have LiteralString as Content 
            }
        }

        return new LiteralString(res);  
    }
        
}
    
    

