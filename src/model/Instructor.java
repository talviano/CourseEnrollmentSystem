package model;

import java.util.ArrayList;
import java.util.List;

import util.TablePrinter;
import util.ColumnExtractor;

/**
 * Represents an instructor in the system.
 * This class provides methods for assigning courses and viewing assigned courses.
 *
 * @version Feb 22, 2025
 */
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
        super(String.valueOf(++lastAssignedId), name, email, password);
        this.assignedCourses = new ArrayList<>();
    }

    /**
     * Assigns a course to the instructor.
     *
     * @param course the course to assign
     * @return true if the course is assigned successfully, false otherwise
     */
    public boolean assignCourse(CourseSection course) {
        if (assignedCourses.contains(course)) {
            System.out.println("Instructor already assigned to this course");
            return false;
        }
        assignedCourses.add(course);
        return true;
    }

    /**
     * Removes a course assignment from the instructor.
     *
     * @param section the course section to remove
     * @return true if the course assignment is removed successfully, false otherwise
     */
    public boolean removeCourseAssignment(CourseSection section) {
        if (this == section.getInstructor()) {
            section.assignInstructor(null);
            assignedCourses.remove(section);
            return true;
        }
        return false;
    }

    /**
     * Returns the list of courses assigned to the instructor.
     *
     * @return the list of assigned courses
     */
    public List<CourseSection> getAssignedCourses() {
        return assignedCourses;
    }

    /**
     * Displays the list of courses assigned to the instructor.
     */
    public void viewAssignedSections() {
        if (assignedCourses.isEmpty()) {
        System.out.println("No assigned courses");
        return;
        }

        List<String> headers = List.of("Course", "Sect", "CRN", "Size", "Time Slots");

        List<ColumnExtractor<CourseSection>> extractors = List.of(
            section -> section.getCourse().getId(),
            CourseSection::getCRN,       
            CourseSection::getSectionId,
            section -> section.getEnrolledCount() + "/" + section.getMaxCapacity(),
            CourseSection::getTimeSlotsFormatted
        );

        TablePrinter<CourseSection> printer = new TablePrinter<>(headers, extractors, assignedCourses);
        printer.printTable();
    }
    
    /**
     * Views the roster of a specific course section.
     *
     * @param section the course section to view the roster of
     * @return true if the roster is viewed successfully, false otherwise
     */
    public boolean viewSectionRoster(CourseSection section) {
        if (assignedCourses.contains(section)) {
            section.viewEnrolledStudents();
            return true;
        }
        System.out.println("Cannot view roster for section " + this.getName() + " is not assigned.");
        return false;
    }   
}
