package client.views;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

import server.models.Item;

/**
 * Displays the Table that holds all the tool information to the user
 * 
 * @author Navjot Brar, Jofred Cayabyab and Tyler Lam
 * @version 1.0.0
 * @since March 31, 2019
 */
public class ToolShopTableModel extends AbstractTableModel {

  /**
   * all the column headers for the table to display data
   */
  String[] columnHeaders = { "ID", "Description", "Quantity", "Price", "Supplier" };

  /**
   * an arrayList of all the items available in the toolshop
   */
  ArrayList<Item> data = new ArrayList<Item>();

  /**
   * sets the data ArrayList
   * 
   * @param data the ArrayList of Data
   */
  public void setData(ArrayList<Item> data) {
    this.data = data;
  }

  public ToolShopTableModel() {
  }

  /**
   * returns the number of rows in the data ArrayList
   * 
   * @return the size of data (number of rows)
   */
  @Override
  public int getRowCount() {
    return data.size();
  }

  /**
   * returns the number of columns in the data ArrayList
   * 
   * @return the number of columns in the arraylist
   */
  @Override
  public int getColumnCount() {
    return columnHeaders.length;
  }

  /**
   * gets a particular data value at a chosen row index and column index
   * 
   * @return the item data with the data at the specified row and column
   */
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