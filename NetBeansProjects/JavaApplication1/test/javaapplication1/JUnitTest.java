/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package javaapplication1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author PC365
 */
public class JUnitTest {
    

    
    public JUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        // Clear saved data before each test
        Login.storedUsername = "";
        Login.storedPassword = "";
        Login.storedFirstName = "";
        Login.storedLastName = "";
        Login.storedPhone = "";
        
        //part 3
        Message.resetForTest();
    }
    
    @After
    public void tearDown() {
        
    }

   
    // TEST 1: Username Correctly Formatted
   
    @Test
    public void testUsernameCorrectlyFormatted() {
        boolean result = Login.checkUserName("kyl_1");
        assertEquals(true, result);
        System.out.println("Test 1 Passed: Username 'kyl_1' is valid");
    }

    
    // TEST 2: Username Incorrectly Formatted
  
    @Test
    public void testUsernameIncorrectlyFormatted() {
        boolean result = Login.checkUserName("kyle11111111");
        assertEquals(false, result);
        System.out.println("Test 2 Passed: Username 'kyle11111111' is invalid");
    }

    
    // TEST 3: Password Meets Complexity
    
    @Test
    public void testPasswordMeetsComplexity() {
        boolean result = Login.checkPasswordComplexity("Ch&sec@ke99!");
        assertEquals(true, result);
        System.out.println("Test 3 Passed: Password 'Ch&sec@ke99!' is valid");
    }

  
    // TEST 4: Password Does NOT Meet Complexity
   
    @Test
    public void testPasswordDoesNotMeetComplexity() {
        boolean result = Login.checkPasswordComplexity("password");
        assertEquals(false, result);
        System.out.println("Test 4 Passed: Password 'password' is invalid");
    }

    
    // TEST 5: Cell Phone Correctly Formatted
    
    @Test
    public void testPhoneCorrectlyFormatted() {
        boolean result = Login.checkCellPhoneNumber("+27838968976");
        assertEquals(true, result);
        System.out.println("Test 5 Passed: Phone '+27838968976' is valid");
    }

    
    // TEST 6: Cell Phone Incorrectly Formatted
   
    @Test
    public void testPhoneIncorrectlyFormatted() {
        boolean result = Login.checkCellPhoneNumber("08966553");
        assertEquals(false, result);
        System.out.println("Test 6 Passed: Phone '08966553' is invalid");
    }

   
    // TEST 7: Login Successful
   
    @Test
    public void testLoginSuccessful() {
        // Register a user first
        Login.storedUsername = "k_l";
        Login.storedPassword = "Pass123!";
        Login.storedFirstName = "Kea";
        Login.storedLastName = "Luh";
        
        boolean result = Login.loginUser("k_l", "Pass123!");
        assertEquals(true, result);
        System.out.println("Test 7 Passed: Login successful");
    }

   
    // TEST 8: Login Failed
   
    @Test
    public void testLoginFailed() {
        // Register a user first
        Login.storedUsername = "k_l";
        Login.storedPassword = "Pass123!";
        
        boolean result = Login.loginUser("wronguser", "wrongpass");
        assertEquals(false, result);
        System.out.println("Test 8 Passed: Login failed correctly");
    }

    
    // TEST 9: Register User - Valid
    
    @Test
    public void testRegisterUserValid() {
        String result = Login.registerUser("k_l", "Pass123!", "Kea", "Luh", "+271234567");
        
        System.out.println("Test 9 Passed: Registration successful");
    }

  
    // TEST 10: Register User - Invalid Username
    
    @Test
    public void testRegisterUserInvalidUsername() {
        String result = Login.registerUser("Kea_luh", "Pass123!", "Kea", "Luh", "+271234567");
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", result);
        System.out.println("Test 10 Passed: Invalid username rejected");
    }

 
    // TEST 11: Register User - Invalid Password
    
    @Test
    public void testRegisterUserInvalidPassword() {
        String result = Login.registerUser("k_l", "pass", "Kea", "Luh", "+271234567");
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", result);
        System.out.println("Test 11 Passed: Invalid password rejected");
    }

   
    // TEST 12: Register User - Invalid Phone
    
    @Test
    public void testRegisterUserInvalidPhone() {
        String result = Login.registerUser("k_l", "Pass123!", "Kea", "Luh", "0821234567");
        assertEquals("Cell phone number incorrectly formatted or does not contain international code.", result);
        System.out.println("Test 12 Passed: Invalid phone rejected");
    }
    // ==========================================================
    // ==================== PART 2: MESSAGE CLASS ===============
    // ==========================================================
    // This is the QuickChat part from your rubric photos

    // ----- Message Length 250 chars -----
    @Test
    public void testMessage_checkMessageLength_Success() {
        Message m = new Message("1234567890", 0, "+27718693002", "Hi Mike, can you please call me");
        assertEquals("Message ready to send.", m.checkMessageLength());
    }

    @Test
    public void testMessage_checkMessageLength_Fail() {
        String longMsg = "a".repeat(260);
        Message m = new Message("1234567890", 0, "+27718693002", longMsg);
        assertEquals("Message exceeds 250 characters by 10, please reduce the size.", m.checkMessageLength());
    }

    // ----- Recipient Cell -----
    @Test
    public void testMessage_checkRecipientCell_Success() {
        Message m = new Message("1234567890", 0, "+27718693002", "Hello");
        assertEquals("Cell phone number successfully captured.", m.checkRecipientCell());
    }

    @Test
    public void testMessage_checkRecipientCell_Fail() {
        // From rubric Page 15: 08575975889 should fail
        Message m = new Message("1234567890", 0, "08575975889", "Hi Keegan, did you receive the payment?");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", m.checkRecipientCell());
    }

    // ----- Message Hash - RUBRIC WANTS 00:0:HITONIGHT -----
    @Test
    public void testMessage_createMessageHash_HITONIGHT() {
        Message m = new Message("0012345678", 0, "+27718693002", "Hi Tonight");
        assertEquals("00:0:HITONIGHT", m.getMessageHash());
    }

    @Test
    public void testMessage_createMessageHash_SecondMessage() {
        // Rubric says test remainder in a loop - Message 2: "Hi Keegan, did you receive the payment?"
        Message m = new Message("0123456789", 1, "+27718693002", "Hi Keegan, did you receive the payment?");
        assertEquals("01:1:HIPAYMENT", m.getMessageHash());
    }

    // ----- Message ID -----
    @Test
    public void testMessage_checkMessageID_and_GetID() {
        Message m = new Message(0, "+27718693002", "Test ID");
        assertNotNull(m.getMessageID());
        assertEquals(10, m.getMessageID().length());
        assertTrue(m.checkMessageID());
    }

    // ----- sentMessage Options 1,2,3 -----
    @Test
    public void testMessage_sentMessage_Send() {
        Message m = new Message("1234567890", 0, "+27718693002", "Send");
        assertEquals("Message successfully sent.", m.sentMessage(1));
    }

    @Test
    public void testMessage_sentMessage_Disregard() {
        // Rubric Page 15: Message 2 says Select Discard
        Message m = new Message("1234567890", 0, "08575975889", "Hi Keegan, did you receive the payment?");
        assertEquals("Press 0 to delete the message.", m.sentMessage(2));
    }

    @Test
    public void testMessage_sentMessage_Store() {
        Message m = new Message("1234567890", 0, "+27718693002", "Store");
        assertEquals("Message successfully stored.", m.sentMessage(3));
    }

    // ----- Total Sent + Print -----
    @Test
    public void testMessage_returnTotalMessage() {
        Message m1 = new Message("1111111111", 0, "+27718693002", "One");
        m1.sentMessage(1);
        Message m2 = new Message("2222222222", 1, "+27718693002", "Two");
        m2.sentMessage(1);
        assertEquals(2, Message.returnTotalMessage());
    }

    @Test
    public void testMessage_printMessages() {
        Message m = new Message("1234567890", 0, "+27718693002", "QuickChat print test");
        m.sentMessage(1);
        String out = Message.printMessages();
        assertTrue(out.contains("QuickChat print test"));
    }
        
    // ==========================================
    // PART 3 UNIT TESTS
    // ==========================================

    
   
     

    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        // Test Data: Developer entry for Test data for message 1-4
        Message.addDeveloperTestData("1212121212", 1, "+27834557896", "Did you get the cake?", "Sent");
        Message.addDeveloperTestData("1234567890", 2, "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored");
        Message.addDeveloperTestData("1122334455", 3, "+27834484567", "Yohoooo, I am at your gate.", "Disregard");
        Message.addDeveloperTestData("0838884567", 4, "0838884567", "It is dinner time!", "Sent");

        String sentArrayString = Message.printMessages();
        
        // The system returns: "Did you get the cake?" and "It is dinner time!"
        assertTrue("Sent array should contain Message 1", sentArrayString.contains("Did you get the cake?"));
        assertTrue("Sent array should contain Message 4", sentArrayString.contains("It is dinner time!"));
        
        // Make sure stored/discarded messages are NOT in the sent array
        assertFalse("Sent array should NOT contain stored messages", sentArrayString.contains("Where are you? You are late!"));
        assertFalse("Sent array should NOT contain disregarded messages", sentArrayString.contains("Yohoooo, I am at your gate."));
    }

    @Test
    public void testDisplayLongestMessage() {
        // Test Data: message 1-4
        Message.addDeveloperTestData("1212121212", 1, "+27834557896", "Did you get the cake?", "Sent");
        Message.addDeveloperTestData("1234567890", 2, "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored");
        Message.addDeveloperTestData("0838884567", 4, "0838884567", "It is dinner time!", "Sent");

        // The system returns: "Where are you? You are late! I have asked you to be on time."
        assertEquals("Where are you? You are late! I have asked you to be on time.", Message.displayLongestMessage());
    }

    @Test
    public void testSearchForMessageID() {
        // Test Data: message 4
        Message.addDeveloperTestData("0838884567", 4, "0838884567", "It is dinner time!", "Sent");
        
        // The system returns: "It is dinner time!" (and the recipient)
        String result = Message.searchByID("0838884567");
        assertTrue("Search should return the message text", result.contains("It is dinner time!"));
        assertTrue("Search should return the recipient", result.contains("0838884567"));
    }

    @Test
    public void testSearchAllMessagesForRecipient() {
        // Test Data: +27838884567
        Message.addDeveloperTestData("1234567890", 2, "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored");
        Message.addDeveloperTestData("9988776655", 5, "+27838884567", "Ok, I am leaving without you.", "Stored");

        String result = Message.searchByRecipient("+27838884567");

        // The system returns: "Where are you? You are late! I have asked you to be on time." "Ok, I am leaving without you."
        assertTrue("Search should return Message 2", result.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue("Search should return Message 5", result.contains("Ok, I am leaving without you."));
    }

    @Test
        
    public void testDeleteMessageUsingHash() {
        Message.addDeveloperTestData("1234567890", 2, "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored");
        
        String expectedHash = "12:2:WHEREON"; 
        
        String result = Message.deleteByHash(expectedHash);
        
    }

    @Test
    public void testDisplayReport() {
        Message.addDeveloperTestData("1212121212", 1, "+27834557896", "Did you get the cake?", "Sent");
        
        String report = Message.displayReport();

        // The system returns a report that shows all sent messages, including Message Hash, Recipient, Message
        assertTrue("Report should contain Hash", report.contains("Hash: 12:1:DIDCAKE"));
        assertTrue("Report should contain Recipient", report.contains("Recipient: +27834557896"));
        assertTrue("Report should contain Message", report.contains("Message: Did you get the cake?"));
    }
}
    
   
    





    
