import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
    private List<Transaction> transactionList = new ArrayList<>();
    private int transactionIdCounter = 1000; // min ID generator

    public static void main(String[] args) {
        TransactionManager transactionManager = new TransactionManager();

        transactionManager.run();
    }

    public void run() {
        int option = 0;
        while (option != 4) {
            displayMenu();
            option = Helper.readInt("Enter an option > ");
            handleOption(option);
        }
    }

    private static void displayMenu() {
        System.out.println("========= Money Exchange Transactions Management =========");
        System.out.println("1. View All Transactions");
        System.out.println("2. Add a Transaction");
        System.out.println("3. Delete a Transaction");
        System.out.println("4. Exit");
        System.out.println("====================================================");
    }
    private void handleOption(int option) {
        switch (option) {
            case 1:
                viewAllTransactions();
                break;
            case 2:
                addTransaction();
                break;
            case 3:
                deleteTransaction();
                break;
            case 4:
                System.out.println("Thank you, goodbye!");
                break;
            default:
                System.out.println("Invalid option");
        }
    }
//ADD TRANSACTION
    private void addTransaction() {
        String customerId = Helper.readString("Enter Customer ID: ");
        String date = Helper.readString("Enter Date: ");
        int amount = Helper.readInt("Amount to be Exchanged: ");
        String currency = Helper.readString("Enter Desired Currency: ");

        boolean result = addTransaction(customerId, date, amount, currency);
        if (result) {
            System.out.println("Transaction added successfully.");
        } else {
            System.out.println("Unable to add transaction. Please try again.");
        }
    }
// delete transaction
    private void deleteTransaction() {
        String transactionID = Helper.readString("Enter transaction ID of the transaction to delete: ");
        deleteTransaction(transactionID);
    }

    private boolean checkTransactionID(String transactionID) {
        boolean correctLength = transactionID.length() >= 6;
        boolean containsUpperCase = false;
        boolean containsLowerCase = false;
        boolean containsNumber = false;
        for (char ch : transactionID.toCharArray()) {
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

    public boolean addTransaction(String customerId, String date, int amount, String currency) {
        if (customerId.isEmpty() || date.isEmpty() || currency.isEmpty()) {
            System.out.println("All fields must not be left empty!");
            return false;
        }

        String transactionID = generateTransactionID();
        int balance = generateNonNegativeBalance();

        Transaction newTransaction = new Transaction(customerId, date, amount, currency, balance, transactionID);
        transactionList.add(newTransaction);
        return true;
    }

    private String generateTransactionID() {
        String prefix = "TXN";
        String transactionID = prefix + transactionIdCounter;
        transactionIdCounter++;
        return transactionID;
    }

    private int generateNonNegativeBalance() {
        int minBalance = 0;       
        int maxBalance = 1000;  // Example: maximum balance can be 1000 units
        return minBalance + (int) (Math.random() * (maxBalance - minBalance + 1));
    }

    public void viewAllTransactions() {
        if (transactionList.isEmpty()) {
            System.out.println("No transactions to display.");
            return;
        }

        System.out.println("================================== Money Exchange Transactions List ====================================");
        System.out.println(String.format("%-20s %-30s %-15s %-15s %-20s %-20s", "Customer ID", "Date", "Amount", "Currency", "Transaction ID", "Balance"));
        for (Transaction transaction : transactionList) {
            System.out.println(String.format("%-20s %-30s %-15s %-15s %-20s %-20s",
                    transaction.getCustomerID(), transaction.getDate(), transaction.getAmount(), transaction.getCurrency(), transaction.getTransactionID(), transaction.getBalance()));
        }
        System.out.println();
    }
//delete
    public boolean deleteTransaction(String transactionID) {
        Transaction transactionToDelete = findTransactionById(transactionID);

        if (transactionToDelete != null) {
            boolean confirmDeletion = Helper.readBoolean("Confirm deletion of this transaction: " + transactionID + "? (Y/N): ");
            if (confirmDeletion) {
                transactionList.remove(transactionToDelete);
                System.out.println("Transaction ID: " + transactionID + " has been deleted successfully.");
                return true;
            } else {
                System.out.println("Transaction ID: " + transactionID + " has been cancelled. No deletion has been made.");
                return false;
            }
        } else {
            System.out.println("Transaction ID: " + transactionID + " not found. Please try again. ");
            return false;
        }
    }

    private Transaction findTransactionById(String transactionID) {
        for (Transaction transaction : transactionList) {
            if (transaction.getTransactionID().equalsIgnoreCase(transactionID)) {
                return transaction;
            }
        }
        return null;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }
}

