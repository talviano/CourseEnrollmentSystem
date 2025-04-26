/**
 * The StudentPage class represents the user interface for students.
 * It provides functionalities for students to register for courses,
 * view their enrolled courses, and perform other student-specific tasks.
 *
 * This class is part of the UI layer and interacts with the underlying
 * system to fetch and display data relevant to students.
 *
 * Responsibilities:
 * - Displaying available courses for enrollment.
 * - Allowing students to enroll in or drop courses.
 * - Viewing the student's current schedule.
 *
 * Usage:
 * StudentPage studentPage = new StudentPage(student, enrollmentSystem);
 * studentPage.display();
 *
 * @version Apr 19, 2025
 */
package ui.student;

import java.util.List;

import model.Student;
import system.EnrollmentSystem;
import ui.Page;

public class StudentPage extends Page {
    /**
     * The student associated with this page.
     */
    private Student student;

    /**
     * The enrollment system used to manage courses and sections.
     */
    private EnrollmentSystem enrollmentSystem;

    /**
     * Constructs a StudentPage with the specified student and enrollment system.
     *
     * @param student the student associated with this page
     * @param enrollmentSystem the enrollment system to interact with
     */
    public StudentPage(Student student, EnrollmentSystem enrollmentSystem) {
        super(student);
        this.student = student;
        this.enrollmentSystem = enrollmentSystem;
    }

    /**
     * Handles the action corresponding to the user's menu choice.
     * 
     * @param choice the user's menu choice
     */
    @Override
    public void handleAction(int choice) {
        switch (choice) {
            case 1:
                enrollmentSystem.viewAllSections();
                break;
            case 2:
                student.viewSchedule();
                break;
            case 3:
                EnrollmentPage enrollmentPage = new EnrollmentPage(student, enrollmentSystem);
                enrollmentPage.display();
                break;
            case 4:
                DropPage dropPage = new DropPage(student, enrollmentSystem);
                dropPage.display();
                break;
            case 5:
                break;
        }
    }

    /**
     * Returns the menu options specific to the student page.
     *
     * @return an array of menu option strings
     */
    @Override
    public List<String> getMenuOptions() {
        return List.of("View available courses", "View schedule", "Enroll in a course", "Drop a course",
                "Logout");
    }

    /**
     * Returns the title of the menu for the student page.
     *
     * @return the menu title
     */
    @Override
    public String getMenuTitle() {
        return "Student Actions";
    }
}
