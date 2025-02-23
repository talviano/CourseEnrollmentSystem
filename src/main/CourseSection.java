/**
 * Represents a section of a course.
 * This class provides methods to manage enrollment and check availability.
 *
 * @version Feb 23, 2025
 */
package main;

import java.util.ArrayList;
import java.util.List;

public class CourseSection {
    private String sectionId;
    private Instructor instructor;
    private TimeSlot timeSlot;
    private int maxCapacity;
    private Course course;
    private List<Student> enrolledStudents;

    /**
     * Constructs a CourseSection with specified details.
     *
     * @param sectionId the ID of the course section
     * @param instructor the instructor of the course section
     * @param timeSlot the time slot of the course section
     * @param maxCapacity the maximum capacity of the course section
     * @param course the course associated with the section
     */
    public CourseSection(String sectionId, Instructor instructor, TimeSlot timeSlot, int maxCapacity, Course course) {
        this.sectionId = sectionId;
        this.instructor = instructor;
        this.timeSlot = timeSlot;
        this.maxCapacity = maxCapacity;
        this.course = course;
        this.enrolledStudents = new ArrayList<>();
    }

    /**
     * Checks if the course section is available for enrollment.
     *
     * @return true if the course section is available, false otherwise
     */
    public boolean isAvailable() {
        return false;
    }

    /**
     * Enrolls a student in the course section.
     *
     * @param student the student to enroll
     * @return true if enrollment is successful, false otherwise
     */
    public boolean enrollStudent(Student student) {
        return false;
    }

    /**
     * Drops a student from the course section.
     *
     * @param student the student to drop
     * @return true if dropping is successful, false otherwise
     */
    public boolean dropStudent(Student student) {
        return false;
    }

}
