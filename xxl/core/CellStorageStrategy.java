package xxl.core;

import java.util.Collection;

import xxl.core.exception.UnrecognizedEntryException;

/**
 * Common interface for all cell storage strategies. (strategy design pattern)
 * Allows for storing cells in a given Spreadsheet object in various ways.
 */
public interface CellStorageStrategy {

    void createCells(int numRows, int numColumns);
    public Cell getCell(int row, int column) throws UnrecognizedEntryException;
    public Collection<Cell> lookUp(SearchCriteria criteria);
}
