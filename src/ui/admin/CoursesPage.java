package ui.admin;

import java.util.ArrayList;
import java.util.List;

import model.Admin;
import model.Permissions;
import system.EnrollmentSystem;
import ui.Page;

/**
 * The CoursesPage class provides a user interface for managing courses.
 * It allows an admin to create new courses, delete existing courses, and view all courses.
 *
 * @version Apr 25, 2025
 */
public class CoursesPage extends Page {
    private Admin admin;
    private EnrollmentSystem enrollmentSystem;

    /**
     * Constructs a CoursesPage with the specified admin and enrollment system.
     *
     * @param admin the admin currently logged in
     * @param enrollmentSystem the enrollment system to interact with
     */
    public CoursesPage(Admin admin, EnrollmentSystem enrollmentSystem) {
        super(admin);
        this.admin = admin;
        this.enrollmentSystem = enrollmentSystem;
    }

    /**
     * Returns the menu options specific to the CoursesPage.
     *
     * @return a list of menu option strings
     */
    @Override
    public List<String> getMenuOptions() {
        List<String> menuOptions = new ArrayList<>();
        if (checkPermission(Permissions.COURSE_MANAGEMENT)) {
            menuOptions.addAll(List.of("Create new course", "Delete existing course"));
        }
        menuOptions.add("View all courses");
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
     * Returns the title of the menu for the CoursesPage.
     *
     * @return the menu title
     */
    @Override
    public String getMenuTitle() {
        return "Manage Courses Actions";
    }

    /**
     * Handles the action based on to the user's menu choice.
     *
     * @param choice the user's menu choice
     */
    @Override
    public void handleAction(int choice) {
        switch (getSelectedOption(choice)) {
            case "Create new course":
                enrollmentSystem.createCourse();
                break;
            case "View all courses":
                enrollmentSystem.viewAllCourses();
                break;
            case "Delete existing course":
                String id = promptCourseId();
                enrollmentSystem.removeCourse(enrollmentSystem.getCourseById(id));
                break;
            case "Return to Admin Menu":
                return;
            default:
                System.out.println("Invalid option");
                break;
        }
    }
}
