package untar.java.tugas1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InventoryApp extends JFrame {
    private JTextField codeField, nameField, qtyField, priceField;
    private JTable table;
    private final ArrayList<ProductModel> products = new ArrayList<>();
    private final ProductTableModel tableModel = new ProductTableModel(products);

    public InventoryApp() {
        setTitle("Inventory App (Memory Only)");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        inputPanel.add(new JLabel("Kode:"));
        codeField = new JTextField();
        inputPanel.add(codeField);

        inputPanel.add(new JLabel("Nama:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Qty:"));
        qtyField = new JTextField();
        inputPanel.add(qtyField);

        inputPanel.add(new JLabel("Harga:"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        JButton addButton = new JButton("Tambah");
        inputPanel.add(new JLabel());
        inputPanel.add(addButton);

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton deleteButton = new JButton("Hapus Produk Terpilih");

        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(deleteButton, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            try {
                String code = codeField.getText();
                String name = nameField.getText();
                String qty = qtyField.getText();
                String price = priceField.getText();
                if (!code.isEmpty() && !name.isEmpty() && !qty.isEmpty() && !price.isEmpty()) {
                    int qtyInt = Integer.parseInt(qty);
                    double priceDouble = Double.parseDouble(price);

                    ProductModel p = new ProductModel(code, name, qtyInt, priceDouble);
                    tableModel.addProduct(p);

                    codeField.setText("");
                    nameField.setText("");
                    qtyField.setText("");
                    priceField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Harap mengisi field yang kosong!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Qty dan Harga harus angka!");
            }
        });

        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                tableModel.removeProduct(row);
            } else {
                JOptionPane.showMessageDialog(this, "Pilih produk yang ingin dihapus!");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InventoryApp().setVisible(true));
    }
}
