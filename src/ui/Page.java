/**
 * The Page class serves as an abstract base class for all user interface pages in the system.
 * It provides common functionality for displaying menus, handling user input, and managing
 * user-specific actions. Subclasses must implement methods to define menu options, handle
 * specific actions, and provide a title for the menu.
 *
 * This class is part of the UI layer and is designed to be extended by role-specific pages
 * such as StudentPage, InstructorPage, and AdminPage.
 *
 * Responsibilities:
 * - Displaying a header with user information.
 * - Displaying a menu with options specific to the page.
 * - Handling user input and delegating actions to subclasses.
 * - Providing a logout option to terminate the session.
 *
 * @version Mar 23, 2025
 */
package ui;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.Admin;
import model.User;
import util.Util;

public abstract class Page {
    /**
     * A shared Scanner instance for reading user input.
     */
    protected static final Scanner input = new Scanner(System.in);

    /**
     * The user associated with this page.
     */
    protected User user;

    /**
     * Constructs a Page with the specified user.
     *
     * @param user the user associated with this page
     */
    public Page(User user) {
        this.user = user;
    }

    /**
     * Displays the page, including the header and menu, and handles user actions.
     * The method runs in a loop until the user chooses to log out.
     */
    public void display() {
        while (true) {
            int choice = displayMenu();
            if (choice == getLogoutOption()) {
                System.out.println("Logging out...");
                break;
            }
            handleAction(choice);
        }
    }

    /**
     * Displays the menu options and handles user input to select an option.
     *
     * @return the user's menu choice
     */
    public int displayMenu() {
        List<String> menuOptions = getMenuOptions();
        Util.createMenu(getMenuTitle(), menuOptions);
        while (true) {
            try {
                System.out.print(">> ");
                int choice = input.nextInt();
                input.nextLine();
                if (Util.isValidMenuChoice(choice, menuOptions.size())) {
                    return choice;
                } else {
                    System.out.println("Invalid option. Please select a valid menu number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number input.");
                input.nextLine();
            }
        }
    }

    /**
     * Returns the user associated with this page.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Prompts the user to enter a CRN (Course Reference Number).
     *
     * @return the entered CRN
     */
    public String promptCRN() {
        System.out.print("CRN: ");
        String crn = input.nextLine().strip();
        return crn;
    }

    /**
     * Prompts the user to enter a user ID or email.
     *
     * @return the entered user ID or email
     */
    public String promptUserId() {
        System.out.print("Email/Id: ");
        String value = input.nextLine().strip().toLowerCase();
        return value;
    }

    /**
     * Prompts the user to enter a course ID.
     *
     * @return the entered course ID
     */
    public String promptCourseId() {
        System.out.print("Id (e.g., MATH 1242): ");
        String id = input.nextLine().strip();
        return id;
    }

    /**
     * Checks if the user has the specified permission.
     *
     * @param permission the permission to check
     * @return {@code true} if the user has the permission, {@code false} otherwise
     */
    public boolean checkPermission(String permission) {
        if (user instanceof Admin admin) {
            return admin.hasPermission(permission);
        }
        return false;
    }

    /**
     * Returns the menu option selected by the user based on their choice.
     *
     * @param choice the user's menu choice
     * @return the selected menu option as a string
     */
    public String getSelectedOption(int choice) {
        List<String> options = getMenuOptions();

        if (choice < 1 || choice > options.size()) {
            System.out.println("Invalid choice!");
            return " ";
        }

        String selected = options.get(choice - 1);

        return selected;
    }

    /**
     * Returns the menu options specific to this page.
     * Subclasses must implement this method to define their menu options.
     *
     * @return a list of menu option strings
     */
    protected abstract List<String> getMenuOptions();

    /**
     * Handles the action corresponding to the user's menu choice.
     * Subclasses must implement this method to define their specific actions.
     *
     * @param choice the user's menu choice
     */
    protected abstract void handleAction(int choice);

    /**
     * Returns the title of the menu for this page.
     * Subclasses must implement this method to provide a menu title.
     *
     * @return the menu title
     */
    protected abstract String getMenuTitle();

    /**
     * Returns the menu option number that corresponds to logging out.
     *
     * @return the logout option number
     */
    protected int getLogoutOption() {
        return getMenuOptions().size();
    }
}
