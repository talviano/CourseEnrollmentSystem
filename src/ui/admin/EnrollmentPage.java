package ui.admin;

import java.util.ArrayList;
import java.util.List;

import model.Admin;
import model.CourseSection;
import model.Instructor;
import model.Student;
import model.User;
import system.AccountManager;
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
    private Admin admin;
    private EnrollmentSystem enrollmentSystem;
    private AccountManager accountManager;
    

    /**
     * Constructs an EnrollmentPage with the specified admin, enrollment system, and account manager.
     *
     * @param admin the admin currently logged in
     * @param enrollmentSystem the enrollment system to interact with
     * @param accountManager the account manager to interact with
     */
    public EnrollmentPage(Admin admin, EnrollmentSystem enrollmentSystem, AccountManager accountManager) {
        super(admin);
        this.admin = admin;
        this.enrollmentSystem = enrollmentSystem;
        this.accountManager = accountManager;
    }

    /**
     * Returns the menu options specific to the EnrollmentPage.
     *
     * @return a list of menu option strings
     */
    @Override
    public List<String> getMenuOptions() {
        List<String> menuOptions = new ArrayList<>();
        menuOptions.addAll(List.of("Enroll student in a section", "Drop student from section",
                "Assign instructor to section", "Remove instructor from section"));
        menuOptions.add("Return to Admin Menu");
        return menuOptions;
    }

    /**
    * Displays message if user if logging out
    */
    @Override
    public void handleLogout() {
        //override default use to do nothing
    };

    /**
     * Returns the title of the menu for the EnrollmentPage.
     *
     * @return the menu title
     */
    @Override
    public String getMenuTitle() {
        return "Manage Enrollment Actions";
    }

    /**
     * Handles the action based on to the user's menu choice.
     *
     * @param choice the user's menu choice
     */
    @Override
    public void handleAction(int choice) {
        switch (getSelectedOption(choice)) {
            case "Enroll student in a section":
                enrollStudentView();
                break;
            case "Drop student from section":
                dropStudentView();
                break;
            case "Assign instructor to section":
                assignInstructorView();
                break;
            case "Remove instructor from section":
                unassignInstructorView();
                break;
            case "Return to Admin Menu":
                return;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

    /**
     * Displays the view for enrolling a student in a course section.
     */
    private void enrollStudentView() {
        String crn = promptCRN();
        CourseSection section = enrollmentSystem.findCourseByCRN(crn);
        if (section == null) {
            System.out.println("Invalid CRN. No section found.\n");
            return;
        }
        String value = promptUserId();
        User user = accountManager.getUserByIdOrEmail(value);
        if (user instanceof Student student) {
            boolean success = student.enroll(section);
            if (success) {
                System.out.println("Enrolled " + student.getName() + " in " + section.getCourse().getId()
                        + " Section " + section.getSectionId());
            } else {
                System.out.println("Enrollment failed.");
            }
        } else {
            System.out.println("No student found with that ID or email.");
        }
    }

    /**
     * Displays the view for dropping a student from a course section.
     */
    private void dropStudentView() {
        String value = promptCRN();
        CourseSection section = enrollmentSystem.findCourseByCRN(value);
        if (section == null) {
            System.out.println("Invalid CRN. No section found.\n");
            return;
        }
        String id = promptUserId();
        User user = accountManager.getUserByIdOrEmail(id);
        if (user instanceof Student student) {
            boolean success = student.drop(section);
            if (success) {
                System.out.println("Dropped " + student.getName() + " from " + section.getCourse().getId() + " Section "
                        + section.getSectionId());
            } else {
                System.out.println("Drop failed. Student may not be enrolled in this section.");
            }
        } else {
            System.out.println("No student found with that ID or email.");
        }
    }

    /**
     * Displays the view for assigning an instructor to a course section.
     */
    public void assignInstructorView() {
        String crn = promptCRN();
        CourseSection section = enrollmentSystem.findCourseByCRN(crn);
        String value = promptUserId();
        User user = accountManager.getUserByIdOrEmail(value);
        
        if (user instanceof Instructor instructor) {
            section.assignInstructor(instructor);
            System.out.println("Assigned " + instructor.getName() + " to " + section.getCourse().getId() + " Section "
                    + section.getSectionId());
        } else {
            System.out.println("No instructor found with that ID or email.");
        }
    }

    /**
     * Displays the view for unassigning an instructor from a course section.
     */
    public void unassignInstructorView() {
        String crn = promptCRN();
        CourseSection section = enrollmentSystem.findCourseByCRN(crn);
        String value = promptUserId();
        User user = accountManager.getUserByIdOrEmail(value);
        if (user instanceof Instructor instructor) {
            instructor.removeCourseAssignment(section);
            System.out.println("Unassigned " + instructor.getName() + " from " + section.getCourse().getId() + " Section " + section.getSectionId());
        } else {
            System.out.println("No instructor found with that ID or email.");
        }
    }
}
