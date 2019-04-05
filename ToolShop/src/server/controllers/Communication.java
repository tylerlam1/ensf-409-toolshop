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
   * Pool of threads
   */
  private ExecutorService pool;

  /**
   * Input socket used to read from the client
   */
  ObjectInputStream socketIn;

  /**
   * Output socket used to write to the client
   */
  ObjectOutputStream socketOut;

  ToolShop theShop;

  /**
   * Constructs the server and initializes the client/server connection.
   * 
   * @param portNumber the port number used to connect the client and server
   */
  public Communication(int portNumber, ToolShop theShop) {
    try {
      this.theShop = theShop;
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
   * Communicates with client by listening for datatypes to be written to input
   * socket, then handling what server should do based on the input datacode
   * received.
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
        case ORDER_ITEMS: {
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
          writeObject(SEND_ERROR);
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

  /**
   * Receives a UserInformation object from the client and validates it with the
   * server. Sends back the user if successfully validated, or a SEND_ERROR string
   * if unsuccessfully validated.
   * 
   * @throws ClassNotFoundException
   * @throws IOException
   */
  private void validateUser() throws ClassNotFoundException, IOException {
    UserInformation user = (UserInformation) socketIn.readObject();
    // TODO: Implement user validation with SQL database
    boolean validUser = true;

    if (validUser) {
      writeObject(user);
    } else {
      writeObject(SEND_ERROR);
    }
  }

  /**
   * Sends the ArrayList<Item> of tools to the client.
   * 
   * @throws IOException
   */
  private void getTools() throws IOException {
    ArrayList<Item> toolList = theShop.getItems().getList();
    writeObject(toolList);
  }

  /**
   * Reads the name of an object from the client, then sends the Item object back
   * to the client.
   * 
   * @throws ClassNotFoundException
   * @throws IOException
   */
  private void searchToolName() throws ClassNotFoundException, IOException {
    String name = (String) socketIn.readObject();
    Item item = theShop.getItems().getItemByName(name);
    if (item == null) {
      writeObject(SEND_ERROR);
    } else {
      writeObject(item);
    }
  }

  /**
   * Reads the ID of an object from the client, then sends the Item object back to
   * the client.
   * 
   * @throws ClassNotFoundException
   * @throws IOException
   */
  private void searchToolId() throws ClassNotFoundException, IOException {
    try {
      int id = Integer.parseInt((String) socketIn.readObject());
      Item item = theShop.getItems().getItemById(id);
      if (item == null) {
        writeObject(SEND_ERROR);
      } else {
        writeObject(item);
      }
    } catch (NumberFormatException e) {
      writeObject(SEND_ERROR);
    }
  }

  /**
   * Checks all of the item stock in the shop then automatically generates an
   * order if items need to be ordered. Furthermore, sends back the updated tool
   * list to the client.
   * 
   * @throws FileNotFoundException
   * @throws IOException
   */
  private void checkItemQuantity() throws FileNotFoundException, IOException {
    System.out.println("Inventory being checked...");
    if (theShop.checkStock()) {
      System.out.println("An order has been generated. Please check the orders.txt log for the new order.");
    } else {
      System.out.println("All items have enough stock.");
    }

    ArrayList<Item> toolList = theShop.getItems().getList();
    writeObject(toolList);
  }

  /**
   * Reads an item and amount to decrease from the client. Sends back the updated
   * tool list to the client.
   * 
   * @throws ClassNotFoundException
   * @throws IOException
   */
  private void decreaseItemQuantity() throws ClassNotFoundException, IOException {
    Item itemToDecrease = (Item) socketIn.readObject();
    int count = Integer.parseInt((String) socketIn.readObject());
    theShop.buy(itemToDecrease, count);
    ArrayList<Item> toolList = theShop.getItems().getList();
    writeObject(toolList);
  }

  /**
   * Reads item information from the input socket, then creates an Item and adds
   * it to the shop.
   * 
   * @throws ClassNotFoundException
   * @throws IOException
   */
  public void addNewTool() throws ClassNotFoundException, IOException {
    String description = (String) socketIn.readObject();
    int quantity = Integer.parseInt((String) socketIn.readObject());
    double price = Double.parseDouble((String) socketIn.readObject());
    int supplierId = Integer.parseInt((String) socketIn.readObject());
    Item newItem = theShop.addNewItem(description, quantity, price, supplierId);
    if (newItem == null) {
      writeObject(SEND_ERROR);
    } else {
      ArrayList<Item> toolList = theShop.getItems().getList();
      writeObject(toolList);
    }
  }

  /**
   * Reads an item from the client, then deletes the item from the ToolShop. Sends
   * back the updated full tool list to the client.
   * 
   * @throws ClassNotFoundException
   * @throws IOException
   */
  public void deleteTool() throws ClassNotFoundException, IOException {
    Item itemToDelete = (Item) socketIn.readObject();
    theShop.getItems().deleteItem(itemToDelete);
    ArrayList<Item> toolList = theShop.getItems().getList();
    writeObject(toolList);
  }

  private void writeObject(Object obj) throws IOException {
    socketOut.writeObject(obj);
    socketOut.reset();
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
      e.printStackTrace();
    }
  }
}