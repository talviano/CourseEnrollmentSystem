/**
 * Tests the functionality of the Course class.
 * This class includes tests for section creation and removal.
 *
 * @version Apr 27, 2025
 */
package tests;

import model.Course;
import model.CourseSection;
import model.TimeSlot;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class CourseTest {
    public static void main(String[] args) {
        System.out.println("########################################################");
        System.out.println("TESTING COURSE CLASS FUNCTIONALITY...");

        // Create a Course
        Course course = new Course("CSCI 1234", "Intro to CS", "An introduction to computer science.", 3);

        // Test: Creating a section
        System.out.println("\nTEST: Creating a new section...");
        List<TimeSlot> timeSlots = List.of(new TimeSlot(LocalTime.of(9, 0), LocalTime.of(10, 15), DayOfWeek.MONDAY));
        CourseSection section = course.createSection(timeSlots, 30);

        if (course.getSections().contains(section)) {
            System.out.println("✓✓✓ PASS --> Section successfully created and added to course.");
        } else {
            System.out.println("xxx FAIL --> Section was not added to course.");
        }

        // Test: Creating another section
        System.out.println("\nTEST: Creating another section...");
        List<TimeSlot> timeSlots2 = List.of(new TimeSlot(LocalTime.of(11, 0), LocalTime.of(12, 15), DayOfWeek.WEDNESDAY));
        CourseSection section2 = course.createSection(timeSlots2, 25);

        if (course.getSections().size() == 2) {
            System.out.println("✓✓✓ PASS --> Second section successfully created and tracked.");
        } else {
            System.out.println("xxx FAIL --> Second section creation or tracking failed.");
        }

        // Test: Removing a section
        System.out.println("\nTEST: Removing the first section...");
        boolean removed = course.removeCourseSection(section);
        if (removed && !course.getSections().contains(section)) {
            System.out.println("✓✓✓ PASS --> Section successfully removed from course.");
        } else {
            System.out.println("xxx FAIL --> Section removal failed.");
        }

        // Test: Attempting to remove a section that doesn't exist
        System.out.println("\nTEST: Attempting to remove the same section again...");
        boolean removedAgain = course.removeCourseSection(section);
        if (!removedAgain) {
            System.out.println("✓✓✓ PASS --> Correctly handled removal of non-existent section.");
        } else {
            System.out.println("xxx FAIL --> Incorrectly allowed removal of non-existent section.");
        }

        System.out.println("\n########################################################");
        System.out.println("COURSE CLASS TESTING COMPLETE.");
    }
}
