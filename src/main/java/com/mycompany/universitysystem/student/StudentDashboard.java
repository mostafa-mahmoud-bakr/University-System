package com.mycompany.universitysystem.student;

import com.mycompany.universitysystem.fees.StudentFeesStatus;
import javax.swing.*;
import java.awt.*;

public class StudentDashboard extends JFrame {

    private JButton viewGradesBtn;
    private JButton viewAttendanceBtn;
    private JButton payFeesBtn;
    private JButton checkFeesBtn;
    public StudentDashboard() {
        setTitle("Student Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        viewGradesBtn = new JButton("View Grades");
        viewAttendanceBtn = new JButton("View Attendance");
        payFeesBtn = new JButton("Pay Fees");
        checkFeesBtn = new JButton("Check Fees Status");

        checkFeesBtn.addActionListener(e -> new StudentFeesStatus().setVisible(true));
        add(checkFeesBtn);

        viewGradesBtn.addActionListener(e -> new ViewGrades().setVisible(true));
        viewAttendanceBtn.addActionListener(e -> new ViewAttendance().setVisible(true));
        payFeesBtn.addActionListener(e -> new PayFees().setVisible(true));

        setLayout(new GridLayout(3, 1, 10, 10));
        add(viewGradesBtn);
        add(viewAttendanceBtn);
        add(payFeesBtn);
    }
}
