package ui.admin;

import java.util.ArrayList;
import java.util.List;

import model.Admin;
import model.CourseSection;
import model.Permissions;
import system.EnrollmentSystem;
import ui.Page;

/**
 * The CourseSectionsPage class provides a user interface for managing course sections.
 * It allows an admin to create new sections, delete existing sections, and view all sections.
 *
 *
 * @version Apr 25, 2025
 */
public class CourseSectionsPage extends Page {
    private Admin admin;
    private EnrollmentSystem enrollmentSystem;

    /**
     * Constructs a CourseSectionsPage with the specified admin and enrollment system.
     *
     * @param admin the admin currently logged in
     * @param enrollmentSystem the enrollment system to interact with
     */
    public CourseSectionsPage(Admin admin, EnrollmentSystem enrollmentSystem) {
        super(admin);
        this.admin = admin;
        this.enrollmentSystem = enrollmentSystem;
    }

    /**
     * Returns the menu options specific to the CourseSectionsPage.
     *
     * @return a list of menu option strings
     */
    @Override
    public List<String> getMenuOptions() {
        List<String> menuOptions = new ArrayList<>();
        if (checkPermission(Permissions.COURSE_MANAGEMENT)) {
            menuOptions.addAll(List.of("Create new section", "Delete existing section"));
        }

        menuOptions.add("View all sections");
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
     * Returns the title of the menu for the CourseSectionsPage.
     *
     * @return the menu title
     */
    @Override
    public String getMenuTitle() {
        return "Manage Course Sections Actions";
    }

    /**
     * Handles the action based on to the user's menu choice.
     *
     * @param choice the user's menu choice
     */
    @Override
    public void handleAction(int choice) {
        switch (getSelectedOption(choice)) {
            case "Create new section":
                enrollmentSystem.createCourseSection();
                break;
            case "View all sections":
                enrollmentSystem.viewAllSections();
                break;
            case "Delete existing section":
                String crn = promptCRN();
                CourseSection section = enrollmentSystem.findCourseByCRN(crn);
                section.getCourse().removeCourseSection(section);
                break;
            case "Return to Admin Menu":
                return;
            default:
                System.out.println("Invalid option");
                break;
        }
    }
}
