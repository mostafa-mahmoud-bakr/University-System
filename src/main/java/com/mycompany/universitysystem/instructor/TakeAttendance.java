package com.mycompany.universitysystem.instructor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;


public class TakeAttendance extends JFrame {

    // ====== Colors (same as Courses) ======
    private static final Color BG_DARK = new Color(24, 24, 24);
    private static final Color CARD_BG = new Color(38, 38, 38);
    private static final Color CARD_HOVER = new Color(48, 48, 48);
    private static final Color GOLD = new Color(218, 165, 32);
    private static final Color TEXT_WHITE = new Color(240, 240, 240);
    private static final Color TEXT_GRAY = new Color(180, 180, 180);
    private static final Color SIDEBAR_BTN_BG = new Color(30, 30, 30);
    private static final Color SIDEBAR_BTN_HOVER = new Color(50, 50, 50);
    private static final Color TABLE_SELECTED = new Color(60, 120, 180);

    // ====== Swing components ======
    private JTable table;
    private DefaultTableModel model;

    private JTextField idField, nameField, courseField, dateField, searchField;
    private JComboBox<String> statusCombo;
    private JButton addBtn, editBtn, deleteBtn, searchBtn, showAllBtn, clearBtn;

    // Keep a reference to the original model so "Show All" works
    private DefaultTableModel originalModel;

