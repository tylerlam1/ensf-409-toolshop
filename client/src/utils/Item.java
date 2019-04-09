package utils;

import server.models.*;

import java.io.Serializable;

/**
 * Contains the information about each tool/item in the shop.
 * 
 * @author Navjot Brar, Jofred Cayabyab and Tyler Lam
 * @version 1.0.0
 * @since March 31, 2019
 */
public class Item implements Serializable {

  private static final long serialVersionUID = 2L;

  /**
   * The id of the item.
   */
  private int id;

  /**
   * A description/name of the item.
   */
  private String description;

  /**
   * The number of items in the inventory of the Shop.
   */
  private int quantity;

  /**
   * The money value of the item.
   */
  private double price;

  /**
   * The Supplier object that the Item is ordered from.
   */
  private Supplier supplier;

  /**
   * Constructs the item with the given attributes. Often read from a line in a
   * text file.
   * 
   * @param id          The id of the new item
   * @param description The description/name of the new item
   * @param quantity    The amount of the new item
   * @param price       The initial print of the item
   * @param supplier    The Supplier object the item is ordered from
   */
  public Item(int id, String description, int quantity, double price, Supplier supplier) {
    this.id = id;
    this.description = description;
    this.quantity = quantity;
    this.price = price;
    this.supplier = supplier;
  }

  /**
   * Gets the id of the item.
   * 
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * Gets the description of the item.
   * 
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Gets the quantity of the item.
   * 
   * @return the quantity
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * Sets the quantity of the item. Often used for purchasing and ordering items.
   * 
   * @param quantity the quantity to set
   */
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  /**
   * Get the price of the item.
   * 
   * @return the price
   */
  public double getPrice() {
    return price;
  }

  /**
   * Get the Supplier object that the Item comes from.
   * 
   * @return the Supplier
   */
  public Supplier getSupplier() {
    return supplier;
  }

  /**
   * Prints the member variables of the Item in a formatted way.
   */
  public String toString() {
    return String.format("%4d%16s%11d%8.2f  %-24s\n", id, description, quantity, price, supplier.getName());
  }

  public boolean equals(Item other) {
    return id == other.getId() && quantity == other.getQuantity() && description.equals(other.getDescription())
        && price == other.getPrice() && supplier.getId() == other.getSupplier().getId();
  }

  /**
   * Gets a header for labelling the stringified version of a single item or
   * multiple items in a table. Implemented as a static class as it does not
   * interact with any member variables.
   * 
   * @return The header String to be concatenated.
   */
  public static String getHeader() {
    return " ID |  Description  | Quantity | Price |        Supplier        |\n";
  }
}