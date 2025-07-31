import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AlumniManagementSystem extends JFrame {
    CardLayout cardLayout;
    JPanel cardPanel;
    JTable alumniTable;
    DefaultTableModel tableModel;

    public AlumniManagementSystem() {
        setTitle("Alumni Connect - All in One Frame");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(loginPanel(), "Login");
        cardPanel.add(dashboardPanel(), "Dashboard");
        cardPanel.add(addAlumniPanel(), "AddAlumni");
        cardPanel.add(viewAlumniPanel(), "ViewAlumni");
        cardPanel.add(galleryPanel(), "Gallery");

        add(cardPanel);
        cardLayout.show(cardPanel, "Login");
        setVisible(true);
    }

    private JPanel loginPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Welcome to Alumni Connect", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e -> cardLayout.show(cardPanel, "Dashboard"));
        panel.add(label, BorderLayout.CENTER);
        panel.add(loginButton, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel dashboardPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JButton addBtn = new JButton("Add Alumni");
        JButton viewBtn = new JButton("View Alumni");
        JButton galleryBtn = new JButton("Gallery");
        JButton exitBtn = new JButton("Exit");

        addBtn.addActionListener(e -> cardLayout.show(cardPanel, "AddAlumni"));
        viewBtn.addActionListener(e -> {
            loadAlumniData();
            cardLayout.show(cardPanel, "ViewAlumni");
        });
        galleryBtn.addActionListener(e -> cardLayout.show(cardPanel, "Gallery"));
        exitBtn.addActionListener(e -> System.exit(0));

        panel.add(addBtn);
        panel.add(viewBtn);
        panel.add(galleryBtn);
        panel.add(exitBtn);
        return panel;
    }

    private JPanel addAlumniPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField nameField = new JTextField(20);
        JTextField batchField = new JTextField(20);
        JTextField deptField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JTextField phoneField = new JTextField(20);
        JTextField jobField = new JTextField(20);
        JTextField imagePathField = new JTextField(20);
        JButton browseBtn = new JButton("Browse");
        JButton saveBtn = new JButton("Save");
        JButton backBtn = new JButton("Back");

        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; panel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Batch:"), gbc);
        gbc.gridx = 1; panel.add(batchField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Department:"), gbc);
        gbc.gridx = 1; panel.add(deptField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; panel.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1; panel.add(phoneField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Job Title:"), gbc);
        gbc.gridx = 1; panel.add(jobField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Image Path:"), gbc);
        gbc.gridx = 1; panel.add(imagePathField, gbc);
        gbc.gridx = 2; panel.add(browseBtn, gbc);

        gbc.gridx = 1; gbc.gridy++;
        panel.add(saveBtn, gbc);
        gbc.gridy++;
        panel.add(backBtn, gbc);

        browseBtn.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                imagePathField.setText(chooser.getSelectedFile().getAbsolutePath());
            }
        });

        saveBtn.addActionListener((ActionEvent e) -> {
            try (PrintWriter writer = new PrintWriter(new FileWriter("alumni.csv", true))) {
                writer.printf("%s,%s,%s,%s,%s,%s,%s\n",
                        nameField.getText(), batchField.getText(), deptField.getText(),
                        emailField.getText(), phoneField.getText(), jobField.getText(), imagePathField.getText());
                JOptionPane.showMessageDialog(this, "Alumni saved.");
                cardLayout.show(cardPanel, "Dashboard");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving data.");
            }
        });

        backBtn.addActionListener(e -> cardLayout.show(cardPanel, "Dashboard"));

        return panel;
    }

    private JPanel viewAlumniPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        tableModel = new DefaultTableModel(new Object[]{"Name", "Batch", "Dept", "Email", "Phone", "Job", "Photo"}, 0) {
            public Class<?> getColumnClass(int column) {
                return column == 6 ? ImageIcon.class : String.class;
            }
        };
        alumniTable = new JTable(tableModel);
        alumniTable.setRowHeight(80);
        JScrollPane scrollPane = new JScrollPane(alumniTable);

        JButton backBtn = new JButton("Back to Dashboard");
        backBtn.addActionListener(e -> cardLayout.show(cardPanel, "Dashboard"));

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(backBtn, BorderLayout.SOUTH);
        return panel;
    }

    private void loadAlumniData() {
        tableModel.setRowCount(0);
        try (BufferedReader reader = new BufferedReader(new FileReader("alumni.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    ImageIcon icon = new ImageIcon(parts[6]);
                    Image img = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                    tableModel.addRow(new Object[]{parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], new ImageIcon(img)});
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error loading alumni data.");
        }
    }

    private JPanel galleryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel grid = new JPanel(new GridLayout(0, 3, 10, 10));
        JScrollPane scrollPane = new JScrollPane(grid);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        File folder = new File("resources/images");
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles((dir, name) -> name.endsWith(".jpg") || name.endsWith(".png"));
            if (files != null && files.length > 0) {
                for (File file : files) {
                    ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                    Image img = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
                    JLabel lbl = new JLabel(new ImageIcon(img));
                    lbl.setHorizontalAlignment(SwingConstants.CENTER);
                    lbl.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                    grid.add(lbl);
                }
            } else {
                grid.add(new JLabel("No images found."));
            }
        } else {
            grid.add(new JLabel("Image folder not found."));
        }

        JButton backBtn = new JButton("Back to Dashboard");
        backBtn.addActionListener(e -> cardLayout.show(cardPanel, "Dashboard"));

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(backBtn, BorderLayout.SOUTH);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AlumniManagementSystem::new);
    }
}
