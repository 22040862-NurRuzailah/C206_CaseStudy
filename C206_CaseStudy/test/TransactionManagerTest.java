import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TransactionManagerTest {
	
	private ArrayList<Transaction> transactionList;

    private TransactionManager transactionManager;

    //junit
    @Before
    public void setUp() {
        transactionManager = new TransactionManager();
    }

    @Test
    //valid  transaction
    public void testValidTransactionCreation() {
        boolean result = transactionManager.addTransaction("CU2000", "12/06/2005", 200, "TXN1000");
        assertTrue(result);
    }

    @Test
    public void testInvalidTransactionID() {
        boolean result = transactionManager.addTransaction("CU2000", "12/06/2005", 200, "tls202i4");
        assertTrue(result);
    }

    @Test
   
    public void testDuplicateTransactionDetail() {
        transactionManager.addTransaction("CU2000", "12/06/2005", 200, "TXN1000");
        boolean result = transactionManager.addTransaction("CU2000", "12/06/2005", 200, "TXN1000");
        assertTrue(result);
    }

    @Test
    public void testDeleteExistingTransaction() {
        transactionManager.addTransaction("CU2000", "02/08/2005", 200, "TXN1000");
        boolean result = transactionManager.deleteTransaction("TXN1000");
        assertTrue(result);
        List<Transaction> transactionList = transactionManager.getTransactionList();
        assertEquals(0, transactionList.size());
    }
    //test delete non existing transaction
    @Test
    public void testDeleteNonExistingTransaction() {
        boolean result = transactionManager.deleteTransaction("tls202i4");
        assertFalse(result);
    }
    //test delete transaction with invalid identifier
    @Test
    public void testDeleteTransactionWithInvalidIdentifier() {
        transactionManager.addTransaction("CU2000", "12/06/2005", 200, "TXN1000");
        boolean result = transactionManager.deleteTransaction("invalid_identifier");
        assertFalse(result);
        List<Transaction> transactionList = transactionManager.getTransactionList();
        assertEquals(1, transactionList.size());
    }

    @Test
    public void testViewExistingTransactions() {
        // Prepare test data
        transactionManager.addTransaction("CU2000", "12/06/2005", 200, "SGD");
        transactionManager.addTransaction("CU2001", "02/07/2005", 300, "USD");

        // Define the expected output
        String expectedOutput = "================================ Money Exchange Transactions List ==================================\n"
                + String.format("%-20s %-30s %-15s %-15s %-20s %-20s", "Customer ID", "Date", "Amount", "Currency",
                        "Transaction ID", "Balance") + "\n"
                + String.format("%-20s %-30s %-15d %-15s %-20s %-20s ", "CU2000", "12/06/2005", 200, "SGD", "TXN1000", transactionManager.getTransactionList().get(0).getBalance()) + "\n"
                + String.format("%-20s %-30s %-15d %-15s %-20s %-20s ", "CU2001", "02/07/2005", 300, "USD", "TXN1001", transactionManager.getTransactionList().get(1).getBalance()) + "\n\n";

        // Get - the actual output from the method being tested.
        String actualOutput = getTransactionsOutput();

        // Split expected and actual outputs into lines
        String[] expectedLines = expectedOutput.split("\n");
        String[] actualLines = actualOutput.split("\n");

        // Compare each line and provide detailed feedback on failure
        for (int i = 0; i < expectedLines.length && i < actualLines.length; i++) {
            assertEquals("Line " + i + " does not match.", expectedLines[i], actualLines[i]);
        }
    }


    @Test
    //view transactions
    public void testViewAllTransactionsEmpty() {
        List<Transaction> transactionList = transactionManager.getTransactionList();
        assertTrue(transactionList.isEmpty());
    }

    @Test
    public void testViewAllTransactionsError() {
        transactionManager = null;

        
        try {
            List<Transaction> transactionList = transactionManager.getTransactionList();
            fail("Expected NullPointerException but no exception was thrown.");
        } catch (NullPointerException e) {
            assertNotNull(e.getMessage());
        }
    }
    private String getTransactionsOutput() {
        StringBuilder sb = new StringBuilder();
        List<Transaction> transactionList = transactionManager.getTransactionList();
        if (transactionList.isEmpty()) {
            sb.append("No transactions available to list.");
        } else {
            sb.append("================================ Money Exchange Transactions List ==================================\n");
            sb.append(String.format("%-20s %-30s %-15s %-15s %-20s %-20s", "Customer ID", "Date", "Amount","Currency", "Transaction ID", "Balance")).append("\n");
            for (Transaction transaction : transactionList) {
                sb.append(String.format("%-20s %-30s %-15d %-15s %-20s %-20s",
                        transaction.getCustomerID(), transaction.getDate(), transaction.getAmount(),transaction.getCurrency(), transaction.getTransactionID(), transaction.getBalance())).append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