    public TakeAttendance() {
        setTitle("Take Attendance");
        setSize(1400, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BG_DARK);

        // Sidebar (reuse style)
        add(createSidebar(), BorderLayout.WEST);

        // Main content
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
            @Override
            public void mouseEntered(MouseEvent e) { btn.setBackground(SIDEBAR_BTN_HOVER); }
            @Override
            public void mouseExited(MouseEvent e)  { btn.setBackground(SIDEBAR_BTN_BG); }
        });
        return btn;
    }
    
    // ================= Side Bare =================
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
        JButton btnAttendance = createSidebarButton("● Take Attendance");

        buttonsPanel.add(btnDashboard);
        buttonsPanel.add(Box.createVerticalStrut(10));
        buttonsPanel.add(Box.createVerticalStrut(10));
        buttonsPanel.add(btnAttendance);

        sidebar.add(buttonsPanel);
        sidebar.add(Box.createVerticalGlue());

        // Hook actions (simple navigation)
        btnDashboard.addActionListener(e -> { new InstructorDashboard().setVisible(true); dispose(); });
        btnAttendance.addActionListener(e -> { new TakeAttendance().setVisible(true); });

        return sidebar;
    }

    // ================= Main Content =================
    private JPanel createMainContent() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BG_DARK);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // Header
        JLabel header = new JLabel("Take Attendance");
        header.setForeground(TEXT_WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 42));
        mainPanel.add(header, BorderLayout.NORTH);

        // Replace cards area with the table and controls
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(BG_DARK);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        // Top controls (input fields + buttons)
        centerPanel.add(createControlsPanel(), BorderLayout.NORTH);

        // Table area
        centerPanel.add(createTablePanel(), BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        return mainPanel;
    }

    // ================ Controls Panel (Form + Buttons) ================
    private JPanel createControlsPanel() {
        JPanel controls = new JPanel();
        controls.setBackground(BG_DARK);
        controls.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        // Student ID
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setForeground(TEXT_GRAY);
        controls.add(idLabel, gbc);

        gbc.gridx = 1;
        idField = new JTextField(12);
        styleField(idField);
        controls.add(idField, gbc);

        // Student Name
        gbc.gridx = 2;
        JLabel nameLabel = new JLabel("Student Name:");
        nameLabel.setForeground(TEXT_GRAY);
        controls.add(nameLabel, gbc);

        gbc.gridx = 3;
        nameField = new JTextField(16);
        styleField(nameField);
        controls.add(nameField, gbc);

        // Course
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel courseLabel = new JLabel("Course:");
        courseLabel.setForeground(TEXT_GRAY);
        controls.add(courseLabel, gbc);

        gbc.gridx = 1;
        courseField = new JTextField(12);
        styleField(courseField);
        controls.add(courseField, gbc);

        // Date
        gbc.gridx = 2;
        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        dateLabel.setForeground(TEXT_GRAY);
        controls.add(dateLabel, gbc);

        gbc.gridx = 3;
        dateField = new JTextField(12);
        styleField(dateField);
        controls.add(dateField, gbc);

        // Status
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setForeground(TEXT_GRAY);
        controls.add(statusLabel, gbc);

        gbc.gridx = 1;
        statusCombo = new JComboBox<>(new String[]{"Present", "Absent", "Late", "Excused"});
        styleCombo(statusCombo);
        controls.add(statusCombo, gbc);

        // Buttons
        gbc.gridx = 2; gbc.gridy = 2;
        addBtn = new JButton("Add");
        styleActionButton(addBtn);
        controls.add(addBtn, gbc);

        gbc.gridx = 3;
        editBtn = new JButton("Edit");
        styleActionButton(editBtn);
        controls.add(editBtn, gbc);

        gbc.gridx = 4;
        deleteBtn = new JButton("Delete");
        styleActionButton(deleteBtn);
        controls.add(deleteBtn, gbc);

        // Search area (right aligned)
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.EAST;

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        searchPanel.setBackground(BG_DARK);
        searchPanel.add(new JLabel("Search:"));
        searchField = new JTextField(20);
        styleField(searchField);
        searchPanel.add(searchField);

        searchBtn = new JButton("Search");
        showAllBtn = new JButton("Show All");
        clearBtn = new JButton("Clear Fields");
        styleActionButton(searchBtn);
        styleActionButton(showAllBtn);
        styleActionButton(clearBtn);

        searchPanel.add(searchBtn);
        searchPanel.add(showAllBtn);
        searchPanel.add(clearBtn);

        controls.add(searchPanel, gbc);

        // Hook button actions
        hookControlActions();

        return controls;
    }

    // ================ Table Panel ================
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BG_DARK);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // Table columns
        String[] columns = {"Student ID", "Student Name", "Course", "Date", "Attendance Status"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model) {
            // make table background and row height pleasant
            @Override
            public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
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
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setSelectionBackground(TABLE_SELECTED);
        table.setSelectionForeground(TEXT_WHITE);
        table.setBackground(new Color(34, 34, 34));
        table.setForeground(TEXT_WHITE);

        // header style
        JTableHeader header = table.getTableHeader();
        header.setBackground(CARD_BG);
        header.setForeground(TEXT_WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setReorderingAllowed(false);

        // set status column editor to combo box
        table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JComboBox<>(new String[]{"Present","Absent","Late","Excused"})));

        // Put table inside scrollpane
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scroll, BorderLayout.CENTER);

        // When selecting a row, populate input fields
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    idField.setText(String.valueOf(model.getValueAt(row, 0)));
                    nameField.setText(String.valueOf(model.getValueAt(row, 1)));
                    courseField.setText(String.valueOf(model.getValueAt(row, 2)));
                    dateField.setText(String.valueOf(model.getValueAt(row, 3)));
                    statusCombo.setSelectedItem(String.valueOf(model.getValueAt(row, 4)));
                }
            }
        });

        // Save a copy of the original model (empty now) for Show All functionality
        originalModel = new DefaultTableModel(columns, 0);

        return panel;
    }

    // ================ Styling helpers ================
    private void styleField(JTextField f) {
        f.setBackground(new Color(50, 50, 50));
        f.setForeground(TEXT_WHITE);
        f.setCaretColor(TEXT_WHITE);
        f.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
        f.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }

    private void styleCombo(JComboBox<String> c) {
        c.setBackground(new Color(50, 50, 50));
        c.setForeground(TEXT_WHITE);
        c.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }

    private void styleActionButton(JButton b) {
        b.setBackground(CARD_BG);
        b.setForeground(TEXT_WHITE);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { b.setBackground(CARD_HOVER); }
            @Override
            public void mouseExited(MouseEvent e)  { b.setBackground(CARD_BG); }
        });
    }

    // ================ Button Actions ================
    private void hookControlActions() {
        // Add
        addBtn.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String course = courseField.getText().trim();
            String date = dateField.getText().trim();
            String status = (String) statusCombo.getSelectedItem();

            if (id.isEmpty() || name.isEmpty() || course.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields (ID, Name, Course, Date).", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }

            model.addRow(new Object[]{id, name, course, date, status});
            // also keep originalModel in sync
            originalModel.addRow(new Object[]{id, name, course, date, status});
            clearFields();
        });

        // Edit
        editBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a row to edit.", "Edit", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String course = courseField.getText().trim();
            String date = dateField.getText().trim();
            String status = (String) statusCombo.getSelectedItem();

            if (id.isEmpty() || name.isEmpty() || course.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields (ID, Name, Course, Date).", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }

            model.setValueAt(id, row, 0);
            model.setValueAt(name, row, 1);
            model.setValueAt(course, row, 2);
            model.setValueAt(date, row, 3);
            model.setValueAt(status, row, 4);

            // update originalModel as well: find matching row by index if same model used
            syncOriginalModelWithModel();
            clearFields();
        });

        // Delete
        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a row to delete.", "Delete", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the selected record?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                model.removeRow(row);
                // also remove from originalModel if they are in sync
                syncOriginalModelWithModel();
                clearFields();
            }
        });

        // Search
        searchBtn.addActionListener(e -> {
            String keyword = searchField.getText().trim().toLowerCase();
            if (keyword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter a search keyword (ID, Name, Course, Date or Status).", "Search", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            String[] columns = {"Student ID", "Student Name", "Course", "Date", "Attendance Status"};
            DefaultTableModel searchModel = new DefaultTableModel(columns, 0);

            for (int i = 0; i < model.getRowCount(); i++) {
                String id = String.valueOf(model.getValueAt(i, 0)).toLowerCase();
                String name = String.valueOf(model.getValueAt(i, 1)).toLowerCase();
                String course = String.valueOf(model.getValueAt(i, 2)).toLowerCase();
                String date = String.valueOf(model.getValueAt(i, 3)).toLowerCase();
                String status = String.valueOf(model.getValueAt(i, 4)).toLowerCase();

                if (id.contains(keyword) || name.contains(keyword) || course.contains(keyword) || date.contains(keyword) || status.contains(keyword)) {
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
            // re-apply header style and status editor for the new table model
            JTableHeader header = table.getTableHeader();
            header.setBackground(CARD_BG);
            header.setForeground(TEXT_WHITE);
            header.setFont(new Font("Segoe UI", Font.BOLD, 14));
            if (table.getColumnModel().getColumnCount() >= 5) {
                table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JComboBox<>(new String[]{"Present","Absent","Late","Excused"})));
            }
        });

        // Show All
        showAllBtn.addActionListener(e -> {
            table.setModel(model); // restore to main model
            // restore header style and status editor
            JTableHeader header = table.getTableHeader();
            header.setBackground(CARD_BG);
            header.setForeground(TEXT_WHITE);
            header.setFont(new Font("Segoe UI", Font.BOLD, 14));
            table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JComboBox<>(new String[]{"Present","Absent","Late","Excused"})));
        });

        // Clear fields
        clearBtn.addActionListener(e -> clearFields());
    }

    // If your model and originalModel drifted, synchronize originalModel to reflect the current model
    private void syncOriginalModelWithModel() {
        // Rebuild originalModel from model
        originalModel = new DefaultTableModel(new String[]{"Student ID", "Student Name", "Course", "Date", "Attendance Status"}, 0);
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
        dateField.setText("");
        statusCombo.setSelectedIndex(0);
        table.clearSelection();
    }

    // ================ Main (test) ================
    public static void main(String[] args) {
        // Create GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            TakeAttendance ta = new TakeAttendance();

            // Add some sample rows for testing
            ta.model.addRow(new Object[]{"S1001", "Ahmed Ali", "Programming 1", "2025-10-01", "Present"});
            ta.model.addRow(new Object[]{"S1002", "Sara Mohamed", "Programming 1", "2025-10-01", "Absent"});
            ta.model.addRow(new Object[]{"S1003", "Omar Hana", "Algorithms", "2025-10-01", "Late"});
            ta.originalModel.addRow(new Object[]{"S1001", "Ahmed Ali", "Programming 1", "2025-10-01", "Present"});
            ta.originalModel.addRow(new Object[]{"S1002", "Sara Mohamed", "Programming 1", "2025-10-01", "Absent"});
            ta.originalModel.addRow(new Object[]{"S1003", "Omar Hana", "Algorithms", "2025-10-01", "Late"});

            ta.setVisible(true);
        });
    }
}