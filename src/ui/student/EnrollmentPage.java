package ui.student;

import java.util.List;

import model.CourseSection;
import model.Student;
import system.EnrollmentSystem;
import ui.Page;

/**
 * The EnrollmentPage class provides a user interface for managing enrollment actions.
 * It allows an admin to enroll students in course sections, drop students from sections,
 * assign instructors to sections, and unassign instructors from sections.
 *
 * @version Apr 25, 2025
 */
public class EnrollmentPage extends Page {
    private Student student;
    private EnrollmentSystem enrollmentSystem;

    /**
     * Constructs an EnrollmentPage with the specified student and enrollment system.
     *
     * @param student the student currently logged in
     * @param enrollmentSystem the enrollment system to interact with
     */
    public EnrollmentPage(Student student, EnrollmentSystem enrollmentSystem) {
        super(student);
        this.student = student;
        this.enrollmentSystem = enrollmentSystem;
    }

    /**
     * Returns the menu options specific to the EnrollmentPage.
     *
     * @return a list of menu option strings
     */
    @Override
    protected List<String> getMenuOptions() {
        return List.of("Enroll By CRN: ", "Return to Student Menu");
    }

    /**
     * Returns the title of the menu for the EnrollmentPage.
     *
     * @return the menu title
     */
    @Override
    protected String getMenuTitle() {
        return "Enrollment Actions";
    }

    /**
     * Handles the  action based on to the user's menu choice.
     *
     * @param choice the user's menu choice
     */
    @Override
    protected void handleAction(int choice) {
        switch (choice) {
            case 1:
                enrollByCRN();
                break;
            case 2:
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    /**
     * Allows the student to enroll in a course section by entering its CRN.
     */
    private void enrollByCRN() {
        String crn = promptCRN();
        CourseSection section = enrollmentSystem.findCourseByCRN(crn);

        if (section == null) {
            System.out.println("No course section found with CRN: " + crn);
        } else {
            boolean success = student.enroll(section);
            if (success) {
                System.out.println("Successfully enrolled in " + section.getCourse().getId() + " Section "
                        + section.getSectionId());
            } else {
                System.out.println("Enrollment failed.");
            }
        }
    }
    
    /**
    * Displays message if user if logging out
    */
    @Override
    public void handleLogout() {
        //override default use to do nothing
    };
}
