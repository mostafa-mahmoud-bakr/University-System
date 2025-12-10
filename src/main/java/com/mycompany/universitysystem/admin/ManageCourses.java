package com.mycompany.universitysystem.admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class ManageCourses extends JFrame {

    private JTextField idField, nameField, creditsField, constructorField, searchField;
    private JTable table;
    private DefaultTableModel model;

    public ManageCourses() {

        setTitle("Manage Courses");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== TABLE =====
        String[] columns = {"ID", "Name", "Credits", "Constructor"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        // ===== INPUT PANEL =====
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));

        inputPanel.add(new JLabel("Course ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Course Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Credits:"));
        creditsField = new JTextField();
        inputPanel.add(creditsField);

        inputPanel.add(new JLabel("Constructor:"));
        constructorField = new JTextField();
        inputPanel.add(constructorField);

        JButton addBtn = new JButton("Add");
        JButton editBtn = new JButton("Edit");
        JButton removeBtn = new JButton("Remove");

        inputPanel.add(addBtn);
        inputPanel.add(editBtn);
        inputPanel.add(removeBtn);

        add(inputPanel, BorderLayout.SOUTH);

        // ===== SEARCH PANEL =====
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search (ID or Name):"));
        searchField = new JTextField(20);
        searchPanel.add(searchField);

        JButton searchBtn = new JButton("Search");
        JButton showAllBtn = new JButton("Show All");
        searchPanel.add(searchBtn);
        searchPanel.add(showAllBtn);

        add(searchPanel, BorderLayout.NORTH);

        // ===== BUTTON ACTIONS =====

        // Add course
        addBtn.addActionListener(e -> {
            String id = idField.getText();
            String name = nameField.getText();
            String credits = creditsField.getText();
            String constructor = constructorField.getText();

            if (id.isEmpty() || name.isEmpty() || credits.isEmpty() || constructor.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields are required!");
                return;
            }

            model.addRow(new Object[]{id, name, credits, constructor});
            clearFields();
        });

        // Remove course
        removeBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Select a row to remove!");
                return;
            }
            model.removeRow(row);
        });

        // Edit selected course
        editBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Select a row to edit!");
                return;
            }

            model.setValueAt(idField.getText(), row, 0);
            model.setValueAt(nameField.getText(), row, 1);
            model.setValueAt(creditsField.getText(), row, 2);
            model.setValueAt(constructorField.getText(), row, 3);

            clearFields();
        });

        // Load clicked row into fields
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                idField.setText(model.getValueAt(row, 0).toString());
                nameField.setText(model.getValueAt(row, 1).toString());
                creditsField.setText(model.getValueAt(row, 2).toString());
                constructorField.setText(model.getValueAt(row, 3).toString());
            }
        });

        // Search course
        searchBtn.addActionListener(e -> {
            String keyword = searchField.getText().trim().toLowerCase();
            if (keyword.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter ID or Name to search");
                return;
            }

            DefaultTableModel searchModel = new DefaultTableModel(columns, 0);

            for (int i = 0; i < model.getRowCount(); i++) {
                String id = model.getValueAt(i, 0).toString().toLowerCase();
                String name = model.getValueAt(i, 1).toString().toLowerCase();

                if (id.contains(keyword) || name.contains(keyword)) {
                    searchModel.addRow(new Object[]{
                            model.getValueAt(i, 0),
                            model.getValueAt(i, 1),
                            model.getValueAt(i, 2),
                            model.getValueAt(i, 3)
                    });
                }
            }

            table.setModel(searchModel);
        });

        // Show all courses again
        showAllBtn.addActionListener(e -> table.setModel(model));

        setVisible(true);
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        creditsField.setText("");
        constructorField.setText("");
    }

    public static void main(String[] args) {
        new ManageCourses();
    }
}