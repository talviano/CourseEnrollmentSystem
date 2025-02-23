/**
 * Manages the enrollment system for courses, students, and instructors.
 * This class provides methods to add and remove courses, register students and instructors, and enroll students in course sections.
 *
 * @version Feb 23, 2025
 */
package main;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentSystem {
    private List<Course> courseCatalog;
    private List<Student> students;
    private List<Instructor> instructors;

    /**
     * Constructs an EnrollmentSystem with empty lists for courses, students, and instructors.
     */
    public EnrollmentSystem() {
        this.courseCatalog = new ArrayList<>();
        this.students = new ArrayList<>();
        this.instructors = new ArrayList<>();
    }

    /**
     * Adds a course to the course catalog.
     *
     * @param course the course to add
     * @return true if the course is added successfully, false otherwise
     */
    public boolean addCourse(Course course) {
        return false;
    }

    /**
     * Removes a course from the course catalog.
     *
     * @param course the course to remove
     * @return true if the course is removed successfully, false otherwise
     */
    public boolean removeCourse(Course course) {
        return false;
    }

    /**
     * Registers a student in the system.
     *
     * @param student the student to register
     * @return true if the student is registered successfully, false otherwise
     */
    public boolean registerStudent(Student student) {
        return false;
    }

    /**
     * Registers an instructor in the system.
     *
     * @param instructor the instructor to register
     * @return true if the instructor is registered successfully, false otherwise
     */
    public boolean registerInstructor(Instructor instructor) {
        return false;
    }

}
  



