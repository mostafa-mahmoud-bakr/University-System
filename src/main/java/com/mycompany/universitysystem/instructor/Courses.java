package com.mycompany.universitysystem.instructor;

import com.mycompany.universitysystem.instructor.InstructorDashboard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Courses extends JFrame {

    // ====== الألوان ======
    private static final Color BG_DARK = new Color(24, 24, 24);
    private static final Color CARD_BG = new Color(38, 38, 38);
    private static final Color CARD_HOVER = new Color(48, 48, 48);
    private static final Color GOLD = new Color(218, 165, 32);
    private static final Color TEXT_WHITE = new Color(240, 240, 240);
    private static final Color TEXT_GRAY = new Color(180, 180, 180);
    private static final Color SIDEBAR_BTN_BG = new Color(30, 30, 30);
    private static final Color SIDEBAR_BTN_HOVER = new Color(50, 50, 50);

    public Courses() {
        setTitle("Instructor Courses");
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
        btn.setSize(100, 80);
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
        
        buttonsPanel.add(btnDashboard);
        buttonsPanel.add(Box.createVerticalStrut(10));
        buttonsPanel.add(btnStudents);
        buttonsPanel.add(Box.createVerticalStrut(10));
       
        buttonsPanel.add(Box.createVerticalStrut(10));
        

        sidebar.add(buttonsPanel);
        sidebar.add(Box.createVerticalGlue()); // المساحة الفارغة تحت الأزرار

        // ====== ربط الأزرار بالصفحات ======
        btnDashboard.addActionListener(e -> { openDashboard(); dispose(); });
        btnStudents.addActionListener(e -> { openCources(); dispose(); });
       
        return sidebar;
    }

    // ====== الصفحات الجانبية ======
    private void openDashboard() { new InstructorDashboard().setVisible(true); }
    private void openCources() { new Courses().setVisible(true); }
    
    // ====== Main Content مع كروت الإحصائيات ======
    private JPanel createMainContent() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BG_DARK);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // Header
        JLabel header = new JLabel("Instructor Courses");
        header.setForeground(TEXT_WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 42));
        mainPanel.add(header, BorderLayout.NORTH);

        // cards for courses
        JPanel courses = new JPanel(new GridLayout(2, 2, 25, 25));
        courses.setBackground(BG_DARK);
        courses.setBorder(BorderFactory.createEmptyBorder(40, 40,40, 40));

        ImageIcon python = new ImageIcon("D:\\java\\UniversitySystem\\src\\main\\java\\icons\\python.png");
        JPanel course1 = createStatCard(python, "Python", "100");

        ImageIcon cpp = new ImageIcon("D:\\java\\UniversitySystem\\src\\main\\java\\icons\\cpp.png");
        JPanel course2 = createStatCard(cpp, "C++","90");

        ImageIcon java = new ImageIcon("D:\\java\\UniversitySystem\\src\\main\\java\\icons\\java.png");
        JPanel course3 = createStatCard(java, "Java","95");

        ImageIcon c = new ImageIcon("D:\\java\\UniversitySystem\\src\\main\\java\\icons\\c.png");
        JPanel course4 = createStatCard(c, "C","85");

        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
        courses.add(course4);

        mainPanel.add(courses, BorderLayout.CENTER);

        return mainPanel;
    }
    

    // ====== إنشاء كارت ======
private JPanel createStatCard(ImageIcon icon, String label, String value) {
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

    // ====== الأيقونة ======
    // تصغير الأيقونة إذا كانت كبيرة (مثل حجم 55px مثل ما كان الـ String emoji)
    Image image = icon.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
    JLabel iconLabel = new JLabel(new ImageIcon(image));
    iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    // ====== النصوص ======
    JLabel labelText = new JLabel(label);
    labelText.setForeground(TEXT_GRAY);
    labelText.setFont(new Font("Segoe UI", Font.PLAIN, 18));
    labelText.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel valueText = new JLabel(value);
    valueText.setForeground(TEXT_WHITE);
    valueText.setFont(new Font("Segoe UI", Font.BOLD, 36));
    valueText.setAlignmentX(Component.CENTER_ALIGNMENT);
    valueText.setName("valueLabel");

    // ====== إضافة العناصر ======
    card.add(iconLabel);
    card.add(Box.createVerticalStrut(15));
    card.add(labelText);
    card.add(Box.createVerticalStrut(10));
    card.add(valueText);

    // ====== تأثيرات Hover ======
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
            JOptionPane.showMessageDialog(
                    Courses.this,
                    "Details of " + label,
                    label,
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    });

    return card;
}

}
