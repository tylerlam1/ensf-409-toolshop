package models;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import utils.Item;

/**
 * The Shop object that represents the entire Shop as a whole.
 * 
 * @author Navjot Brar, Jofred Cayabyab and Tyler Lam
 * @version 1.0.0
 * @since March 31, 2019
 */
public class ToolShop implements Quantities {

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
  public ToolShop(SupplierList suppliers, ItemList items) {
    this.suppliers = suppliers;
    this.items = items;
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
   * This simulates the sale or purchase of an item. Afterwards, check the stock
   * of the item to see if a new order needs to be made.
   * 
   * @param item  The item to buy from
   * @param count The number of items to buy
   */
  public void buy(Item inputItem, int count) {
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
}