
package com.mycompany.universitysystem.auth;

import java.util.HashMap;

public class AuthService {

    // Admin login (can be changed or moved to DB later)
    private final String ADMIN_USERNAME = "admin";
    private final String ADMIN_PASSWORD = "1234";

    // Memory-based accounts for students and instructors
    private HashMap<String, String> studentAccounts = new HashMap<>();
    private HashMap<String, String> instructorAccounts = new HashMap<>();

    public boolean loginAdmin(String username, String password) {
        return username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD);
    }

    public boolean loginStudent(String username, String password) {
        return studentAccounts.containsKey(username)
                && studentAccounts.get(username).equals(password);
    }

    public boolean loginInstructor(String username, String password) {
        return instructorAccounts.containsKey(username)
                && instructorAccounts.get(username).equals(password);
    }

    public void createStudent(String username, String password) {
        studentAccounts.put(username, password);
    }

    public void createInstructor(String username, String password) {
        instructorAccounts.put(username, password);
    }
}