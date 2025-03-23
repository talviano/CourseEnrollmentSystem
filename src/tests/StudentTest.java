/**
 * TODO Write a one-sentence summary of your class here.
 * TODO Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 *
 * @author  TODO Your Name
 * @version Feb 26, 2025
 */
package tests;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import model.Course;
import model.CourseSection;
import model.Student;
import model.TimeSlot;

public class StudentTest {
    public static void main(String[] args) {
        System.out.println("########################################################");
        System.out.println("TESTING STUDENT CLASS FUNCTIONALITY...");

        // Create a Student
        Student student = new Student("S1mple", "s1mple@example.com", "password123", false);
        Student student2 = new Student("Donk", "donk@example.com", "password123", false);

        // Create Courses
        Course course1 = new Course("ITSC 1212", "Intro to Computer Science", "Learn programming basics", 4);
        Course course2 = new Course("MATH 1241", "Calculus I", "Fundamentals of calculus", 3);

        // Create TimeSlots
        TimeSlot morningSlot = new TimeSlot(LocalTime.of(9, 0), LocalTime.of(10, 30), DayOfWeek.MONDAY);
        TimeSlot conflictingSlot = new TimeSlot(LocalTime.of(10, 0), LocalTime.of(11, 30), DayOfWeek.MONDAY);
        TimeSlot nonConflictingSlot = new TimeSlot(LocalTime.of(14, 0), LocalTime.of(15, 30), DayOfWeek.MONDAY);

        // Create Course Sections
        List<TimeSlot> timeSlots1 = new ArrayList<>();
        timeSlots1.add(morningSlot);
        CourseSection smallCapacitySection = course1.createSection(timeSlots1, 1);

        List<TimeSlot> timeSlots2 = new ArrayList<>();
        timeSlots2.add(conflictingSlot);
        CourseSection conflictingSection = course2.createSection(timeSlots2, 30);

        List<TimeSlot> timeSlots3 = new ArrayList<>();
        timeSlots3.add(nonConflictingSlot);
        CourseSection nonConflictingSection = course2.createSection(timeSlots3, 24);
        CourseSection nonConflictingSectionOfSameCourse = course1.createSection(timeSlots3, 30);

        // Test: Enrolling in a course
        System.out.println("\nTEST: Enrolling in ITSC 1242 (9:00 - 10:30 AM)...");
        boolean enrolled1 = student.enroll(smallCapacitySection);
        if (enrolled1) {
            System.out.println("✓✓✓ PASS --> Student successfully enrolled in ITSC 1242.");
        } else {
            System.out.println("xxx FAIL --> Enrollment in ITSC 1242 failed.");
        }

        // Test: Enrolling in a course at max capacity (should fail)
        System.out.println("\nTEST: Enrolling in ITSC 1242 (9:00 - 10:30 AM)...");
        boolean enrolled2 = student2.enroll(smallCapacitySection);
        if (!enrolled2) {
            System.out.println("✓✓✓ PASS --> Student kept from enrolling in full ITSC 1242.");
        } else {
            System.out.println("xxx FAIL --> Enrollment in full ITSC 1242 was allowed.");
        }

        // Test: Enrolling in the same course section again (should fail)
        System.out.println("\nTEST: Attempting to enroll in the same course section again...");
        boolean duplicateSectionEnroll = student.enroll(smallCapacitySection);
        if (!duplicateSectionEnroll) {
            System.out.println("✓✓✓ PASS --> Duplicate section enrollment prevented.");
        } else {
            System.out.println("xxx FAIL --> Duplicate section enrollment allowed incorrectly.");
        }

        //Test: Enrolling in a different course section of the same course
        System.out.println("\nTEST: Attempting to enroll in a different course section but the same course again...");
        boolean duplicateCourseEnroll = student.enroll(nonConflictingSectionOfSameCourse);
        if (!duplicateCourseEnroll) {
            System.out.println("✓✓✓ PASS --> Duplicate course enrollment prevented.");
        } else {
            System.out.println("xxx FAIL --> Duplicate course enrollment allowed incorrectly.");
        }

        // Test: Dropping a course
        System.out.println("\nTEST: Dropping ITSC 1242...");
        boolean dropped = student.drop(smallCapacitySection);
        if (dropped) {
            System.out.println("✓✓✓ PASS --> Student successfully dropped ITSC 1242.");
        } else {
            System.out.println("xxx FAIL --> Course drop failed.");
        }

        // Test: Dropping a non-existent course (should fail)
        System.out.println("\nTEST: Attempting to drop a non-existent course...");
        boolean nonExistentDrop = student.drop(smallCapacitySection);
        if (!nonExistentDrop) {
            System.out.println("✓✓✓ PASS --> Non-existent course drop correctly prevented.");
        } else {
            System.out.println("xxx FAIL --> Student dropped a non-existent course.");
        }

        // Test: Advising hold prevents enrollment
        System.out.println("\nTEST: Setting advising hold and attempting enrollment...");
        student.setAdvisingHold(true);
        boolean enrollWithHold = student.enroll(smallCapacitySection);
        if (!enrollWithHold) {
            System.out.println("✓✓✓ PASS --> Enrollment prevented due to advising hold.");
        } else {
            System.out.println("xxx FAIL --> Enrollment succeeded despite advising hold.");
        }
        student.setAdvisingHold(false);

        // Test: Enrolling in a course
        System.out.println("\nTEST: Enrolling in ITSC 1242 (9:00 - 10:30 AM)...");
        boolean enrolled3 = student.enroll(smallCapacitySection);
        if (enrolled3) {
            System.out.println("✓✓✓ PASS --> Student successfully enrolled in ITSC 1242.");
        } else {
            System.out.println("xxx FAIL --> Enrollment in ITSC 1242 failed.");
        }

        // Test: Enrolling in conflicting course (should fail)
        System.out.println("\nTEST: Attempting to enroll in MATH201 (10:00 - 11:30 AM, conflicts with ITSC 1242)...");
        boolean enrolledConflict = student.enroll(conflictingSection);
        if (!enrolledConflict) {
            System.out.println("✓✓✓ PASS --> Time conflict detected. Enrollment prevented.");
        } else {
            System.out.println("xxx FAIL --> Student incorrectly enrolled in a conflicting course.");
        }

        // Test: Enrolling in a non-conflicting course (should pass)
        System.out.println("\nTEST: Enrolling in MATH201 (2:00 - 3:30 PM, no conflict)...");
        boolean enrolledNoConflict = student.enroll(nonConflictingSection);
        if (enrolledNoConflict) {
            System.out.println("✓✓✓ PASS --> No time conflict. Enrollment successful.");
        } else {
            System.out.println("xxx FAIL --> Enrollment failed despite no time conflict.");
        }

        System.out.println("\n########################################################");
        System.out.println("STUDENT CLASS TESTING COMPLETE.");
    }

}
