/**
 * Represents a course that students can enroll in.
 * This class provides methods to check prerequisites and manage course sections.
 *
 * @version Feb 22, 2025
 */
package model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String id;
    private String name;
    private String description;
    private int credits;
    private List<CourseSection> sections;
    private String crn;
    private static int lastAssignedCrn = 10000;
    private static int sectionCount = 0;

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
        this.crn = String.valueOf(++lastAssignedCrn);
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
     * Creates and adds a section to the course.
     *
     * @param timeSlots the time slots of the course section
     * @param maxCapacity the maximum capacity of the course section
     * @return the created course section
     */
    public CourseSection createSection(List<TimeSlot> timeSlots, int maxCapacity) {
        String sectionId = String.format("%03d", ++sectionCount);
        CourseSection section = new CourseSection(this, sectionId, timeSlots, maxCapacity);
        sections.add(section);
        return section;
    }

    /**
     * Returns the list of sections for the course.
     *
     * @return the list of sections
     */
    public List<CourseSection> getSections() {
        return sections;
    }

    /**
     * Returns the total number of sections created.
     *
     * @return the total number of sections
     */
    public int getSectionCount() {
        return sectionCount;
    }
}
