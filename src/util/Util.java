/**
 * Utility class providing helper methods for formatting and printing.
 * This class includes methods to create separators, table separators, and format time slots.
 * 
 * @version Feb 26, 2025
 */
package util;

import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import model.Course;
import model.CourseSection;
import model.Instructor;
import model.Student;
import model.TimeSlot;
import model.User;
import system.AccountManager;

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
     * Validates if the given menu choice is within the valid range.
     *
     * @param choice the menu choice to validate
     * @param size the number of menu options
     * @return true if the choice is valid, false otherwise
     */
    public static boolean isValidMenuChoice(int choice, int size) {
        return choice > 0 && choice < size + 1;
    }

    /**
     * Creates and prints a menu with the given name and actions.
     *
     * @param name the name of the menu
     * @param actions the actions to be included in the menu
     */
    public static void createMenu(String name, List<String> actions) {
        String longest = "";
        for (String action : actions) {
            if (action.length() > longest.length()) {
                longest = action;
            }
        }
        if (name.length() > longest.length()) {
            longest = name;
        }
        int length = longest.length() + 15;
        createSeperator(length);
        System.out.println(name);
        createSeperator(length);
        for (int i = 0; i < actions.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + actions.get(i));
        }
        createSeperator(length);
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
     * Calculates the maximum width required to display the time slots of a list of courses.
     *
     * @param courses the list of courses
     * @return the maximum width required to display the time slots
     */
    public static int getMaxTimeSlotWidth(List<Course> courses) {
        int max = 0;
        for (Course course : courses) {
            for (CourseSection section : course.getSections()) {
                max = Math.max(max, section.getTimeSlotsFormatted().length());
            }
        }
        return max;
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

    /**
     * Calculates the maximum width required to display the names of users managed by the account manager.
     *
     * @param manager the account manager
     * @return the maximum width required to display the names of users
     */
    public static int getMaxUserNameWidth(AccountManager manager) {
        int max = 0;
        for (User user : manager.getUsers()) {
            max = Math.max(max, user.getName().length());
        }
        return max;
    }

    /**
     * Calculates the maximum width required to display the email addresses of users managed by the account manager.
     *
     * @param manager the account manager
     * @return the maximum width required to display the email addresses of users
     */
    public static int getMaxUserEmailWidth(AccountManager manager) {
        int max = 0;
        for (User user : manager.getUsers()) {
            max = Math.max(max, user.getEmail().length());
        }
        return max;
    }

    /**
     * Calculates the maximum width required to display the passwords of users managed by the account manager.
     *
     * @param manager the account manager
     * @return the maximum width required to display the passwords of users
     */
    public static int getMaxUserPasswordWidth(AccountManager manager) {
        int max = 0;
        for (User user : manager.getUsers()) {
            max = Math.max(max, user.getEmail().length());
        }
        return max;
    }

    /**
     * Converts a yes/no input string to a boolean value.
     *
     * @param input the input string ("y" for yes, "n" for no)
     * @return true if the input is "y", false otherwise
     */
    public static boolean yesNoToBoolean(String input) {
        switch (input.strip().toLowerCase()) {
            case "y":
                return true;
            case "n":
                return false;
            default:
                throw new InputMismatchException(input + " is an invalid input. Please enter (y/n).");
        }
    }
}
