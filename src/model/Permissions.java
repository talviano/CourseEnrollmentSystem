/**
 * The Permissions class defines a set of constants representing different permissions
 * that can be assigned to users in the course registration system. These permissions
 * determine the actions users are allowed to perform, such as managing courses, users,
 * or viewing specific details.
 *
 * Responsibilities:
 * - Providing a centralized list of permission constants.
 * - Converting the list of permissions into a formatted string.
 *
 * Permissions:
 * - COURSE_MANAGEMENT: Allows managing courses and course sections.
 * - USER_MANAGEMENT: Allows creating and managing users.
 * - ADMIN_MANAGEMENT: Allows managing other admins.
 * - VIEW_COURSES: Allows viewing course details.
 * - VIEW_USERS: Allows viewing user details.
 *
 * Usage:
 * Permissions can be used to check or assign specific capabilities to users.
 *
 * @version Mar 24, 2025
 */
package model;

import java.util.List;

public class Permissions {
    /**
     * Permission to manage courses and course sections.
     */
    public static final String COURSE_MANAGEMENT = "COURSE_MANAGEMENT";

    /**
     * Permission to manage users, including creating and editing user accounts.
     */
    public static final String USER_MANAGEMENT = "USER_MANAGEMENT";

    /**
     * Permission to manage other admins.
     */
    public static final String ADMIN_MANAGEMENT = "ADMIN_MANAGEMENT";

    /**
     * Permission to view course details.
     */
    public static final String VIEW_COURSES = "VIEW_COURSES";

    /**
     * Permission to view user details.
     */
    public static final String VIEW_USERS = "VIEW_USERS";

    /**
     * Converts the list of permissions into a formatted string.
     *
     * @return a comma-separated string of all permissions
     */
    public static String toPermissionsString() {
        List<String> permissions = List.of(COURSE_MANAGEMENT, USER_MANAGEMENT, ADMIN_MANAGEMENT, VIEW_COURSES,
                VIEW_USERS);
        String result = "";
        for (String permission : permissions) {
            result += permission + ", ";
        }
        return result.substring(0, result.length() - 2); // Remove the trailing comma and space
    }
}