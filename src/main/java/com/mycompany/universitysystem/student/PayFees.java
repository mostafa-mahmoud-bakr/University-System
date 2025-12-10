package com.mycompany.universitysystem.student;

import javax.swing.*;
import com.mycompany.universitysystem.fees.FeesService;
import com.mycompany.universitysystem.fees.FeeRecord;

public class PayFees extends JFrame {

    private JTextField amountField;
    private JButton payBtn;
    String studentId = "S100"; // get logged in student later
    double amount = Double.parseDouble(amountField.getText());
    boolean ok = FeesService.payFees(studentId, amount);
    
    public PayFees() {
        setTitle("Pay Fees");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(20, 30, 100, 25);
        add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(120, 30, 180, 25);
        add(amountField);

        payBtn = new JButton("Pay");
        payBtn.setBounds(120, 80, 120, 30);
        add(payBtn);

        payBtn.addActionListener(e -> {
            String amount = amountField.getText();

            JOptionPane.showMessageDialog(this,
                "Payment Successful!\nAmount Paid: " + amount);
        });
    }
}
