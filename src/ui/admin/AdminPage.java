package ui.admin;

import java.util.ArrayList;
import java.util.List;

import model.Admin;
import model.User;
import model.Permissions;
import system.AccountManager;
import system.EnrollmentSystem;
import ui.Page;

/**
 * The AdminPage class represents the user interface for administrators.
 * It provides functionalities for administrators to manage users,
 * courses, course sections, and enrollment. Administrators can also
 * perform other admin-specific tasks such as assigning instructors
 * and generating reports.
 *
 * @version Apr 19, 2025
 */
public class AdminPage extends Page {
    /**
     * The enrollment system used to manage courses and sections.
     */
    private EnrollmentSystem enrollmentSystem;

    /**
     * The account manager used to manage users.
     */
    private AccountManager accountManager;

    /**
     * The admin associated with this page.
     */
    private Admin admin;

    /**
     * Constructs an AdminPage with the specified admin, enrollment system, and account manager.
     *
     * @param admin the admin associated with this page
     * @param enrollmentSystem the enrollment system to interact with
     * @param accountManager the account manager to interact with
     */
    public AdminPage(Admin admin, EnrollmentSystem enrollmentSystem, AccountManager accountManager) {
        super(admin);
        this.admin = admin;
        this.enrollmentSystem = enrollmentSystem;
        this.accountManager = accountManager;
    }

     /**
     * Prompts user to select specific admin and displays admin permissions page for that admin
     */
    public void manageAdminPermissionsView() {
        while (true) {
            System.out.print("Enter Admin ID or Email: ");
            String value = input.nextLine().strip();
            User user = accountManager.getUserByIdOrEmail(value);
    
            if (user == null) {
                System.out.println("User not found.");
                continue;
            }
            if (!(user instanceof Admin targetAdmin)) {
                System.out.println("User is not an admin.");
                continue;
            }
    
            AdminPermissionsPage page = new AdminPermissionsPage(admin, targetAdmin, accountManager);
            page.display();
            break;
        }
    }

    /**
     * Handles the action based on to the user's menu choice.
     *
     * @param choice the user's menu choice
     */
    @Override
    public void handleAction(int choice) {
        switch (getSelectedOption(choice)) {
            case "Manage Users":
                UsersPage manageUsersPage = new UsersPage(admin, accountManager);
                manageUsersPage.display();
                break;
            case "Manage Courses":
                CoursesPage manageCoursesPage = new CoursesPage(admin, enrollmentSystem);
                manageCoursesPage.display();
                break;
            case "Manage Course Sections":
                CourseSectionsPage manageCourseSectionsPage = new CourseSectionsPage(admin, enrollmentSystem);
                manageCourseSectionsPage.display();
                break;
            case "Manage Enrollment":
                EnrollmentPage manageEnrollmentPage = new EnrollmentPage(admin, enrollmentSystem, accountManager);
                manageEnrollmentPage.display();
                break;
            case "Manage Admin Permissions":
                manageAdminPermissionsView();
                break;
            case "Manage Advising Holds":
                AdvisingHoldPage manageAdvisingHoldPage = new AdvisingHoldPage(admin, accountManager);
                manageAdvisingHoldPage.display();
                break;
            case "Log out":
                return;
            default:
                System.out.println("Invalid option!");
                break;
        }
    }

    /**
     * Returns the menu options specific to the admin page.
     *
     * @return an array of menu option strings
     */
    @Override
    public List<String> getMenuOptions() {
        List<String> menuOptions = new ArrayList<>();

        if (checkPermission(Permissions.VIEW_USERS)) {
            menuOptions.add("Manage Users");
        }

        if (checkPermission(Permissions.VIEW_COURSES)) {
            menuOptions.addAll(List.of("Manage Courses", "Manage Course Sections"));
        }

        if (checkPermission(Permissions.USER_MANAGEMENT)) {
            menuOptions.add("Manage Enrollment");
        }

        if (checkPermission(Permissions.ADMIN_MANAGEMENT)) {
            menuOptions.add("Manage Admin Permissions");
        }

        if (checkPermission(Permissions.USER_MANAGEMENT)) {
            menuOptions.add("Manage Advising Holds");
        }

        menuOptions.add("Log out");

        return menuOptions;
    }

    /**
     * Returns the title of the menu for the admin page.
     *
     * @return the menu title
     */
    @Override
    public String getMenuTitle() {
        return "Admin actions";
    }
}
