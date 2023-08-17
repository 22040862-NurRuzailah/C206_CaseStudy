import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CurrencyManager {
    private List<Currency> currencies;

    public CurrencyManager() {
        currencies = new ArrayList<>();
    }

    public static void main(String[] args) {
        CurrencyManager currencyManager = new CurrencyManager();
        currencyManager.run();
    }

    public void run() {
        int option = 0;

        currencies.add(new Currency("Euro", "EUR"));
        currencies.add(new Currency("United States Dollar", "USD"));
        currencies.add(new Currency("Singapore Dollar", "SGD"));

        while (option != 4) {
            displayMenu();
            option = Helper.readInt("Enter an option > ");

            switch (option) {
                case 1:
                    displayCurrencies();
                    break;
                case 2:
                    addCurrencyWithInput();
                    break;
                case 3:
                    deleteCurrency();
                    break;
                case 4:
                    System.out.println("Thank you, goodbye!");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public void displayCurrencies() {
        if (currencies.isEmpty()) {
            System.out.println("No currencies to display.");
            return;
        }

        System.out.println("================================ Money Exchange Currency List ==================================");
        System.out.println(String.format("%-40s %-30s", "Currency Name", "Currency Code"));

        for (Currency currency : currencies) {
            System.out.println(String.format("%-40s %-30s", currency.getName(), currency.getCode()));
        }

        System.out.println();
    }

    public void addCurrencyWithInput() {
        String name = Helper.readString("Enter Currency Name: ");
        String code = Helper.readString("Enter Currency Code: ");

        if (addCurrency(name, code)) {
            System.out.println("Currency added successfully.");
        }
    }

    public boolean addCurrency(String name, String code) {
        if (!containsCurrencyWithCode(code)) {
            Currency newCurrency = new Currency(name, code);
            currencies.add(newCurrency);
            System.out.println("Currency added successfully.");
        } else {
            System.out.println("Currency with code " + code + " already exists.");
        }
		return false;
    }

    public boolean containsCurrencyWithCode(String code) {
        String codeUpperCase = code.toUpperCase();
        for (Currency currency : currencies) {
            if (currency.getCode().equalsIgnoreCase(codeUpperCase)) {
                return true;
            }
        }
        return false;
    }

    public void deleteCurrency() {
        if (currencies.isEmpty()) {
            System.out.println("No currencies to delete.");
            return;
        }

        String codeToDelete = Helper.readString("Enter Currency Code to delete: ");
        Iterator<Currency> iterator = currencies.iterator();
        boolean currencyDeleted = false;

        while (iterator.hasNext()) {
            Currency currency = iterator.next();
            if (currency.getCode().equalsIgnoreCase(codeToDelete)) {
                boolean confirmDeletion = Helper.readBoolean("Confirm deletion of this currency: " + currency.getName() + " (" + currency.getCode() + ")? (Y/N): ");
                if (confirmDeletion) {
                    iterator.remove();
                    System.out.println("Currency with code: " + codeToDelete + " has been deleted successfully.");
                    currencyDeleted = true;
                } else {
                    System.out.println("Currency deletion with code: " + codeToDelete + " has been cancelled. No deletion has been made.");
                }
                break;
            }
        }

        if (!currencyDeleted) {
            System.out.println("Currency with code: " + codeToDelete + " not found. No deletion has been made.");
        }
    }



    public void displayMenu() {
        System.out.println("===== Currency Manager =====");
        System.out.println("1. Display all currencies");
        System.out.println("2. Add new currency");
        System.out.println("3. Delete currency");
        System.out.println("4. Exit");
    }

    public List<Currency> getCurrencyList() {
        return currencies;
    }
}
