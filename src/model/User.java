package model;

/**
 * Represents a user in the system.
 * This class provides methods for user authentication.
 *
 * @version Feb 22, 2025
 */
public class User {
    private String id;
    private String name; 
    private String email;
    private String password;
    private boolean needsPasswordReset = true;
    
    /**
     * Constructs a User with specified details.
     *
     * @param id the user ID
     * @param name the name of the user
     * @param email the email of the user
     * @param password the password of the user
     */
    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    /**
     * Returns the email of the user.
     *
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the name of the user.
     *
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the ID of the user.
     *
     * @return the ID of the user
     */
    public String getId() {
        return id;
    }

    /**
     * Returns wheter user needs to reset there password
     *
     * @return {@code true} if the user needs to reset their password, {@code false} otherwise
     */
    public boolean needsPasswordReset() {
        return needsPasswordReset;
    }

    /**
     * Sets whether the user needs to reset their password
     *
     * @param needPasswordReset {@code true} if a password reset is required, {@code false} otherwise
     */
    public void setNeedsPasswordReset(boolean needsPasswordReset) {
        this.needsPasswordReset = needsPasswordReset;
    }


    /**
     * Sets the user's password
     * 
     * @param password the new password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
