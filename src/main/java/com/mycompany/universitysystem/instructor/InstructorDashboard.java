package com.mycompany.universitysystem.instructor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InstructorDashboard extends JFrame {

    // ====== الألوان ======
    private static final Color BG_DARK = new Color(24, 24, 24);
    private static final Color CARD_BG = new Color(38, 38, 38);
    private static final Color CARD_HOVER = new Color(48, 48, 48);
    private static final Color GOLD = new Color(218, 165, 32);
    private static final Color TEXT_WHITE = new Color(240, 240, 240);
    private static final Color TEXT_GRAY = new Color(180, 180, 180);
    private static final Color SIDEBAR_BTN_BG = new Color(30, 30, 30);
    private static final Color SIDEBAR_BTN_HOVER = new Color(50, 50, 50);

    // ====== بيانات الإحصائيات ======
    private int studentsCount = 300;
    private int coursesCount = 4;
    private int attendanceCount = 90;
    private int gradesCount = 90;

    private JLabel studentsValueLabel;
    private JLabel instructorsValueLabel;
    private JLabel coursesValueLabel;
    private JLabel attendanceValueLabel;
    private JLabel gradesValueLabel;
    private JLabel feesValueLabel;

    public InstructorDashboard() {
        setTitle("Instructor Dashboard");
        setSize(1400, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BG_DARK);

        add(createSidebar(), BorderLayout.WEST);
        add(createMainContent(), BorderLayout.CENTER);
    }

    // ====== Sidebar Buttons ======
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
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(SIDEBAR_BTN_HOVER);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(SIDEBAR_BTN_BG);
            }
        });
        return btn;
    }

    // ====== Sidebar مع اللوغو واسم النظام ======
    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(280, getHeight()));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(BG_DARK);
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

        // ====== Logo + System Name ======
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
        sidebar.add(Box.createVerticalStrut(20)); // مسافة بعد اللوجو

        // ====== Buttons Panel ======
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBackground(BG_DARK);
        buttonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnDashboard = createSidebarButton("▶ Dashboard");
        JButton btnStudents = createSidebarButton("● Courses");
        JButton btnInstructors = createSidebarButton("● Take Attandance");
        JButton btnCourses = createSidebarButton("● Upload grades");

        buttonsPanel.add(btnDashboard);
        buttonsPanel.add(Box.createVerticalStrut(10));
        buttonsPanel.add(btnStudents);
        buttonsPanel.add(Box.createVerticalStrut(10));
        buttonsPanel.add(btnInstructors);
        buttonsPanel.add(Box.createVerticalStrut(10));
        buttonsPanel.add(btnCourses);

        sidebar.add(buttonsPanel);
        sidebar.add(Box.createVerticalGlue()); // المساحة الفارغة تحت الأزرار

        // ====== ربط الأزرار بالصفحات ======
        btnDashboard.addActionListener(e -> { openDashboard(); dispose(); });
        btnStudents.addActionListener(e -> { openCources(); dispose(); });
        btnInstructors.addActionListener(e -> { openManageInstructors(); dispose(); });
        btnCourses.addActionListener(e -> { openManageCourses(); dispose(); });

        return sidebar;
    }

    // ====== الصفحات الجانبية ======
    private void openDashboard() { new InstructorDashboard().setVisible(true); }
    private void openCources() { new Courses().setVisible(true); }
    private void openManageInstructors() { new TakeAttendance().setVisible(true); }
    private void openManageCourses() { new UploadGrades().setVisible(true); }

    // ====== Main Content مع كروت الإحصائيات ======
    private JPanel createMainContent() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BG_DARK);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // Header
        JLabel header = new JLabel("Instructor Dashboard");
        header.setForeground(TEXT_WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 42));
        mainPanel.add(header, BorderLayout.NORTH);

        // Stats Grid
        JPanel statsGrid = new JPanel(new GridLayout(2, 3, 25, 25));
        statsGrid.setBackground(BG_DARK);
        statsGrid.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JPanel studentsCard = createStatCard("👤", "Students", String.valueOf(studentsCount));
        studentsValueLabel = getValueLabelFromCard(studentsCard);

        JPanel coursesCard = createStatCard("📚", "Courses", String.valueOf(coursesCount));
        coursesValueLabel = getValueLabelFromCard(coursesCard);

        JPanel attendanceCard = createStatCard("✓", "Attendance", String.valueOf(attendanceCount));
        attendanceValueLabel = getValueLabelFromCard(attendanceCard);

        JPanel gradesCard = createStatCard("📋", "Grades", String.valueOf(gradesCount));
        gradesValueLabel = getValueLabelFromCard(gradesCard);


        statsGrid.add(studentsCard);
        statsGrid.add(coursesCard);
        statsGrid.add(attendanceCard);
        statsGrid.add(gradesCard);

        mainPanel.add(statsGrid, BorderLayout.CENTER);

        return mainPanel;
    }

    // ====== إنشاء كارت ======
    private JPanel createStatCard(String icon, String label, String value) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }
        };

        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setOpaque(false);
        card.setBackground(CARD_BG);
        card.setBorder(BorderFactory.createEmptyBorder(40, 30, 40, 30));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 55));
        iconLabel.setForeground(GOLD);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel labelText = new JLabel(label);
        labelText.setForeground(TEXT_GRAY);
        labelText.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        labelText.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel valueText = new JLabel(value);
        valueText.setForeground(TEXT_WHITE);
        valueText.setFont(new Font("Segoe UI", Font.BOLD, 36));
        valueText.setAlignmentX(Component.CENTER_ALIGNMENT);
        valueText.setName("valueLabel");

        card.add(iconLabel);
        card.add(Box.createVerticalStrut(15));
        card.add(labelText);
        card.add(Box.createVerticalStrut(10));
        card.add(valueText);

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBackground(CARD_HOVER);
                card.repaint();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                card.setBackground(CARD_BG);
                card.repaint();
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(InstructorDashboard.this,
                        "Details of " + label,
                        label,
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        return card;
    }

    private JLabel getValueLabelFromCard(JPanel card) {
        for (Component comp : card.getComponents()) {
            if (comp instanceof JLabel && "valueLabel".equals(comp.getName())) {
                return (JLabel) comp;
            }
        }
        return null;
    }

}
