/**
 * Represents an admin user in the system.
 * This class provides methods for managing courses.
 *
 * @version Feb 22, 2025
 */
package model;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    private static int lastAssignedId = 802999999;
    private List<String> permissions;
    
    /**
     * Constructs an Admin with specified details.
     *
     * @param name the name of the admin
     * @param id the ID of the admin
     * @param email the email of the admin
     * @param password the password of the admin
     */
    public Admin(String name, String email, String password) {
        super(String.valueOf(++lastAssignedId), name, email, password);
        this.permissions = new ArrayList<>();
    }
}
