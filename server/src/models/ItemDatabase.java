package models;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.sql.*;
import utils.*;
import utils.Item;

/**
 * The Shop object that represents the entire Shop as a whole.
 * 
 * @author Navjot Brar, Jofred Cayabyab and Tyler Lam
 * @version 1.0.0
 * @since March 31, 2019
 */
public class ItemDatabase implements Quantities, DBCredentials {

  /**
   * connection
   */
  private Connection connection;

  /**
   * list of suppliers
   */
  private SupplierList suppliers;

  /**
   * the inventory of items
   */
  private ItemList items;

  /**
   * ArrayList of all the orders
   */
  private ArrayList<Order> orders;

  /**
   * Constructs a Shop object with references to pre-existing ArrayLists and
   * suppliers and items.
   * 
   * @param suppliers the list of suppliers the shop contacts
   * @param items     the list of items in the shop
   */
  public ItemDatabase(Connection connection) {
    this.connection = connection;
    orders = new ArrayList<Order>();
  }

  /**
   * Gets the list of items in the Shop.
   * 
   * @return the list of Items in the shop
   */
  public ItemList getItems() {
    return items;
  }

  /**
   * 
   */
  public void setSuppliers(SupplierList suppliers2) {
    suppliers = suppliers2;
  }

  public void setItems(ItemList items) {
    this.items = items;
  }

  public void readIntoDatabase() {
    clearDatabase();
    for (Item a : items.getList()) {
      fillDatabase(a);
    }
  }

