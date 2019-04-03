package server.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import utils.DataCodes;
import server.models.*;

/**
 * Allows for communication between the server and the client as well as create
 * a thread pool to allow for multiple clients
 * 
 * @author Navjot Brar, Jofred Cayabyab and Tyler Lam
 * @version 1.0.0
 * @since March 31, 2019
 */
public class Communication implements DataCodes {

  /**
   * Server socket that accepts new incoming connections used to build new sockets
   */
  private ServerSocket serverSocket;

  /**
   * Socket used to implement a client-server connection
   */
  private Socket aSocket;

  /**
   * pool of threads
   */
  private ExecutorService pool;

  /**
   * input socket used to read from the client
   */
  ObjectInputStream socketIn;

  /**
   * output socket used to write to the client
   */
  ObjectOutputStream socketOut;

  ToolShop theShop;

  /**
   * constructs the server and initializes the client and server connection
   * 
   * @param portNumber the port number used to connect the client and server
   */
  public Communication(int portNumber, ToolShop theShop) {
    try {
      serverSocket = new ServerSocket(3000);
      System.out.println("Server is now running.");
      aSocket = serverSocket.accept();
      System.out.println("A client has connected.");
      socketOut = new ObjectOutputStream(aSocket.getOutputStream());
      socketIn = new ObjectInputStream(aSocket.getInputStream());
    } catch (IOException e) {
      // TODO: Create formal error handling
      e.printStackTrace();
    }
  }

  /**
   * continously reads and writes to the client to read and display tool shop
   * information
   */
  public void communicateWithClient() {
    while (true) {
      try {
        String dataType = (String) socketIn.readObject();
        System.out.println("Received " + dataType);
        switch (dataType) {
        case SEND_USERDATA: {
          validateUser();
          break;
        }
        case GET_TOOLS: {
          getTools();
          break;
        }
        case SEARCH_TOOL_NAME: {
          searchToolName();
          break;
        }
        case SEARCH_TOOL_ID: {
          searchToolId();
          break;
        }
        case CREATE_ITEM: {
          addNewTool();
          break;
        }
        case DELETE_ITEM: {
          deleteTool();
          break;
        }
        case ORDER_ITEM: {
          checkItemQuantity();
          break;
        }
        case DECREASE_ITEM: {
          decreaseItemQuantity();
          break;
        }
        case STOP_SERVER: {
          return;
        }
        default: {
          socketOut.writeObject(SEND_ERROR);
          break;
        }
        }
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
        System.err.println("Ensure that the class files are consistent between client and server.");
      } catch (IOException e) {
        // TODO: Create formal error handling
        e.printStackTrace();
      }
    }
  }

  private void validateUser() throws ClassNotFoundException, IOException {
    UserInformation user = (UserInformation) socketIn.readObject();
    // TODO: Implement user validation with SQL database
    boolean validUser = true;

    if (validUser) {
      socketOut.writeObject(user);
    } else {
      socketOut.writeObject(SEND_ERROR);
    }
  }

  private void getTools() throws IOException {
    ArrayList<Item> toolList = theShop.getItems().getList();
    socketOut.writeObject(toolList);
  }

  /**
   * Asks the user for a name of an object, then prints the object information to
   * the console, then returns the object.
   * 
   * @return The Item if it exists, otherwise returns null
   * @throws IOException
   */
  private void searchToolName() throws IOException, ClassNotFoundException {
    String name = (String) socketIn.readObject();
    Item item = theShop.getItems().getItemByName(name);
    if (item == null) {
      socketOut.writeObject(SEND_ERROR);
    } else {
      socketOut.writeObject(item);
    }
  }

  /**
   * Asks the user for an ID of an object, the prints the object information to
   * the console, then returns the object
   * 
   * @return The Item if it exists, otherwise returns null
   * @throws IOException
   */
  private void searchToolId() throws ClassNotFoundException, IOException {
    try {
      int id = Integer.parseInt((String) socketIn.readObject());
      Item item = theShop.getItems().getItemById(id);
      if (item == null) {
        socketOut.writeObject(SEND_ERROR);
      } else {
        socketOut.writeObject(item);
      }
    } catch (NumberFormatException e) {
      socketOut.writeObject(SEND_ERROR);
    }
  }

  /**
   * Checks all of the item stock in the shop then automatically generates an
   * order if items need to be ordered.
   * 
   * @throws FileNotFoundException
   */
  private void checkItemQuantity() throws FileNotFoundException, IOException {
    System.out.println("Inventory being checked...");
    if (theShop.checkStock()) {
      System.out.println("An order has been generated. Please check the orders.txt log for the new order.");
    } else {
      System.out.println("All items have enough stock.");
    }

    ArrayList<Item> toolList = theShop.getItems().getList();
    socketOut.writeObject(toolList);
  }

  /**
   * Asks the user for an item to decrease the quantity.
   * 
   * @throws IOException
   */
  private void decreaseItemQuantity() throws IOException, ClassNotFoundException {
    Item itemToDecrease = (Item) socketIn.readObject();
    int count = Integer.parseInt((String) socketIn.readObject());
    theShop.buy(itemToDecrease, count);
    ArrayList<Item> toolList = theShop.getItems().getList();
    socketOut.writeObject(toolList);
  }

  /**
   * Asks the user for information about a new tool, then generates a new tool in
   * the ItemList.
   */
  public void addNewTool() throws ClassNotFoundException, IOException {
    String description = (String) socketIn.readObject();
    int quantity = Integer.parseInt((String) socketIn.readObject());
    double price = Double.parseDouble((String) socketIn.readObject());
    int supplierId = Integer.parseInt((String) socketIn.readObject());
    Item newItem = theShop.addNewItem(description, quantity, price, supplierId);
    if (newItem == null) {
      socketOut.writeObject(SEND_ERROR);
    } else {
      ArrayList<Item> toolList = theShop.getItems().getList();
      socketOut.writeObject(toolList);
    }
  }

  /**
   * Asks the user for a tool, then deletes the tool from the shop inventory.
   * 
   * @throws IOException
   */
  public void deleteTool() throws IOException, ClassNotFoundException {
    Item itemToDelete = (Item) socketIn.readObject();
    theShop.getItems().deleteItem(itemToDelete);
    ArrayList<Item> toolList = theShop.getItems().getList();
    socketOut.writeObject(toolList);
  }

  public static void main(String[] args) {
    try {
      SupplierList suppliers = new SupplierList("suppliers.txt");
      ItemList items = new ItemList("items.txt", suppliers);
      ToolShop theShop = new ToolShop(suppliers, items);
      Communication theServer = new Communication(3000, theShop);
      theServer.communicateWithClient();
    } catch (FileNotFoundException e) {
      System.out.println("File(s) \"suppliers.txt\" and/or \"items.txt\" not found. Please try again.");
      System.exit(1);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}