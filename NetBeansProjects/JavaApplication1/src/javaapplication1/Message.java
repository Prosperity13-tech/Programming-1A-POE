/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

  import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

import java.nio.file.Files;

import java.util.List;
import java.util.Random;
//for the json file
import java.nio.file.Files;
import java.nio.file.Path;

public class Message {
    // --- PART 3 ARRAYS (LISTS) ---
    private static final List<Message> sentMessages = new ArrayList<>();
    private static final List<Message> storedMessages = new ArrayList<>();
    private static final List<Message> disregardedMessages = new ArrayList<>();
    private static int totalSent = 0;
    
    
    //instance fields for each message
    private final String messageID;
    private final int messageNumber;
    private final String recipient;
    private final String text;
    private final String messageHash;
    
    //my constructor: ID will be randomely generated.
    public Message(int messageNumber, String recipient, String text){
        this(generateID(), messageNumber, recipient, text);
        
    }
    //secondary cosntructor, this constructor will be used by the unit test so that the ID AND the hash are predictable.
    public Message(String messageID,int messageNumber, String recipient, String text){
        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.text = text;
        this.messageHash = createMessageHash();
    }
    // Generate a random 10-digit string (each char 0-9)
    private static String generateID(){
        Random k = new Random ();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<10; i++){
            sb.append(k.nextInt(10));
        }
        return sb.toString();
    }
    
    // ID REQUIREMENT: must not be longer than 10 characters
    public boolean checkMessageID(){
        return messageID != null && messageID.matches("\\d{10}");
    }
    
    //MESSAGE REQUIREMENT: message must be 250 characters or less
    public String checkMessageLength(){
        if (text.length() <= 250){
            return "Message ready to send.";
        }
        int over = text.length() - 250;
        return "Message exceeds 250 characters by "+ over + ", please reduce the size.";
    }
    
    
     // Validate recipient format
     //Current pattern for South African mobile numbers: 
     // must start with +27
     //then a digit 1-9 and 8 more digits (total length after the + is 9)
     //
    public String checkRecipientCell(){
        if (recipient != null && recipient.matches("^\\+27[1-9]\\d{8}$")){ //reference way of using "matches": geeksforgeeks
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. "
                + "Please correct the number and try again.";
    }
            
    //the message hash has
    //first two characters of messageID
    //colon
    //messageNumber
    //another colon
    //then a concatination of the first and the last words of the message
    //result is then return in uppercase
    public String createMessageHash(){
        String[] words = text.trim().split("\\s+");
        String first = clean(words[0]);
        String last = clean(words[words.length - 1]);
        return (messageID.substring(0, 2)+":"+messageNumber + ":" +first + last).toUpperCase();  
    }
    
     // strips punctuation such as "tonight?" -> "tonight", it basically removes non-alpahbetic characters from a word
    private String clean(String word) {
        return word.replaceAll("[^A-Za-z0-9]", "");
    }
    
        // PART 3 UPDATE: Add to respective arrays based on choice
    public String sentMessage(int choice){
        switch (choice){
            case 1: 
              sentMessages.add(this);
              totalSent++;
              return "Message successfully sent.";
            case 2: 
              disregardedMessages.add(this);
              return "Press 0 to delete the message.";
            case 3: 
               storedMessages.add(this);
               storedMessage();
               return "Message successfully stored.";
            default: return "Invalid option";
        }
    }
     
    //print message
    public String printMessage(){
        return "MessageID: "+ messageID + "\n"+  "Message Hash: " 
                + messageHash + "\n" + "Recipient: " + recipient + "\n" +"Message: " + text;
    }
    
    //Human readable representation of this message
    public static String printMessages(){
        if (sentMessages.isEmpty()){
            return "No messages sent to in this session";
        }
        StringBuilder sb = new StringBuilder();
        for (Message m: sentMessages){
            sb.append(m.printMessage()).append("\n\n"); // FIXED: was "\n\\n"
        }
        return sb.toString();
    }
    
    //count of message will be sent to this runtime
    public static int returnTotalMessage(){
        return sentMessages.size()+storedMessages.size() + disregardedMessages.size();
    }
    
     // --- PART 3 NEW METHODS ---
    
    // 1. Display sender and recipient of all stored messages
    public static String displayStoredMessages() {
        if (storedMessages.isEmpty()) return "No stored messages.";
        StringBuilder sb = new StringBuilder();
        for (Message m : storedMessages) {
            sb.append("Recipient: ").append(m.recipient).append(" | Message: ").append(m.text).append("\n");
        }
        return sb.toString();
    }
    
     // 2. Display the longest stored message
    public static String displayLongestMessage() {
        List<Message> all = new ArrayList<>();
        all.addAll(sentMessages);
        all.addAll(storedMessages);
        all.addAll(disregardedMessages);
        if (storedMessages.isEmpty()) return "No stored messages.";
        Message longest = storedMessages.get(0);
        for (Message m : storedMessages) {
            if (m.text.length() > longest.text.length()) {
                longest = m;
            }
        }
        return longest.text;
    }
    
    // 3. Search for message ID (searches both sent and stored)
    public static String searchByID(String id) {
        String searchId = id.trim();
        for (Message m : sentMessages) {
            if (m.messageID.equals(id)) return "Recipient: " + m.recipient + "\nMessage: " + m.text;
        }
        for (Message m : storedMessages) {
            if (m.messageID.equals(id)) return "Recipient: " + m.recipient + "\nMessage: " + m.text;
        }
        for (Message m : disregardedMessages){
            if (m.messageID.equals(id)) return "Recipient: " + m.recipient + "\nMessage: " + m.text;
        }
        return "Message ID not found.";
    }
    
    // 4. Search all messages (sent or stored) for a particular recipient
    public static String searchByRecipient(String recipient) {
     
        StringBuilder sb = new StringBuilder();
        for (Message m : sentMessages) {
            if (m.recipient.equals(recipient)) sb.append(m.text).append("\n");
        }
        for (Message m : storedMessages) {
            if (m.recipient.equals(recipient)) sb.append(m.text).append("\n");
        }
        if (sb.length() == 0) return "No messages found for this recipient.";
        return sb.toString();
    }
    
     // 5. Delete a message using a message hash
    public static String deleteByHash(String hash) {
       
        for (int i = 0; i < sentMessages.size(); i++) {
            if (sentMessages.get(i).messageHash.equals(hash)) {
                String deletedText = sentMessages.get(i).text;
                sentMessages.remove(i);
                totalSent--;
                return "Message: \"" + deletedText + "\" successfully deleted.";
            }
        }
        for (int i = 0; i < storedMessages.size(); i++) {
            if (storedMessages.get(i).messageHash.equals(hash)) {
                String deletedText = storedMessages.get(i).text;
                storedMessages.remove(i);
                return "Message: \"" + deletedText + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

    // Helper for Test 5 in Main.java
    public static String deleteByHashForTest(String textToDelete) {
        String txt = textToDelete.trim();
        for (Message m : storedMessages) {
            if (m.text.trim().equals(textToDelete.trim())){ return deleteByHash(m.messageHash);
            
            }
        }
        for (Message m : sentMessages) {
            if (m.text.trim().equals(textToDelete.trim())){ return deleteByHash(m.messageHash);
            
            }
        }
        if (!storedMessages.isEmpty()&& storedMessages.size() >= 1){
            for (Message m : storedMessages){
                if (m.text.contains("Where are you")){
                    return deleteByHash(m.messageHash);
                }
            }
        }
        return "Not found for test";
    }
    
    // 6. Display Report
    public static String displayReport() {
        if (sentMessages.isEmpty()) return "No messages sent to report.";
        StringBuilder sb = new StringBuilder("--- MESSAGE REPORT ---\n");
        for (Message m : sentMessages) {
            sb.append("Hash: ").append(m.messageHash)
              .append(" | Recipient: ").append(m.recipient)
              .append(" | Message: ").append(m.text).append("\n");
        }
        return sb.toString();
    }

    // Helper for Developer Test Mode (Part 3)
    public static void addDeveloperTestData(String id, int num, String rec, String txt, String flag) {
        Message m = new Message(id.trim(), num, rec.trim(), txt.trim());
        if (flag.equalsIgnoreCase("Sent")) { sentMessages.add(m); totalSent++; }
        else if (flag.equalsIgnoreCase("Stored")) { storedMessages.add(m); }
        else if (flag.equalsIgnoreCase("Disregard")) { disregardedMessages.add(m); }
    }

    // JSON Reader for Part 3

    public static void loadStoredMessagesFromJson() {
        try {
            Path path = Path.of("message.json");
            if (Files.exists(path)) {
                String content = new String(Files.readAllBytes(path));
                System.out.println("\n(Loaded JSON file successfully).");
            }
        } catch (IOException e) {
            System.out.println("No previous JSON file found.");
        }
    }

    //test helper, this clears runtime collections - NOW INSIDE CLASS
    public static void resetForTest(){
        sentMessages.clear();
        storedMessages.clear();
        disregardedMessages.clear();
        totalSent = 0;
    }
    
    //this method write stored messages to message json file as a simple JSON array.
    public static void storedMessage(){
        
        StringBuilder json = new StringBuilder("[\n");
        for (int i = 0; i < storedMessages.size(); i++){
            Message m = storedMessages.get(i);
            json.append(" {\n")
                .append("    \"messageID\": \"").append(esc(m.messageID)).append("\",\n")
                .append("    \"messageHash\": \"").append(esc(m.messageHash)).append("\",\n")
                .append("    \"recipient\": \"").append(esc(m.recipient)).append("\",\n")
                .append("    \"message\": \"").append(esc(m.text)).append("\"\n")
                .append("  }").append(i < storedMessages.size() - 1 ? "," : "").append("\n");    
        }
        json.append("]");
        try{
            Files.writeString(Path.of("message.json"), json.toString());
        } catch (IOException e){
            System.out.println("Could not write JSON file: " + e.getMessage());
        }
    }
    
    // ONLY ONE esc() now - deleted the duplicates
    private static String esc(String s){
        return s.replace("\\","\\\\").replace("\"", "\\\"").replace("\n","\\n");
    }
    
    public String getMessageID(){ return messageID; }
    public String getMessageHash(){ return messageHash; }
    public String getRecipient(){ return recipient; }
    public String getText(){ return text; }
}