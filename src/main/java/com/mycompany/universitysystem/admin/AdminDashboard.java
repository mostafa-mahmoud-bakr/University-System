
package com.mycompany.universitysystem.admin;

import com.mycompany.universitysystem.fees.FeesDashboard;
import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    private JButton manageFeesBtn;
    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        manageFeesBtn = new JButton("Manage Fees");
        manageFeesBtn.addActionListener(e -> new ManageFees().setVisible(true));
        add(manageFeesBtn);
        
        JButton btnStudents = new JButton("Manage Students");
        JButton btnInstructors = new JButton("Manage Instructors");
        JButton btnCourses = new JButton("Manage Courses");

        btnStudents.addActionListener(e -> new ManageStudents().setVisible(true));
        btnInstructors.addActionListener(e -> new ManageInstructors().setVisible(true));
        btnCourses.addActionListener(e -> new ManageCourses().setVisible(true));

        setLayout(new GridLayout(3, 1, 10, 10));
        add(btnStudents);
        add(btnInstructors);
        add(btnCourses);
    }


}