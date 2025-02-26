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
    private List<TimeSlot> timeSlots;
    private int maxCapacity;
    private Course course;
    private List<Student> enrolledStudents;

    /**
     * Constructs a CourseSection with specified details.
     *
     * @param sectionId the ID of the course section
     * @param instructor the instructor of the course section
     * @param timeSlots the time slots of the course section
     * @param maxCapacity the maximum capacity of the course section
     * @param course the course associated with the section
     */
    public CourseSection(String sectionId, Instructor instructor, List<TimeSlot> timeSlots, int maxCapacity, Course course) {
        this.sectionId = sectionId;
        this.instructor = instructor;
        this.timeSlots = timeSlots;
        this.maxCapacity = maxCapacity;
        this.course = course;
        this.enrolledStudents = new ArrayList<>();
    }

    /**
     * Checks if the course section is full.
     *
     * @return {@code true} if the course section is full, {@code false} otherwise
     */
    public boolean isFull() {
        return enrolledStudents.size() >= maxCapacity;
    }

    /**
     * Enrolls a student in the course section:
     *
     * Enrollment is unsuccessful if the student is already enrolled in the section
     * or if the section has reached its maximum capacity.
     *
     * @param student the student to enroll
     * @return {@code true} if enrollment is successful, {@code false} if:
     *         <ul>
     *             <li>The student is already enrolled in the section</li>
     *             <li>The section has reached its maximum capacity</li>
     *         </ul>
     */
    public boolean enrollStudent(Student student) {
        if (enrolledStudents.contains(student)) {
            System.out.println(student.getName() + " is already enrolled in " + this.course.getId());
            return false;
        }
        if (isFull()) {
            System.out.println("This section at capacity");
            return false;
        }
        enrolledStudents.add(student);
        student.getEnrolledCourses().add(this);
        return true;
    }

    /**
     * Drops a student from the course section.
     *
     * Dropping is unsuccessful if the student is not enrolled in the section.
     *
     * @param student the student to drop
     * @return {@code true} if dropping is successful, {@code false} if:
     *         <ul>
     *             <li>The student is not enrolled in the section</li>
     *         </ul>
     */
    public boolean dropStudent(Student student) {
        if (enrolledStudents.contains(student)) {
            enrolledStudents.remove(student);
            student.getEnrolledCourses().remove(this);
            System.out.println(
                    student.getName() + " removed from " + this.course.getName() + "Section: " + this.sectionId);
            return true;
        }
        System.out.println("Student is not enrolled in " + this.course.getName() + "Section: " + this.sectionId);
        return false;
    }

    /**
     * Returns the list of students enrolled in the course section.
     *
     * @return the list of enrolled students
     */
    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    /**
     * Returns the ID of the course section.
     *
     * @return the section ID
     */
    public String getSectionId() {
        return sectionId;
    }

    /**
     * Returns the list of time slots for the course section.
     *
     * @return the list of time slots
     */
    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    /**
     * Returns the course associated with the section.
     *
     * @return the course
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Returns the number of students enrolled in the course section.
     *
     * @return the number of enrolled students
     */
    public int getEnrolledCount() {
        return enrolledStudents.size();
    }
}
