package javaapplication1;

import java.util.regex.Pattern;

public class Login {

    // === STORAGE VARIABLES ===
    // these hold the registered user's details
    public static String storedUsername = "";
    public static String storedPassword = "";
    public static String storedFirstName = "";
    public static String storedLastName = "";
    public static String storedPhone = "";

    // === PHONE VALIDATION PATTERN ===
    // Regex: starts with +27 and has 10 digits after it (total 13 chars like +27718693002)
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+27[1-9]\\d{8}$");

    // this method checks if username is correctly formatted
    public static boolean checkUserName(String username) {
        if (username.length() == 0 || username.length() > 5) {
            return false;
        }
        boolean hasUnderscore = false;
        for (int i = 0; i < username.length(); i++) {
            if (username.charAt(i) == '_') {
                hasUnderscore = true;
            }
        }
        return hasUnderscore;
    }

    // this method checks if password meets complexity requirements
    public static boolean checkPasswordComplexity(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                hasCapital = true;
            }
            if (Character.isDigit(c)) {
                hasNumber = true;
            }
            if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }
        return hasCapital && hasNumber && hasSpecial;
    }

    public static boolean checkCellPhoneNumber(String phone) {
        return PHONE_PATTERN.matcher(phone).matches();
    }

    // this method handles user registration
    public static String registerUser(String username, String password, String firstName, String lastName, String phone) {
        if (checkUserName(username) == false) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (checkPasswordComplexity(password) == false) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (checkCellPhoneNumber(phone) == false) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        // if everything is valid, save
        storedUsername = username;
        storedPassword = password;
        storedFirstName = firstName;
        storedLastName = lastName;
        storedPhone = phone;

        return "User registered successfully.";
    }

    // this method checks if the username and password match the saved ones
    public static boolean loginUser(String username, String password) {
        return username.equals(storedUsername) && password.equals(storedPassword);
    }

    // this method returns the login status message - used by Main
    public static String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + storedFirstName + " " + storedLastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}



    

            
    
               
        
        
    
    


        
    


    
        
    
  