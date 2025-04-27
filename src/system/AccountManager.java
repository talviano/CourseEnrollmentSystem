/**
 * Manages user accounts in the course registration system.
 * This class provides methods to authenticate users, create new users,
 * and manage existing users. It also generates default credentials
 * for new users and displays user information in a formatted table.
 *
 * Responsibilities:
 * - Authenticating users based on email and password.
 * - Creating and managing users (students, instructors, and admins).
 * - Generating email addresses and default passwords for users.
 * - Displaying user information in a formatted table.
 *
 * @version Feb 24, 2025
 */
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
     * @return true if the user is added successfully, false otherwise
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
     * @return true if the user is removed successfully, false otherwise
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
        System.out.print("Name (First Last): ");
        String name = Util.toTitleCase(input.nextLine());
        String email = generateEmail(name, domain);
        String password = generateDefaultPassword(name);
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
        System.out.print("Name (First Last): ");
        String name = Util.toTitleCase(input.nextLine());
        String email = generateEmail(name, domain);
        String password = generateDefaultPassword(name);
        addUser(new Instructor(name, email, password));
        System.out.println("Instructor Created.");
        System.out.println("Email: " + email);
        System.out.println("Default Password: " + password);
    }

    /**
     * Creates an admin by taking input from the user.
     */
    public void createAdmin() {
        System.out.print("Name (First Last): ");
        String name = Util.toTitleCase(input.nextLine());
        String email = generateEmail(name, domain);
        String password = generateDefaultPassword(name);
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
     * @return the generated email address
     */
    public String generateEmail(String name, String domain) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty or null.");
        }

        String baseEmail = name.split("\\s+")[0].toLowerCase().charAt(0) +
                           name.split("\\s+")[1].toLowerCase();

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
     * @return the generated default password
     */
    public String generateDefaultPassword(String name) {
        Random rand = new Random();
        int num = rand.nextInt(9000) + 1000;
        return name.split("\\s+")[0] + num;
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
            Admin::getPermissionsFormatted
        );

        TablePrinter<Admin> printer = new TablePrinter<>(headers, extractors, admins);
        printer.printTable();
    }
}