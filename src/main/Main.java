/**
 * TODO Write a one-sentence summary of your class here.
 * TODO Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 *
 * @author  TODO Your Name
 * @version Feb 24, 2025
 */
package main;

import java.util.Scanner;
import model.*;
import system.AccountManager;
import system.EnrollmentSystem;
import ui.LoginPage;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        AccountManager accountManager = new AccountManager();
        EnrollmentSystem enrollmentSystem = new EnrollmentSystem();
        LoginPage loginPage = new LoginPage(accountManager);

        while (true) {
            loginPage.displayMenu();
            User currentUser = loginPage.getCurrentUser();

            if (currentUser instanceof Student) {
                System.out.println("Redirecting to Student Page");
                break;
            } else if (currentUser instanceof Instructor) {
                System.out.println("Redirecting to Instructor Page");
                break;
            } else if (currentUser instanceof Admin) {
                System.out.println("Redirecting to Admin Page");
                break;
            }
        }
        input.close();
    }
}

