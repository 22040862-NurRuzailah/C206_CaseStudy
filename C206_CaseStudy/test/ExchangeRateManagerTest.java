import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ExchangeRateManagerTest {

    private static final int A = 0;
	private ExchangeRateManager manager;
// test case for add
    @Before
    public void setUp() {
        manager = new ExchangeRateManager();
    }

    @Test
    public void testAddNewExchangeRate() {
        boolean result = manager.addExchangeRate("SGD", "GBP", 1.75);
        assertTrue(result);
 
    } @Test
    public void testAddNewExchangeRateWithInvalidDetails() {
        boolean result = manager.addExchangeRate("EUR", "USD", -0.85);
        assertFalse(result);
        
    // test case for delete
    } @Test
     public void testDeleteExchangeRateWithCorrectDetails() {
    	 boolean result = manager.deleteExchangeRate(1);
    	 assertTrue(result);
    	 
    } @Test
     public void testDeleteExchangeRateWithWrongDetails() {
    	boolean result = manager.deleteExchangeRate(7);
    	assertFalse(result);
    	
    } @Test
     public void testDeleteExchangeRateWithIncorrectDetails() {
    	boolean result = manager.deleteExchangeRate(A);
    	assertFalse(result);
    	
    // test case for display
    } @Test
     public void testDisplayExchangeRateWithCorrectIndex() {
    	boolean result = manager.displayExchangeRate(2);
    	assertTrue(result);
    	
    } @Test
     public void testDisplayExchangeRateWithUnavailableIndex() {
    	boolean result = manager.displayExchangeRate(5);
    	assertFalse(result);
    	
    } @Test 
     public void testDisplayExchangeRateWithWrongIndex() {
    	boolean result = manager.displayExchangeRate("-1");
    	assertFalse(result);
    		
    }
    }