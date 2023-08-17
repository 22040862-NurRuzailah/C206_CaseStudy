import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CurrencyManagerTest {
    private CurrencyManager currencyManager;

    @Before
    public void setUp() {
        currencyManager = new CurrencyManager();
    }

    @Test
    public void testAddCurrency_Normal() {
        currencyManager.addCurrency("Japanese Yen", "JPY");

        assertEquals(1, currencyManager.getCurrencyList().size());
        assertEquals("JPY", currencyManager.getCurrencyList().get(0).getCode());
    }

    @Test
    public void testAddCurrency_Boundary() {
        currencyManager.addCurrency("Currency 1", "CUR1");
        currencyManager.addCurrency("Currency 2", "CUR2");
        currencyManager.addCurrency("Currency 3", "CUR3");

        assertEquals(3, currencyManager.getCurrencyList().size());
    }

    @Test
    public void testAddCurrency_Error() {
        currencyManager.addCurrency("Currency 1", "CUR1");
        currencyManager.addCurrency("Currency 1", "CUR1"); // Trying to add a duplicate currency

        assertEquals(1, currencyManager.getCurrencyList().size());
    }

    @Test
    public void testViewCurrencies_Normal() {
        assertTrue(currencyManager.getCurrencyList().isEmpty());
    }

    @Test
    public void testViewCurrencies_Boundary() {
        // Simulate adding currencies with user input
        Helper.setUserInput("Euro\nEUR");
        currencyManager.addCurrencyWithInput();

        Helper.setUserInput("United States Dollar\nUSD");
        currencyManager.addCurrencyWithInput();

        // Now check the size of the currency list
        assertEquals(2, currencyManager.getCurrencyList().size());
    }

    @Test
    public void testViewCurrencies_Error() {
        // No currencies added, list should be empty
        assertTrue(currencyManager.getCurrencyList().isEmpty());
    }

    @Test
    public void testDeleteCurrency_Normal() {
        // Simulate adding currencies with user input
        Helper.setUserInput("Euro\nEUR");
        currencyManager.addCurrencyWithInput();

        Helper.setUserInput("United States Dollar\nUSD");
        currencyManager.addCurrencyWithInput();

        // Simulate user input for deleting a currency
        Helper.setUserInput("EUR\nY"); // Assuming 'Y' for confirmation
        currencyManager.deleteCurrency();

        // Now check the size of the currency list and the remaining currency's code
        assertEquals(1, currencyManager.getCurrencyList().size());
        assertEquals("USD", currencyManager.getCurrencyList().get(0).getCode());
    }

    @Test
    public void testDeleteCurrency_Boundary() {
        currencyManager.addCurrency("Euro", "EUR");
        currencyManager.addCurrency("United States Dollar", "USD");

        // Simulate user input for deleting a non-existent currency
        Helper.setUserInput("GBP");
        currencyManager.deleteCurrency();

        assertEquals(2, currencyManager.getCurrencyList().size()); // No currency should be deleted
    }

    @Test
    public void testDeleteCurrency_Error() {
        // Simulate adding currencies with user input
        Helper.setUserInput("Euro\nEUR");
        currencyManager.addCurrencyWithInput();

        Helper.setUserInput("United States Dollar\nUSD");
        currencyManager.addCurrencyWithInput();

        // Simulate user input for deleting a non-existent currency
        Helper.setUserInput("GBP");
        currencyManager.deleteCurrency();

        assertEquals(2, currencyManager.getCurrencyList().size()); // No currency should be deleted
    }
}
