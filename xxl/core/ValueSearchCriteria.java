package xxl.core;

/**
 * Class that represents the search criteria for a value lookup.
 */
public class ValueSearchCriteria implements SearchCriteria {
    
    private String _valueToFind;
    
    public ValueSearchCriteria(String valueToFind){
        _valueToFind = valueToFind;
    }


    @Override
    public boolean matches(Cell cell) {
        Literal cellValue = cell.value();
        return cellValue != null && cellValue.toString().equals(_valueToFind);
    }
      
}
