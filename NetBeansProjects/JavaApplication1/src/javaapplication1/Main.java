package javaapplication1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // === PART 1: Register + Login ===
        registerUser(scan);
        loginUser(scan);
        
        // === PART 3: DEVELOPER TEST MODE (FOR UNIT TESTING) ===
        System.out.print("\nAre you running Unit Tests (Developer Mode)? (y/n): ");
        String devMode = scan.nextLine();
        if (devMode.equalsIgnoreCase("y")) {
            System.out.println("\nPopulating arrays with Test Data Messages 1-5...");
            
            // Message 1: Sent
            Message.addDeveloperTestData("1212121212", 1, "+27834557896", "Did you get the cake?", "Sent");
            // Message 2: Stored (Longest)
            Message.addDeveloperTestData("1234567890", 2, "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored");
            // Message 3: Disregard
            Message.addDeveloperTestData("1122334455", 3, "+27834484567", "Yohoooo, I am at your gate.", "Disregard");
            // Message 4: Sent
            Message.addDeveloperTestData("0838884567", 4, "0838884567", "It is dinner time!", "Sent");
            // Message 5: Stored
            Message.addDeveloperTestData("9988776655", 5, "+27838884567", "Ok, I am leaving without you.", "Stored");
            
            // Run automated assertions (Unit Tests)
            System.out.println("\n--- RUNNING PART 3 UNIT TESTS ---");
            System.out.println("Test 1 (Sent Array populated): " + (Message.printMessages().contains("Did you get the cake?") ? "PASS" : "FAIL"));
            System.out.println("Test 2 (Longest Msg): " + (Message.displayLongestMessage().equals("Where are you? You are late! I have asked you to be on time.") ? "PASS" : "FAIL"));
            System.out.println("Test 3 (Search ID 0838884567): " + (Message.searchByID("0838884567").contains("It is dinner time!") ? "PASS" : "FAIL"));
            System.out.println("Test 4 (Search Recipient +27838884567): \n" + Message.searchByRecipient("+27838884567"));
System.out.println("Test 5 (Delete Hash): " + Message.deleteByHashForTest("Where are you? You are late! I have asked you to be on time."));            System.out.println("Test 6 (Display Report): \n" + Message.displayReport());
            System.out.println("--------------------------------\n");
        }
        
        //=== PART 2 ===
        // ----- QUICK CHAT STARTS HERE ----
        System.out.println("------Welcome to QuickChat.-------");
        System.out.println();
        int maxMessages = readInt(scan, "How many messages do you wish to enter?");
        int entered = 0;
        boolean running = true;

        while (running) {
            System.out.println("1 Send messages");
            System.out.println("2 Show recently sent messages");
            System.out.println("3 Quit");
            int option = readInt(scan, "Choose an option: ");

            switch (option) {
                case 1 -> {
                    if (entered >= maxMessages) {
                        System.out.println("You have already entered all " + maxMessages + " messages.");
                    } else {
                        
                        for (int i = entered; i < maxMessages; i++) {
                            SendOne(scan, i + i);
                            entered++;
                        }
                        System.out.println("\n" + Message.printMessages());
                        System.out.println("Total messages sent: " + Message.returnTotalMessage());
                    }
                }
                case 2 -> {
                    // PART 3 REQUIREMENT - was Coming Soon, now shows report
                    System.out.println(Message.displayReport());
                    System.out.println("Stored messages: ");
                    System.out.println(Message.displayStoredMessages());
                    System.out.println("Longest message: " + Message.displayLongestMessage());
                }
                case 3 -> {
                    System.out.println("Goodbye");
                    running = false;
                }
                 case 4 -> {
                    // PART 3: Stored Messages Menu
                    System.out.println("\n--- STORED MESSAGES MENU ---");
                    System.out.println("a) Display sender and recipient of all stored messages");
                    System.out.println("b) Display longest stored message");
                    System.out.println("c) Search by Message ID");
                    System.out.println("d) Search by Recipient");
                    System.out.println("e) Delete by Message Hash");
                    System.out.println("f) Display Report");
                    System.out.print("Choose: ");
                    String storedChoice = scan.nextLine().trim().toLowerCase();
                    
                    switch(storedChoice) {
                        case "a" -> System.out.println(Message.displayStoredMessages());
                        case "b" -> System.out.println("Longest Message: " + Message.displayLongestMessage());
                        case "c" -> {
                            System.out.print("Enter ID: ");
                            System.out.println(Message.searchByID(scan.nextLine()));
                        }
                        case "d" -> {
                            System.out.print("Enter Recipient (+27...): ");
                            System.out.println(Message.searchByRecipient(scan.nextLine()));
                        }
                        case "e" -> {
                            System.out.print("Enter Hash: ");
                            System.out.println(Message.deleteByHash(scan.nextLine()));
                        }
                        case "f" -> System.out.println(Message.displayReport());
                        default -> System.out.println("Invalid choice.");
                    }
                }
                default -> System.out.println("Invalid option");
            }
               
        }
        scan.close();
    }
    
    // handles one message at a time
    private static void SendOne(Scanner scannerInput, int number) {
        Message m = null;
        while (true) {
            System.out.print("Recipient cell number (e.g +27718693002): ");
            String recipient = scannerInput.nextLine().trim();
            System.out.print("Messages (max 250 characters): ");
            String text = scannerInput.nextLine().trim();

            if (text.isEmpty()) {
                System.out.println("Message cannot be empty");
                continue;
            }

            m = new Message(number, recipient, text);
            String cellResult = m.checkRecipientCell();
            String lenResult = m.checkMessageLength();
            System.out.println(cellResult);
            System.out.println(lenResult);

            if (cellResult.startsWith("Cell phone number successfully")
                    && lenResult.equals("Message ready to send.")) {
                break;
            }
        }
        System.out.println("Messages ID generated: " + m.getMessageID());
        System.out.println("Message Hash: " + m.getMessageHash());

        int choice = 0;
        while (choice < 1 || choice > 3) {
            System.out.println("1) Send Messages 2)Disregard Message 3)Store message to send later");
            choice = readInt(scannerInput, "Choice:");
        }
        System.out.println(m.sentMessage(choice));
    }

    private static int readInt(Scanner userInput, String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                return Integer.parseInt(userInput.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number");
            }
        }
    }

    // === PART 1: Register ===
    public static void registerUser(Scanner scan) {
        String firstName, lastName, username, password, phone;

        System.out.println();
        System.out.println("enter your details  ");

        System.out.print("Enter your name: ");
        firstName = scan.nextLine();

        System.out.print("Enter your surname: ");
        lastName = scan.nextLine();

        while (true) {
            System.out.print("Enter username (must contain an underscore and should be not more than 5 characters): ");
            username = scan.nextLine();
            if (Login.checkUserName(username)) {
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        }

        while (true) {
            System.out.print("Enter password (>= 8 characters, 1 capital, 1 number, 1 special char): ");
            password = scan.nextLine();
            if (Login.checkPasswordComplexity(password)) {
                System.out.println("Password successfully captured.");
                break;
            } else {
                System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            }
        }

        while (true) {
            System.out.print("Enter cell phone number (must start with +27 and followed by up to 10 digits): ");
            phone = scan.nextLine();
            if (Login.checkCellPhoneNumber(phone)) {
                System.out.println("Cell phone number successfully added.");
                break;
            } else {
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            }
        }

        String result = Login.registerUser(username, password, firstName, lastName, phone);
        System.out.println(result);

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

    // === LOGIN ===
    public static boolean loginUser(Scanner scan) {
        String username, password;
        System.out.println();
        System.out.println("enter your login details");
        while (true) {
            System.out.print("Enter username: ");
            username = scan.nextLine();
            System.out.print("Enter password: ");
            password = scan.nextLine();

            String message = Login.returnLoginStatus(username, password);
            System.out.println(message);

            if (!message.equalsIgnoreCase("Username or password incorrect, please try again.")) {
                return true;
            }
            System.out.println();
            System.out.println("Please try again.");
            System.out.println();
        }
    }
}

    

    


   

   
  
    

