import java.util.ArrayList;

public class AdministratorManager {
    private ArrayList<Administrator> users = new ArrayList<>();

    public static void main(String[] args) {
        AdministratorManager administrativeManager = new AdministratorManager();
        int option = 0;
        
        while (option != 4) {
            displayMenu();
            option = Helper.readInt("Enter an option > ");

            if (option == 1) {
                administrativeManager.viewAllUserAccounts();
            } else if (option == 2) {
                addUser(administrativeManager);
            } else if (option == 3) {
                deleteUser(administrativeManager);
            } else if (option == 4) {
                System.out.println("Thank you, goodbye!");
            } else {
                System.out.println("Invalid option");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("========= Money Exchange Account Management =========");
        System.out.println("1. View All User Account");
        System.out.println("2. Add User");
        System.out.println("3. Delete User");
        System.out.println("4. Exit");
        System.out.println("====================================================");
    }

    private static void addUser(AdministratorManager administratorManager) {
        String fullname = Helper.readString("Enter your name: ");
        String email = Helper.readString("Enter email: ");
        String contactNum = Helper.readString("Enter contact number: ");
        String userrole = Helper.readString("Enter your role: ");
        
        boolean result = administratorManager.addUserToSystem(fullname, email, contactNum, userrole, userrole, userrole);
        
        if (result) {
            System.out.println("User added successfully.");
        } else {
            System.out.println("Unable to add User. Please try again.");
        }
    }

    private static void deleteUser(AdministratorManager administratorManager) {
        String email = Helper.readString("Enter email of the user to delete: ");
        Administrator userToDelete = administratorManager.getUserByEmail(email);

        if (userToDelete != null) {
            boolean deleted = administratorManager.deleteUser(userToDelete);
            
            if (deleted) {
                System.out.println("User with email " + email + " has been deleted successfully.");
            } else {
                System.out.println("Unable to delete the user with email " + email + ". Please try again.");
            }
        } else {
            System.out.println("User with email " + email + " not found in the system.");
        }
    }

    public boolean addUserToSystem(String fullname, String email, String contactNum, String accountID, String customerID , String userrole) {
        Administrator newUser = new Administrator(fullname, email, contactNum, accountID, customerID, userrole);
        return users.add(newUser);
    }

    public Administrator getUserByEmail(String email) {
        for (Administrator user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public boolean deleteUser(Administrator userToDelete) {
        return users.remove(userToDelete);
    }

    public void viewAllUserAccounts() {
        System.out.println("===== Registered Users in Money Exchange System =====");

        if (users.isEmpty()) {
            System.out.println("No users registered in the system.");
        } else {
            for (Administrator user : users) {
                System.out.println("Full Name: " + user.getFullName());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Contact Number: " + user.getContactNum());
                System.out.println("Role: " + user.getUserRole());
                System.out.println("---------------------------");
            }
        }
    }
}