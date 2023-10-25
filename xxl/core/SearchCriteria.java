package xxl.core;

/**
 * Universal interface designed to integrate diverse search criteria across 
 * the program's different search features, ensuring extensibility for future additions.
 */
public interface SearchCriteria {
    
    boolean matches(Cell cell);
}
