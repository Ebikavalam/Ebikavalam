import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class Lab11 extends JFrame {
    private JTextField nameField, categoryField, priceField, idField;
    private JTable table;
    private DefaultTableModel model;
    private Connection conn;

    public Lab11() {
        setTitle("Commodity Management System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/commodity_db", "root", "tiger");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Connection Failed!", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Commodity Details"));

        inputPanel.add(new JLabel("Commodity Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Category:"));
        categoryField = new JTextField();
        inputPanel.add(categoryField);

        inputPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        inputPanel.add(new JLabel("Commodity ID (for Update/Delete):"));
        idField = new JTextField();
        inputPanel.add(idField);

        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 5, 5));
        JButton addButton = new JButton("Add");
        JButton viewButton = new JButton("View All");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.CENTER);

        model = new DefaultTableModel(new String[]{"ID", "Name", "Category", "Price"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Commodity Records"));
        add(scrollPane, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addCommodity());
        viewButton.addActionListener(e -> loadCommodities());
        updateButton.addActionListener(e -> updateCommodity());
        deleteButton.addActionListener(e -> deleteCommodity());

        setVisible(true);
    }

    private void addCommodity() {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO commodities (name, category, price) VALUES (?, ?, ?)");
            stmt.setString(1, nameField.getText());
            stmt.setString(2, categoryField.getText());
            stmt.setDouble(3, Double.parseDouble(priceField.getText()));
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Commodity added successfully!");
            clearFields();
            loadCommodities();
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please check the details!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadCommodities() {
        model.setRowCount(0);
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM commodities")) {
            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt("id"), rs.getString("name"), rs.getString("category"), rs.getDouble("price")});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateCommodity() {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE commodities SET name=?, category=?, price=? WHERE id=?");
            stmt.setString(1, nameField.getText());
            stmt.setString(2, categoryField.getText());
            stmt.setDouble(3, Double.parseDouble(priceField.getText()));
            stmt.setInt(4, Integer.parseInt(idField.getText()));
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Commodity updated successfully!");
                clearFields();
                loadCommodities();
            } else {
                JOptionPane.showMessageDialog(this, "Commodity ID not found!", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID or input!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteCommodity() {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM commodities WHERE id=?");
            stmt.setInt(1, Integer.parseInt(idField.getText()));
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Commodity deleted successfully!");
                clearFields();
                loadCommodities();
            } else {
                JOptionPane.showMessageDialog(this, "Commodity ID not found!", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        nameField.setText("");
        categoryField.setText("");
        priceField.setText("");
        idField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Lab11());
    }
}
