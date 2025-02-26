/**
 * Represents a course that students can enroll in.
 * This class provides methods to check prerequisites and manage course sections.
 *
 * @version Feb 22, 2025
 */
package main;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String id;
    private String name;
    private String description;
    private int credits;
    private List<CourseSection> sections;

    /**
     * Constructs a Course with specified details.
     *
     * @param id the course ID
     * @param name the course name
     * @param description the course description
     * @param credits the number of credits for the course
     */
    public Course(String id, String name, String description, int credits) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.credits = credits;
        this.sections = new ArrayList<>();
    }

    /**
     * Checks if a student meets the prerequisites for the course.
     *
     * @param student the student to check
     * @return true if the student meets the prerequisites, false otherwise
     */
    public boolean checkPrerequisites(Student student) {
        return false;
    }

    /**
     * Returns the ID of the course.
     *
     * @return the course ID
     */
    public String getId() {
        return id;
    }
    
    /**
     * Returns the name of the course.
     *
     * @return the course name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the number of credits for the course.
     *
     * @return the number of credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Adds a section to the course.
     *
     * @param section the course section to add
     * @return true if the section is added successfully, false otherwise
     */
    public boolean addSection(CourseSection section) {
        for (CourseSection s : sections) {
            if (s.getSectionId().equals(section.getSectionId())) {
                return false;
            }
        }
        this.sections.add(section);
        return true;
    }
}
