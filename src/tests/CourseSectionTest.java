package tests;

import model.Course;
import model.CourseSection;
import model.Student;
import model.Instructor;
import model.TimeSlot;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

/**
 * Tests the functionality of the CourseSection class.
 * This class includes tests for enrollment, dropping, instructor assignment, and capacity checks.
 *
 * @version Apr 27, 2025
 */
public class CourseSectionTest {
    public static void main(String[] args) {
        System.out.println("########################################################");
        System.out.println("TESTING COURSE SECTION CLASS FUNCTIONALITY...");

        // Setup Course and Section
        Course course = new Course("CSCI 2000", "Software Engineering", "Study of software processes.", 3);
        List<TimeSlot> timeSlots = List.of(new TimeSlot(LocalTime.of(9, 0), LocalTime.of(10, 15), DayOfWeek.MONDAY));
        CourseSection section = course.createSection(timeSlots, 2); // max 2 students

        // Create Students
        Student student1 = new Student("Alice", "alice@example.com", "password", false);
        Student student2 = new Student("Bob", "bob@example.com", "password", false);
        Student student3 = new Student("Charlie", "charlie@example.com", "password", false);

        // Create Instructor
        Instructor instructor = new Instructor("Dr. Smith", "smith@example.com", "password");

        // Test: Enrolling first student
        System.out.println("\nTEST: Enrolling first student...");
        boolean enrolled1 = section.enrollStudent(student1);
        if (enrolled1) {
            System.out.println("✓✓✓ PASS --> First student enrolled successfully.");
        } else {
            System.out.println("xxx FAIL --> First student enrollment failed.");
        }

        // Test: Enrolling second student
        System.out.println("\nTEST: Enrolling second student...");
        boolean enrolled2 = section.enrollStudent(student2);
        if (enrolled2) {
            System.out.println("✓✓✓ PASS --> Second student enrolled successfully.");
        } else {
            System.out.println("xxx FAIL --> Second student enrollment failed.");
        }

        // Test: Section should now be full
        System.out.println("\nTEST: Checking if section is full...");
        if (section.isFull()) {
            System.out.println("✓✓✓ PASS --> Section correctly reports being full.");
        } else {
            System.out.println("xxx FAIL --> Section incorrectly reports not being full.");
        }

        // Test: Enrolling a third student (should fail)
        System.out.println("\nTEST: Trying to enroll third student (should fail)...");
        boolean enrolled3 = section.enrollStudent(student3);
        if (!enrolled3) {
            System.out.println("✓✓✓ PASS --> Third student enrollment correctly prevented.");
        } else {
            System.out.println("xxx FAIL --> Third student enrollment incorrectly allowed.");
        }

        // Test: Dropping a student
        System.out.println("\nTEST: Dropping first student...");
        boolean dropped = section.dropStudent(student1);
        if (dropped) {
            System.out.println("✓✓✓ PASS --> Student dropped successfully.");
        } else {
            System.out.println("xxx FAIL --> Student drop failed.");
        }

        // Test: Dropping a student not enrolled (should fail)
        System.out.println("\nTEST: Dropping non-enrolled student...");
        boolean droppedAgain = section.dropStudent(student3);
        if (!droppedAgain) {
            System.out.println("✓✓✓ PASS --> Drop prevented for non-enrolled student.");
        } else {
            System.out.println("xxx FAIL --> Drop allowed for non-enrolled student.");
        }

        // Test: Assigning an instructor
        System.out.println("\nTEST: Assigning instructor...");
        section.assignInstructor(instructor);
        if (section.getInstructor() == instructor) {
            System.out.println("✓✓✓ PASS --> Instructor assigned successfully.");
        } else {
            System.out.println("xxx FAIL --> Instructor assignment failed.");
        }

        // Test: Unassigning instructor
        System.out.println("\nTEST: Unassigning instructor...");
        section.unassignInstructor();
        if (section.getInstructor() == null) {
            System.out.println("✓✓✓ PASS --> Instructor unassigned successfully.");
        } else {
            System.out.println("xxx FAIL --> Instructor unassignment failed.");
        }

        // Test: Checking enrolled count
        System.out.println("\nTEST: Checking enrolled count...");
        if (section.getEnrolledCount() == 1) {
            System.out.println("✓✓✓ PASS --> Enrolled count correct after operations.");
        } else {
            System.out.println("xxx FAIL --> Enrolled count incorrect.");
        }

        System.out.println("\n########################################################");
        System.out.println("COURSE SECTION CLASS TESTING COMPLETE.");
    }
}
