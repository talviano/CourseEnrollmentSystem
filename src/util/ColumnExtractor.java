package util;

/**
 * Utility class providing helper methods for formatting and printing.
 * This class includes methods to create separators, table separators, and format time slots.
 * 
 * @version Feb 26, 2025
 */
@FunctionalInterface
public interface ColumnExtractor<T> {
    /**
     * Extracts a string for a specific column value from the given object.
     *
     * @param obj the object from which the column value is extracted
     * @return the extracted column value as a string
     */
    public String extract(T obj);
}