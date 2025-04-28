package model;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * Represents a time slot for a course.
 * This class provides methods to check for conflicts with other time slots.
 *
 * @version Feb 22, 2025
 */
public class TimeSlot {
    private LocalTime startTime;
    private LocalTime endTime;
    private DayOfWeek day;

    /**
     * Constructs a TimeSlot with a specified day, start time, and end time.
     *
     * @param day the day of the time slot
     * @param startTime the start time of the time slot
     * @param endTime the end time of the time slot
     */
    public TimeSlot(LocalTime startTime, LocalTime endTime, DayOfWeek day) {
        if (startTime == endTime) {
            throw new IllegalArgumentException("Classes must have a duration greater than zero.");
        }
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
    }

    /**
     * Checks if this time slot conflicts with another time slot.
     *
     * @param other the other time slot to check for conflicts
     * @return true if the time slots conflict, false otherwise
     */
    public boolean conflictsWith(TimeSlot other) {
        if (this.day == other.day) { // no conflict if they are on the same day
            if (this.startTime == other.startTime) {
                return true;
            }
            TimeSlot first = other.startTime.isBefore(this.startTime) ? other : this; // If the start time of other is before this then first is other otherwise first and this
            TimeSlot last = first == other ? this : other; //If first is other than last is this otherwise other
            return (last.startTime.isBefore(first.endTime) || last.startTime.equals(first.endTime)); //If last starts before first ends or if the startTime equals the end time then there is a conflict
        }
        return false;
    }

    /**
     * Returns the day of the time slot.
     *
     * @return the day of the time slot
     */
    public DayOfWeek getDay() {
        return day;
    }

    /**
     * Returns the start time of the time slot.
     *
     * @return the start time of the time slot
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Returns the end time of the time slot.
     *
     * @return the end time of the time slot
     */
    public LocalTime getEndTime() {
        return endTime;
    }
}
