import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AccountManager {
	//THIS WILL GENERATE RANDOM UNIQUE ID TO USERS 
	private List<Account> accountList = new ArrayList<>();
	private int accountIdCounter = 1000; //min ID
	private int customerIdCounter = 2000; // min ID

	public static void main(String[] args) {
		AccountManager accountManager = new AccountManager();

		int option = 0;
		while (option != 4) {
			displayMenu();
			option = Helper.readInt("Enter an option > ");

			switch (option) {
			case 1:
				accountManager.viewAllAccounts();
				break;
			case 2:
				addAccount(accountManager);
				break;
			case 3:
				deleteAccount(accountManager);
				break;
			case 4:
				System.out.println("Thank you, goodbye!");
				break;
			default:
				System.out.println("Invalid option");
			}
		}
	}
	//display menu
	private static void displayMenu() {
		System.out.println("========= Money Exchange Account Management =========");
		System.out.println("1. View All Accounts");
		System.out.println("2. Add an Account");
		System.out.println("3. Delete an Account");
		System.out.println("4. Exit");
		System.out.println("====================================================");
	}
	//add account
	private static void addAccount(AccountManager accountManager) {
		String username = Helper.readString("Enter username: ");
		String email = Helper.readString("Enter email: ");
		String contactNum = Helper.readString("Enter contact number: ");
		String password = Helper.readString("Enter password: ");

		boolean result = accountManager.addAccount(username, email, contactNum, password);
		if (result) {
			System.out.println("Account added successfully.");
		} else {
			System.out.println("Unable to add account. Please try again.");
		}
	}
	// to delete account
	// will ask for email
	private static void deleteAccount(AccountManager accountManager) {
		String email = Helper.readString("Enter email of the account to delete: ");
		accountManager.deleteAccount(email);
	}
	//password policy
	private boolean checkPassword(String password) {
		//must be equal or more than 6
		boolean correctLength = password.length() >= 6;
		boolean containsUpperCase = false;
		boolean containsLowerCase = false;
		boolean containsNumber = false;
		//must have uppercase, lowercase and digit
		for (char ch : password.toCharArray()) {
			if (Character.isUpperCase(ch)) {
				containsUpperCase = true;
			} else if (Character.isLowerCase(ch)) {
				containsLowerCase = true;
			} else if (Character.isDigit(ch)) {
				containsNumber = true;
			}
		}

		return correctLength && containsUpperCase && containsLowerCase && containsNumber;
	}
	//requirements to add account
	public boolean addAccount(String username, String email, String contactNum, String password) {
		if (username.isEmpty() || email.isEmpty() || contactNum.isEmpty() || password.isEmpty()) {
			//cannot be left empty
			System.out.println("All fields must not be left empty!");
			return false;
		}
		//display message for password policy
		if (!checkPassword(password)) {
			System.out.println("Invalid password. Password must be more than 6 characters and contain at least one uppercase letter, one lowercase letter, and one number.");
			return false;
		}
		// must have @ symbol for email
		if (!email.contains("@")) {
			System.out.println("Invalid email format. Please try again.");
			return false;
		}
		//display if duplicate email
		for (Account account : accountList) {
			if (account.getEmail().equalsIgnoreCase(email)) {
				System.out.println("Email is taken by another user. Please try another email.");
				return false;
			}
		}
		//increment of ID for every account added
		String accountID = "ACC" + accountIdCounter++;
		String customerID = "CUS" + customerIdCounter++;

		Account newAccount = new Account(username, email, password, contactNum, customerID, accountID);
		accountList.add(newAccount);
		return true;
	}
	//view accounts
	public void viewAllAccounts() {
	    if (accountList.isEmpty()) {
	        System.out.println("No accounts to display.");
	        return;
	    }
	    
	    System.out.println("================================ Money Exchange Account List ==================================");
	    System.out.println(String.format("%-20s %-30s %-15s %-15s %-15s %-15s", "Username", "Email", "Contact Num", "Customer ID", "Account ID", "Balance"));
	    
	    Random random = new Random();
	    
	    for (Account account : accountList) {
	        List<Transaction> transactions = account.getTransactions();
	        if (!transactions.isEmpty()) {
	            Transaction lastTransaction = transactions.get(transactions.size() - 1);
	            System.out.println(String.format("%-20s %-30s %-15s %-15s %-15s %-15d",
	                    account.getUsername(), account.getEmail(), account.getContactNum(),
	                    account.getCustomerID(), account.getAccountID(), lastTransaction.getBalance()));
	        } else {
	            // Generate a random balance between 0 and 1000
	            int randomBalance = random.nextInt(901) + 0; // Generates a value between 0 and 1000
	            System.out.println(String.format("%-20s %-30s %-15s %-15s %-15s %-15d",
	                    account.getUsername(), account.getEmail(), account.getContactNum(),
	                    account.getCustomerID(), account.getAccountID(), randomBalance));
	        }
	    }
	}

	//delete account, will ask for email but not case sensitive
	public boolean deleteAccount(String email) {
		Account accountToDelete = null;

		for (Account account : accountList) {
			if (account.getEmail().equalsIgnoreCase(email)) {
				accountToDelete = account;
				break;
			}
		}
		//confirmation message to be displayed before deletion
		if (accountToDelete != null) {
			boolean confirmDeletion = Helper.readBoolean("Confirm deletion of this account: " + email + "? (Y/N): ");
			if (confirmDeletion) {
				accountList.remove(accountToDelete);
				System.out.println("Account with email: " + email + " has been deleted successfully.");
				return true;
			} else {
				System.out.println("Account deletion with email: " + email + " has been cancelled. No deletion has been made.");
				return false;
			}
		} else {
			//if account to delete does not exists
			System.out.println("Account with email: " + email + " not found. Please try again. ");
			return false;
		}
	}

	public List<Account> getAccountList() {
		return accountList;
	}
}
