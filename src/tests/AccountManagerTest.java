package tests;

import model.Admin;
import model.Instructor;
import model.Student;
import model.User;
import system.AccountManager;

/**
 * Tests the functionality of the AccountManager class.
 * This class includes tests for adding users, authenticating, removing, and looking up users.
 *
 * @version Apr 27, 2025
 */
public class AccountManagerTest {
    public static void main(String[] args) {
        System.out.println("########################################################");
        System.out.println("TESTING ACCOUNT MANAGER CLASS FUNCTIONALITY...");

        // Create AccountManager
        AccountManager accountManager = new AccountManager();

        // Create Users
        Student student = new Student("John Doe", "john@example.com", "password123", false);
        Instructor instructor = new Instructor("Jane Smith", "jane@example.com", "password123");
        Admin admin = new Admin("Master Admin", "master@example.com", "adminpass");

        // Test: Adding users
        System.out.println("\nTEST: Adding users...");
        boolean addedStudent = accountManager.addUser(student);
        boolean addedInstructor = accountManager.addUser(instructor);
        boolean addedAdmin = accountManager.addUser(admin);

        if (addedStudent && addedInstructor && addedAdmin) {
            System.out.println("✓✓✓ PASS --> Users added successfully.");
        } else {
            System.out.println("xxx FAIL --> Failed to add one or more users.");
        }

        // Test: Authenticating users
        System.out.println("\nTEST: Authenticating student...");
        User authStudent = accountManager.authenticate("john@example.com", "password123");
        if (authStudent instanceof Student) {
            System.out.println("✓✓✓ PASS --> Student authenticated successfully.");
        } else {
            System.out.println("xxx FAIL --> Student authentication failed.");
        }

        System.out.println("\nTEST: Authenticating instructor...");
        User authInstructor = accountManager.authenticate("jane@example.com", "password123");
        if (authInstructor instanceof Instructor) {
            System.out.println("✓✓✓ PASS --> Instructor authenticated successfully.");
        } else {
            System.out.println("xxx FAIL --> Instructor authentication failed.");
        }

        System.out.println("\nTEST: Authenticating admin...");
        User authAdmin = accountManager.authenticate("master@example.com", "adminpass");
        if (authAdmin instanceof Admin) {
            System.out.println("✓✓✓ PASS --> Admin authenticated successfully.");
        } else {
            System.out.println("xxx FAIL --> Admin authentication failed.");
        }

        // Test: Lookup by email
        System.out.println("\nTEST: Getting user by email...");
        User lookupByEmail = accountManager.getUserByIdOrEmail("john@example.com");
        if (lookupByEmail == student) {
            System.out.println("✓✓✓ PASS --> User lookup by email successful.");
        } else {
            System.out.println("xxx FAIL --> User lookup by email failed.");
        }

        // Test: Lookup by ID
        System.out.println("\nTEST: Getting user by ID...");
        User lookupById = accountManager.getUserByIdOrEmail(student.getId());
        if (lookupById == student) {
            System.out.println("✓✓✓ PASS --> User lookup by ID successful.");
        } else {
            System.out.println("xxx FAIL --> User lookup by ID failed.");
        }

       // Test: Setting all advising holds true
        System.out.println("\nTEST: Setting all advising holds true...");
        accountManager.setAllAdvisingHoldsTrue();
        if (student.hasAdvisingHold()) {
            System.out.println("✓✓✓ PASS --> Advising hold set successfully.");
        } else {
            System.out.println("xxx FAIL --> Advising hold not set properly.");
        }

        // Test: Removing user
        System.out.println("\nTEST: Removing user...");
        boolean removed = accountManager.removeUser(student);
        if (removed && accountManager.getUserByIdOrEmail("john@example.com") == null) {
            System.out.println("✓✓✓ PASS --> User removed successfully.");
        } else {
            System.out.println("xxx FAIL --> User removal failed.");
        }

       

        System.out.println("\n########################################################");
        System.out.println("ACCOUNT MANAGER CLASS TESTING COMPLETE.");
    }
}

