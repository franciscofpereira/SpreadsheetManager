package xxl.core;

import java.util.Collection;

import xxl.core.exception.InvalidCellException;

/**
 * Interface for the Spreadsheet cell storage strategy. (strategy design pattern)
 * Allows for implementing various ways of storing the cells in a given Spreadsheet object.
 */
public interface CellStorageStrategy {

    void createCells(int numRows, int numColumns);
    public Cell getCell(int row, int column) throws InvalidCellException;
    public Collection<Cell> lookUp(SearchCriteria criteria);
}
