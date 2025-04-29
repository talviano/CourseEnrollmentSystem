package ui.admin;

import java.util.ArrayList;
import java.util.List;

import model.Admin;
import model.Permissions;
import system.AccountManager;
import ui.Page;

/**
 * The AdminPermissionsPage class provides a user interface for managing the permissions
 * of a specific admin. It allows an admin to grant or revoke permissions for another admin
 * and view all admins in the system.
 *
 * @version Apr 25, 2025
 */
public class AdminPermissionsPage extends Page {
    /**
     * The admin currently logged in and managing permissions.
     */
    private Admin admin;

    /**
     * The target admin whose permissions are being managed.
     */
    private Admin targetAdmin;

    /**
     * The account manager used to manage user accounts.
     */
    private AccountManager accountManager;

    /**
     * Constructs an AdminPermissionsPage with the specified admin, target admin, and account manager.
     *
     * @param admin the admin currently logged in
     * @param targetAdmin the target admin whose permissions are being managed
     * @param accountManager the account manager to interact with
     */
    public AdminPermissionsPage(Admin admin, Admin targetAdmin, AccountManager accountManager) {
        super(admin);
        this.admin = admin;
        this.targetAdmin = targetAdmin;
        this.accountManager = accountManager;
    }

    /**
     * Returns the menu options specific to the AdminPermissionsPage.
     *
     * @return a list of menu option strings
     */
    @Override
    public List<String> getMenuOptions() {
        List<String> menuOptions = new ArrayList<>();
        if (checkPermission(Permissions.ADMIN_MANAGEMENT)) {
            menuOptions.addAll(List.of("Grant All Permissions", "View all admins", "Grant Specific Permissions", "Revoke Specific Permission"));
        }
        menuOptions.add("Return to Admin Menu");
        return menuOptions;
    }

    /**
     * Returns the title of the menu for the AdminPermissionsPage.
     *
     * @return the menu title
     */
    @Override
    public String getMenuTitle() {
        return "Manage Users Actions";
    }

    /**
    * Displays message if user if logging out
    */
    @Override
    public void handleLogout() {
        //override default use to do nothing
    };

    /**
     * Handles the  action based on to the user's menu choice.
     *
     * @param choice the user's menu choice
     */
    @Override
    public void handleAction(int choice) {
        switch (choice) {
            case 1:
                targetAdmin.grantAllPermissions();
                System.out.println("All permissions granted to " + targetAdmin.getName() + ".");
                break;
            case 2:
                accountManager.viewAllAdmins();
                break;
            case 3:
                System.out.println("Possible Permissions: " + Permissions.toPermissionsString());
                System.out.print("Enter permission to grant (e.g. COURSE_MANAGEMENT): ");
                String permissionToGrant = input.nextLine().toUpperCase().strip().replace(" ", "_");
                targetAdmin.addPermission(permissionToGrant);
                System.out.println("Permission granted.");
                break;
            case 4: 
                System.out.println("Current Permissions: " + targetAdmin.getPermissions());
                System.out.print("Enter permission to revoke (e.g. COURSE_MANAGEMENT): ");
                String permissionToRevoke = input.nextLine().toUpperCase().strip().replace(" ", "_");
                targetAdmin.revokePermission(permissionToRevoke);
                System.out.println("Permission revoked.");
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid option");
        }
    }
}
