/**
 * Tests the functionality of the TimeSlot class.
 * This class includes tests for conflict detection between time slots.
 *
 * @version Apr 27, 2025
 */
package tests;

import model.TimeSlot;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class TimeSlotTest {
    public static void main(String[] args) {
        System.out.println("########################################################");
        System.out.println("TESTING TIMESLOT CLASS FUNCTIONALITY...");

        // Create time slots
        TimeSlot slot1 = new TimeSlot(LocalTime.of(9, 0), LocalTime.of(10, 15), DayOfWeek.MONDAY);
        TimeSlot slot2 = new TimeSlot(LocalTime.of(9, 30), LocalTime.of(10, 45), DayOfWeek.MONDAY);
        TimeSlot slot3 = new TimeSlot(LocalTime.of(11, 0), LocalTime.of(12, 15), DayOfWeek.MONDAY);
        TimeSlot slot4 = new TimeSlot(LocalTime.of(9, 0), LocalTime.of(10, 15), DayOfWeek.TUESDAY);
        TimeSlot slot5 = new TimeSlot(LocalTime.of(10, 15), LocalTime.of(11, 30), DayOfWeek.MONDAY);

        // Test: Conflict on same day, overlapping times
        System.out.println("\nTEST: Overlapping time slots on same day (should conflict)...");
        if (slot1.conflictsWith(slot2)) {
            System.out.println("✓✓✓ PASS --> Detected conflict correctly.");
        } else {
            System.out.println("xxx FAIL --> Failed to detect conflict.");
        }

        // Test: No conflict, different times same day
        System.out.println("\nTEST: Non-overlapping time slots on same day (no conflict)...");
        if (!slot1.conflictsWith(slot3)) {
            System.out.println("✓✓✓ PASS --> No conflict detected correctly.");
        } else {
            System.out.println("xxx FAIL --> Incorrect conflict detected.");
        }

        // Test: No conflict, different days
        System.out.println("\nTEST: Time slots on different days (no conflict)...");
        if (!slot1.conflictsWith(slot4)) {
            System.out.println("✓✓✓ PASS --> Different days correctly not conflicting.");
        } else {
            System.out.println("xxx FAIL --> Incorrect conflict between different days.");
        }

        // Test: Touching edges (end time of one == start time of next)
        System.out.println("\nTEST: End time of one == start time of next (should conflict)...");
        if (slot1.conflictsWith(slot5)) {
            System.out.println("✓✓✓ PASS --> Conflict correctly detected at touching times.");
        } else {
            System.out.println("xxx FAIL --> Failed to detect conflict at touching times.");
        }

        System.out.println("\n########################################################");
        System.out.println("TIMESLOT CLASS TESTING COMPLETE.");
    }
}