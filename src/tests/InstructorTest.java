package tests;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import model.Course;
import model.CourseSection;
import model.Instructor;
import model.Student;
import model.TimeSlot;

/**
 * Tests the functionality of the Instructor class.
 * This class includes tests for assigning courses, viewing assigned courses, and viewing section rosters.
 *
 * @version Feb 27, 2025
 */
public class InstructorTest {
    public static void main(List<String> args) {
        System.out.println("########################################################");
        System.out.println("TESTING INSTRUCTOR CLASS FUNCTIONALITY...");

        // Create Instructors
        Instructor instructor = new Instructor("Dr. Smith", "smith@example.com", "password123");
        Instructor instructor2 = new Instructor("Dr. Johnson", "johnson@example.com", "securePass");

        // Create Courses
        Course course1 = new Course("ITSC 1212", "Intro to Computer Science", "Learn programming basics", 4);
        Course course2 = new Course("MATH 1241", "Calculus I", "Fundamentals of calculus", 3);

        // Create TimeSlots
        TimeSlot morningSlot = new TimeSlot(LocalTime.of(9, 0), LocalTime.of(10, 30), DayOfWeek.MONDAY);
        TimeSlot afternoonSlot = new TimeSlot(LocalTime.of(14, 0), LocalTime.of(15, 30), DayOfWeek.WEDNESDAY);
        TimeSlot extraSlot = new TimeSlot(LocalTime.of(16, 0), LocalTime.of(17, 30), DayOfWeek.THURSDAY);

        // Create Course Sections
        List<TimeSlot> timeSlots1 = new ArrayList<>();
        timeSlots1.add(morningSlot);
        CourseSection section1 = course1.createSection(timeSlots1, 30);

        List<TimeSlot> timeSlots2 = new ArrayList<>();
        timeSlots2.add(afternoonSlot);
        timeSlots2.add(extraSlot);
        CourseSection section2 = course2.createSection(timeSlots2, 25);

        // Create Students
        Student student1 = new Student("Alice", "alice@example.com", "password123", false);
        Student student2 = new Student("Bob", "bob@example.com", "password123", false);
        Student student3 = new Student("Charlie", "charlie@example.com", "password123", false);
        Student student4 = new Student("David", "david@example.com", "password123", false);

        // Enroll students in section1
        student1.enroll(section1);
        student2.enroll(section1);
        student3.enroll(section1);
        student4.enroll(section1);

        // Test: Assigning a course section
        System.out.println("\nTEST: Assigning instructor to ITSC 1212 section 001...");
        boolean assigned1 = instructor.assignCourse(section1);
        if (assigned1) {
            System.out.println("✓✓✓ PASS --> Instructor successfully assigned to ITSC 1212 section 001.");
        } else {
            System.out.println("xxx FAIL --> Instructor assignment failed.");
        }

        // Test: Assigning the same course section again (should fail)
        System.out.println("\nTEST: Assigning instructor to ITSC 1212 section 001 again...");
        boolean duplicateAssign = instructor.assignCourse(section1);
        if (!duplicateAssign) {
            System.out.println("✓✓✓ PASS --> Duplicate course assignment prevented.");
        } else {
            System.out.println("xxx FAIL --> Instructor assigned to the same course section twice.");
        }

        // Test: Viewing assigned courses
        instructor.assignCourse(section2);
        System.out.println("\nTEST: Viewing assigned courses with varying time slot counts...");
        instructor.viewAssignedSections();

        // Test: Viewing section roster (should succeed)
        System.out.println("\nTEST: Viewing roster for assigned section 001 with enrolled students...");
        boolean rosterViewSuccess = instructor.viewSectionRoster(section1);
        if (rosterViewSuccess) {
            System.out.println("✓✓✓ PASS --> Instructor can view roster for assigned section.");
        } else {
            System.out.println("xxx FAIL --> Instructor could not view roster for assigned section.");
        }

        // Test: Removing instructor assignment from a section they were never assigned to
        System.out.println("\nTEST: Removing instructor2 assignment from ITSC 1212 section 001 (should fail)...");
        boolean removeFail = instructor2.removeCourseAssignment(section1);
        if (!removeFail) {
            System.out.println("✓✓✓ PASS --> Unassigned instructor correctly prevented from removing assignment.");
        } else {
            System.out.println("xxx FAIL --> Unassigned instructor incorrectly allowed to remove assignment.");
        }

        // Test: Removing instructor and assigning new instructor
        System.out.println("\nTEST: Removing and assigning a new instructor...");
        section1.assignInstructor(instructor);
        instructor.removeCourseAssignment(section1);
        section1.assignInstructor(instructor2);
        if (section1.getInstructor() == instructor2) {
            System.out.println("✓✓✓ PASS --> Instructor successfully reassigned.");
        } else {
            System.out.println("xxx FAIL --> Instructor reassignment did not update correctly.");
        }

        System.out.println("\n########################################################");
        System.out.println("INSTRUCTOR CLASS TESTING COMPLETE.");
    }
}
