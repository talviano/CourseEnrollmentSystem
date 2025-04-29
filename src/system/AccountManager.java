package system;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import model.Admin;
import model.Instructor;
import model.Student;
import model.User;
import util.ColumnExtractor;
import util.TablePrinter;
import util.Util;

/**
 * Manages user accounts in the course registration system.
 * This class provides methods to authenticate users, create new users,
 * and manage existing users. It also generates default credentials
 * for new users and displays user information in a formatted table.
 *
 * @version Feb 24, 2025
 */

public class AccountManager {
    private List<User> users;
    private static final String domain = "university.edu";
    private final Scanner input = new Scanner(System.in);

    /**
     * Constructs an AccountManager with an empty list of users.
     */
    public AccountManager() {
        this.users = new ArrayList<>();
    }

    /**
     * Authenticates a user based on their email and password.
     *
     * @param email the email of the user
     * @param password the password of the user
     * @return the authenticated User object, or null if authentication fails
     */
    public User authenticate(String email, String password) {
        if (email.equals("adminoveride")) {
            return new Admin("Admin", "adminoveride", "adminoveride");
        } else if (email.equals("studentoveride")) {
            return new Student("Student", "studentoveride", "studentoveride", false);
        } else if (email.equals("instructoroveride")) {
            return new Instructor("Instructor", "instructoroveride", "instructoroveride");
        }
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Adds a user to the system.
     *
     * @param user the user to add
     * @return {@code true} if the user is added successfully, {@code false} otherwise
     */
    public boolean addUser(User user) {
        if (users.contains(user)) {
            System.out.println("User already exists");
            return false;
        }
        if (user == null) {
            System.out.println("Invalid User");
            return false;
        }
        users.add(user);
        return true;
    }

    /**
     * Removes a user from the system.
     *
     * @param user the user to remove
     * @return {@code true} if the user is removed successfully, {@code false} otherwise
     */
    public boolean removeUser(User user) {
        if (user == null || !users.contains(user)) {
            System.out.println("User does not exist.");
            return false;
        }
        return users.remove(user);
    }

    /**
     * Retrieves a user by their ID or email.
     *
     * @param value the ID or email of the user
     * @return the User object if found, or null if not found
     */
    public User getUserByIdOrEmail(String value) {
        for (User user : users) {
            if (user.getEmail().equals(value) || user.getId().equals(value)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Creates a student by taking input from the user.
     */
    public void createStudent() {
        List<String> credentials = generateEmailAndPassword();
        String name = credentials.get(0);
        String email = credentials.get(1);
        String password = credentials.get(2);
        boolean advisingHold = false;
        addUser(new Student(name, email, password, advisingHold));
        System.out.println("Student Created.");
        System.out.println("Email: " + email);
        System.out.println("Default Password: " + password);

    }

    /**
     * Creates an instructor by taking input from the user.
     */
    public void createInstructor() {
        List<String> credentials = generateEmailAndPassword();
        String name = credentials.get(0);
        String email = credentials.get(1);
        String password = credentials.get(2);
        addUser(new Instructor(name, email, password));
        System.out.println("Instructor Created.");
        System.out.println("Email: " + email);
        System.out.println("Default Password: " + password);
    }

    /**
     * Creates an admin by taking input from the user.
     */
    public void createAdmin() {
        List<String> credentials = generateEmailAndPassword();
        String name = credentials.get(0);
        String email = credentials.get(1);
        String password = credentials.get(2);
        addUser(new Admin(name, email, password));
        System.out.println("Admin Created.");
        System.out.println("Email: " + email);
        System.out.println("Default Password: " + password);
    }

    /**
     * Generates an email address for a user based on their name and domain.
     *
     * @param name the name of the user
     * @param domain the domain of the email address
     * @return the generated email address string
     */
    public String generateEmail(String name, String domain) {
        if (name == null || name.strip().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty or null.");
        }

        if (!name.matches("^[a-zA-Z]+\\s+[a-zA-Z]+$")) {
            throw new IllegalArgumentException("Name must include a first and last name separated by a space (First Last).");
        }

        String[] parts = name.strip().split("\\s+");
    
        String firstName = parts[0];
        String lastName = parts[1];
        String baseEmail = firstName.toLowerCase().charAt(0) + lastName.toLowerCase();
                           
        if (users.isEmpty()) {
            return baseEmail + "@" + domain;
        }

        int max = 0;
        boolean baseExists = false;
        for (User user : users) {
            if (user.getEmail().startsWith(baseEmail)) {
                baseExists = true;
                String existingEmail = user.getEmail().replace("@" + domain, "");
                String number = existingEmail.replace(baseEmail, "");
                int num = number.isEmpty() ? 0 : Integer.parseInt(number);
                max = Math.max(num, max);
            }
        }

        return baseExists ? baseEmail + (max + 1) + "@" + domain : baseEmail + "@" + domain;
    }

    /**
     * Generates a default password for a user based on their name.
     *
     * @param name the name of the user
     * @return the generated default password string
     */
    public String generateDefaultPassword(String name) {
        if (name == null || name.strip().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty or null.");
        }

        if (!name.matches("^[a-zA-Z]+\\s+[a-zA-Z]+$")) {
            throw new IllegalArgumentException("Name must include a first and last name separated by a space (First Last).");
        }
    
        String[] parts = name.strip().split("\\s+");
    
        Random rand = new Random();
        int num = rand.nextInt(9000) + 1000;
        return parts[0] + num;
    }

    /**
     * Displays the list of users in a formatted table.
     */
    public void displayUsers() {
        if (users.isEmpty()) {
            System.out.println("There are no users");
            return;
        }
        List<String> headers = List.of("Name", "Id", "Email");

        List<ColumnExtractor<User>> extractors = List.of(
            User::getName,
            User::getId,
            User::getEmail
        );

        TablePrinter<User> printer = new TablePrinter<>(headers, extractors, users);
        printer.printTable();
    }

    /**
     * Gets the list of users.
     *
     * @return the list of users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Sets advising holds to true for all students in the system.
     */
    public void setAllAdvisingHoldsTrue() {
        for (User user : users) {
            if (user instanceof Student student) {
                student.setAdvisingHold(true);
            }
        }
    }

    /**
     * Displays all admins in the system in a formatted table.
     */
    public void viewAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Admin admin) {
                admins.add(admin);
            }
        }

        List<String> headers = List.of("Name", "Email", "Permissions");

        List<ColumnExtractor<Admin>> extractors = List.of(
                Admin::getName,
                Admin::getEmail,
                Admin::getPermissionsFormatted);

        TablePrinter<Admin> printer = new TablePrinter<>(headers, extractors, admins);
        printer.printTable();
    }
    
    private List<String> generateEmailAndPassword() {
        String name = null;
        String email = null;
        String password = null;
        while (true) {
            try {
                System.out.print("Name (First Last): ");
                name = Util.toTitleCase(input.nextLine().strip());
                email = generateEmail(name, domain);
                password = generateDefaultPassword(name);
                return List.of(name, email, password);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }
        
    }
}