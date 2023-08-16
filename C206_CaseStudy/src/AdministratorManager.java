import java.util.ArrayList;
import java.util.List;

public class AdministratorManager {
    private List<Administrator> users = new ArrayList<>();


    public static void main(String[] args) {
        AdministratorManager administratorManager = new AdministratorManager();
        displayMenu(administratorManager);
    }

    private static void displayMenu(AdministratorManager administratorManager) {
        int option = 0;
        while (option != 4) {
            System.out.println("========= Money Exchange User Management =========");
            System.out.println("1. View All Users");
            System.out.println("2. Add a User");
            System.out.println("3. Delete a User");
            System.out.println("4. Exit");
            System.out.println("===============================================");

            option = Helper.readInt("Enter an option > ");

            switch (option) {
                case 1:
                    administratorManager.viewAllUsers();
                    break;
                case 2:
                    addUser(administratorManager);
                    break;
                case 3:
                    deleteUser(administratorManager);
                    break;
                case 4:
                    System.out.println("Thank you, goodbye!");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void addUser(AdministratorManager administratorManager) {
        String fullName = Helper.readString("Enter full name: ");
        String email = Helper.readString("Enter email: ");
        String contactNum = Helper.readString("Enter contact number: ");
        String accountID = Helper.readString("Enter account ID: ");
        String customerID = Helper.readString("Enter customer ID: ");
        String userRole = Helper.readString("Enter user role: ");

        // Validate contact number
        if (!contactNum.matches("[89]\\d{7}")) {
            System.out.println("Invalid Mobile number!");
            return;
        }

        boolean result = administratorManager.addUser(fullName, email, contactNum, accountID, customerID, userRole);

        if (result) {
            System.out.println("User added successfully.");
        } else {
            System.out.println("Unable to add user. Please try again.");
        }
    }


    private static void deleteUser(AdministratorManager administratorManager) {
        String email = Helper.readString("Enter email of the user to delete: ");
        boolean deleted = administratorManager.deleteUser(email);
        if (deleted) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User not found or unable to delete user. Please try again.");
        }
    }

    // Validation logic and user creation
    public boolean addUser(String fullName, String email, String contactNum, String accountID, String customerID, String userRole) {
        if (fullName.isEmpty() || email.isEmpty() || contactNum.isEmpty() || accountID.isEmpty() || customerID.isEmpty() || userRole.isEmpty()) {
            System.out.println("All fields must not be left empty!");
            return false;
        }

        // Check if a user with the same email already exists
        for (Administrator existingUser : users) {
            if (existingUser.getEmail().equals(email)) {
                System.out.println("A user with the same email already exists.");
                return false;
            }
        }

        // Create a new user and add it to the list
        Administrator newUser = new Administrator(fullName, email, contactNum, accountID, customerID, userRole);
        users.add(newUser);
        return true;
    }


    // View users
    public void viewAllUsers() {
        if (users.isEmpty()) {
            System.out.println("===== Registered Users in Money Exchange System =====");
            System.out.println("No users registered in the system.");
            return;
        }

        System.out.println("===== Registered Users in Money Exchange System =====");
        for (Administrator user : users) {
            System.out.println("Full Name: " + user.getFullName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Contact Number: " + user.getContactNum());
            System.out.println("Role: " + user.getUserRole());
            System.out.println("---------------------------");
        }
    }


    // Delete user
    public boolean deleteUser(String email) {
        Administrator userToDelete = null;

        for (Administrator user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                userToDelete = user;
                break;
            }
        }

        if (userToDelete != null) {
            users.remove(userToDelete);
            System.out.println("User with email: " + email + " has been deleted successfully.");
            return true;
        } else {
            System.out.println("User with email: " + email + " not found. Deletion failed.");
            return false;
        }
    }

	public Administrator getUserByEmail(String string) {
		   for (Administrator user : users) {
	            if (user.getEmail().equalsIgnoreCase(string)) {
	                return user;
	            }
	        }
	        return null;
	    }
	}





