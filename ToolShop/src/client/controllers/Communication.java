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
  public void send(String dataType, Object object) {
    try {
      socketOut.writeObject(dataType);
      socketOut.writeObject(object);
    } catch (IOException e) {
      // TODO: Create formal error handling
      e.printStackTrace();
    }
  }
}