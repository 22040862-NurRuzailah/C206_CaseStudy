import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class AdministratorManagerTest {
    private ArrayList<Administrator> users;
    private AdministratorManager administratorManager;

    @Before
    public void setUp() {
        users = new ArrayList<>();
        administratorManager = new AdministratorManager();
    }

    @Test
    public void testAddUserToSystem_Success() {
        boolean result = administratorManager.addUserToSystem("John Doe", "john@example.com", "1234567890", "account123", "customer123", "admin");
        assertTrue(result);

        Administrator user = administratorManager.getUserByEmail("john@example.com");
        assertNotNull(user);
        assertEquals("John Doe", user.getFullName());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("1234567890", user.getContactNum());
        assertEquals("account123", user.getAccountID());
        assertEquals("customer123", user.getCustomerID());
        assertEquals("admin", user.getUserRole());
    }

    @Test
    public void testAddUserToSystem_DuplicateEmail_Failure() {
        administratorManager.addUserToSystem("Jane Smith", "jane@example.com", "9876543210", "account456", "customer456", "admin");
        boolean result = administratorManager.addUserToSystem("Jane Smith", "jane@example.com", "9876543210", "account789", "customer789", "user");
        assertFalse(result);

        Administrator user = administratorManager.getUserByEmail("jane@example.com");
        assertNull(user);
    }

    @Test
    public void testDeleteUser_ExistingUser_Success() {
        administratorManager.addUserToSystem("James Johnson", "james@example.com", "5555555555", "account111", "customer111", "user");

        Administrator userToDelete = administratorManager.getUserByEmail("james@example.com");
        assertNotNull(userToDelete);
        boolean deleted = administratorManager.deleteUser(userToDelete);
        assertTrue(deleted);

        Administrator user = administratorManager.getUserByEmail("james@example.com");
        assertNull(user);
    }

    @Test
    public void testDeleteUser_NonExistingUser_Failure() {
        Administrator userToDelete = new Administrator("Non Existent", "non@existent.com", "9999999999", "account222", "customer222", "user");
        boolean deleted = administratorManager.deleteUser(userToDelete);
        assertFalse(deleted);
    }

    @Test
    public void testViewAllUserAccounts_NoUsers() {
        String expectedOutput = "===== Registered Users in Money Exchange System =====\n"
                + "No users registered in the system.\n";
        assertEquals(expectedOutput, captureSystemOut(() -> administratorManager.viewAllUserAccounts()));
    }

    private String captureSystemOut(Runnable runnable) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);

        runnable.run();

        System.out.flush();
        System.setOut(oldOut);
        return baos.toString();
    }
}
