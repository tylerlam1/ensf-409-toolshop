package server.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import utils.Item;

/**
 * A wrapper for an ArrayList of Item objects. Contains logic for adding items,
 * searching for items by name/id, and reading items from text.
 * 
 * @author Navjot Brar, Jofred Cayabyab and Tyler Lam
 * @version 1.0.0
 * @since March 31, 2019
 */
public class ItemList {

  /**
   * The list of items in the Shop
   */
  private ArrayList<Item> list;

  /**
   * @return the list
   */
  public ArrayList<Item> getList() {
    return list;
  }

  /**
   * Constructs the item list, given a list of suppliers to get the Supplier
   * object from the given ID in the file.
   * 
   * @param itemFileName The name of the file that the Item information should be
   *                     read from
   * @param suppliers    The SupplierList object that contains all Supplier
   *                     objects
   * @throws FileNotFoundException
   * @throws IOException
   */
  public ItemList(String itemFileName, SupplierList suppliers) throws FileNotFoundException, IOException {
    list = new ArrayList<Item>();

    File file = new File(itemFileName);
    BufferedReader br = new BufferedReader(new FileReader(file));

    String inputStr;
    while ((inputStr = br.readLine()) != null) {
      addItemFromText(inputStr, suppliers);
    }

    br.close();
  }

  /**
   * Adds an item to the list, essentially adding a new item to the Shop.
   * 
   * @param id          The unique id of the item (should be one more than the
   *                    last item in the Shop).
   * @param description The description/name of the item
   * @param quantity    The initial quantity of the item
   * @param price       The price the item should be set to
   * @param supplierId  The id of the supplier
   * @param suppliers   The list of suppliers to get the supplier of the item from
   */
  public Item addItem(int id, String description, int quantity, double price, int supplierId, SupplierList suppliers) {
    Supplier supplier = suppliers.getSupplierById(supplierId);
    if (supplier == null) {
      System.out.println("Could not add item: supplier with ID " + supplierId + " not found.");
      return null;
    }
    Item newItem = new Item(id, description, quantity, price, supplier);

    list.add(newItem);

    return newItem;
  }

  /**
   * Removes an item to the list, essentially removing the item from sale within
   * the Shop.
   * 
   * @param toDelete the Item object to be deleted
   */
  public void deleteItem(Item toDelete) {
    for (Item item : list) {
      if (item.equals(toDelete)) {
        list.remove(item);
        return;
      }
    }
  }

  /**
   * Returns an Item object that has the given name.
   * 
   * @param name The name string to search for
   * @return The item with the given name, otherwise returns null.
   */
  public Item getItemByName(String name) {
    for (Item item : list) {
      if (item.getDescription().toLowerCase().equals(name.toLowerCase()))
        return item;
    }

    return null;
  }

  /**
   * Returns an Item object that has the given id.
   * 
   * @param id The id to search for
   * @return The item with the given id, otherwise returns null.
   */
  public Item getItemById(int id) {
    for (Item item : list) {
      if (item.getId() == id)
        return item;
    }

    return null;
  }

  /**
   * Uses text data to construct an Item object, then add it to the list.
   * 
   * @param textLine  the line from the text file to construct the Item with.
   * @param suppliers the list of suppliers to search for the supplier ID
   */
  private void addItemFromText(String textLine, SupplierList suppliers) {
    String[] dataValues = textLine.split(";");

    int id = Integer.parseInt(dataValues[0]);
    String description = dataValues[1];
    int quantity = Integer.parseInt(dataValues[2]);
    double price = Double.parseDouble(dataValues[3]);
    int supplierId = Integer.parseInt(dataValues[4]);
    addItem(id, description, quantity, price, supplierId, suppliers);
  }

  /**
   * Prints the entire list of items with a header.
   */
  public String toString() {
    String str = Item.getHeader();
    for (Item item : list) {
      str += item.toString();
    }

    return str;
  }
}