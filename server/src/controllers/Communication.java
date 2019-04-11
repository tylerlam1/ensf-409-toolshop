package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import utils.DataCodes;
import utils.Item;
import utils.UserInformation;

/**
 * Allows for communication between the server and the client as well as create
 * a thread pool to allow for multiple clients
 * 
 * @author Navjot Brar, Jofred Cayabyab and Tyler Lam
 * @version 1.0.0
 * @since March 31, 2019
 */
public class Communication implements DataCodes, Runnable {
  Socket aSocket;

  ObjectInputStream socketIn;

  /**
   * Output socket used to write to the client
   */
  ObjectOutputStream socketOut;
  /**
   * the database that holds all the tool information
   */
  DatabaseController databaseControl;

  /**
   * Constructs the server and initializes the client/server connection.
   * 
   * @param portNumber the port number used to connect the client and server
   */
  public Communication(Socket aSocket, DatabaseController databaseControl) {
    try {
      this.databaseControl = databaseControl;
      System.out.println("Server is now running.");
      this.aSocket = aSocket;
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
    databaseControl.getLoginDatabase().clearDatabase();
    databaseControl.getLoginDatabase().readFromFile("logins.txt");
    boolean validUser = databaseControl.getLoginDatabase().checkUser(user);

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
    // ArrayList<Item> toolList = theShop.getItems().getList();
    // databaseControl.getItemDatabase().fillingDatabase("items.txt");
    ArrayList<Item> toolList = databaseControl.getItemDatabase().getItemList().getList();
    databaseControl.getItemDatabase().clearDatabase();
    for (Item a : toolList) {
      databaseControl.getItemDatabase().fillDatabase(a);
    }
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
    try {
      String name = (String) socketIn.readObject();
      int foundInteger = databaseControl.getItemDatabase().getIdByDescription(name);
      if (foundInteger == -1) {
        writeObject(SEND_ERROR);
      } else {
        writeObject(foundInteger);
      }
    } catch (NumberFormatException e) {
      socketOut.writeObject(SEND_ERROR);
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
      int returnVal = databaseControl.getItemDatabase().getItemById(id);
      Integer returnInteger = new Integer(returnVal);
      if (returnVal == -1) {
        writeObject(SEND_ERROR);
      } else {
        writeObject(returnInteger);
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
    if (databaseControl.getItemDatabase().checkStock()) {
      System.out.println("An order has been generated. Please check the orders.txt log for the new order.");
    } else {
      System.out.println("All items have enough stock.");
    }

    ArrayList<Item> toolList = databaseControl.getItemDatabase().getItemList().getList();
    databaseControl.getItemDatabase().clearDatabase();
    for (Item a : toolList) {
      databaseControl.getItemDatabase().addItem(a);
    }
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
    try {
      Item itemToDecrease = (Item) socketIn.readObject();
      int count = Integer.parseInt((String) socketIn.readObject());
      databaseControl.getItemDatabase().buyItem(itemToDecrease, count);
      ArrayList<Item> toolList = databaseControl.getItemDatabase().getItemList().getList();
      writeObject(toolList);
    } catch (NumberFormatException e) {
      writeObject(SEND_ERROR);
    }
  }

  /**
   * Reads item information from the input socket, then creates an Item and adds
   * it to the shop.
   * 
   * @throws ClassNotFoundException
   * @throws IOException
   */
  public void addNewTool() throws ClassNotFoundException, IOException {
    int readCount = 0;

    try {
      String description = (String) socketIn.readObject();
      readCount++;
      int quantity = Integer.parseInt((String) socketIn.readObject());
      readCount++;
      double price = Double.parseDouble((String) socketIn.readObject());
      readCount++;
      int supplierId = Integer.parseInt((String) socketIn.readObject());
      readCount++;
      Item newItem = databaseControl.getItemDatabase().addNewItem(description, quantity, price, supplierId);
      if (newItem == null) {
        writeObject(SEND_ERROR);
      } else {
        databaseControl.getItemDatabase().addItem(newItem);
        ArrayList<Item> toolList = databaseControl.getItemDatabase().getItemList().getList();
        writeObject(toolList);
      }
    } catch (NumberFormatException e) {
      // if an exception occurs, read the rest of what the client sends
      while (readCount < 4 - 1) {
        readCount++;
        socketIn.readObject();
      }
      writeObject(SEND_ERROR);
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
    databaseControl.getItemDatabase().deleteItem(itemToDelete);
    ArrayList<Item> toolList = databaseControl.getItemDatabase().getItemList().getList();
    writeObject(toolList);
  }

  /**
   * writes out to a socket and resets it
   * 
   * @param obj the object that will be written out
   */
  private void writeObject(Object obj) throws IOException {
    socketOut.writeObject(obj);
    socketOut.reset();
  }

  @Override
  public void run() {
    communicateWithClient();
  }
}