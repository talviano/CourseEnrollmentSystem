/**
 * Represents an instructor in the system.
 * This class provides methods for assigning courses and viewing assigned courses.
 *
 * @version Feb 22, 2025
 */
package main;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends User {
    private List<CourseSection> assignedCourses;

    /**
     * Constructs an Instructor with specified details.
     *
     * @param name the name of the instructor
     * @param id the ID of the instructor
     * @param email the email of the instructor
     * @param password the password of the instructor
     * @param assignedCourses the list of courses assigned to the instructor
     */
    public Instructor(String name, String id, String email, String password, List<Course> assignedCourses) {
        super(name, id, email, password);
        this.assignedCourses = new ArrayList<>();
    }

    /**
     * Assigns a course to the instructor.
     *
     * @param course the course to assign
     * @return true if the course is assigned successfully, false otherwise
     */
    public boolean assignCourse(Course course) {
        return false;
    }

    /**
     * Returns the list of courses assigned to the instructor as a string.
     *
     * @return the list of assigned courses
     */
    public String viewAssignedCourses() {
        return "";
    }
}
