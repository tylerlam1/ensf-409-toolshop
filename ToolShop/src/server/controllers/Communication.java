package server.controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import utils.DataCodes;

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

  /**
   * constructs the server and initializes the client and server connection
   * 
   * @param portNumber the port number used to connect the client and server
   */
  public Communication(int portNumber) {
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
        switch (dataType) {
        case SEND_USERDATA: {
          System.out.println("Received " + SEND_USERDATA);
          // TODO: Change this test input
          System.out.println((String) socketIn.readObject());
          break;
        }
        case GET_TOOLS: {
          System.out.println("Received " + GET_TOOLS);
          break;
        }
        case SEARCH_TOOL_NAME: {
          System.out.println("Received " + SEARCH_TOOL_NAME);
          break;
        }
        case SEARCH_TOOL_ID: {
          System.out.println("Received " + SEARCH_TOOL_ID);
          break;
        }
        case CREATE_ITEM: {
          System.out.println("Received " + CREATE_ITEM);
          break;
        }
        case DELETE_ITEM: {
          System.out.println("Received " + DELETE_ITEM);
          break;
        }
        case ORDER_ITEM: {
          System.out.println("Received " + ORDER_ITEM);
          break;
        }
        case DECREASE_ITEM: {
          System.out.println("Received " + DECREASE_ITEM);
          break;
        }
        case STOP_SERVER: {
          System.out.println("Received " + STOP_SERVER);
          return;
        }
        default: {
          System.out.println("Unexpected data type received.");
          break;
        }
        }
      } catch (ClassNotFoundException e) {
        // TODO: Create formal error handling
        e.printStackTrace();
      } catch (IOException e) {
        // TODO: Create formal error handling
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    Communication theServer = new Communication(3000);
    theServer.communicateWithClient();
  }
}