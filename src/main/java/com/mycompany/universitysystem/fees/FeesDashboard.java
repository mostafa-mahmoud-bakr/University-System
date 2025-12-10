package com.mycompany.universitysystem.fees;

import com.mycompany.universitysystem.admin.ManageFees;
import com.mycompany.universitysystem.fees.FeesService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FeesDashboard extends JFrame {

    private JButton addOrUpdateBtn;
    private JButton viewStatusBtn;
    private JButton viewPendingBtn;
    private JButton viewPaidBtn;

    public FeesDashboard() {
        setTitle("Fees Dashboard");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        // Buttons
        addOrUpdateBtn = new JButton("Add / Update Student Fees");
        viewStatusBtn = new JButton("View Student Fees Status");
        viewPendingBtn = new JButton("View Pending Fees");
        viewPaidBtn = new JButton("View Fully Paid Students");

        // Add buttons to window
        add(addOrUpdateBtn);
        add(viewStatusBtn);
        add(viewPendingBtn);
        add(viewPaidBtn);

        // 1️⃣ Add or update fee record
        addOrUpdateBtn.addActionListener(e -> new ManageFees().setVisible(true));

        // 2️⃣ View status of a single student
        viewStatusBtn.addActionListener(e -> new StudentFeesStatus().setVisible(true));

        // 3️⃣ View list of students with pending fees
        viewPendingBtn.addActionListener(e -> showPendingFees());

        // 4️⃣ View students who fully paid
        viewPaidBtn.addActionListener(e -> showPaidStudents());
    }

    // ≡ LIST OF PENDING FEES
    private void showPendingFees() {
        ArrayList<String> list = FeesService.getPendingStudents();

        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No pending payments!");
            return;
        }

        JOptionPane.showMessageDialog(this,
                String.join("\n", list),
                "Pending Fees",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // ≡ LIST OF FULLY PAID STUDENTS
    private void showPaidStudents() {
        ArrayList<String> list = FeesService.getPaidStudents();

        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No fully paid students yet!");
            return;
        }

        JOptionPane.showMessageDialog(this,
                String.join("\n", list),
                "Paid Students",
                JOptionPane.INFORMATION_MESSAGE);
    }
}