package tests;

import model.Admin;
import system.AccountManager;
import system.EnrollmentSystem;

/**
 * Tests the functionality of the Admin class.
 * This class includes manual input tests for permissions, course creation, and user creation.
 *
 * Instructions:
 * - When prompted, type the requested test input exactly.
 * - Press Enter after each input.
 *
 * @version Apr 27, 2025
 */
public class AdminTest {
    public static void main(String[] args) {
        System.out.println("########################################################");
        System.out.println("TESTING ADMIN CLASS FUNCTIONALITY (MANUAL INPUT)...");

        // Create Admin
        Admin admin = new Admin("MasterAdmin", "master@admin.com", "securePassword");

        // Grant permissions
        System.out.println("\nTEST: Granting all permissions to admin...");
        admin.grantAllPermissions();

        if (admin.getPermissions().size() >= 5) {
            System.out.println("✓✓✓ PASS --> All permissions granted.");
        } else {
            System.out.println("xxx FAIL --> Permissions not fully granted.");
        }

        // Setup EnrollmentSystem and AccountManager
        EnrollmentSystem enrollmentSystem = new EnrollmentSystem();
        AccountManager accountManager = new AccountManager();

        // Test: Course creation (manual input)
        System.out.println("\nTEST: Creating a course (MANUAL INPUT REQUIRED)");
        System.out.println("Please enter the following when prompted:");
        System.out.println("MATH 1242");
        System.out.println("Calculus II");
        System.out.println("Advanced integrals");
        System.out.println("4");
        boolean createdCourse = admin.createCourse(enrollmentSystem);
        if (createdCourse) {
            System.out.println("✓✓✓ PASS --> Course creation allowed with permission.");
        } else {
            System.out.println("xxx FAIL --> Course creation failed despite permissions.");
        }

        // Test: Student creation (manual input)
        System.out.println("\nTEST: Creating a student (MANUAL INPUT REQUIRED)");
        System.out.println("Please enter the following when prompted:");
        System.out.println("John Doe");
        boolean createdStudent = admin.createStudent(accountManager);
        if (createdStudent) {
            System.out.println("✓✓✓ PASS --> Student creation allowed with permission.");
        } else {
            System.out.println("xxx FAIL --> Student creation failed despite permissions.");
        }

        // Test: Instructor creation (manual input)
        System.out.println("\nTEST: Creating an instructor (MANUAL INPUT REQUIRED)");
        System.out.println("Please enter the following when prompted:");
        System.out.println("Jane Smith");
        boolean createdInstructor = admin.createInstructor(accountManager);
        if (createdInstructor) {
            System.out.println("✓✓✓ PASS --> Instructor creation allowed with permission.");
        } else {
            System.out.println("xxx FAIL --> Instructor creation failed despite permissions.");
        }

        // Test: Admin creation (manual input)
        System.out.println("\nTEST: Creating another admin (MANUAL INPUT REQUIRED)");
        System.out.println("Please enter the following when prompted:");
        System.out.println("Super Admin");
        System.out.println("superadmin@admin.com");
        boolean createdAdmin = admin.createAdmin(accountManager);
        if (createdAdmin) {
            System.out.println("✓✓✓ PASS --> Admin creation allowed with permission.");
        } else {
            System.out.println("xxx FAIL --> Admin creation failed despite permissions.");
        }

        System.out.println("\n########################################################");
        System.out.println("ADMIN CLASS MANUAL INPUT TESTING COMPLETE.");
    }
}