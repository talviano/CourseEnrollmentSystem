/**
 * Manages the enrollment system for courses, students, and instructors.
 * This class provides methods to add and remove courses, register students and instructors,
 * and enroll students in course sections. It also includes functionality for managing
 * course sections and displaying course-related information.
 *
 * Responsibilities:
 * - Managing courses and course sections.
 * - Enrolling and dropping students from course sections.
 * - Assigning and unassigning instructors to course sections.
 * - Displaying available courses and course sections in a formatted table.
 *
 * @version Apr 19, 2025
 */
package system;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import model.Course;
import model.CourseSection;
import model.Student;
import model.TimeSlot;
import util.TablePrinter;
import util.ColumnExtractor;
import util.Util;

public class EnrollmentSystem {
    private List<Course> courses;
    private static int crn = 10000;

    /**
     * Constructs an EnrollmentSystem with an empty list of courses.
     */
    public EnrollmentSystem() {
        this.courses = new ArrayList<>();
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
                System.out.println("Course ID already exists");
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
     * This method also removes all associated course sections and unenrolls students.
     *
     * @param course the course to remove
     * @return true if the course is removed successfully, false otherwise
     */
    public boolean removeCourse(Course course) {
        if (!courses.contains(course)) {
            System.out.println("Course does not exist");
            return false;
        }

        System.out.println("Deleting course: " + course.getId() + " - " + course.getName());

        for (CourseSection section : course.getSections()) {
            for (Student student : section.getEnrolledStudents()) {
                section.dropStudent(student);
            }

            if (section.getInstructor() != null) {
                section.getInstructor().removeCourseAssignment(section);
            }
        }

        courses.remove(course);
        System.out.println(course.getId() + " and its sections deleted successfully");
        return true;
    }

    /**
     * Creates a course by taking input from the user.
     *
     * @param input the Scanner object to read user input
     */
    public void createCourse(Scanner input) {
        System.out.print("Id (e.g., MATH 1241): ");
        String id = input.nextLine();
        System.out.print("Name (e.g., Calculus 1): ");
        String name = input.nextLine();
        System.out.print("Description: ");
        String description = input.nextLine();
        System.out.print("Credits: ");
        int credits = input.nextInt();
        addCourse(new Course(id, name, description, credits));
        System.out.println("Course created.");
        input.nextLine();
    }

    /**
     * Creates a course section by taking input from the user.
     * This method prompts the user for course ID, maximum capacity, and time slots.
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
            String id = input.nextLine().strip();
            course = getCourseById(id);

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
                maxCapacity = Integer.parseInt(input.nextLine().strip());
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
            String dayInput = input.nextLine().strip();
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
                    startTime = parseTime(input.nextLine().strip());
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid time format. Please use hh:mm AM/PM.");
                }
            }

            while (true) {
                try {
                    System.out.print("End time (hh:mm AM/PM): ");
                    endTime = parseTime(input.nextLine().strip());

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
            try {
                if (!Util.yesNoToBoolean(input.nextLine().strip())) {
                    break;
                }
            } catch (InputMismatchException e) {
                continue;
            }
        }

        course.createSection(timeSlots, maxCapacity);
        System.out.println("Course section created successfully!");
    }

    /**
     * Displays all courses in a formatted table.
     */
    public void viewAllCourses() {
        List<String> headers = List.of("Id", "Name", "Credits", "Description");

        List<ColumnExtractor<Course>> extractors = List.of(
                Course::getId,
                Course::getName,
                course -> String.valueOf(course.getCredits()),
                Course::getDescription);

        TablePrinter<Course> printer = new TablePrinter<>(headers, extractors, courses);
        printer.printTable();
    }

    /**
     * Displays all course sections in a formatted table.
     */
    public void viewAllSections() {
        List<String> headers = List.of("Id", "Sect", "CRN", "Name", "Credits", "Meeting Times", "Status", "Instructor");

        List<ColumnExtractor<CourseSection>> extractors = List.of(
            section -> section.getCourse().getId(),
            CourseSection::getSectionId,
            CourseSection::getCRN,
            section -> section.getCourse().getName(),
            section -> String.valueOf(section.getCourse().getCredits()),
            CourseSection::getTimeSlotsFormatted,
            CourseSection::getSize,
            section -> section.getInstructor().getName()         
        );

        List<CourseSection> sections = new ArrayList<>();

        for (Course course : courses) {
            for (CourseSection section : course.getSections()) {
                sections.add(section);
            }
        }

        TablePrinter<CourseSection> printer = new TablePrinter<>(headers, extractors, sections);
        printer.printTable();


    }

    /**
     * Finds a course section by its CRN.
     *
     * @param crn the CRN of the course section
     * @return the CourseSection object if found, or null if not found
     */
    public CourseSection findCourseByCRN(String crn) {
        for (Course course : courses) {
            for (CourseSection section : course.getSections()) {
                if (section.getCRN().equals(crn)) {
                    return section;
                }
            }
        }
        return null;
    }

    /**
     * Finds a course by its ID.
     *
     * @param id the ID of the course
     * @return the Course object if found, or null if not found
     */
    public Course getCourseById(String id) {
        for (Course course : courses) {
            if (course.getId().equals(id)) {
                return course;
            }
        }
        return null;
    }

    /**
     * Parses a day of the week from a string input.
     *
     * @param input the string input representing the day of the week
     * @return the corresponding DayOfWeek object, or null if the input is invalid
     */
    private DayOfWeek parseDayOfWeek(String input) {
        switch (input.strip().toUpperCase()) {
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
        input = input.strip().toUpperCase();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        return LocalTime.parse(input, formatter);
    }

    /**
     * Returns the list of courses in the system.
     *
     * @return the list of courses
     */
    public List<Course> getCourses() {
        return courses;
    }
}




