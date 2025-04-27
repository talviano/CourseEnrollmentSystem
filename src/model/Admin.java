/**
 * Represents an admin user in the system.
 * This class provides methods for managing courses, course sections, and users.
 * Admins have specific permissions that determine the actions they can perform.
 *
 * Responsibilities:
 * - Managing courses and course sections.
 * - Creating and managing users (students, instructors, and other admins).
 * - Granting and revoking permissions.
 *
 * Permissions:
 * - COURSE_MANAGEMENT: Allows managing courses and course sections.
 * - USER_MANAGEMENT: Allows creating and managing users.
 * - ADMIN_MANAGEMENT: Allows managing other admins.
 * - VIEW_USERS: Allows viewing user details.
 * - VIEW_COURSES: Allows viewing course details.
 *
 * @version Feb 22, 2025
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import system.AccountManager;
import system.EnrollmentSystem;

public class Admin extends User {
    /**
     * The last assigned ID for an admin.
     * Used to generate unique IDs for new admins.
     */
    private static int lastAssignedId = 802999999;

    /**
     * The list of permissions assigned to the admin.
     */
    private List<String> permissions;

    /**
     * The message displayed when an admin attempts an action without the required permission.
     */
    private static String noPermissionString = "You do not have permission for this action.";
    private Scanner input = new Scanner(System.in);

    /**
     * Constructs an Admin with specified details.
     *
     * @param name the name of the admin
     * @param email the email of the admin
     * @param password the password of the admin
     */
    public Admin(String name, String email, String password) {
        super(String.valueOf(++lastAssignedId), name, email, password);
        this.permissions = new ArrayList<>();
    }

    /**
     * Checks if the admin has a specific permission.
     *
     * @param permission the permission to check
     * @return true if the admin has the permission, false otherwise
     */
    public boolean hasPermission(String permission) {
        return permissions.contains(permission);
    }

    /**
     * Adds a permission to the admin.
     *
     * @param permission the permission to add
     */
    public void addPermission(String permission) {
        if (!permissions.contains(permission)) {
            permissions.add(permission);
        }
    }

    /**
     * Revokes a permission from the admin.
     *
     * @param permission the permission to revoke
     */
    public void revokePermission(String permission) {
        if (permissions.contains(permission)) {
            permissions.remove(permission);
        }
    }

    /**
     * Grants all available permissions to the admin.
     */
    public void grantAllPermissions() {
        permissions.clear();
        permissions.addAll(List.of(Permissions.ADMIN_MANAGEMENT, Permissions.COURSE_MANAGEMENT,
                Permissions.USER_MANAGEMENT, Permissions.VIEW_USERS, Permissions.VIEW_COURSES));
    }

    /**
     * Returns the list of permissions assigned to the admin.
     *
     * @return the list of permissions
     */
    public List<String> getPermissions() {
        return permissions;
    }

    /**
     * Creates a course using the enrollment system.
     *
     * @param enrollmentSystem the enrollment system to interact with
     * @param input the Scanner object to read user input
     * @return true if the course is created successfully, false otherwise
     */
    public boolean createCourse(EnrollmentSystem enrollmentSystem) {
        if (this.hasPermission(Permissions.COURSE_MANAGEMENT)) {
            enrollmentSystem.createCourse();
            return true;
        }
        System.out.println(noPermissionString);
        return false;
    }

    /**
     * Creates a course section using the enrollment system.
     *
     * @param enrollmentSystem the enrollment system to interact with
     * @param input the Scanner object to read user input
     * @return true if the course section is created successfully, false otherwise
     */
    public boolean createCourseSection(EnrollmentSystem enrollmentSystem) {
        if (this.hasPermission(Permissions.COURSE_MANAGEMENT)) {
            enrollmentSystem.createCourseSection();
            return true;
        }
        System.out.println(noPermissionString);
        return false;
    }

    /**
     * Creates a student using the account manager.
     *
     * @param accountManager the account manager to interact with
     * @param input the Scanner object to read user input
     * @return true if the student is created successfully, false otherwise
     */
    public boolean createStudent(AccountManager accountManager) {
        if (this.hasPermission(Permissions.USER_MANAGEMENT)) {
            accountManager.createStudent();
            return true;
        }
        System.out.println(noPermissionString);
        return false;
    }

    /**
     * Creates an instructor using the account manager.
     *
     * @param accountManager the account manager to interact with
     * @param input the Scanner object to read user input
     * @return true if the instructor is created successfully, false otherwise
     */
    public boolean createInstructor(AccountManager accountManager) {
        if (this.hasPermission(Permissions.USER_MANAGEMENT)) {
            accountManager.createInstructor();
            return true;
        }
        System.out.println(noPermissionString);
        return false;
    }

    /**
     * Creates another admin using the account manager.
     *
     * @param accountManager the account manager to interact with
     * @param input the Scanner object to read user input
     * @return true if the admin is created successfully, false otherwise
     */
    public boolean createAdmin(AccountManager accountManager) {
        if (this.hasPermission(Permissions.USER_MANAGEMENT)) {
            accountManager.createAdmin();
            return true;
        }
        System.out.println(noPermissionString);
        return false;
    }

    /**
     * Returns the permissions assigned to the admin as a formatted string.
     *
     * @return a comma-separated string of permissions
     */
    public String getPermissionsFormatted() {
        String result = "";
        for (String permission : permissions) {
            result += permission + ", ";
        }

        if (result.isEmpty()) {
            return "";
        }

        return result.substring(0, result.length() - 2);
    }
}
