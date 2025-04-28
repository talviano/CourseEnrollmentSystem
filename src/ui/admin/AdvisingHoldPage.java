package ui.admin;

import java.util.List;

import model.Admin;
import model.Student;
import model.User;
import system.AccountManager;
import ui.Page;
import util.Util;

/**
 * The AdvisingHoldPage class provides a user interface for managing advising holds
 * on student accounts. It allows an admin to toggle advising holds for individual students
 * or set advising holds for all students in the system.
 *
 * @version Apr 25, 2025
 */
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
    * Displays message if user if logging out
    */
    @Override
    public void handleLogout() {
        //override default use to do nothing
    };

    /**
     * Handles the action based on to the user's menu choice.
     *
     * @param choice the user's menu choice
     */
    @Override
    public void handleAction(int choice) {
        switch (choice) {
            case 1:
                System.out.println();
                System.out.println("WARNING: This action will set ALL students' advising holds to ON.");
                System.out.println("This will overwrite all previous advising hold statuses and cannot be undone.");
                System.out.println();
                System.out.print("Are you sure you want to proceed? (Y/N): ");
                String confirmFirst = input.nextLine().strip();
                
                if (Util.yesNoToBoolean(confirmFirst)) {
                    System.out.print("Type 'CONFIRM' to finalize this action, or press ENTER to cancel: ");
                    String confirmSecond = input.nextLine().strip();
                    if (confirmSecond.equals("CONFIRM")) {
                        accountManager.setAllAdvisingHoldsTrue();
                        System.out.println("All advising holds have been set to ON.");
                    } else {
                        System.out.println("Action canceled.");
                    }
                } else {
                    System.out.println("Action canceled.");
                }
                break;
            case 2:
                String id = promptUserId();
                User user = accountManager.getUserByIdOrEmail(id);
                if (user instanceof Student student) {
                    boolean currentStatus = student.hasAdvisingHold();
                    System.out.println("Current advising hold status: " + (currentStatus ? "ON" : "OFF"));
                    System.out.print("Press ENTER to toggle advising hold, or type 'CANCEL' to abort: ");
                    String toggleInput = input.nextLine().strip();
                    if (!toggleInput.equalsIgnoreCase("CANCEL")) {
                        student.setAdvisingHold(!currentStatus);
                        System.out.println("Advising hold is now: " + (student.hasAdvisingHold() ? "ON" : "OFF"));
                    } else {
                        System.out.println("No changes made.");
                    }
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

