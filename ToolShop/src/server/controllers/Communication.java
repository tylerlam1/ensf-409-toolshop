package server.controllers;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 * Allows for communication between the server and the client as well as create
 * a thread pool to allow for multiple clients
 * 
 * @author Navjot Brar, Jofred Cayabyab and Tyler Lam
 * @version 1.0.0
 * @since March 31, 2019
 */
public class Communication {

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

  }

  /**
   * continously reads and writes to the client to read and display tool shop
   * information
   */
  public void communicateWithClient() {

  }
}