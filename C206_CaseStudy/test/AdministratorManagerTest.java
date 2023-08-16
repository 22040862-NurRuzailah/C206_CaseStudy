import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AdministratorManagerTest {

    private AdministratorManager administratorManager;

    @Before
    public void setUp() {
        administratorManager = new AdministratorManager();
    }
    
    @After
    public void tearDown() {
        administratorManager = null;
    }

    @Test
    // NORMAL TEST CASE
    // ADD A VALID USER WITH ALL REQUIREMENTS MET
    public void testValidUserCreation() {
        boolean result = administratorManager.addUser("Vishnuwaran", "vishnuwaran@example.com", "98765432", "account123", "customer123", "user");
        assertTrue(result);
    }

    @Test
 // BOUNDARY TEST CASE
 // ADD A USER WITH INVALID CONTACT NUMBER
 public void testInvalidContactNumber() {
     
     String fullName = "Alice Smith";
     String email = "alice@example.com";
     String invalidContactNum = "12345"; // Invalid contact number

     // Call the addUser method
     administratorManager.addUser(fullName, email, invalidContactNum, "account456", "customer456", "admin");

     assertFalse(administratorManager.addUser(fullName, email, invalidContactNum, "account456", "customer456", "admin"));
 }

    @Test
    // ERROR TEST CASE
    // ADD A DUPLICATE USER
    public void testDuplicateUser() {
        // Add user first
        administratorManager.addUser("Bob Johnson", "bob@example.com", "9876543210", "account789", "customer789", "user");

        // Adding another user but with the same email
        boolean result = administratorManager.addUser("Bobby Johnson", "bob@example.com", "1111111111", "account999", "customer999", "admin");
        assertFalse(result);
    }
    

    @Test
    // NORMAL TEST CASE
    // DELETE AN EXISTING USER
    public void testDeleteExistingUser() {
        // Adding a user first
        administratorManager.addUser("James Johnson", "james@example.com", "5555555555", "account111", "customer111", "user");

        // Attempt to delete
        Administrator userToDelete = administratorManager.getUserByEmail("james@example.com");
        assertNotNull(userToDelete);
        boolean deleted = administratorManager.deleteUser(userToDelete.getEmail());
        assertTrue(deleted);

        Administrator user = administratorManager.getUserByEmail("james@example.com");
        assertNull(user);
    }

    @Test
    // BOUNDARY TEST CASE
    // DELETE A NON-EXISTING USER
    public void testDeleteNonExistingUser() {
        boolean deleted = administratorManager.deleteUser("non@existent.com");
        assertFalse(deleted);
    }

    @Test
    // ERROR TEST CASE
    // ATTEMPT TO DELETE USER WITH AN INVALID EMAIL
    public void testDeleteUserWithInvalidEmail() {
        // Add a user to delete
        administratorManager.addUser("Sarah Johnson", "sarah@example.com", "7777777777", "account333", "customer333", "admin");

        // Attempt to delete with an invalid email
        boolean deleted = administratorManager.deleteUser("invalid_email@example.com");
        assertFalse(deleted);

        // Check if user is still there
        Administrator user = administratorManager.getUserByEmail("sarah@example.com");
        assertNotNull(user);
    }

    // VIEW USER TEST CASES
    @Test
 // NORMAL TEST CASE
 // WILL VIEW AND LIST ALL USERS AVAILABLE
 public void testViewExistingUsers() {
     // Add users first
     administratorManager.addUser("Alice Smith", "alice@example.com", "1234567890", "account123", "customer123", "admin");
     administratorManager.addUser("Bob Johnson", "bob@example.com", "9876543210", "account456", "customer456", "user");

     // View users
     String expectedOutput = "===== Registered Users in Money Exchange System =====" + System.lineSeparator()
             + "Full Name: Alice Smith" + System.lineSeparator()
             + "Email: alice@example.com" + System.lineSeparator()
             + "Contact Number: 1234567890" + System.lineSeparator()
             + "Role: admin" + System.lineSeparator()
             + "---------------------------" + System.lineSeparator()
             + "Full Name: Bob Johnson" + System.lineSeparator()
             + "Email: bob@example.com" + System.lineSeparator()
             + "Contact Number: 9876543210" + System.lineSeparator()
             + "Role: user" + System.lineSeparator()
             + "---------------------------";
     String capturedOutput = captureSystemOut(() -> administratorManager.viewAllUsers());
     assertEquals(expectedOutput.trim(), capturedOutput.trim());
 }
    @Test
 // VIEW USERS WHEN THERE ARE NO USERS
 public void testViewAllUsersEmpty() {
     String expectedOutput = "===== Registered Users in Money Exchange System =====" + System.lineSeparator()
             + "No users registered in the system.";
     String capturedOutput = captureSystemOut(() -> administratorManager.viewAllUsers());
     
     // Normalize line separators in expected and captured outputs
     expectedOutput = expectedOutput.replace("\r\n", "\n").replace("\r", "\n");
     capturedOutput = capturedOutput.replace("\r\n", "\n").replace("\r", "\n");
     
     assertEquals(expectedOutput, capturedOutput.trim());
 }
    @Test
 // ERROR TEST CASE
 // SIMULATE AN EXCEPTION WHILE VIEWING USERS
 public void testViewAllUsersError() {
     // Set up a mock scenario where an exception occurs when viewing users
     AdministratorManager administratorManager = new AdministratorManager() {
         @Override
         public void viewAllUsers() {
             throw new RuntimeException("An exception occurred while viewing users.");
         }
     };

     // Capture the system output
     String capturedOutput = captureSystemOut(() -> administratorManager.viewAllUsers());

     // Verify that the captured output contains the error message
     assertTrue(capturedOutput.contains("An exception occurred while viewing users."));
 }

    private String captureSystemOut(Runnable runnable) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        PrintStream oldErr = System.err; // Capture the old standard error stream
        System.setOut(ps);
        System.setErr(ps); // Redirect standard error stream to the same PrintStream

        try {
            runnable.run();
        } catch (Exception e) {
            e.printStackTrace(System.err); // Print the exception stack trace
        } finally {
            System.out.flush();
            System.err.flush(); // Flush standard error stream
            System.setOut(oldOut);
            System.setErr(oldErr); // Restore the old standard error stream
        }

        return baos.toString().trim();
    }
}
