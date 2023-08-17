import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExchangeRateManager {
	private List<ExchangeRate> exchangeRatesList;

	public ExchangeRateManager() {
		exchangeRatesList = new ArrayList<>();
		// Add some initial exchange rates
		exchangeRatesList.add(new ExchangeRate("USD", "EUR", 0.85));
		exchangeRatesList.add(new ExchangeRate("USD", "JPY", 110.0));
	}
//to add new exchange
	private boolean addNewExchangeRate() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the currency you're exchanging from: ");
		String exchangeFrom = scanner.next();

		System.out.print("Enter the currency you're exchanging to: ");
		String exchangeTo = scanner.next();

		System.out.print("Enter the Currency Rate: ");
		double rate = scanner.nextDouble();

		boolean result = addExchangeRate(exchangeFrom, exchangeTo, rate);
		if (result) {
			System.out.println("Exchange rate added successfully!");
		} else {
			System.out.println("Unable to add Exchange rate");
		}
		return result;
	}

	public boolean addExchangeRate(String currencyFrom, String currencyTo, double rate) {
		if (rate < 0) {
			System.out.println("Invalid input: Exchange rate cannot be negative.");
			return false;
		}

		ExchangeRate newRate = new ExchangeRate(currencyFrom, currencyTo, rate);

		// Check if the rate already exists
		for (ExchangeRate existingRate : exchangeRatesList) {
			if (existingRate.getCurrencyFrom().equals(currencyFrom) && existingRate.getCurrencyTo().equals(currencyTo)) {
				System.out.println("Exchange rate already exists.");
				return false;
			}
		}

		// Add the new rate
		exchangeRatesList.add(newRate);
		return true;
	}

	public void deleteExchangeRate(String currencyFrom, String currencyTo) {
		exchangeRatesList.removeIf(rate -> rate.getCurrencyFrom().equals(currencyFrom) && rate.getCurrencyTo().equals(currencyTo));
	}

	public void displayExchangeRates() {
		System.out.println("Current Exchange Rates:");
		for (int i = 0; i < exchangeRatesList.size(); i++) {
			ExchangeRate rate = exchangeRatesList.get(i);
			System.out.println((i + 1) + ") " + rate);
		}
	}

	public static void main(String[] args) {
		ExchangeRateManager manager = new ExchangeRateManager();
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to the Exchange Rate Manager!");

		while (true) {
			System.out.println("\nMenu:");
			System.out.println("1. Add New Exchange Rate");
			System.out.println("2. Display Exchange Rates");
			System.out.println("3. Delete Exchange Rate");
			System.out.println("4. Exit");
			System.out.print("Select an option: ");

			if (scanner.hasNextInt()) {
				int choice = scanner.nextInt();

				switch (choice) {
				case 1:
					manager.addNewExchangeRate();
					break;
				case 2:
					manager.displayExchangeRates();
					break;
				case 3:
					System.out.print("Enter the index of the exchange rate to delete: ");
					if (scanner.hasNextInt()) {
						int indexToDelete = scanner.nextInt();
						if (indexToDelete >= 1 && indexToDelete <= manager.getExchangeRatesList().size()) {
							ExchangeRate rateToDelete = manager.getExchangeRatesList().get(indexToDelete - 1);
							manager.deleteExchangeRate(rateToDelete.getCurrencyFrom(), rateToDelete.getCurrencyTo());
							System.out.println("Exchange rate deleted successfully!");
						} else {
							System.out.println("Invalid index. Please choose a valid index.");
						}
					} else {
						System.out.println("Please enter a valid integer index.");
						scanner.next(); // Clear the invalid input
					}
					break;
				case 4:
					System.out.println("Goodbye!");
					scanner.close();
					System.exit(0);
				default:
					System.out.println("Invalid option. Please choose a valid option.");
				}
			} else {
				System.out.println("Invalid input. Please enter a valid option.");
				scanner.next(); // Clear the invalid input
			}
		}
	}

	private List<ExchangeRate> getExchangeRatesList() {
		return exchangeRatesList;
	}

	public boolean hasExchangeRate(String currencyFrom, String currencyTo) {
		return false;
	}

	public boolean deleteExchangeRate(int i) {
		if (i < 1 || i > exchangeRatesList.size()) {
			System.out.println("Invalid index. Please choose a valid index.");
			return false;
		}

		ExchangeRate rateToDelete = exchangeRatesList.get(i - 1);
		deleteExchangeRate(rateToDelete.getCurrencyFrom(), rateToDelete.getCurrencyTo());
		System.out.println("Exchange rate deleted successfully!");
		return true;
	}

	public boolean displayExchangeRate(int i) {
		if (i < 1 || i > exchangeRatesList.size()) {
			System.out.println("Invalid index. Please choose a valid index.");
			return false;
		}

		ExchangeRate rateToDisplay = exchangeRatesList.get(i - 1);
		System.out.println("Exchange Rate Details:");
		System.out.println("From: " + rateToDisplay.getCurrencyFrom());
		System.out.println("To: " + rateToDisplay.getCurrencyTo());
		System.out.println("Rate: " + rateToDisplay.getRate());
		return true;
	}

	public boolean displayExchangeRate(String string) {
		// TODO Auto-generated method stub
		return false;
	}
}