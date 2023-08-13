/*
 * I declare that this code was written by me. 
 * I do not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: Vishnu
 * Student ID: {type your id here}
 * Class: {type your class here}
 * Date/Time created: Tuesday 01-08-2023 01:24
 */

/**
 * @author Vishnu
 *
 */
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class AdministratorManagerTest {
    private AdministratorManager administratorManager;

    @Before
    public void setUp() {
        administratorManager = new AdministratorManager();
    }

    @Test
    public void testAddUserToSystem() {
        boolean result = administratorManager.addUserToSystem("John Doe", "john@example.com", "1234567890");
        assertTrue(result); 
        // Expecting the user to be added successfully

        // Verify if the user is added to the 'users' ArrayList
        Administrator user = administratorManager.getUserByEmail("john@example.com");
        assertNotNull(user);
        assertEquals("John Doe", user.getFullName());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("1234567890", user.getContactNum());
    }

    @Test
    public void testAddDuplicateUserToSystem() {
        // Adding the same user twice with the same email, should return false
        administratorManager.addUserToSystem("Jane Smith", "jane@example.com", "9876543210");
        boolean result = administratorManager.addUserToSystem("Jane Smith", "jane@example.com", "9876543210");
        assertFalse(result); // Expecting the user addition to fail

        // Verify that only one user with the email "jane@example.com" exists in the 'users' ArrayList
        int count = 0;
        for (Administrator user : administratorManager.users) {
            if (user.getEmail().equals("jane@example.com")) {
                count++;
            }
        }
        assertEquals(1, count);
    }

    @Test
    public void testDeleteUser() {
        // Add a user first
        administratorManager.addUserToSystem("James Johnson", "james@example.com", "5555555555");

        // Delete the added user
        Administrator userToDelete = administratorManager.getUserByEmail("james@example.com");
        assertNotNull(userToDelete);
        boolean deleted = administratorManager.deleteUser(userToDelete);
        assertTrue(deleted); // Expecting the user to be deleted successfully

        // Verify that the user is removed from the 'users' ArrayList
        Administrator user = administratorManager.getUserByEmail("james@example.com");
        assertNull(user);
    }

    @Test
    public void testDeleteNonExistingUser() {
        // Attempt to delete a user that does not exist in the 'users' ArrayList
        Administrator userToDelete = new Administrator("Non Existent", "non@existent.com", "9999999999", "id123", "acc123");
        boolean deleted = administratorManager.deleteUser(userToDelete);
        assertFalse(deleted); // Expecting the deletion to fail

        // Verify that the 'users' ArrayList remains unchanged
        int initialSize = administratorManager.users.size();
        assertEquals(0, initialSize);
    }

    @Test
    public void testViewAllUserAccounts() {
        // Add some users to the 'users' ArrayList
        administratorManager.addUserToSystem("Alice Smith", "alice@example.com", "1111111111");
        administratorManager.addUserToSystem("Bob Johnson", "bob@example.com", "2222222222");

        // Capture the output of the 'viewAllUserAccounts' method and verify the expected output
        String expectedOutput = "===== Registered Users in Money Exchange System =====\n"
                + "Full Name: Alice Smith\n"
                + "Email: alice@example.com\n"
                + "Contact Number: 1111111111\n"
                + "Customer ID: customerID\n"
                + "Account ID: accountID\n"
                + "---------------------------\n"
                + "Full Name: Bob Johnson\n"
                + "Email: bob@example.com\n"
                + "Contact Number: 2222222222\n"
                + "Customer ID: customerID\n"
                + "Account ID: accountID\n"
                + "---------------------------\n";

        assertEquals(expectedOutput, captureSystemOut(() -> administratorManager.viewAllUserAccounts()));
    }

    // Helper method to capture the System.out output
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
