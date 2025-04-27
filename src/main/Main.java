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
import ui.InstructorPage;
import ui.LoginPage;
import ui.admin.AdminPage;
import ui.student.StudentPage;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        AccountManager accountManager = new AccountManager();
        EnrollmentSystem enrollmentSystem = new EnrollmentSystem();
        LoginPage loginPage = new LoginPage(accountManager);

        Admin mockAdmin = new Admin("System Admin", "sysadmin@university.edu", "temppass");
        accountManager.addUser(mockAdmin);
        mockAdmin.grantAllPermissions();

        while (true) {
            loginPage.display();
            User currentUser = loginPage.getCurrentUser();

            if (currentUser == null) {
                System.out.println("Exiting program. Goodbye!");
                break; 
            }

            if (currentUser instanceof Student student) {
                StudentPage studentPage = new StudentPage(student, enrollmentSystem);
                studentPage.display();
            } else if (currentUser instanceof Instructor instructor) {
                InstructorPage instructorPage = new InstructorPage(instructor, enrollmentSystem);
                instructorPage.display();
            } else if (currentUser instanceof Admin admin) {
                AdminPage adminPage = new AdminPage(admin, enrollmentSystem, accountManager);
                adminPage.display();
            }

            loginPage.clearCurrentUser();
        }
        input.close();
    }
}

