/**
 * Represents an instructor in the system.
 * This class provides methods for assigning courses and viewing assigned courses.
 *
 * @version Feb 22, 2025
 */
package model;

import java.util.ArrayList;
import java.util.List;

import util.Util;

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

    public boolean removeCourseAssignment(CourseSection section) {
        if (this == section.getInstructor()) {
            section.assignInstructor(null);
            assignedCourses.remove(section);
            return true;
        }
        return false;
    }

    /**
     * Returns the list of courses assigned to the instructor as a string.
     *
     * @return the list of assigned courses
     */
    public List<CourseSection> getAssignedCourses() {
        return assignedCourses;
    }

    public void viewAssignedCourses() {
        int maxTimeSlotsWidth = Util.getMaxScheduleWidth(this);
        int[] stringWidths = new int[] { 9, 5, 4, Util.getMaxScheduleWidth(this) };
        if (assignedCourses.isEmpty()) {
            System.out.println("No assigned courses");
            return;
        }
        int count = 0;
        for (CourseSection section : assignedCourses) {
            if (count == 0) {
                Util.createTableSeperator(stringWidths);
                System.out.printf("| %-9s | %-5s | %-4s | %-" + maxTimeSlotsWidth + "s |\n",
                        "Course",
                        "Sect",
                        "Size",
                        "Time Slots");
                count++;
            }
            Util.createTableSeperator(stringWidths);
            System.out.printf("| %s | %s | %s | %-" + maxTimeSlotsWidth + "s |\n",
                    section.getCourse().getId(),
                    " " + section.getSectionId() + " ",
                    section.getEnrolledCount() + "/" + section.getMaxCapacity(),
                    section.getTimeSlotsFormatted());
        }
        Util.createTableSeperator(stringWidths);

    }
    
    public boolean viewSectionRoster(CourseSection section) {
        if (assignedCourses.contains(section)) {
            section.viewEnrolledStudents();
            return true;
        }
        System.out.println("Cannot view roster for section " + this.getName() + " is not assigned.");
        return false;
    }   
}
