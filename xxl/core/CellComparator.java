package xxl.core;

import java.util.Comparator;

public class CellComparator implements Comparator<Cell> {
    @Override
    public int compare(Cell cell1, Cell cell2) {
        // Compares function names alphabetically
        return cell1.getContentFunctionName().compareTo(cell2.getContentFunctionName());
    }
}
