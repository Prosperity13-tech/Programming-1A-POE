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
import java.util.Scanner;

/**
 *
 * @author PC365
 */
public class QuickChat {
  

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "1234";

    private final List<Message> sentMessages = new ArrayList<>();
    private final List<Message> storedMessages = new ArrayList<>();
    private int maxMessages = 0;
    private int totalMessageCount = 0;

    public static void main(String[] args) {
        QuickChat app = new QuickChat();

        if (app.login()) {
            app.startProgram();
        } else {
            System.out.println("Login failed. Goodbye.");
        }
    }

    private boolean login() {
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

    void startProgram() {
        Scanner sc = new Scanner(System.in);

        System.out.print("How many messages do you want to send? ");
        maxMessages = readInt(sc, "Please enter a valid number.");

        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Send Message");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.print("Choose an option: ");

            String option = sc.nextLine().trim();

            switch (option) {
                case "1" -> {
                    if (totalMessageCount >= maxMessages) {
                        System.out.println("You have reached the maximum number of messages allowed.");
                    } else {
                        sendMessageProcess();
                    }
                }

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

    private void sendMessageProcess() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n--- Send Message ---");

        System.out.print("Enter recipient cell number: ");
        String recipient = sc.nextLine().trim();

        if (!checkRecipientCell(recipient)) {
            System.out.println("Invalid recipient number. It must be 10 characters long and contain numeric digits.");
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
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 0 ? words[words.length - 1] : "";

        String hash = firstTwoDigits + ":" + messageNumber + ":" + firstWord + ":" + lastWord;
        return hash.toUpperCase();
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
            e.printStackTrace();
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

    private int readInt(Scanner sc, String invalidMessage) {
        while (true) {
            String input = sc.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(invalidMessage);
            }
        }
    }

    static class Message {

        static String returnTotalMessages() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        static String generatemessageID() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
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

        boolean printMessageDetails() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        String SentMessage(String action) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
}
    

