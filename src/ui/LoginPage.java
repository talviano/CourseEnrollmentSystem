/**
 * The LoginPage class represents the user interface for user login.
 * It provides functionalities for users to log in to the system
 * using their email and password.
 *
 * This class is part of the UI layer and interacts with the AccountManager
 * to authenticate users.
 *
 * Responsibilities:
 * - Displaying the login menu.
 * - Authenticating users based on their email and password.
 * - Managing the current logged-in user.
 * - Allowing users to exit the login page.
 *
 * Usage:
 * LoginPage loginPage = new LoginPage(accountManager);
 * loginPage.display();
 *
 * @version Mar 22, 2025
 */
package ui;

import java.util.List;
import model.User;
import system.AccountManager;
import util.Util;

public class LoginPage extends Page {
    private AccountManager accountManager;
    private User currentUser;
    private boolean shouldExit = false;

    /**
     * Constructs a LoginPage with the specified AccountManager.
     *
     * @param accountManager the account manager to interact with for user authentication
     */
    public LoginPage(AccountManager accountManager) {
        super(null);
        this.accountManager = accountManager;
    }

    /**
     * Returns the menu options specific to the LoginPage.
     *
     * @return a list of menu option strings
     */
    @Override
    protected List<String> getMenuOptions() {
        return List.of("Login", "Exit");
    }

    /**
     * Returns the title of the menu for the LoginPage.
     *
     * @return the menu title
     */
    @Override
    protected String getMenuTitle() {
        return "Login Menu"; 
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
                if (login()) {
                    return;
                }
                break;
            case 2:
                shouldExit = true;
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    /**
     * Authenticates the user by prompting for email and password.
     *
     * @return {@code true} if the login is successful, {@code false} otherwise
     */
    private boolean login() {
        Util.createSeperator(50);
        System.out.print("Email: ");
        String email = input.nextLine().strip();
        Util.createSeperator(50);
        System.out.print("Password: ");
        String password = input.nextLine().strip();
        Util.createSeperator(50);

        User loggedInUser = accountManager.authenticate(email, password);
        if (loggedInUser == null) {
            System.out.println("Invalid Credentials Try Again\n");
            return false;
        } else {
            if (loggedInUser.needsPasswordReset()) {
                System.out.println("You must change your password before continuing.");
                System.out.print("Enter new password: ");
                String newPassword = input.nextLine();
                loggedInUser.setPassword(newPassword);
                loggedInUser.setNeedsPasswordReset(false);
                System.out.println("Password updated successfully!\n");
            }
            System.out.println("Login successful. Welcome " + loggedInUser.getName() + ".\n");
            this.currentUser = loggedInUser;
            return true;
        }
    }

    /**
     * Returns the currently logged-in user.
     *
     * @return the current user, or {@code null} if no user is logged in
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Clears the currently logged-in user.
     */
    public void clearCurrentUser() {
        this.currentUser = null;
    }

    /**
     * Displays the login page and handles user interactions.
     * The method continues to display the menu until the user logs in
     * or chooses to exit.
     */
    @Override
    public void display() {
        while (!shouldExit && currentUser == null) {
            int choice = displayMenu();
            handleAction(choice);
    
            if (currentUser != null || shouldExit) {
                break;
            }
        }
    }
}