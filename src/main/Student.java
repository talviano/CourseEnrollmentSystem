/**
 * Represents a student enrolled in courses.
 * This class provides methods to enroll in, drop, and view courses.
 *
 * @version Feb 22, 2025
 */
package main;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private static int lastAssignedId = 800999999;
    private List<CourseSection> enrolledCourses;
    private boolean advisingHold;

    /**
     * Constructs a Student with specified details.
     *
     * @param name the name of the student
     * @param email the email of the student
     * @param password the password of the student
     * @param advisingHold the advising hold status of the student
     */
    public Student(String name, String email, String password, boolean advisingHold) {
        super(name, String.valueOf(++lastAssignedId), email, password);
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
     * Attempts to enroll the student in a course section.
     * 
     * Enrollment is denied if the student has an advising hold, is already enrolled 
     * in another section of the same course, or if there is a time conflict with an 
     * existing course.
     *
     * @param course the course section to enroll in
     * @return {@code true} if enrollment is successful, {@code false} if:
     *         <ul>
     *             <li>The student has an advising hold</li>
     *             <li>The student is already enrolled in another section of the same course</li>
     *             <li>There is a time conflict with an existing enrolled course</li>
     *         </ul>
     */
    public boolean enroll(CourseSection course) {
        if (advisingHold) {
            System.out.println("There is currently an advising hold for: " + this.getName());
            return false;
        }
        
        for (CourseSection section : enrolledCourses) {
            if (course.getCourse() == section.getCourse()) {
                System.out.println(this.getName() + "is already registered for: " + course.getCourse().getId());
                return false;
            }
            for (TimeSlot existingSlot : section.getTimeSlots()) {
                for (TimeSlot newSlot : course.getTimeSlots()) {
                    if (existingSlot.conflictsWith(newSlot)) {
                        System.out.println("Cannot register due to time conflict.");
                        return false;
                    }
                }

            }
        }
        return course.enrollStudent(this);
    }

    /**
     * Drops the student from a course.
     *
     * Dropping is unsuccessful if the student is not enrolled in the course.
     *
     * @param course the course to drop
     * @return {@code true} if dropping is successful, {@code false} otherwise
     */
    public boolean drop(CourseSection course) {
        return course.dropStudent(this);
    }

    /**
     * Prints the student's schedule to the console in a table format.
     */
    public void viewSchedule() {
        System.out.println(" ".repeat((39 - "Your Schedule".length()) / 2) + "Your Schedule");
        Util.createSeperator(39);
        System.out.print("\n");
        for (int i = 1; i <= 5; i++) {
            printClassesForDay(DayOfWeek.of(i));
        }
        
    }
    
    /**
     * Prints all of the student's classes for a specific day of the week in a table format
     *
     * @param day the day of the week
     */
    public void printClassesForDay(DayOfWeek day) {
        System.out.println(" ".repeat((39 - day.toString().length()) / 2) + day);
        Util.createTableSeperator(39);
        for (CourseSection course : enrolledCourses) {
            for (TimeSlot slot : course.getTimeSlots()) {
                if (slot.getDay().equals(day)) {
                    System.out.println("|  " + course.getCourse().getId() + "  |  "
                            + Util.formatTimeSlot(slot) + "  |");
                }
            }
            
        }
        Util.createTableSeperator(39);
        System.out.print("\n");
    }

    /**
     * Returns the list of courses the student is enrolled in.
     *
     * @return the list of enrolled courses
     */
    public List<CourseSection> getEnrolledCourses() {
        return enrolledCourses;
    }

}
