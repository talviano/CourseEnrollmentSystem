/**
 * Represents a section of a course.
 * This class provides methods to manage enrollment, assign instructors, and check availability.
 *
 * @version Feb 23, 2025
 */
package model;

import java.util.ArrayList;
import java.util.List;
import util.Util;

public class CourseSection {
    private String sectionId;
    private Instructor instructor;
    private List<TimeSlot> timeSlots;
    private int maxCapacity;
    private Course course;
    private List<Student> enrolledStudents;
    private String crn;
    private static int lastAssignedCrn = 10000;

    /**
     * Constructs a CourseSection with specified details.
     *
     * @param course the course associated with the section
     * @param sectionId the ID of the course section
     * @param timeSlots the time slots of the course section
     * @param maxCapacity the maximum capacity of the course section
     */
    public CourseSection(Course course, String sectionId, List<TimeSlot> timeSlots, int maxCapacity) {
        this.sectionId = sectionId;
        this.instructor = null;
        this.timeSlots = timeSlots;
        this.maxCapacity = maxCapacity;
        this.course = course;
        this.enrolledStudents = new ArrayList<>();
        this.crn = String.valueOf(++lastAssignedCrn);
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
     * Enrolls a student in the course section.
     *
     * Enrollment is unsuccessful if the student is already enrolled in the section
     * or if the section has reached its maximum capacity.
     *
     * @param student the student to enroll
     * @return {@code true} if enrollment is successful, {@code false} otherwise
     */
    public boolean enrollStudent(Student student) {
        if (enrolledStudents.contains(student)) {
            System.out.println(student.getName() + " is already enrolled in " + this.course.getId());
            return false;
        }
        if (isFull()) {
            System.out.println("This section is at capacity");
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
     * @return {@code true} if dropping is successful, {@code false} otherwise
     */
    public boolean dropStudent(Student student) {
        if (enrolledStudents.contains(student)) {
            enrolledStudents.remove(student);
            student.getEnrolledCourses().remove(this);
            System.out.println(
                    student.getName() + " removed from " + this.course.getName() + " Section: " + this.sectionId);
            return true;
        }
        System.out.println("Student is not enrolled in " + this.course.getName() + " Section: " + this.sectionId);
        return false;
    }

    /**
     * Assigns an instructor to the course section.
     *
     * If the section already has an instructor, the current instructor is removed before assigning the new instructor.
     *
     * @param instructor the instructor to assign
     */
    public void assignInstructor(Instructor instructor) {
        if (this.instructor != null) {
            this.instructor.getAssignedCourses().remove(this);
        }
        this.instructor = instructor;
        if (this.instructor != null) {
            this.instructor.assignCourse(this);
        }
    }

    /**
     * Unassigns the current instructor from the course section.
     */
    public void unassignInstructor() {
        if (this.instructor == null) {
            return;
        }
        this.instructor = null;
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

    /**
     * Returns the maximum capacity of the course section.
     *
     * @return the maximum capacity
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * Returns the size of the course section as a string.
     *
     * @return the size of the course section
     */
    public String getSize() {
        return enrolledStudents.size() + "/" + maxCapacity;
    }

    /**
     * Returns the instructor assigned to the course section.
     *
     * @return the instructor
     */
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     * Returns the formatted string of time slots for the course section.
     *
     * @return the formatted string of time slots
     */
    public String getTimeSlotsFormatted() {
        StringBuilder formatted = new StringBuilder();
        for (TimeSlot slot : timeSlots) {
            if (formatted.length() > 0) {
                formatted.append(", ");
            }
            formatted.append(slot.getDay().toString().charAt(0)).append(" ").append(Util.formatTimeSlot(slot));
        }
        return formatted.toString();
    }

    /**
     * Views the list of students enrolled in the course section.
     *
     * @return {@code true} if there are students enrolled, {@code false} otherwise
     */
    public boolean viewEnrolledStudents() {
        if (this.enrolledStudents.isEmpty()) {
            System.out.println("There are no students enrolled in this section");
            return false;
        }
        int maxEnrolledStudentsNameWidth = Util.getMaxEnrolledStudentNameWidth(this);
        int maxEnrolledEmailWidth = Util.getMaxEnrolledStudentEmailWidth(this);
        int[] stringWidths = new int[] { maxEnrolledStudentsNameWidth, 9, maxEnrolledEmailWidth };
        System.out.println(Util.centerAlign(this.getCourse().getId() + "-" + this.sectionId + " Roster",
                (maxEnrolledStudentsNameWidth + 2) + 9 + (maxEnrolledEmailWidth + 2)));
        String rowFormat = "| %-" + maxEnrolledStudentsNameWidth + "s | %-9s | %-" + maxEnrolledEmailWidth + "s |\n";
        Util.createTableSeperator(stringWidths);
        System.out.printf(rowFormat, "Name", "Id", "Email");
        Util.createTableSeperator(stringWidths);
        for (Student student : this.enrolledStudents) {
            System.out.printf(rowFormat, student.getName(), student.getId(), student.getEmail());
            Util.createTableSeperator(stringWidths);
        }
        return true;
    }

    /**
     * Returns the CRN (Course Reference Number) of the course section.
     *
     * @return the CRN of the course section
     */
    public String getCRN() {
        return crn;
    }
}
