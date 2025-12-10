package com.mycompany.universitysystem.admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ManageStudents extends JFrame {

    private DefaultTableModel tableModel;
    private JTable table;

    // Temporary storage
    private ArrayList<String[]> students = new ArrayList<>();

    public ManageStudents() {
        setTitle("Manage Students");
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Title
        JLabel title = new JLabel("Student Management", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        // Table to display students
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Email"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Buttons
        JButton add = new JButton("Add Student");
        JButton update = new JButton("Update Student");
        JButton delete = new JButton("Delete Student");

        // Add Student functionality
        add.addActionListener(e -> addStudent());

        // Update Student functionality
        update.addActionListener(e -> updateStudent());

        // Delete Student functionality
        delete.addActionListener(e -> deleteStudent());

        // Layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(add);
        buttonPanel.add(update);
        buttonPanel.add(delete);

        setLayout(new BorderLayout());
        add(title, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addStudent() {
        String id = JOptionPane.showInputDialog(this, "Enter Student ID:");
        String name = JOptionPane.showInputDialog(this, "Enter Student Name:");
        String email = JOptionPane.showInputDialog(this, "Enter Student Email:");

        if (id != null && name != null && email != null) {
            String[] student = {id, name, email};
            students.add(student);
            tableModel.addRow(student);

            JOptionPane.showMessageDialog(this, "Student Added Successfully!");
        }
    }

    private void updateStudent() {
        int selected = table.getSelectedRow();

        if (selected == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to update!");
            return;
        }

        String id = JOptionPane.showInputDialog(this, "Enter New ID:", tableModel.getValueAt(selected, 0));
        String name = JOptionPane.showInputDialog(this, "Enter New Name:", tableModel.getValueAt(selected, 1));
        String email = JOptionPane.showInputDialog(this, "Enter New Email:", tableModel.getValueAt(selected, 2));

        tableModel.setValueAt(id, selected, 0);
        tableModel.setValueAt(name, selected, 1);
        tableModel.setValueAt(email, selected, 2);

        students.set(selected, new String[]{id, name, email});

        JOptionPane.showMessageDialog(this, "Student Updated Successfully!");
    }

    private void deleteStudent() {
        int selected = table.getSelectedRow();

        if (selected == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to delete!");
            return;
        }

        students.remove(selected);
        tableModel.removeRow(selected);

        JOptionPane.showMessageDialog(this, "Student Deleted Successfully!");
    }
}