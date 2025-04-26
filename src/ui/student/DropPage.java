/**
 * The DropPage class provides a user interface for students to drop courses.
 * It allows a student to drop a course section by entering its CRN (Course Reference Number).
 *
 * Responsibilities:
 * - Dropping a course section by CRN.
 * - Returning to the student menu.
 *
 * Usage:
 * DropPage dropPage = new DropPage(student, enrollmentSystem);
 * dropPage.display();
 *
 * @version Apr 24, 2025
 */
package ui.student;

import java.util.List;

import model.CourseSection;
import model.Student;
import system.EnrollmentSystem;
import ui.Page;

public class DropPage extends Page {
    private Student student;
    private EnrollmentSystem enrollmentSystem;

    /**
     * Constructs a DropPage with the specified student and enrollment system.
     *
     * @param student the student currently logged in
     * @param enrollmentSystem the enrollment system to interact with
     */
    public DropPage(Student student, EnrollmentSystem enrollmentSystem) {
        super(student);
        this.student = student;
        this.enrollmentSystem = enrollmentSystem;
    }

    /**
     * Returns the menu options specific to the DropPage.
     *
     * @return a list of menu option strings
     */
    @Override
    protected List<String> getMenuOptions() {
        return List.of("Drop By CRN: ", "Return to Student Menu");
    }

    /**
     * Returns the title of the menu for the DropPage.
     *
     * @return the menu title
     */
    @Override
    protected String getMenuTitle() {
        return "Drop Actions";
    }

    /**
     * Handles the action corresponding to the user's menu choice.
     *
     * @param choice the user's menu choice
     */
    @Override
    protected void handleAction(int choice) {
        switch (choice) {
            case 1:
                dropByCRN();
                break;
            case 2:
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    /**
     * Allows the student to drop a course section by entering its CRN.
     */
    private void dropByCRN() {
        while (true) {
            String crn = promptCRN();
            CourseSection section = enrollmentSystem.findCourseByCRN(crn);
            if (section == null) {
                System.out.println("No course section found with CRN: " + crn);
                continue;
            } else {
                boolean success = student.drop(section);
                if (success) {
                    System.out.println("Successfully dropped " + section.getCourse().getId() + " Section "
                            + section.getSectionId());
                } else {
                    System.out.println("Drop failed.");
                    break;
                }
            }
            break;
        }
    }
}
