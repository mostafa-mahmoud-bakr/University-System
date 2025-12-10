package com.mycompany.universitysystem.admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class ManageFees extends JFrame {

    private JTextField idField, nameField, paidField, remainField, totalField, searchField;
    private JTable table;
    private DefaultTableModel model;

    public ManageFees() {

        setTitle("Manage Student Fees");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ======= TABLE MODEL =======
        String[] columns = {"Student ID", "Student Name", "Paid", "Remain", "Total Fees"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        // ======= INPUT PANEL =======
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.insets = new Insets(5, 5, 5, 5); // margin
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        
        // ---------- Row 0 ----------
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Student ID:"), gbc);
        
        gbc.gridx = 1;
        idField = new JTextField();
        inputPanel.add(idField, gbc);
        
        // ---------- Row 1 ----------
        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("Student Name:"), gbc);
        
        gbc.gridx = 1;
        nameField = new JTextField();
        inputPanel.add(nameField, gbc);

        // ---------- Row 2 ----------
        gbc.gridx = 0; gbc.gridy = 2;
        inputPanel.add(new JLabel("Paid Amount:"), gbc);
        
        gbc.gridx = 1;
        paidField = new JTextField();
        inputPanel.add(paidField, gbc);
        
        // ---------- Row 3 ----------
        gbc.gridx = 0; gbc.gridy = 3;
        inputPanel.add(new JLabel("Remain Amount:"), gbc);
        
        gbc.gridx = 1;
        remainField = new JTextField();
        inputPanel.add(remainField, gbc);
        
        // ---------- Row 4 ----------
        gbc.gridx = 0; gbc.gridy = 4;
        inputPanel.add(new JLabel("Total Fees:"), gbc);
        
        gbc.gridx = 1;
        totalField = new JTextField();
        inputPanel.add(totalField, gbc);
        
        // ---------- Row 5 : BUTTON PANEL ----------
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addBtn = new JButton("Add");
        JButton editBtn = new JButton("Edit");
        JButton deleteBtn = new JButton("Delete");

        btnPanel.add(addBtn);
        btnPanel.add(editBtn);
        btnPanel.add(deleteBtn);

        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2; 
        inputPanel.add(btnPanel, gbc);
        add(inputPanel, BorderLayout.SOUTH);

        // ======= SEARCH PANEL =======
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search (ID or Name):"));
        searchField = new JTextField(20);
        searchPanel.add(searchField);

        JButton searchBtn = new JButton("Search");
        JButton showAllBtn = new JButton("Show All");
        searchPanel.add(searchBtn);
        searchPanel.add(showAllBtn);

        add(searchPanel, BorderLayout.NORTH);

        // ======= BUTTON ACTIONS =======

        // ADD FEES
        addBtn.addActionListener(e -> {
            String id = idField.getText();
            String name = nameField.getText();
            String paid = paidField.getText();
            String remain = remainField.getText();
            String total = totalField.getText();

            if (id.isEmpty() || name.isEmpty() || paid.isEmpty() ||
                remain.isEmpty() || total.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields are required!");
                return;
            }

            model.addRow(new Object[]{id, name, paid, remain, total});
            clearFields();
        });

        // DELETE FEES
        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Select a row to delete!");
                return;
            }
            model.removeRow(row);
            clearFields();
        });

        // EDIT FEES
        editBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Select a row to edit!");
                return;
            }

            model.setValueAt(idField.getText(), row, 0);
            model.setValueAt(nameField.getText(), row, 1);
            model.setValueAt(paidField.getText(), row, 2);
            model.setValueAt(remainField.getText(), row, 3);
            model.setValueAt(totalField.getText(), row, 4);

            clearFields();
        });

        // LOAD ROW INTO INPUT FIELDS
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                idField.setText(model.getValueAt(row, 0).toString());
                nameField.setText(model.getValueAt(row, 1).toString());
                paidField.setText(model.getValueAt(row, 2).toString());
                remainField.setText(model.getValueAt(row, 3).toString());
                totalField.setText(model.getValueAt(row, 4).toString());
            }
        });

        // SEARCH FEES
        searchBtn.addActionListener(e -> {
            String keyword = searchField.getText().toLowerCase();

            if (keyword.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter ID or Name to search.");
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
                            model.getValueAt(i, 3),
                            model.getValueAt(i, 4)
                    });
                }
            }

            table.setModel(searchModel);
        });

        // SHOW ALL
        showAllBtn.addActionListener(e -> table.setModel(model));

        setVisible(true);
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        paidField.setText("");
        remainField.setText("");
        totalField.setText("");
    }

    public static void main(String[] args) {
        new ManageFees();
    }
}