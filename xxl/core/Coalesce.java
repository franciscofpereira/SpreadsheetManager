package xxl.core;


public class Coalesce extends RangeFunction {
    
    public Coalesce(Range range){
        super(range,"COALESCE");
    }

    @Override
    protected Literal compute() {
       
        for (Cell cell : _range.getCellList()) {
            try {;

                // Check if the cell content is a LiteralString
                if (cell.value().toString().charAt(0) == '\'') {
                    return cell.value();
                }
            } catch (NullPointerException e) {
                // Ignore and continue
            }
        }

        return new LiteralString("'");  
    }
        
}

