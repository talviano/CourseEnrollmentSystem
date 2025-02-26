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

    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

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
