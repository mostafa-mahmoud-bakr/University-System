package com.mycompany.universitysystem.instructor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;

public class UploadGrades extends JFrame {

    // ====== Colors (same as Courses & TakeAttendance) ======
    private static final Color BG_DARK = new Color(24, 24, 24);
    private static final Color CARD_BG = new Color(38, 38, 38);
    private static final Color CARD_HOVER = new Color(48, 48, 48);
    private static final Color GOLD = new Color(218, 165, 32);
    private static final Color TEXT_WHITE = new Color(240, 240, 240);
    private static final Color TEXT_GRAY = new Color(180, 180, 180);
    private static final Color SIDEBAR_BTN_BG = new Color(30, 30, 30);
    private static final Color SIDEBAR_BTN_HOVER = new Color(50, 50, 50);
    private static final Color TABLE_SELECTED = new Color(60, 120, 180);

    // ====== Table ======
    private JTable table;
    private DefaultTableModel model;
    private DefaultTableModel originalModel;

    // ====== Form fields ======
    private JTextField idField, nameField, courseField, gradeItemField, gradeField, searchField;

    private JButton addBtn, editBtn, deleteBtn, searchBtn, showAllBtn, clearBtn;

    public UploadGrades() {
        setTitle("Upload Grades");
        setSize(1400, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG_DARK);
        setLayout(new BorderLayout());

        add(createSidebar(), BorderLayout.WEST);
        add(createMainContent(), BorderLayout.CENTER);
    }

