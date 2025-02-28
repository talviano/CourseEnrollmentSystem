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
     * Creates a table separator line based on the widths of the columns and prints it to the console.
     * The separator line starts and ends with a '+' character.
     *
     * @param stringWidths an array of integers representing the widths of the columns
     */
    public static void createTableSeperator(int[] stringWidths) {
        for (int width : stringWidths) {
            System.out.printf("+%" + (width + 2) + "s", "-".repeat(width + 2));
        }
        System.out.println("+");
    }

    /**
     * Formats a time slot into a string representation.
     * The format is "hh:mm a - hh:mm a".
     * Example: "09:30 AM - 11:00 AM"
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

    /**
     * Centers a given text within a specified width.
     * If the text length is greater than or equal to the width, the text is returned as is.
     *
     * @param text the text to center align
     * @param width the width to center the text within
     * @return the centered text
     */
    public static String centerAlign(String text, int width) {
        if (text.length() >= width) {
            return text; // If already at max width, return as is
        }
        int padding = (width - text.length()) / 2;
        String paddingStr = " ".repeat(padding);
    
        // Ensure it's exactly the correct width
        return String.format("%-" + width + "s", paddingStr + text + paddingStr);
    }

    /**
     * Calculates the maximum width required to display the schedule of an instructor.
     * The width is based on the number of time slots for the courses assigned to the instructor.
     *
     * @param instructor the instructor whose schedule width is to be calculated
     * @return the maximum width required to display the schedule
     */
    public static int getMaxScheduleWidth(Instructor instructor) {
        int max = 0;
        for (CourseSection course : instructor.getAssignedCourses()) {
            max = Math.max(max, course.getTimeSlots().size());
        }
        return (max * 21) + ((max - 1) * 2);
    }

    /**
     * Calculates the maximum width required to display the names of students enrolled in a course section.
     *
     * @param section the course section whose enrolled students' names are to be considered
     * @return the maximum width required to display the names of enrolled students
     */
    public static int getMaxEnrolledStudentNameWidth(CourseSection section) {
        int max = 0;
        for (Student student : section.getEnrolledStudents()) {
            if (student.getName().length() > max) {
                max = student.getName().length();
            }
        }
        return max;
    }

    /**
     * Calculates the maximum width required to display the email addresses of students enrolled in a course section.
     *
     * @param section the course section whose enrolled students' email addresses are to be considered
     * @return the maximum width required to display the email addresses of enrolled students
     */
    public static int getMaxEnrolledStudentEmailWidth(CourseSection section) {
        int max = 0;
        for (Student student : section.getEnrolledStudents()) {
            if (student.getEmail().length() > max) {
                max = student.getEmail().length();
            }
        }
        return max;
    }

}
