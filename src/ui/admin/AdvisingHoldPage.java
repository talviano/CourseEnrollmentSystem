/**
 * The AdvisingHoldPage class provides a user interface for managing advising holds
 * on student accounts. It allows an admin to toggle advising holds for individual students
 * or set advising holds for all students in the system.
 *
 * Responsibilities:
 * - Turning on advising holds for all students.
 * - Toggling the advising hold status for a specific student.
 * - Returning to the admin menu.
 *
 * Usage:
 * AdvisingHoldPage advisingHoldPage = new AdvisingHoldPage(admin, accountManager);
 * advisingHoldPage.display();
 *
 * @version Apr 25, 2025
 */
package ui.admin;

import java.util.List;

import model.Admin;
import model.Student;
import model.User;
import system.AccountManager;
import ui.Page;
import util.Util;

public class AdvisingHoldPage extends Page {
    private Admin admin;
    private AccountManager accountManager;

    /**
     * Constructs an AdvisingHoldPage with the specified admin and account manager.
     *
     * @param admin the admin currently logged in
     * @param accountManager the account manager to interact with
     */
    public AdvisingHoldPage(Admin admin, AccountManager accountManager) {
        super(admin);
        this.admin = admin;
        this.accountManager = accountManager;
    }

    /**
     * Returns the menu options specific to the AdvisingHoldPage.
     *
     * @return a list of menu option strings
     */
    @Override
    public List<String> getMenuOptions() {
        return List.of("Turn all advising holds on", "Toggle Student Advising Hold", "Return to Admin Menu");
    }

    /**
     * Returns the title of the menu for the AdvisingHoldPage.
     *
     * @return the menu title
     */
    @Override
    public String getMenuTitle() {
        return "Manage Advising Hold Actions";
    }

    /**
     * Handles the action corresponding to the user's menu choice.
     *
     * @param choice the user's menu choice
     */
    @Override
    public void handleAction(int choice) {
        switch (choice) {
            case 1:
                System.out.println(
                        "This action will set all students advising holds to true and render previous advising hold statuses unrecoverable.");
                System.out.print("Are you sure you want to go through with this action? (y/n): ");
                String value = input.nextLine();
                if (Util.yesNoToBoolean(value)) {
                    System.out.print("Please type CONFIRM to confirm this action (or press enter to quit): ");
                    String value2 = input.nextLine();
                    if (value2.equals("CONFIRM")) {
                        accountManager.setAllAdvisingHoldsTrue();
                    }
                }
                break;
            case 2:
                String id = promptUserId();
                User user = accountManager.getUserByIdOrEmail(id);
                if (user instanceof Student student) {
                    boolean status = student.hasAdvisingHold();
                    System.out.println("Advising hold " + "(" + (status ? "ON" : "OFF") + ")");
                    System.out.println("Press ENTER to toggle:");
                    student.setAdvisingHold(!status);
                    System.out.println("Advising hold is now " + (student.hasAdvisingHold() ? "ON" : "OFF") + ".");
                } else {
                    System.out.println("No student found with the given ID.");
                }
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
}

