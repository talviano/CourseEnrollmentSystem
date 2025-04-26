/**
 * The UsersPage class provides a user interface for managing user accounts.
 * It allows an admin to create new users, view all users, and delete existing users.
 *
 * Responsibilities:
 * - Creating new users (students, instructors, or admins).
 * - Viewing all users in the system.
 * - Deleting existing users from the system.
 * - Returning to the admin menu.
 *
 * Usage:
 * UsersPage usersPage = new UsersPage(admin, accountManager);
 * usersPage.display();
 *
 * @version Apr 25, 2025
 */
package ui.admin;

import java.util.ArrayList;
import java.util.List;

import model.Admin;
import model.Permissions;
import system.AccountManager;
import ui.Page;

public class UsersPage extends Page {
    private Admin admin;
    private AccountManager accountManager;

    /**
     * Constructs a UsersPage with the specified admin and account manager.
     *
     * @param admin the admin currently logged in
     * @param accountManager the account manager to interact with
     */
    public UsersPage(Admin admin, AccountManager accountManager) {
        super(admin);
        this.admin = admin;
        this.accountManager = accountManager;
    }

    /**
     * Returns the menu options specific to the UsersPage.
     *
     * @return a list of menu option strings
     */
    @Override
    public List<String> getMenuOptions() {
        List<String> menuOptions = new ArrayList<>();
        if (checkPermission(Permissions.USER_MANAGEMENT)) {
            menuOptions.addAll(
                    List.of("Create new user", "View all users", "Delete existing user", "Return to Admin Menu"));
        }
        menuOptions.addAll(List.of("View all users", "Return to Admin Menu"));
        return menuOptions;
    }

    /**
     * Returns the title of the menu for the UsersPage.
     *
     * @return the menu title
     */
    @Override
    public String getMenuTitle() {
        return "Manage Users Actions";
    }

    /**
     * Handles the action corresponding to the user's menu choice.
     *
     * @param choice the user's menu choice
     */
    @Override
    public void handleAction(int choice) {
        switch (getSelectedOption(choice)) {
            case "Create new user":
                createUserView();
                break;
            case "View all users":
                accountManager.displayUsers();
                break;
            case "Delete existing user":
                String value = promptUserId();
                accountManager.removeUser(accountManager.getUserByIdOrEmail(value));
                break;
            case "Return to Admin Menu":
                return;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

    /**
     * Displays the view for creating a new user.
     */
    private void createUserView() {
        while (true) {
            System.out.print("User type (Student, Instructor, Admin): ");
            String userType = input.nextLine().strip().toLowerCase();

            switch (userType) {
                case "student":
                    accountManager.createStudent(input);
                    break;
                case "instructor":
                    accountManager.createInstructor(input);
                    break;
                case "admin":
                    accountManager.createAdmin(input);
                    break;
                default:
                    System.out.println(userType + " is an invalid user type. Please enter either Student, Instructor, or Admin.");
                    continue;
            }
            break;
        }
    }
}
