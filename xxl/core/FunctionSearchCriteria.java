package xxl.core;

/**
 * Class that represents a function as search criteira for the lookUp method.
 */
public class FunctionSearchCriteria implements SearchCriteria {
    
    private String _functionToFind;

    public FunctionSearchCriteria(String functionToFind){
        _functionToFind = functionToFind;
    }

    @Override
    public boolean matches(Cell cell) {
        
        String functionName = null;

        if (cell.getContent() instanceof Function) {
            Function function = (Function) cell.getContent();
            functionName = function.getFunctionName();
        }   
        
        return cell.getContent() != null && functionName!=null && functionName.contains(_functionToFind);
    }
}
