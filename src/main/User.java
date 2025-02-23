/**
 * Represents a user in the system.
 * This class provides methods for user authentication.
 *
 * @version Feb 22, 2025
 */
package main;

public class User {
    private String id;
    private String name; 
    private String email;
    private String password;
    
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
     * Authenticates user entered login credentials.
     *
     * @param email the email entered by the user
     * @param password the password entered by the user
     * @return true if the user is authenticated, false otherwise
     */
    public boolean login(String email, String password) {
        //checks if email and password taken as arguments match any users
        //If so returns true, otherwise returns false
        return false;
    }

    /**
     * Logs out the user from the system.
     */
    public void logout() {  
    }

}
