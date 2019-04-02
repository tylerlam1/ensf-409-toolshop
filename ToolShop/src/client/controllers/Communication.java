package client.controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import utils.DataCodes;

/**
 * Client class that allows for interaction with the server
 * 
 * @author Navjot Brar, Jofred Cayabyab and Tyler Lam
 * @version 1.0.0
 * @since March 31, 2019
 */
public class Communication implements DataCodes {

  /**
   * Main socket used for basic communication between client and server
   */
  private Socket aSocket;

  /**
   * Input reader socket that allows the client to read in from the server
   */
  private ObjectInputStream socketIn;

  /**
   * the output reader socket that allows for writing out to the server
   */
  private ObjectOutputStream socketOut;

  /**
   * Constructs the basic functionality (port number, etc.) of the client
   * communicator
   * 
   * @param serverName the name of the server
   * @param portNumber the port number used for communicating with server
   */
  public Communication(String serverName, int portNumber) {
    try {
      aSocket = new Socket(serverName, portNumber);
      socketOut = new ObjectOutputStream(aSocket.getOutputStream());
      socketIn = new ObjectInputStream(aSocket.getInputStream());
    } catch (IOException e) {
      // TODO: Create formal error handling
      e.printStackTrace();
    }
  }

  /**
   * Function to send data to the backend.
   * 
   * @param dataType
   * @param object
   */
  public Object send(String dataType, Object object) {
    Object obj = null;

    try {
      socketOut.writeObject(dataType);
      socketOut.writeObject(object);

      obj = socketIn.readObject();

    } catch (IOException e) {
      // TODO: Create formal error handling
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      System.out.println("Class not found.");
    }

    return obj;
  }

  /**
   * sends the Item Information used for creating a new Item
   * 
   * @return the return item object
   */
  public Object sendItemInfo(String itemDescription, String quantity, String price, String supplierId) {
    Object obj = null;
    try {
      socketOut.writeObject(CREATE_ITEM);
      socketOut.writeObject(itemDescription);
      socketOut.writeObject(quantity);
      socketOut.writeObject(price);
      socketOut.writeObject(supplierId);
      obj = socketIn.readObject();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      System.out.println("Class not found.");
    }
    return obj;
  }
}