/**
 * Represents a student enrolled in courses.
 * This class provides methods to enroll in, drop, and view courses.
 *
 * Responsibilities:
 * - Managing the student's enrolled courses.
 * - Handling enrollment and dropping of courses.
 * - Displaying the student's schedule and enrolled courses.
 *
 * @version Feb 22, 2025
 */
package model;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import util.ColumnExtractor;
import util.TablePrinter;
import util.Util;

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
        super(String.valueOf(++lastAssignedId), name, email, password);
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
     * @return {@code true} if enrollment is successful, {@code false} otherwise
     */
    public boolean enroll(CourseSection course) {
        if (advisingHold) {
            System.out.println("There is currently an advising hold for: " + this.getName());
            return false;
        }
        
        for (CourseSection section : enrolledCourses) {
            if (course.getCourse() == section.getCourse()) {
                System.out.println(this.getName() + " is already registered for: " + course.getCourse().getId());
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
     * Displays the student's schedule, organized by day of the week.
     */
    public void viewSchedule() {
        int maxCourseIdWidth = 0;
        int maxTimeSlotWidth = 0;

        for (CourseSection course : enrolledCourses) {
            String id = course.getCourse().getId();
            maxCourseIdWidth = Math.max(maxCourseIdWidth, id.length());
            for (TimeSlot slot : course.getTimeSlots()) {
                String formatted = Util.formatTimeSlot(slot);
                maxTimeSlotWidth = Math.max(maxTimeSlotWidth, formatted.length());
            }
        }

        if (maxCourseIdWidth == 0) {
            maxCourseIdWidth = 15; 
        } else {
            maxCourseIdWidth += 4; 
        }

        if (maxTimeSlotWidth == 0) {
            maxTimeSlotWidth = 20; 
        } else {
            maxTimeSlotWidth += 4; 
        }

        System.out.println(); 
        for (int i = 1; i <= 5; i++) {
            printClassesForDay(DayOfWeek.of(i), maxCourseIdWidth, maxTimeSlotWidth);
        }
    }

    /**
     * Displays the list of courses the student is enrolled in as a formatted table.
     */
    public void viewEnrolledCourses() {
        if (enrolledCourses.isEmpty()) {
            System.out.println("No assigned courses");
            return;
        }

        List<String> headers = List.of("Course", "Sect", "Size", "Time Slots");

        List<ColumnExtractor<CourseSection>> extractors = List.of(
            section -> section.getCourse().getId(),
            CourseSection::getSectionId,
            section -> section.getEnrolledCount() + "/" + section.getMaxCapacity(),
            CourseSection::getTimeSlotsFormatted
        );

        TablePrinter<CourseSection> printer = new TablePrinter<>(headers, extractors, enrolledCourses);
        printer.printTable();
    }

    /**
     * Prints the classes for a specific day of the week.
     *
     * @param day the day of the week
     * @param courseColWidth the width of the course column
     * @param timeColWidth the width of the time column
     */
    public void printClassesForDay(DayOfWeek day, int courseColWidth, int timeColWidth) {
        System.out.println(" ".repeat((courseColWidth + timeColWidth + 7 - day.toString().length()) / 2) + day);
        Util.createTableSeperator(new int[]{courseColWidth, timeColWidth});
        System.out.printf("| %-" + courseColWidth + "s | %-" + timeColWidth + "s |\n", "Course", "Time Slot");
        Util.createTableSeperator(new int[]{courseColWidth, timeColWidth});
    
        for (CourseSection course : enrolledCourses) {
            for (TimeSlot slot : course.getTimeSlots()) {
                if (slot.getDay().equals(day)) {
                    System.out.printf("| %-" + courseColWidth + "s | %-" + timeColWidth + "s |\n",
                            course.getCourse().getId(),
                            Util.formatTimeSlot(slot));
                }
            }
        }
        Util.createTableSeperator(new int[]{courseColWidth, timeColWidth});
        System.out.println();
    }
    

    /**
     * Returns the list of courses the student is enrolled in.
     *
     * @return the list of enrolled courses
     */
    public List<CourseSection> getEnrolledCourses() {
        return enrolledCourses;
    }

    /**
     * Checks if the student has an advising hold.
     *
     * @return {@code true} if the student has an advising hold, {@code false} otherwise
     */
    public boolean hasAdvisingHold() {
        return advisingHold;
    }
}
