package com.mycompany.universitysystem.fees;

import javax.swing.*;

public class StudentFeesStatus extends JFrame {

    private JTextField studentIdField;
    private JButton checkStatusBtn;

    public StudentFeesStatus() {
        setTitle("Check Fees Status");
        setSize(350, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setBounds(20, 20, 100, 25);
        add(idLabel);

        studentIdField = new JTextField();
        studentIdField.setBounds(120, 20, 180, 25);
        add(studentIdField);

        checkStatusBtn = new JButton("Check Status");
        checkStatusBtn.setBounds(100, 70, 140, 30);
        add(checkStatusBtn);

        checkStatusBtn.addActionListener(e -> {
            String id = studentIdField.getText();
            FeeRecord record = FeesService.getFeeRecord(id);

            if (record == null) {
                JOptionPane.showMessageDialog(this, "No record found for Student ID: " + id);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Total Fees: " + record.getTotalFees() +
                        "\nPaid: " + record.getPaidAmount() +
                        "\nRemaining: " + record.getRemainingFees() +
                        "\nStatus: " + (record.isFullyPaid() ? "Fully Paid" : "Pending")
                );
            }
        });
    }
}
