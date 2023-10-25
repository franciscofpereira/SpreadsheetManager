package xxl.core;

public class FunctionSearchCriteria implements SearchCriteria {
    
    private String _functionToFind;

    public FunctionSearchCriteria(String functionToFind){
        _functionToFind = functionToFind;
    }

    @Override
    public boolean matches(Cell cell) {
        Content cellContent = cell.getContent();
        return cellContent != null && cellContent.toString().contains(_functionToFind);
    }
}
