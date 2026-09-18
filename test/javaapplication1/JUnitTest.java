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
        assertEquals("User registered successfully.", result);
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
}
