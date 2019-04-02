package client.views;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

import server.models.Item;

public class ToolShopTableModel extends AbstractTableModel {

  String[] columnHeaders = {"ID", "Description", "Quantity", "Price", "Supplier"};
  ArrayList<Item> data = new ArrayList<Item>();

  public void setData(ArrayList<Item> data) {
    this.data = data;
  }

  public ToolShopTableModel() {
  }

  @Override
  public int getRowCount() {
    return data.size();
  }

  @Override
  public int getColumnCount() {
    return columnHeaders.length;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Item item = data.get(rowIndex);
    switch (columnIndex) {
    case 0:
      return item.getId();
    case 1:
      return item.getDescription();
    case 2:
      return item.getQuantity();
    case 3:
      return item.getPrice();
    case 4:
      return item.getSupplier().getName();
    default:
      return null;
    }
  }

}