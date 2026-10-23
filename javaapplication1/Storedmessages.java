/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author PC365
 */
class Storedmessages {
    

public class StoredMessages {
    private final ArrayList<String> storedMessages = new ArrayList<>();

    public void addStoredMessage(String message) {
        storedMessages.add(message);
    }

    public void showStoredMessages() {
        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages available.");
        } else {
            System.out.println("Stored messages:");
            for (String m : storedMessages) {
                System.out.println(m);
            }
        }
    }
}

    void storedmessagesMenu(Scanner input) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