    // ================= Sidebar =================
    private JButton createSidebarButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setBackground(SIDEBAR_BTN_BG);
        btn.setForeground(TEXT_WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 10));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(SIDEBAR_BTN_HOVER); }
            public void mouseExited(MouseEvent e)  { btn.setBackground(SIDEBAR_BTN_BG); }
        });
        return btn;
    }

  private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(280, getHeight()));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(BG_DARK);
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

        // Logo area
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.X_AXIS));
        logoPanel.setBackground(BG_DARK);
        logoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        ImageIcon btuIcon = new ImageIcon("D:\\java\\UniversitySystem\\src\\main\\java\\icons\\btu.png");
        JLabel btuLabel =new JLabel(btuIcon);
        logoPanel.add(btuLabel);
        logoPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(BG_DARK);
        titlePanel.setAlignmentY(Component.TOP_ALIGNMENT);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 0));

        JLabel title = new JLabel("University");
        title.setForeground(GOLD);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        JLabel subtitle = new JLabel("Management System");
        subtitle.setForeground(TEXT_WHITE);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        titlePanel.add(title);
        titlePanel.add(subtitle);

        
        logoPanel.add(titlePanel);
        sidebar.add(logoPanel);
        sidebar.add(Box.createVerticalStrut(20));

        // Buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBackground(BG_DARK);
        buttonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnDashboard = createSidebarButton("▶ Dashboard");
        JButton btnUploadGrades = createSidebarButton("● Upload Grades");

        buttonsPanel.add(btnDashboard);
        buttonsPanel.add(Box.createVerticalStrut(10));
        buttonsPanel.add(Box.createVerticalStrut(10));
        buttonsPanel.add(btnUploadGrades);

        sidebar.add(buttonsPanel);
        sidebar.add(Box.createVerticalGlue());

        // Hook actions (simple navigation)
        btnDashboard.addActionListener(e -> { new InstructorDashboard().setVisible(true); dispose(); });
        btnUploadGrades.addActionListener(e -> { new UploadGrades().setVisible(true); dispose(); });

        return sidebar;
    }

    // ================= Main Content =================
    private JPanel createMainContent() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BG_DARK);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel header = new JLabel("Upload Grades");
        header.setForeground(TEXT_WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 42));
        mainPanel.add(header, BorderLayout.NORTH);

        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(BG_DARK);
        center.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        center.add(createControlsPanel(), BorderLayout.NORTH);
        center.add(createTablePanel(), BorderLayout.CENTER);

        mainPanel.add(center, BorderLayout.CENTER);

        return mainPanel;
    }

    // ================= Controls Panel =================
    private JPanel createControlsPanel() {
        JPanel controls = new JPanel(new GridBagLayout());
        controls.setBackground(BG_DARK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        // Student ID
        gbc.gridx = 0; gbc.gridy = 0;
        controls.add(makeLabel("Student ID:"), gbc);

        gbc.gridx = 1;
        idField = new JTextField(12);
        styleField(idField);
        controls.add(idField, gbc);

        // Student Name
        gbc.gridx = 2;
        controls.add(makeLabel("Student Name:"), gbc);

        gbc.gridx = 3;
        nameField = new JTextField(16);
        styleField(nameField);
        controls.add(nameField, gbc);

        // Course
        gbc.gridx = 0; gbc.gridy = 1;
        controls.add(makeLabel("Course:"), gbc);

        gbc.gridx = 1;
        courseField = new JTextField(12);
        styleField(courseField);
        controls.add(courseField, gbc);

        // Grade Item
        gbc.gridx = 2;
        controls.add(makeLabel("Grade Item:"), gbc);

        gbc.gridx = 3;
        gradeItemField = new JTextField(16);
        styleField(gradeItemField);
        controls.add(gradeItemField, gbc);

        // Grade Value
        gbc.gridx = 0; gbc.gridy = 2;
        controls.add(makeLabel("Grade:"), gbc);

        gbc.gridx = 1;
        gradeField = new JTextField(12);
        styleField(gradeField);
        controls.add(gradeField, gbc);

        // Buttons
        gbc.gridx = 2;
        addBtn = makeButton("Add");
        controls.add(addBtn, gbc);

        gbc.gridx = 3;
        editBtn = makeButton("Edit");
        controls.add(editBtn, gbc);

        gbc.gridx = 4;
        deleteBtn = makeButton("Delete");
        controls.add(deleteBtn, gbc);

        // Search Row
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.EAST;

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        searchPanel.setBackground(BG_DARK);

        searchPanel.add(makeLabel("Search:"));

        searchField = new JTextField(20);
        styleField(searchField);
        searchPanel.add(searchField);

        searchBtn = makeButton("Search");
        showAllBtn = makeButton("Show All");
        clearBtn = makeButton("Clear Fields");

        searchPanel.add(searchBtn);
        searchPanel.add(showAllBtn);
        searchPanel.add(clearBtn);

        controls.add(searchPanel, gbc);

        hookActions();

        return controls;
    }

    private JLabel makeLabel(String txt) {
        JLabel l = new JLabel(txt);
        l.setForeground(TEXT_GRAY);
        return l;
    }

    // ================= Table Panel =================
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BG_DARK);

        String[] columns = {"Student ID", "Student Name", "Course", "Grade Item", "Grade"};
        model = new DefaultTableModel(columns, 0);
        originalModel = new DefaultTableModel(columns, 0);

        table = new JTable(model) {
            public Component prepareRenderer(javax.swing.table.TableCellRenderer r, int row, int col) {
                Component c = super.prepareRenderer(r, row, col);
                if (isRowSelected(row)) {
                    c.setBackground(TABLE_SELECTED);
                    c.setForeground(TEXT_WHITE);
                } else {
                    c.setBackground(new Color(34, 34, 34));
                    c.setForeground(TEXT_WHITE);
                }
                return c;
            }
        };

        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setBackground(new Color(34, 34, 34));
        table.setForeground(TEXT_WHITE);
        table.setSelectionBackground(TABLE_SELECTED);
        table.setSelectionForeground(TEXT_WHITE);

        JTableHeader header = table.getTableHeader();
        header.setBackground(CARD_BG);
        header.setForeground(TEXT_WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scroll, BorderLayout.CENTER);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if (r != -1) {
                    idField.setText(model.getValueAt(r, 0).toString());
                    nameField.setText(model.getValueAt(r, 1).toString());
                    courseField.setText(model.getValueAt(r, 2).toString());
                    gradeItemField.setText(model.getValueAt(r, 3).toString());
                    gradeField.setText(model.getValueAt(r, 4).toString());
                }
            }
        });

        return panel;
    }

    // ================= Styling =================
    private void styleField(JTextField f) {
        f.setBackground(new Color(50, 50, 50));
        f.setForeground(TEXT_WHITE);
        f.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
        f.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }

    private JButton makeButton(String txt) {
        JButton b = new JButton(txt);
        b.setBackground(CARD_BG);
        b.setForeground(TEXT_WHITE);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));

        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { b.setBackground(CARD_HOVER); }
            public void mouseExited(MouseEvent e)  { b.setBackground(CARD_BG); }
        });

        return b;
    }

    // ================= CRUD Logic =================
    private void hookActions() {

        // Add
        addBtn.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String course = courseField.getText().trim();
            String item = gradeItemField.getText().trim();
            String grade = gradeField.getText().trim();

            if (id.isEmpty() || name.isEmpty() || course.isEmpty() || item.isEmpty() || grade.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill all fields.");
                return;
            }

            model.addRow(new Object[]{id, name, course, item, grade});
            originalModel.addRow(new Object[]{id, name, course, item, grade});
            clearFields();
        });

        // Edit
        editBtn.addActionListener(e -> {
            int r = table.getSelectedRow();
            if (r == -1) {
                JOptionPane.showMessageDialog(this, "Select a row to edit.");
                return;
            }

            model.setValueAt(idField.getText(), r, 0);
            model.setValueAt(nameField.getText(), r, 1);
            model.setValueAt(courseField.getText(), r, 2);
            model.setValueAt(gradeItemField.getText(), r, 3);
            model.setValueAt(gradeField.getText(), r, 4);

            syncOriginalModel();
            clearFields();
        });

        // Delete
        deleteBtn.addActionListener(e -> {
            int r = table.getSelectedRow();
            if (r == -1) {
                JOptionPane.showMessageDialog(this, "Select a row to delete.");
                return;
            }
            model.removeRow(r);
            syncOriginalModel();
            clearFields();
        });

        // Search
        searchBtn.addActionListener(e -> {
            String key = searchField.getText().trim().toLowerCase();
            if (key.isEmpty()) return;

            String[] cols = {"Student ID", "Student Name", "Course", "Grade Item", "Grade"};
            DefaultTableModel searchModel = new DefaultTableModel(cols, 0);

            for (int i = 0; i < model.getRowCount(); i++) {
                boolean match = false;
                for (int c = 0; c < 5; c++) {
                    String val = model.getValueAt(i, c).toString().toLowerCase();
                    if (val.contains(key)) match = true;
                }
                if (match) {
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

        // Show All
        showAllBtn.addActionListener(e -> table.setModel(model));

        // Clear fields
        clearBtn.addActionListener(e -> clearFields());
    }

    private void syncOriginalModel() {
        originalModel.setRowCount(0); 
        for (int i = 0; i < model.getRowCount(); i++) {
            originalModel.addRow(new Object[]{
                model.getValueAt(i, 0),
                model.getValueAt(i, 1),
                model.getValueAt(i, 2),
                model.getValueAt(i, 3),
                model.getValueAt(i, 4)
            });
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        courseField.setText("");
        gradeItemField.setText("");
        gradeField.setText("");
        table.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UploadGrades g = new UploadGrades();

            // sample data
            g.model.addRow(new Object[]{"S1001", "Ahmed Ali", "Programming 1", "Midterm", "87"});
            g.model.addRow(new Object[]{"S1002", "Sara Mohamed", "Programming 1", "Final", "92"});
            g.model.addRow(new Object[]{"S1003", "Omar Hana", "Algorithms", "Assignment 1", "75"});

            g.syncOriginalModel();
            g.setVisible(true);
        });
    }
}
