/**
 * Represents an admin user in the system.
 * This class provides methods for managing courses.
 *
 * @version Feb 22, 2025
 */
package main;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    private List<String> permissions;
    /**
     * Constructs an Admin with specified details.
     *
     * @param name the name of the admin
     * @param id the ID of the admin
     * @param email the email of the admin
     * @param password the password of the admin
     */
    public Admin(String name, String id, String email, String password) {
        super(name, id, email, password);
        this.permissions = new ArrayList<>();
    }

    /**
     * Adds a new course to the system.
     *
     * @return true if the course is added successfully, false otherwise
     */
    public boolean addCourse() {
        return false;
    }

    /**
     * Removes a course from the system.
     *
     * @return true if the course is removed successfully, false otherwise
     */
    public boolean removeCourse() {
        return false;
    }
}
