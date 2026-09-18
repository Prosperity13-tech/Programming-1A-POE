/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author PC365
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {     
    Scanner scan = new Scanner(System.in);
   //Displaying welcome messages
        System.out.println("welcome to CHATS registration");
        System.out.println("please enter your details to create an account");
        System.out.println("username must contain '_' and be max 5 characters");
        
    // This calls the method to handle registration part
       registerUser(scan);
       System.out.println();
       System.out.println("Please login");
       
    // Calls a method that will be handling the login process
       loginUser(scan);
    }
// This the method that handles the user registration process
// Prompts for and validates the name, the surname, password,username, phone number
    public static void registerUser(Scanner scan) {
        String firstName, lastName, username, password, phone; // Declare variables to store user name details
        
        System.out.println();
        System.out.println("enter your details  ");
        
    //Prompting the user for their name
        System.out.print("Enter your name: ");
        firstName = scan.nextLine();
        
    //Prompting the user for their surname
        System.out.print("Enter your surname: ");
        lastName = scan.nextLine();
        
    // === Username validation loop ===
    //This loop will keep asking the user to enter their username until they meet the requirements
        while (true) {
            System.out.print("Enter username (must contain an underscore and should be not more than 5 characters): ");
            username = scan.nextLine();
         
        // If statement to check if the username is valid, using a method from the login class
            if (Login.checkUserName(username)) {
                System.out.println("Username successfully captured.");
                break; // exit the loop if the if statement is valid
            }
            else {
                System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        }
    // === Password validation loop ===
    // This while loop will keep asking the user to enter their password until the password entered ments the complexity rules
        while (true) {
            System.out.print("Enter password (>= 8 characters, 1 capital, 1 number, 1 special char): ");
            password = scan.nextLine();
            
        // An if statement to check if the password is valid using the method from the login class
            if (Login.checkPasswordComplexity(password)) {
                System.out.println("Password successfully captured.");
                break;  // terminates the loop if the if statement is valid
            }
            else {
                System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            }
        }
        
    // === Cell phone number Validation Loop ===
    // This loop will keep on asking the user to enter their password until the password meets complexity rules
        while (true) {
            System.out.print("Enter cell phone number (must start with +27 and followed by up to 10 digits): ");
            phone = scan.nextLine();
            
        // An if statement to check if the cell phone number is valid using the method from the login class
            if (Login.checkCellPhoneNumber(phone)) {
                System.out.println("Cell phone number successfully added.");
                break; // terminates the loop if the if statement is valid
            }
            else {
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            }
        }
        
    // This submits all the details to register the user
        String result = Login.registerUser(username, password, firstName, lastName, phone);
        System.out.println(result); // show registration result message
        
    //if registration is successful, then this summary will be displayed
        if (result.equals("User registered successfully.")) {
            System.out.println();
            System.out.println("Registration complete, Welcome to chat");
            System.out.println("Name: " + firstName);
            System.out.println("Surname: " + lastName);
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            System.out.println("Phone: " + phone);
        }
    }
    
  // A method that will handle the login process, while it keeps on asking dor credentials until login is successfu
   public static void loginUser(Scanner scan) {
    String username, password; // stores the login credentials

    System.out.println();
    System.out.println("enter your login details");

// == LOGIN LOOP ===
// a loop that keeps asking until the login is successfully
    while (true) {
      
    // Prompting the user for details
        System.out.print("Enter username: ");
        username = scan.nextLine();
        System.out.print("Enter password: ");
        password = scan.nextLine();

// This will check the login status using a method from the login class
        String message = Login.returnLoginStatus(username, password);
        System.out.println(message);  // shows result message

    // if message is not incorrect, login succeeded
        if (!message.equalsIgnoreCase("Username or password incorrect, please try again.")) {
            break; // terminates the if statement
        }

    // login faild, try again
        System.out.println();
        System.out.println("Please try again.");
        System.out.println();
    }
  }
}
    

