/**
 * Manages the enrollment system for courses, students, and instructors.
 * This class provides methods to add and remove courses, register students and instructors, and enroll students in course sections.
 *
 * @version Feb 23, 2025
 */
package system;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Course;
import model.CourseSection;
import model.Instructor;
import model.Student;
import model.TimeSlot;
import util.Util;

public class EnrollmentSystem {
    private List<Course> courses;
    private List<Student> students;
    private List<Instructor> instructors;
    private static int crn = 10000;

    /**
     * Constructs an EnrollmentSystem with empty lists for courses, students, and instructors.
     */
    public EnrollmentSystem() {
        this.courses = new ArrayList<>();
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
        if (courses.contains(course)) {
            System.out.println("Course already exists");
            return false;
        }
        for (Course c : courses) {
            if (c.getId().equals(course.getId())) {
                System.out.println("Course Id already exists");
                return false;
            }
            if (c.getName().equals(course.getName())) {
                System.out.println("Course name already exists");
                return false;
            }
        }
        courses.add(course);
        return true;
    }

    /**
     * Removes a course from the course catalog.
     *
     * @param course the course to remove
     * @return true if the course is removed successfully, false otherwise
     */
    public boolean removeCourse(Course course) {
        if (!courses.contains(course)) {
            System.out.println("Course does not exist");
        }
        System.out.println("Course removed");
        return true;

    }
    
    /**
     * Creates a course by taking input from the user.
     *
     * @param input the Scanner object to read user input
     */
    public void createCourse(Scanner input) {
        System.out.print("Id (e.g. MATH 1241): ");
        String id = input.nextLine();
        System.out.print("Name (e.g. Calculus 1): ");
        String name = input.nextLine();
        System.out.print("Description: ");
        String description = input.nextLine();
        System.out.print("Credits: ");
        int credits = input.nextInt();
        addCourse(new Course(id, name, description, credits));
        for (Course course : this.courses) {
            System.out.print(course.getId());
        }
        System.out.println("Course created.");
        input.nextLine();
    }

    /**
     * Creates a course section by taking input from the user.
     *
     * @param input the Scanner object to read user input
     */
    public void createCourseSection(Scanner input) {
        if (courses.isEmpty()) {
            System.out.println("You must create a course first.");
            return;
        }
    
        Course course = null;
        while (course == null) {
            System.out.print("Course ID (ABCD 1234): ");
            String id = input.nextLine().trim();
            course = findCourseById(id);

            if (!id.matches("^[A-Z]{4} \\d{4}$")) {
                System.out.println("Invalid format. Course ID must be in the format ABCD 1234 (e.g., MATH 1241).");
                continue;
            }

            if (course == null) {
                System.out.println("Invalid course ID. Please enter a valid course ID.");
            }
        }
    
        int maxCapacity = 0;
        while (true) {
            System.out.print("Max capacity: ");
            try {
                maxCapacity = Integer.parseInt(input.nextLine().trim());
                if (maxCapacity <= 0) {
                    System.out.println("Capacity must be a positive number.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    
        List<TimeSlot> timeSlots = new ArrayList<>();
        System.out.println("Add Time Slots:");
    
        while (true) {
            System.out.print("Day (M/T/W/Th/F): ");
            String dayInput = input.nextLine().trim();
            DayOfWeek day = parseDayOfWeek(dayInput);
    
            if (day == null) {
                System.out.println("Invalid day. Please enter M, T, W, Th, or F.");
                continue;
            }
    
            LocalTime startTime = null;
            LocalTime endTime = null;
    
            while (true) {
                try {
                    System.out.print("Start time (hh:mm AM/PM): ");
                    startTime = parseTime(input.nextLine().trim());
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid time format. Please use hh:mm AM/PM.");
                }
            }
    
            while (true) {
                try {
                    System.out.print("End time (hh:mm AM/PM): ");
                    endTime = parseTime(input.nextLine().trim());
    
                    if (endTime.isBefore(startTime) || endTime.equals(startTime)) {
                        System.out.println("End time must be after start time.");
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid time format. Please use hh:mm AM/PM.");
                }
            }
    
            System.out.println("Adding time slot...");
            timeSlots.add(new TimeSlot(startTime, endTime, day));
    
            System.out.print("Do you need to add another slot? (y/n): ");
            if (!Util.yesNoToBoolean(input.nextLine().trim())) {
                break;
            }
        }
    
        course.createSection(timeSlots, maxCapacity);
        System.out.println("Course section created successfully!");
    }
    
    /**
     * Parses a day of the week from a string input.
     *
     * @param input the string input representing the day of the week
     * @return the corresponding DayOfWeek object, or null if the input is invalid
     */
    private DayOfWeek parseDayOfWeek(String input) {
        switch (input.trim().toUpperCase()) {
            case "M":
                return DayOfWeek.MONDAY;
            case "T":
                return DayOfWeek.TUESDAY;
            case "W":
                return DayOfWeek.WEDNESDAY;
            case "TH":
                return DayOfWeek.THURSDAY;
            case "F":
                return DayOfWeek.FRIDAY;
            default:
                System.out.println("Invalid day. Please enter M, T, W, Th, or F.\n");
        }
        return null;
    }

    /**
     * Parses a time from a string input.
     *
     * @param input the string input representing the time
     * @return the corresponding LocalTime object
     */
    private LocalTime parseTime(String input) {
        input = input.trim().toUpperCase();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        LocalTime time = LocalTime.parse(input, formatter);
        return time;

    }

    /**
     * Finds a course by its ID.
     *
     * @param id the ID of the course to find
     * @return the Course object if found, or null if not found
     */
    public Course findCourseById(String id) {
        for (Course course : this.courses) {
            if (course.getId().equals(id)) {
                return course;
            }
        }
        return null;
    }

    /**
     * Gets the list of courses.
     *
     * @return the list of courses
     */
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * Displays the available courses in a formatted table.
     */
    public void displayAvailableCourses() {
        int[] stringWidths = { 9, 5, 7, Util.getMaxTimeSlotWidth(courses) };
        String rowFormat = "| %-9s | %-5s | %-7s | %-" + Util.getMaxTimeSlotWidth(courses) + "s |\n";
        Util.createTableSeperator(stringWidths);
        System.out.printf(rowFormat, "Course", "Sec #", "Size", "Time Slots");
        Util.createTableSeperator(stringWidths);
        for (Course course : courses) {
            for (CourseSection section : course.getSections()) {
                System.out.printf(rowFormat,
                        course.getId(), section.getSectionId(), section.getSize(),
                        section.getTimeSlotsFormatted());
                Util.createTableSeperator(stringWidths);
            }
        }
    }


}




