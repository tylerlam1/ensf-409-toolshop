package server.controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import server.models.*;
/**
 * FrontEnd class that handles all logic pertaining to user input. Communicates
 * with the backend in order to handle shop logic.
 * 
 * 
 */
public class FrontEnd {

  // TODO: Delete this class later, as it is only used for reference
  
  /**
   * The BufferedReader object that takes user input. This is made as a member
   * variable for easier access from all of the FrontEnd object's member
   * functions.
   */
  BufferedReader stdin;
  /**
   * The Shop object that contains information about the suppliers and items that
   * the actual shop has. This is made as a member variable for easier access from
   * all of the FrontEnd object's member functions.
   */
  private Shop theShop;

  /**
   * The constructor for FrontEnd. This calls the constructor for the ItemList and
   * SupplierList suppliers, which in turn reads information from the text files.
   * This also initializes the input stream.
   * 
   * @param theShop the shop object that encapsulates the back end of the program.
   * 
   * @throws FileNotFoundException
   * @throws IOException
   */
  public FrontEnd(Shop theShop) throws FileNotFoundException, IOException {
    this.theShop = theShop;
    stdin = new BufferedReader(new InputStreamReader(System.in));
  }

  /**
   * @return the theShop
   */
  public Shop getTheShop() {
    return theShop;
  }

  /**
   * This is the interface that will be displayed to the user through the console.
   * This program repeatedly asks the user for input until a quit command in
   * given.
   * 
   * @throws IOException
   */
  public void start() throws IOException {
    while (true) {
      switch (getUserChoice()) {
      case 1:
        listTools();
        pressEnter();
        break;
      case 2:
        searchToolName();
        pressEnter();
        break;
      case 3:
        searchToolId();
        pressEnter();
        break;
      case 4:
        checkItemQuantity();
        pressEnter();
        break;
      case 5:
        decreaseItemQuantity();
        pressEnter();
        break;
      case 6:
        addNewTool();
        break;
      case 7:
        deleteTool();
        break;
      case 8:
        System.out.println("Exiting program...");
        System.exit(0);
      default:
        System.out.println("Not a valid input. Please try again.\n");
      }
    }
  }

  /**
   * Gives the user a list of choices then prompts the user for input. Contains
   * 
   * @return
   * @throws IOException
   */
  private int getUserChoice() {
    System.out.println("Please select from the following options:");
    System.out.println("1. List all tools in the shop");
    System.out.println("2. Search for tool by name");
    System.out.println("3. Search for tool by ID");
    System.out.println("4. Check the quantity of all items");
    System.out.println("5. Decrease the quantity of an item");
    System.out.println("6. Add a new tool to the database");
    System.out.println("7. Delete a tool to the database");
    System.out.println("8. Quit");
    System.out.print("Select your choice: ");
    try {
      return Integer.parseInt(stdin.readLine());
    } catch (NumberFormatException e) {
      // Not a valid input, return an integer that is not an option
      return -1;
    } catch (IOException e) {
      System.out.println("An error occurred trying to read your input.");
      return -1;
    }
  }

  /**
   * Lists all of the tools held within the shop.
   */
  private void listTools() {
    System.out.println(theShop.getItems());
  }

  /**
   * Asks the user for a name of an object, then prints the object information to
   * the console, then returns the object.
   * 
   * @return The Item if it exists, otherwise returns null
   * @throws IOException
   */
  private Item searchToolName() throws IOException {
    System.out.print("Enter the name of the tool: ");
    String name = stdin.readLine();
    Item item = theShop.getItems().getItemByName(name);

    if (item != null) {
      System.out.println("Here is the information for the item:");
      System.out.println(Item.getHeader() + item);
    } else {
      System.out.println("Could not find an item with that name. Please try again.");
    }

    return item;
  }

