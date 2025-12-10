

package com.mycompany.universitysystem.auth;

import com.mycompany.universitysystem.admin.AdminDashboard;
import com.mycompany.universitysystem.student.StudentDashboard;
import com.mycompany.universitysystem.instructor.InstructorDashboard;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {

    private AuthService authService = new AuthService();

    public LoginForm() {
        setTitle("University Multi-Login System");
        setSize(420, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblTitle = new JLabel("Login Portal", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel lblRole = new JLabel("Select Role:");
        JLabel lblUser = new JLabel("Username:");
        JLabel lblPass = new JLabel("Password:");

        String[] roles = {"Admin", "Student", "Instructor"};
        JComboBox<String> roleBox = new JComboBox<>(roles);

        JTextField txtUser = new JTextField();
        JPasswordField txtPass = new JPasswordField();

        JButton btnLogin = new JButton("Login");
        JButton btnCreate = new JButton("Create Account");

        // LOGIN BUTTON
        btnLogin.addActionListener(e -> {
            String role = (String) roleBox.getSelectedItem();
            String username = txtUser.getText();
            String password = new String(txtPass.getPassword());

            switch (role) {
                case "Admin":
                    if (authService.loginAdmin(username, password)) {
                        JOptionPane.showMessageDialog(this, "Admin Login Successful!");
                        new AdminDashboard().setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid Admin Credentials!");
                    }
                    break;

                case "Student":
                    if (authService.loginStudent(username, password)) {
                        JOptionPane.showMessageDialog(this, "Student Login Successful!");
                        new StudentDashboard().setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid Student Credentials!");
                    }
                    break;

                case "Instructor":
                    if (authService.loginInstructor(username, password)) {
                        JOptionPane.showMessageDialog(this, "Instructor Login Successful!");
                        new InstructorDashboard().setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid Instructor Credentials!");
                    }
                    break;
            }
        });

        // CREATE ACCOUNT BUTTON
        btnCreate.addActionListener(e -> {
            String role = (String) roleBox.getSelectedItem();

            if (role.equals("Admin")) {
                JOptionPane.showMessageDialog(this, "Admin accounts cannot be created here.");
                return;
            }

            String username = JOptionPane.showInputDialog("Enter new username:");
            String password = JOptionPane.showInputDialog("Enter new password:");

            if (username == null || password == null) return;

            if (role.equals("Student")) {
                authService.createStudent(username, password);
                JOptionPane.showMessageDialog(this, "Student account created!");
            } else {
                authService.createInstructor(username, password);
                JOptionPane.showMessageDialog(this, "Instructor account created!");
            }
        });

        // Form Layout
        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        form.add(lblRole);
        form.add(roleBox);
        form.add(lblUser);
        form.add(txtUser);
        form.add(lblPass);
        form.add(txtPass);
        form.add(btnLogin);
        form.add(btnCreate);

        setLayout(new BorderLayout(10, 10));
        add(lblTitle, BorderLayout.NORTH);
        add(form, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new LoginForm().setVisible(true);
    }
}