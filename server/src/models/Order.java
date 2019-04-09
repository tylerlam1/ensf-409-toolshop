package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import utils.Item;

/**
 * The class that contains all information for each order.
 * 
 * @author Navjot Brar, Jofred Cayabyab and Tyler Lam
 * @version 1.0.0
 * @since March 31, 2019
 */
public class Order implements Quantities {

  /**
   * The randomly generated id of the order.
   */
  private int id;
  /**
   * The date and time the order was initialized.
   */
  private Date dateOrdered;
  /**
   * The items to be ordered.
   */
  private ArrayList<Item> items;

  /**
   * Creates a new Order object with a randomized id, the current time and date,
   * and an empty ArrayList of Items.
   */
  public Order() {
    Random rand = new Random();
    id = rand.nextInt(100000);
    dateOrdered = new Date();
    items = new ArrayList<Item>();
  }

  /**
   * @return the items
   */
  public ArrayList<Item> getItems() {
    return items;
  }

  /**
   * Adds an item to the list of Items in the order.
   * 
   * @param item the item to be added to the order
   */
  public void orderItem(Item item) {
    items.add(item);
  }

  /**
   * Restocks the items in an order. This function simulates the order being
   * received by the Shop.
   */
  public void completeOrder() {
    for (Item item : getItems()) {
      item.setQuantity(ORDER_TO_AMOUNT);
    }
  }

  /**
   * Prints the complete Order in the specified format to be printed to a text
   * file.
   */
  public String toString() {
    String str = String.format("%-25s%d\n", "ORDER ID:", id);
    str += String.format("%-25s%s\n\n", "Date ordered:", dateOrdered);
    for (Item item : items) {
      str += String.format("%-25s%s\n", "Item description:", item.getDescription());
      str += String.format("%-25s%d\n", "Amount ordered:", ORDER_TO_AMOUNT - item.getQuantity());
      str += String.format("%-25s%s\n\n", "Supplier:", item.getSupplier().getName());
    }

    str += "****************************************\n";

    return str;
  }
}