import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class AccountManagerTest {

    private AccountManager accountManager;

    	//================ TESTING FOR ADD ACCOUNT =====================
    @Before
    public void setUp() {
        accountManager = new AccountManager();
    }
    @Test
    // NORMAL TEST CASE
    // CREATE A VALID ACCOUNT WITH ALL REQUIREMENTS MET
    public void testValidAccountCreation() {
        boolean result = accountManager.addAccount("Nur Ruzailah", "22040862@myrp.edu.sg", "99999999", "Password123");
        assertTrue(result);
    }
    
    @Test
    //BOUNDARY TEST CASE
    // CREATE A VALID ACCOUNT BUT PASSWORD REQUIREMENTS ARE NOT MET
    public void testInvalidPassword() {
        boolean result = accountManager.addAccount("Nur Ruzailah", "22040862@myrp.edu.sg", "99999999", "hahapw");
        assertFalse(result);
    }
    @Test
    //ERROR TEST CASE
    // CREATE A DUPLICATE ACCOUNT
    public void testDuplicateAccountDetail() {
        // Create account first
        accountManager.addAccount("Nur Ruzailah", "22040862@myrp.edu.sg", "99998888", "Password123");

        // Creating another account but with the same email
        boolean result = accountManager.addAccount("Nurul Ruzailah", "22040862@myrp.edu.sg", "98989898", "HelloWorld123");
        assertFalse(result);
    }
    // ===================== TESTING FOR DELETE ACCOUNT =====================
    @Test
    //will delete existing account 
    //NORMAL
    public void testDeleteExistingAccount() {
        // Adding an account first
        accountManager.addAccount("Fiz", "Fiz@gmail.com", "89898989", "Hellooo1234");

        // Attempt to delete
        boolean result = accountManager.deleteAccount("Fiz@gmail.com");
        assertTrue(result);

        // Check accountList to see if it is deleted
        List<Account> accountList = accountManager.getAccountList();
        assertEquals(0, accountList.size());
    }
    @Test
    // will delete nonexisting account
    // BOUNDARY
    public void testDeleteNonExistingAccount() {
        // attempting to delete nonexisting account
        boolean result = accountManager.deleteAccount("nonexistentuser@gmail.com");
        assertFalse(result);
    }
    @Test
    // will attempt to delete account with an invalid identifier
    // ERROR
    public void testDeleteAccountWithInvalidIdentifier() {
        // the account to delete
        accountManager.addAccount("Shah", "Shah@testing.com", "85858585", "Meow102900");

        // invalid identifier
        boolean result = accountManager.deleteAccount("invalid_identifier");
        assertFalse(result);

        // check accountList if account is still there or not
        List<Account> accountList = accountManager.getAccountList();
        assertEquals(1, accountList.size());
    }
    
    	// ============== TESTING FOR VIEWING ACCOUNT =================
    @Test
    //NORMAL
    // WILL VIEW AND LIST ALL ACCOUNT AVAILABLE
    public void testViewExistingAccounts() {
        // Add accounts first
        accountManager.addAccount("Karl", "karl@gmail.com", "98989899", "Mystery1230");
        accountManager.addAccount("Melody", "melody@hotmail.com", "96969595", "Secret01990");

        // View accounts
        String expectedOutput = "================================ Money Exchange Account List ==================================\n" +
                String.format("%-20s %-30s %-15s %-15s %-15s %-15s", "Username", "Email", "Contact Num", "Customer ID", "Account ID", "Password") + "\n" +
                String.format("%-20s %-30s %-15s %-15s %-15s %-15s", "Karl", "karl@gmail.com", "98989899", "CUS2000", "ACC1000", "Mystery1230") + "\n" +
                String.format("%-20s %-30s %-15s %-15s %-15s %-15s", "Melody", "melody@hotmail.com", "96969595", "CUS2001", "ACC1001", "Secret01990") + "\n\n";
        String actualOutput = getAccountsOutput();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    //BOUNDARY
    //WILL CHECK IF THE LIST IS EMPTY OR NOT
    public void testViewAllAccountsEmpty() {
        // VIEW LIST WHEN THERE IS NO ACCOUNTS
        List<Account> accountList = accountManager.getAccountList();
        assertTrue(accountList.isEmpty());
    }

    @Test
    //ERROR
    // null represent a situation where the system has errors, unable to load the list
    public void testViewAllAccountsError() {
        // Simulate an error by setting accountList to null
        accountManager = null;

        // Attempt to view all accounts
        try {
            List<Account> accountList = accountManager.getAccountList();
            fail("Expected NullPointerException but no exception was thrown.");
        } catch (NullPointerException e) {
            // The test will pass if a NullPointerException is thrown
            assertNotNull(e.getMessage());
        }
    }

    private String getAccountsOutput() {
    	//stringbuilder allows modification such as append, insert, delete
    	// will return a string to display account in tabular format
        StringBuilder sb = new StringBuilder();
        List<Account> accountList = accountManager.getAccountList();
        if (accountList.isEmpty()) {
            sb.append("No accounts available to list.");
        } else {
            sb.append("================================ Money Exchange Account List ==================================\n");
            sb.append(String.format("%-20s %-30s %-15s %-15s %-15s %-15s", "Username", "Email", "Contact Num", "Customer ID", "Account ID", "Password")).append("\n");
            for (Account account : accountList) {
                sb.append(String.format("%-20s %-30s %-15s %-15s %-15s %-15s",
                        account.getUsername(), account.getEmail(), account.getContactNum(),
                        account.getCustomerID(), account.getAccountID(), account.getPassword())).append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}