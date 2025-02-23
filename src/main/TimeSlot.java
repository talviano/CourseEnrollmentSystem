/**
 * Represents a time slot for a course.
 * This class provides methods to check for conflicts with other time slots.
 *
 * @version Feb 22, 2025
 */
package main;

public class TimeSlot {
    private String day;
    private int startTime; // Will look for time class later
    private int endTime; // Will look for time class later

    /**
     * Constructs a TimeSlot with a specified day, start time, and end time.
     *
     * @param day the day of the time slot
     * @param startTime the start time of the time slot
     * @param endTime the end time of the time slot
     */
    public TimeSlot(String day, int startTime, int endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Checks if this time slot conflicts with another time slot.
     *
     * @param other the other time slot to check for conflicts
     * @return true if the time slots conflict, false otherwise
     */
    public boolean conflictsWith(TimeSlot other) {

        return false;
    }
}
