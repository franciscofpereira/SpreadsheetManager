package xxl.core;

/**
 * Universal interface designed to integrate diverse search criteria across 
 * the program's search feature, ensuring extensibility for future desired lookups.
 */
public interface SearchCriteria {
    
    boolean matches(Cell cell);
}
