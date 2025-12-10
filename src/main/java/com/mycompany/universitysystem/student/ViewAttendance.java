package com.mycompany.universitysystem.student;

import javax.swing.*;

public class ViewAttendance extends JFrame {

    public ViewAttendance() {
        setTitle("View Attendance");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea attendanceArea = new JTextArea(
            "CSE101: 90%\n" +
            "CSE102: 86%\n" +
            "MAT101: 92%"
        );
        attendanceArea.setEditable(false);
        add(new JScrollPane(attendanceArea));
    }
}