  public Item getItemByDescription(String description) {
    try {
      PreparedStatement searchByDescriptionStatement = connection.prepareStatement(GET_ITEM_BY_DESCRIPTION);
      searchByDescriptionStatement.setString(1, description);
      ResultSet rs = searchByDescriptionStatement.executeQuery();
      if (rs.next()) {
        int id = rs.getInt(1);
        String desc = rs.getString(2);
        int quantity = rs.getInt(3);
        double price = rs.getDouble(4);
        int supplierId = rs.getInt(5);
        Supplier supplier = suppliers.getSupplierById(supplierId);
        Item item = new Item(id, desc, quantity, price, supplier);
        return item;
      }
      searchByDescriptionStatement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void addItem(Item item) {
    try {
      PreparedStatement addItemStatement = connection.prepareStatement(ADD_ITEM);
      addItemStatement.setInt(1, item.getId());
      addItemStatement.setString(2, item.getDescription());
      addItemStatement.setInt(3, item.getQuantity());
      addItemStatement.setDouble(4, item.getPrice());
      addItemStatement.setInt(5, item.getSupplier().getId());

      addItemStatement.execute();
      addItemStatement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void fillDatabase(Item item) {
    try {
      PreparedStatement addItemStatement = connection.prepareStatement(ADD_ITEM);
      addItemStatement.setInt(1, item.getId());
      addItemStatement.setString(2, item.getDescription());
      addItemStatement.setInt(3, item.getQuantity());
      addItemStatement.setDouble(4, item.getPrice());
      addItemStatement.setInt(5, item.getSupplier().getId());

      addItemStatement.execute();
      addItemStatement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void clearDatabase() {
    String query = "DELETE FROM item";
    try {
      PreparedStatement deleteStatement = connection.prepareStatement(query);
      deleteStatement.execute();
      deleteStatement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void deleteItem(Item item) {
    try {
      PreparedStatement deleteItemStatement = connection.prepareStatement(DELETE_ITEM);
      deleteItemStatement.setInt(1, item.getId());
      deleteItemStatement.execute();
      items.deleteItem(item);
      deleteItemStatement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Item getItemById(int inputId) {
    try {
      PreparedStatement searchByIdStatement = connection.prepareStatement(GET_ITEM_BY_ID);
      searchByIdStatement.setInt(1, inputId);
      ResultSet rs = searchByIdStatement.executeQuery();
      if (rs.next()) {
        int id = rs.getInt(1);
        String description = rs.getString(2);
        int quantity = rs.getInt(3);
        double price = rs.getDouble(4);
        int supplierId = rs.getInt(5);
        Supplier supplier = suppliers.getSupplierById(supplierId);
        Item item = new Item(id, description, quantity, price, supplier);
        searchByIdStatement.close();
        return item;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void buyItem(Item itemToDecrease, int count) {
    if (itemToDecrease.getQuantity() < count) {
      deleteItem(itemToDecrease);
      return;
    }
    try {
      PreparedStatement buy = connection.prepareStatement(BUY_ITEM);
      int finalQuantity = itemToDecrease.getQuantity() - count;
      buy.setInt(1, finalQuantity);
      buy.setInt(2, itemToDecrease.getId());
      buy.execute();
      buyHelper(itemToDecrease, count);
      buy.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void buyHelper(Item inputItem, int count) {
    // Check if decreasing more than the current stock
    Item item = null;
    for (Item i : items.getList()) {
      if (inputItem.equals(i)) {
        item = i;
      }
    }

    if (item != null) {
      int newQuantity = item.getQuantity() - count;
      if (newQuantity < 0) {
        count = item.getQuantity();
        System.out
            .println("Specified amount is greater than the current stock. Will only decrease by " + count + " items.");
        newQuantity = 0;
      }
      item.setQuantity(newQuantity);
      System.out.println(item.getDescription() + " has been decreased by " + count + " items. The new amount is "
          + item.getQuantity() + ".");
      if (checkStock(item)) {
        System.out.println("The new quantity is below 40. An order has been automatically made to restock.");
      }
    }
  }

  /**
   * Gets the list of suppliers in the Shop.
   * 
   * @return the list of suppliers in the Shop
   */
  public SupplierList getSuppliers() {
    return suppliers;
  }

  /**
   * Checks the stock of all items in the ItemList. If any items have a quantity
   * below 40, then generate a new order.
   * 
   * @return true if an order is generated
   */
  public boolean checkStock() {
    Order newOrder = new Order();

    for (Item item : items.getList()) {
      if (item.getQuantity() < LOW_STOCK_THRESHOLD) {
        newOrder.orderItem(item);
      }
    }

    if (newOrder.getItems().size() != 0) {
      orders.add(newOrder);
      printOrdersToFile();
      newOrder.completeOrder();
      return true;
    }
    return false;
  }

  /**
   * Checks the stock of a single item in the ItemList. If the item has a quantity
   * below 40, generate a new order. This overloaded function exists to prevent
   * checking every item after a change in only one item for efficiency sake.
   * 
   * @param item The item to check the stock of
   * @return true if an order is generated
   */
  public boolean checkStock(Item item) {
    if (item.getQuantity() < LOW_STOCK_THRESHOLD) {
      Order newOrder = new Order();
      newOrder.orderItem(item);
      orders.add(newOrder);
      printOrdersToFile();
      newOrder.completeOrder();
      return true;
    }
    return false;
  }

  /**
   * Gets a tool by its name.
   * 
   * @param name The name of the tool
   * @return The Item object representing the tool
   */
  public Item getToolByName(String name) {
    return null;
  }

  /**
   * Gets a tool by its name.
   * 
   * @param id The id of the tool
   * @return The Item object representing the tool
   */
  public Item getToolById(int id) {
    return null;
  }

  public ItemList getItemList() {
    return items;
  }

  /**
   * Prints the entire member ArrayList of orders to a text file.
   */
  public void printOrdersToFile() {
    PrintWriter pw;
    try {
      pw = new PrintWriter("orders.txt");
      pw.println("****************************************");
      for (Order order : orders) {
        pw.println(order);
      }

      pw.close();
    } catch (FileNotFoundException e) {
      System.out.println("File orders.txt not found. Please create this file then try again.");
      System.exit(1);
    }
  }

  /**
   * Get order text file as a string..
   */
  public String getOrderString() {
    String s = "****************************************\n";
    for (Order order : orders) {
      s += order;
    }

    return s;
  }

  /**
   * Create a new item based on parameters from user input
   * 
   * @param description The description of the item
   * @param quantity    The initial quantitiy of the item
   * @param price       The initial price of the item
   * @param supplierId  The id of the supplier that the item is ordered from
   */
  public Item addNewItem(String description, int quantity, double price, int supplierId) {
    // new Id is automatically one greater than the last item in the list.
    int newId = items.getList().get(items.getList().size() - 1).getId() + 1;

    Item newItem = items.addItem(newId, description, quantity, price, supplierId, suppliers);

    return newItem;
  }

  /**
   * Returns the names of every item in the shop
   * 
   * @return
   */
  public ArrayList<String> getItemName() {
    String query = "SELECT description FROM item";
    try {
      PreparedStatement getStatement = connection.prepareStatement(query);
      ResultSet rs = getStatement.executeQuery();
      ArrayList<String> names = new ArrayList<String>();
      while (rs.next()) {
        names.add(rs.getString("description"));
      }
      getStatement.close();
      System.out.println(names.size());
      return names;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }
}