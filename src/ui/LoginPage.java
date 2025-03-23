/**
 * The LoginPage class represents the user interface for user login.
 * It provides functionalities for users to log in to the system
 * using their email and password.
 *
 * This class is part of the UI layer and interacts with the AccountManager
 * to authenticate users.
 *
 * Usage:
 * LoginPage loginPage = new LoginPage(accountManager);
 * loginPage.displayMenu();
 *
 * @version Mar 22, 2025
 */
package ui;

import java.util.InputMismatchException;
import java.util.Scanner;
import model.User;
import system.AccountManager;
import util.Util;

public class LoginPage {
    private AccountManager accountManager;
    private User currentUser;

    static Scanner input = new Scanner(System.in);
    
    /**
     * Constructs a LoginPage with the specified AccountManager.
     *
     * @param accountManager the account manager to handle user authentication
     */
    public LoginPage(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    /**
     * Prompts the user for email and password, and attempts to log in.
     *
     * @return true if login is successful, false otherwise
     */
    public boolean login() {
        Util.createSeperator(50);
        System.out.print("Email: ");
        String email = input.next();
        Util.createSeperator(50);
        System.out.print("Password: ");
        String password = input.next();
        Util.createSeperator(50);
        User loggedInUser = accountManager.authenticate(email, password);
        if (loggedInUser == null) {
            System.out.println("Invalid Credentials Try Again\n");
            return false;
        } else {
            System.out.println("Login successful. Welcome " + loggedInUser.getName() + ".\n");
            this.currentUser = loggedInUser;
            return true;
        }
    }

    /**
     * Displays the login menu and handles user input.
     */
    public void displayMenu() {
        String[] menuOptions = { "Login", "Exit" };
        while (true) {
            Util.createMenu("Login Menu", menuOptions);
            try {
                int choice = input.nextInt();
                if (Util.isValidMenuChoice(choice, 2)) {
                    switch (choice) {
                        case 1:
                            System.out.println("\n\n\nLogin");
                            if (!login()) {
                                continue;
                            }
                            break; // if you don't add this break the program will go into case 2 ending the program
                        case 2:
                            System.exit(0);
                            break;
                    }
                } else {
                    System.out.println("Please select a valid menu option!");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Invalid input. Please enter a number!\n");
            }
        }
    }
    
    /**
     * Returns the currently logged-in user.
     *
     * @return the current user
     */
    public User getCurrentUser() {
        return currentUser;
    }
}
