package untar.java.tugas1;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ProductTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Kode", "Nama", "Qty", "Harga"};
    private final ArrayList<ProductModel> products;

    public ProductTableModel(ArrayList<ProductModel> products) {
        this.products = products;
    }

    @Override
    public int getRowCount() {
        return products.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProductModel product = products.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> product.getCode();
            case 1 -> product.getName();
            case 2 -> product.getQty();
            case 3 -> product.getPrice();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void addProduct(ProductModel p) {
        products.add(p);
        fireTableRowsInserted(products.size() - 1, products.size() - 1);
    }

    public void removeProduct(int index) {
        if (index >= 0 && index < products.size()) {
            products.remove(index);
            fireTableRowsDeleted(index, index);
        }
    }
}
