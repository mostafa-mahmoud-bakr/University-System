package com.mycompany.universitysystem.student;

import javax.swing.*;

public class ViewGrades extends JFrame {

    public ViewGrades() {
        setTitle("View Grades");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea gradesArea = new JTextArea(
            "Course ID: CSE101 - Grade: A\n" +
            "Course ID: CSE102 - Grade: B+\n" +
            "Course ID: MAT101 - Grade: A-"
        );
        gradesArea.setEditable(false);
        add(new JScrollPane(gradesArea));
    }
}
