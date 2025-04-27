/**
 * Tests the functionality of the EnrollmentSystem class.
 * This class includes tests for adding, finding, and removing courses and sections.
 *
 * @version Apr 27, 2025
 */
package tests;

import model.Course;
import model.CourseSection;
import model.TimeSlot;
import system.EnrollmentSystem;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class EnrollmentSystemTest {
    public static void main(String[] args) {
        System.out.println("########################################################");
        System.out.println("TESTING ENROLLMENT SYSTEM CLASS FUNCTIONALITY...");

        // Create EnrollmentSystem
        EnrollmentSystem enrollmentSystem = new EnrollmentSystem();

        // Create Course
        Course course = new Course("MATH 2142", "Calculus III", "Multivariable Calculus", 4);

        // Test: Adding a course
        System.out.println("\nTEST: Adding a new course...");
        boolean courseAdded = enrollmentSystem.addCourse(course);
        if (courseAdded) {
            System.out.println("✓✓✓ PASS --> Course added successfully.");
        } else {
            System.out.println("xxx FAIL --> Course addition failed.");
        }

        // Create a section for the course
        List<TimeSlot> timeSlots = List.of(
                new TimeSlot(LocalTime.of(9, 0), LocalTime.of(10, 15), DayOfWeek.TUESDAY)
        );
        CourseSection section = course.createSection(timeSlots, 30);

        // Test: Finding course by ID
        System.out.println("\nTEST: Finding course by ID...");
        Course foundCourse = enrollmentSystem.getCourseById("MATH 2142");
        if (foundCourse != null && foundCourse.getName().equals("Calculus III")) {
            System.out.println("✓✓✓ PASS --> Course found by ID successfully.");
        } else {
            System.out.println("xxx FAIL --> Course lookup by ID failed.");
        }

        // Test: Finding section by CRN
        System.out.println("\nTEST: Finding course section by CRN...");
        CourseSection foundSection = enrollmentSystem.findCourseByCRN(section.getCRN());
        if (foundSection != null && foundSection.getCRN().equals(section.getCRN())) {
            System.out.println("✓✓✓ PASS --> Course section found by CRN successfully.");
        } else {
            System.out.println("xxx FAIL --> Course section lookup by CRN failed.");
        }

        // Test: Removing the course
        System.out.println("\nTEST: Removing course and all its sections...");
        boolean removedCourse = enrollmentSystem.removeCourse(course);
        if (removedCourse && enrollmentSystem.getCourseById("MATH 242") == null) {
            System.out.println("✓✓✓ PASS --> Course and its sections removed successfully.");
        } else {
            System.out.println("xxx FAIL --> Course removal failed.");
        }

        // Test: Section should also be gone now
        System.out.println("\nTEST: Ensuring section is also removed...");
        CourseSection removedSection = enrollmentSystem.findCourseByCRN(section.getCRN());
        if (removedSection == null) {
            System.out.println("✓✓✓ PASS --> Section correctly removed along with course.");
        } else {
            System.out.println("xxx FAIL --> Section still exists after course removal.");
        }

        System.out.println("\n########################################################");
        System.out.println("ENROLLMENT SYSTEM CLASS TESTING COMPLETE.");
    }
}
