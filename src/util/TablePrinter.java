/**
 * The TablePrinter class is a utility for printing tabular data to the console.
 * It dynamically generates a table based on the provided headers, data, and column extractors.
 * This class is generic and can be used to print tables for any type of object.
 *
 * Responsibilities:
 * - Dynamically calculate column widths based on the data and headers.
 * - Print a formatted table with headers, rows, and separators.
 *
 * Usage:
 * <pre>
 * List<String> headers = List.of("Id", "Name", "Credits");
 * List<ColumnExtractor<Course>> extractors = List.of(
 *     Course::getId,
 *     Course::getName,
 *     course -> String.valueOf(course.getCredits())
 * );
 * List<Course> courses = ...; // List of Course objects
 * TablePrinter<Course> printer = new TablePrinter<>(headers, extractors, courses);
 * printer.printTable();
 * </pre>
 *
 * @param <T> the type of the objects to be printed in the table
 * @version Apr 19, 2025
 */
package util;

import java.util.List;

public class TablePrinter<T> {
    /**
     * The headers for the table columns.
     */
    private List<String> headers;

    /**
     * The column extractors used to extract data from objects for each column.
     */
    private List<ColumnExtractor<T>> extractors;

    /**
     * The data to be displayed in the table.
     */
    private List<T> data;

    /**
     * Constructs a TablePrinter with the specified headers, column extractors, and data.
     *
     * @param headers the headers for the table columns
     * @param extractors the column extractors for extracting data from objects
     * @param data the data to be displayed in the table
     * @throws IllegalArgumentException if the number of headers does not match the number of extractors
     */
    public TablePrinter(List<String> headers, List<ColumnExtractor<T>> extractors, List<T> data) {
        if (headers.size() != extractors.size()) {
            throw new IllegalArgumentException("There cannot be more headers than extractors");
        }
        this.headers = headers;
        this.extractors = extractors;
        this.data = data;
    }

    /**
     * Prints the table to the console, including headers, rows, and separators.
     */
    public void printTable() {
        int[] columnWidths = new int[headers.size()];

        // Calculate the maximum width for each column
        for (int i = 0; i < headers.size(); i++) {
            columnWidths[i] = headers.get(i).length();
        }

        for (T item : data) {
            for (int i = 0; i < extractors.size(); i++) {
                String cell = extractors.get(i).extract(item);
                columnWidths[i] = Math.max(columnWidths[i], cell.length());
            }
        }

        // Print the header row
        printSeparator(columnWidths);
        printRow(headers, columnWidths);
        printSeparator(columnWidths);

        // Print the data rows
        for (T item : data) {
            printDataRow(item, columnWidths);
            printSeparator(columnWidths);
        }
    }

    /**
     * Prints a single row of data for the specified object.
     *
     * @param item the object to extract data from
     * @param columnWidths the widths of the columns
     */
    private void printDataRow(T item, int[] columnWidths) {
        for (int i = 0; i < extractors.size(); i++) {
            String value = extractors.get(i).extract(item);
            System.out.printf("| %-" + columnWidths[i] + "s ", value);
        }
        System.out.println("|");
    }

    /**
     * Prints a row of cells with the specified column widths.
     *
     * @param cells the list of cell values to print
     * @param widths the widths of the columns
     */
    private void printRow(List<String> cells, int[] widths) {
        for (int i = 0; i < cells.size(); i++) {
            System.out.printf("| %-" + widths[i] + "s ", cells.get(i));
        }
        System.out.println("|");
    }

    /**
     * Prints a separator line based on the column widths.
     *
     * @param widths the widths of the columns
     */
    private void printSeparator(int[] widths) {
        for (int width : widths) {
            System.out.print("+");
            System.out.print("-".repeat(width + 2));
        }
        System.out.println("+");
    }
}
