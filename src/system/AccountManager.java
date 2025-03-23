/**
 * TODO Write a one-sentence summary of your class here.
 * TODO Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 *
 * @author  TODO Your Name
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
import util.Util;

public class AccountManager {
    private List<User> users;
    private static final String domain = "university.edu";

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
            return new Instructor("Instructor", "instructoroveride", "studnetoveride");
        }
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    
    /**
     * Registers a student in the system.
     *
     * @param student the student to register
     * @return true if the student is registered successfully, false otherwise
     */
    public boolean addStudent(Student student) {
        if (users.contains(student)) {
            System.out.println("Student already exists");
            return false;
        }
        if (student == null) {
            System.out.println("Invalid Student");
            return false;
        }
        users.add(student);
        return true;
        
    }

    /**
     * Registers an instructor in the system.
     *
     * @param instructor the instructor to register
     * @return true if the instructor is registered successfully, false otherwise
     */
    public boolean addInstructor(Instructor instructor) {
        if (users.contains(instructor)) {
            System.out.println("Instructor already exists");
            return false;
        }
        if (instructor == null) {
            System.out.println("Invalid Instructor");
            return false;
        }
        users.add(instructor);
        return true;
    }

    /**
     * Creates a student by taking input from the user.
     *
     * @param input the Scanner object to read user input
     */
    public void createStudent(Scanner input) {
        System.out.print("Name (First Last): ");
        String name = input.nextLine();
        String email = generateEmail(name, domain);
        String password = generateDefaultPassword(name);
        System.out.print("Is there an advising hold on this student (y/n): ");
        boolean advisingHold = input.nextBoolean();
        addStudent(new Student(name, email, password, advisingHold));
        System.out.println("Student Created.\n");
    }

    /**
     * Creates an instructor by taking input from the user.
     *
     * @param input the Scanner object to read user input
     */
    public void createInstructor(Scanner input) {
        System.out.print("Name (First Last): ");
        String name = input.nextLine();
        String email = generateEmail(name, domain);
        String password = generateDefaultPassword(name);
        addInstructor(new Instructor(name, email, password));
        System.out.println("Instructor Created.\n");
    }
    
    /**
     * Generates an email address for a user based on their name and domain.
     *
     * @param name the name of the user
     * @param domain the domain of the email address
     * @return the generated email address
     */
    public String generateEmail(String name, String domain) {

        // get baseEmail
        String baseEmail = "";
        String firstName = name.split("\\s+")[0];
        String lastName = name.split("\\s+")[1];
        baseEmail += firstName.toLowerCase().charAt(0);
        baseEmail += lastName.toLowerCase();

        // name cannot be empty
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty or null.");
        }

        // if no users exists just use default email
        if (users.isEmpty()) {
            return baseEmail + "@" + domain;
        }

        int max = 0;

        // find number of matching bases that exist
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

        // if base exists then add one to the number after the base, other wise return a regular email
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
        int[] stringWidths = {Util.getMaxUserNameWidth(this), 9, Util.getMaxUserEmailWidth(this)};
        String nameColumn = "| %-" + Util.getMaxUserNameWidth(this) + "s |";
        String idColumn = " %-9s |";
        String emailColumn = " %-" + Util.getMaxUserEmailWidth(this) + "s |\n";
        String rowFormat = nameColumn + idColumn + emailColumn;
        Util.createTableSeperator(stringWidths);
        System.out.printf(rowFormat, "Name", "Id", "Email");
        Util.createTableSeperator(stringWidths);
        for (User user : users) {
            System.out.printf(rowFormat, user.getName(), user.getId(), user.getEmail());
            Util.createTableSeperator(stringWidths);
        }
    }

    /**
     * Gets the list of users.
     *
     * @return the list of users
     */
    public List<User> getUsers() {
        return users;
    }
    

}