/**
 * Utility class providing helper methods for formatting and printing.
 * This class includes methods to create separators, table separators, and format time slots.
 * 
 * @version Feb 26, 2025
 */
package main;

import java.time.format.DateTimeFormatter;

public class Util {
    /**
     * Creates a separator line of a specified length and prints it to the console.
     *
     * @param length the length of the separator line
     */
    public static void createSeperator(int length) {
        for (int i = 0; i < length - 1; i++) {
            System.out.print("-");
        }
        System.out.println("-");
    }

    /**
     * Creates a table separator line of a specified length and prints it to the console.
     * The separator line starts and ends with a '+' character.
     *
     * @param length the length of the table separator line
     */
    public static void createTableSeperator(int length) {
        System.out.print("+");
        for (int i = 0; i < length - 2; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }

    /**
     * Formats a time slot into a string representation.
     * The format is "hh:mm a - hh:mm a".
     * Ex: 9:30 AM - 11:00 AM
     *
     * @param slot the time slot to format
     * @return the formatted time slot string
     */
    public static String formatTimeSlot(TimeSlot slot) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String start = slot.getStartTime().format(formatter);
        String end = slot.getEndTime().format(formatter);
        return start + " - " + end;
    }
}
