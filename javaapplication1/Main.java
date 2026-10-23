/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;
/**
 *
 * @author PC365
 */

public class Main {
    public static void main(String[] args) { 
       
    Scanner scan = new Scanner(System.in);
    System.out.print("How many messages do you want to send?");
    
           
        Login.storedUsername = "Mi_ke";
        Login.storedPassword = "Shani@2007";
        Login.storedFirstName = "Pros";
        Login.storedLastName = "chauke";

 
   //Displaying welcome messages
        System.out.println("welcome to CHATS registration");
        System.out.println("please enter your details to create an account");
       
        
    // This calls the method to handle registration part
       registerUser(scan);
       System.out.println();
       System.out.println("Please login");
       
       boolean _ = loginUser(scan);
        boolean loggedIn = loginUser(scan);
       if (loggedIn){
           message.QuickChat(scan);
       }
    
     
}

     // === Part 1 ===
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
   public static boolean loginUser(Scanner scan) {
    String username, password; // stores the login credentials

    System.out.println();
    System.out.println("enter your login details");

// == LOGIN LOOP ===
// a loop that keeps asking until the login is successfully
    while (true) {
      
    // Prompting the user for details
        System.out.print("Enter username: ");
        username = scan.nextLine(); // reads username and saves it
        System.out.print("Enter password: ");
        password = scan.nextLine();
        
// calls returnloginstatus from login class
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
    return false;
   }

public class QuickChat {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "1234";

    private final List<Message> sentMessages = new ArrayList<>();
    private final List<Message> storedMessages = new ArrayList<>();
    private int totalMessageCount = 0;

    public static boolean main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        System.out.println("=== QuickChat Login ===");
        System.out.print("Username: ");
        String user = sc.nextLine();

        System.out.print("Password: ");
        String pass = sc.nextLine();

        if (USERNAME.equals(user) && PASSWORD.equals(pass)) {
            System.out.println("Welcome to QuickChat.");
            return true;
        }

        return false;
    }

    @SuppressWarnings("unused")
    private void startProgram(Scanner input,int numMemberOfMessages) {
        
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Send Message");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.print("Choose an option: ");

            String option = sc.nextLine().trim();

            switch (option) {
                case "1" -> sendMessage(sc);

                case "2" -> System.out.println("Coming Soon.");

                case "3" -> {
                    System.out.println("Total number of messages sent: " + totalMessageCount);
                    System.out.println("Goodbye.");
                    return;
                }

                default -> System.out.println("Invalid option. Please choose 1, 2 or 3.");
            }
        }
    }

    private void sendMessage(Scanner sc) {
        System.out.println("\n--- Send Message ---");

        System.out.print("Enter recipient cell number: ");
        String recipient = sc.nextLine().trim();

        if (!checkRecipientCell(recipient)) {
            System.out.println("Invalid recipient number. It must be exactly 10 digits.");
            return;
        }

        System.out.print("Enter message: ");
        String messageText = sc.nextLine();

        if (messageText.length() > 250) {
            System.out.println("Please enter a message less than 250 characters.");
            return;
        }

        String messageID = generateUniqueMessageID();

        while (!checkMessageID(messageID)) {
            messageID = generateUniqueMessageID();
        }

        int messageNumber = totalMessageCount + 1;
        String messageHash = createMessageHash(messageID, messageNumber, messageText);

        Message message = new Message(messageID, messageNumber, recipient, messageText, messageHash);

        System.out.println("\nChoose an action:");
        System.out.println("1. Send");
        System.out.println("2. Store for later");
        System.out.println("0. Disregard");
        System.out.print("Option: ");

        String action = sc.nextLine().trim();

        switch (action) {
            case "1" -> {
                totalMessageCount++;
                sentMessages.add(message);
                saveMessagesToJson();
                System.out.println("Message successfully sent");
                printMessageDetails(message);
            }

            case "2" -> {
                storedMessages.add(message);
                saveMessagesToJson();
                System.out.println("Message successfully stored");
                printMessageDetails(message);
            }

            case "0" -> System.out.println("Message discarded.");

            default -> System.out.println("Invalid action. Message was not saved.");
        }
    }

    private String generateUniqueMessageID() {
        Random random = new Random();
        long id = 1000000000L + (Math.abs(random.nextLong()) % 9000000000L);
        return String.valueOf(id);
    }

    private boolean checkMessageID(String id) {
        if (id == null || id.length() != 10) {
            return false;
        }

        try {
            Long.valueOf(id);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean checkRecipientCell(String recipient) {
        if (recipient == null || recipient.length() != 10) {
            return false;
        }

        return recipient.matches("^[0-9]+$");
    }

    private String createMessageHash(String messageID, int messageNumber, String messageText) {
        String firstTwoDigits = messageID.substring(0, 2);

        String[] words = messageText.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0].toUpperCase() : "";
        String lastWord = words.length > 0 ? words[words.length - 1].toUpperCase() : "";

        return firstTwoDigits + ":" + messageNumber + ":" + firstWord + ":" + lastWord;
    }

    private void printMessageDetails(Message message) {
        System.out.println("\nMessage details:");
        System.out.println("Message ID: " + message.getMessageID());
        System.out.println("Message Hash: " + message.getMessageHash());
        System.out.println("Recipient: " + message.getRecipient());
        System.out.println("Message: " + message.getMessage());
    }

    private void saveMessagesToJson() {
        Path file = Paths.get("messages.json");

        StringBuilder json = new StringBuilder();
        json.append("[");

        for (int i = 0; i < sentMessages.size(); i++) {
            Message m = sentMessages.get(i);

            json.append("{")
                    .append("\"messageID\":\"").append(escapeJson(m.getMessageID())).append("\",")
                    .append("\"messageNumber\":").append(m.getMessageNumber()).append(",")
                    .append("\"recipient\":\"").append(escapeJson(m.getRecipient())).append("\",")
                    .append("\"message\":\"").append(escapeJson(m.getMessage())).append("\",")
                    .append("\"messageHash\":\"").append(escapeJson(m.getMessageHash())).append("\"")
                    .append("}");

            if (i < sentMessages.size() - 1) {
                json.append(",");
            }
        }

        json.append("]");

        try {
            Files.writeString(file, json.toString());
            System.out.println("Messages saved to messages.json");
        } catch (IOException e) {
            System.out.println("Could not save messages to file.");
        }
    }

    private String escapeJson(String value) {
        if (value == null) {
            return "";
        }

        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }

    static class Message {
        private final String messageID;
        private final int messageNumber;
        private final String recipient;
        private final String message;
        private final String messageHash;

        public Message(String messageID, int messageNumber, String recipient, String message, String messageHash) {
            this.messageID = messageID;
            this.messageNumber = messageNumber;
            this.recipient = recipient;
            this.message = message;
            this.messageHash = messageHash;
        }

        public String getMessageID() {
            return messageID;
        }

        public int getMessageNumber() {
            return messageNumber;
        }

        public String getRecipient() {
            return recipient;
        }

        public String getMessage() {
            return message;
        }

        public String getMessageHash() {
            return messageHash;
        }
    }
}
}
    

    


   

   
  
    

