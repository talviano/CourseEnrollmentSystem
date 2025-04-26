/**
 * The InstructorPage class represents the user interface for instructors.
 * It provides functionalities for instructors to manage their assigned
 * courses, view enrolled students, and perform other instructor-specific tasks.
 *
 * This class is part of the UI layer and interacts with the underlying
 * system to fetch and display data relevant to instructors.
 *
 * Responsibilities:
 * - Viewing assigned course sections.
 * - Viewing the roster of students enrolled in a specific section.
 *
 * Usage:
 * InstructorPage instructorPage = new InstructorPage(instructor, enrollmentSystem);
 * instructorPage.display();
 *
 * @version Apr 19, 2025
 */
package ui;

import java.util.List;

import model.CourseSection;
import model.Instructor;
import system.EnrollmentSystem;

public class InstructorPage extends Page {
    /**
     * The instructor associated with this page.
     */
    private Instructor instructor;

    /**
     * The enrollment system used to manage courses and sections.
     */
    private EnrollmentSystem enrollmentSystem;

    /**
     * Constructs an InstructorPage with the specified instructor and enrollment system.
     *
     * @param instructor the instructor associated with this page
     * @param enrollmentSystem the enrollment system to interact with
     */
    public InstructorPage(Instructor instructor, EnrollmentSystem enrollmentSystem) {
        super(instructor);
        this.instructor = instructor;
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
                instructor.viewAssignedSections();
                break;
            case 2:
                rosterView();
                break;
            case 3:
                break;
        }
    }

    /**
     * Returns the menu options specific to the instructor page.
     *
     * @return an array of menu option strings
     */
    @Override
    public List<String> getMenuOptions() {
        return List.of("View assigned sections", "View roster for section", "Log out");
    }

    /**
     * Returns the title of the menu for the instructor page.
     *
     * @return the menu title
     */
    @Override
    public String getMenuTitle() {
        return "Instructor Actions";
    }

    /**
     * Displays the roster of students enrolled in a specific course section.
     * Prompts the instructor to enter the CRN of the section and displays the roster.
     */
    public void rosterView() {
        System.out.print("Enter CRN for section: ");
        String crn = input.nextLine();
        CourseSection section = enrollmentSystem.findCourseByCRN(crn);
        instructor.viewSectionRoster(section);
    }
}
