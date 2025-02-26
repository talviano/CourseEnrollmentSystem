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
    private static int lastAssignedId = 801999999;
    private List<CourseSection> assignedCourses;

    /**
     * Constructs an Instructor with specified details.
     *
     * @param name the name of the instructor
     * @param email the email of the instructor
     * @param password the password of the instructor
     */
    public Instructor(String name, String email, String password) {
        super(name, String.valueOf(++lastAssignedId), email, password);
        this.assignedCourses = new ArrayList<>();
    }

    /**
     * Assigns a course to the instructor.
     *
     * @param course the course to assign
     * @return true if the course is assigned successfully, false otherwise
     */
    public boolean assignCourse(Course course) {
        // TODO: Implement the logic to assign a course to the instructor
        return false;
    }

    /**
     * Returns the list of courses assigned to the instructor as a string.
     *
     * @return the list of assigned courses
     */
    public String viewAssignedCourses() {
        // TODO: Implement the logic to return the list of assigned courses as a string
        return "";
    }
}
