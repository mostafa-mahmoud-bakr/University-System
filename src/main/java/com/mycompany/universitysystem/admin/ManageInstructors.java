package com.mycompany.universitysystem.admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ManageInstructors extends JFrame {

    private DefaultTableModel tableModel;
    private JTable table;

    private ArrayList<String[]> instructors = new ArrayList<>();

    public ManageInstructors() {
        setTitle("Manage Instructors");
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Title
        JLabel title = new JLabel("Instructor Management", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        // Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Department"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Buttons
        JButton add = new JButton("Add Instructor");
        JButton update = new JButton("Update Instructor");
        JButton delete = new JButton("Delete Instructor");

        add.addActionListener(e -> addInstructor());
        update.addActionListener(e -> updateInstructor());
        delete.addActionListener(e -> deleteInstructor());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(add);
        buttonPanel.add(update);
        buttonPanel.add(delete);

        setLayout(new BorderLayout());
        add(title, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addInstructor() {
        String id = JOptionPane.showInputDialog(this, "Enter Instructor ID:");
        String name = JOptionPane.showInputDialog(this, "Enter Instructor Name:");
        String dept = JOptionPane.showInputDialog(this, "Enter Department:");

        if (id != null && name != null && dept != null) {
            String[] instructor = {id, name, dept};
            instructors.add(instructor);
            tableModel.addRow(instructor);

            JOptionPane.showMessageDialog(this, "Instructor Added Successfully!");
        }
    }

    private void updateInstructor() {
        int selected = table.getSelectedRow();

        if (selected == -1) {
            JOptionPane.showMessageDialog(this, "Please select an instructor to update!");
            return;
        }

        String id = JOptionPane.showInputDialog(this, "Enter New ID:", tableModel.getValueAt(selected, 0));
        String name = JOptionPane.showInputDialog(this, "Enter New Name:", tableModel.getValueAt(selected, 1));
        String dept = JOptionPane.showInputDialog(this, "Enter New Department:", tableModel.getValueAt(selected, 2));

        tableModel.setValueAt(id, selected, 0);
        tableModel.setValueAt(name, selected, 1);
        tableModel.setValueAt(dept, selected, 2);

        instructors.set(selected, new String[]{id, name, dept});

        JOptionPane.showMessageDialog(this, "Instructor Updated Successfully!");
    }

    private void deleteInstructor() {
        int selected = table.getSelectedRow();

        if (selected == -1) {
            JOptionPane.showMessageDialog(this, "Please select an instructor to delete!");
            return;
        }

        instructors.remove(selected);
        tableModel.removeRow(selected);

        JOptionPane.showMessageDialog(this, "Instructor Deleted Successfully!");
    }
}