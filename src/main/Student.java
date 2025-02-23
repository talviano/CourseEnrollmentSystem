/**
 * Represents a student enrolled in courses.
 * This class provides methods to enroll in, drop, and view courses.
 *
 * @version Feb 22, 2025
 */
package main;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private List<CourseSection> enrolledCourses;
    private boolean advisingHold;

    /**
     * Constructs a Student with specified details.
     *
     * @param name the name of the student
     * @param id the ID of the student
     * @param email the email of the student
     * @param password the password of the student
     * @param advisingHold the advising hold status of the student
     */
    public Student(String name, String id, String email, String password, boolean advisingHold) {
        super(name, id, email, password);
        this.enrolledCourses = new ArrayList<>();
        this.advisingHold = advisingHold;
    }

    /**
     * Sets the advising hold status for the student.
     *
     * @param advisingHold the new advising hold status
     */
    public void setAdvisingHold(boolean advisingHold) {
        this.advisingHold = advisingHold;
    }

    /**
     * Enrolls the student in a course.
     *
     * @param course the course to enroll in
     * @return true if enrollment is successful, false otherwise
     */
    public boolean enroll(Course course) {
        return false;
    }

    /**
     * Drops the student from a course.
     *
     * @param course the course to drop
     * @return true if dropping is successful, false otherwise
     */
    public boolean drop(Course course) {
        return false;
    }

    /**
     * Returns the student's schedule as a string.
     *
     * @return the student's schedule
     */
    public String viewSchedule() {
        return "";
    }
}