  /**
   * Asks the user for an ID of an object, the prints the object information to
   * the console, then returns the object
   * 
   * @return The Item if it exists, otherwise returns null
   * @throws IOException
   */
  private Item searchToolId() throws IOException {
    System.out.print("Enter the ID of the tool: ");
    int id = Integer.parseInt(stdin.readLine());
    Item item = theShop.getItems().getItemById(id);

    if (item != null) {
      System.out.println("Here is the information for the item:");
      System.out.println(Item.getHeader() + item);
    } else {
      System.out.println("Could not find an item with that ID. Please try again.");
    }

    return item;
  }

  /**
   * Checks all of the item stock in the shop then automatically generates an
   * order if items need to be ordered.
   * 
   * @throws FileNotFoundException
   */
  private void checkItemQuantity() throws FileNotFoundException {
    System.out.println("Inventory being checked...");
    if (theShop.checkStock()) {
      System.out.println("An order has been generated. Please check the orders.txt log for the new order.");
    } else {
      System.out.println("All items have enough stock.");
    }
  }

  /**
   * Asks the user for an item to decrease the quantity.
   * 
   * @throws IOException
   */
  private void decreaseItemQuantity() throws IOException {
    System.out.println("Would you like to input the name or the ID of the tool you'd like to delete?");
    System.out.println("1. name\n2. ID");
    int choice = Integer.parseInt(stdin.readLine());
    Item item = null;
    switch (choice) {
    case 1:
      item = searchToolName();
      break;
    case 2:
      item = searchToolId();
      break;
    default:
      System.out.println("Invalid input.");
      return;
    }
    if (item != null) {
      System.out.println("How much would you like to decrease by?");
      int count = Integer.parseInt(stdin.readLine());
      theShop.buy(item, count);
    } else {
      System.out.println("Could not find an item with your input.");
    }
  }

  /**
   * Asks the user for information about a new tool, then generates a new tool in
   * the ItemList.
   */
  public void addNewTool() {
    System.out.print("Enter the name of your new tool: ");

    try {
      String name = stdin.readLine();
      System.out.print("Enter the quantity: ");
      int quantity = Integer.parseInt(stdin.readLine());
      System.out.print("Enter the price of the item: ");
      double price = Double.parseDouble(stdin.readLine());
      System.out.print("Enter the ID of its supplier: ");
      int supplierId = Integer.parseInt(stdin.readLine());

      theShop.addNewItem(name, quantity, price, supplierId);
      System.out.println("Successfully added a new item called " + name + " to the inventory.");
    } catch (NumberFormatException e) {
      System.out.println("Expected a number. Please try again.");
      return;
    } catch (IOException e) {
      System.out.println("Error occured trying to read your input. Please try again.");
      return;
    }
  }

  /**
   * Asks the user for a tool, then deletes the tool from the shop inventory.
   * 
   * @throws IOException
   */
  public void deleteTool() throws IOException {
    System.out.println("Would you like to input the name or the ID of the tool you'd like to delete?");
    System.out.println("1. name\n2. ID");
    int choice = Integer.parseInt(stdin.readLine());
    Item item = null;
    switch (choice) {
    case 1:
      item = searchToolName();
      break;
    case 2:
      item = searchToolId();
      break;
    default:
      System.out.println("Invalid input.");
      return;
    }
    if (item != null) {
      System.out.println("Are you sure you want to delete this item? (Y/N)");
      if (stdin.readLine().toUpperCase().trim().equals("N")) {
        System.out.println("Item deletion cancelled.");
        return;
      }

      theShop.getItems().deleteItem(item);
      System.out.println("Item successfully deleted.");
    } else {
      System.out.println("Could not find an item with your input.");
    }
  }

  /**
   * Prompts the user to press enter before continuing.
   * 
   * @throws IOException
   */
  private void pressEnter() throws IOException {
    System.out.println("Press enter to continue:");
    stdin.readLine();
    System.out.println();
  }

  public static void main(String[] args) throws FileNotFoundException, IOException {
    SupplierList suppliers = new SupplierList("suppliers.txt");
    // items needs suppliers to map a supplier to each item
    ItemList items = new ItemList("items.txt", suppliers);
    Shop theShop = new Shop(suppliers, items);
    // FrontEnd contains the shop item.
    FrontEnd fe = new FrontEnd(theShop);

    fe.start();
  }
}