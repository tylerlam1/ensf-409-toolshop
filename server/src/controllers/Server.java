package controllers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The server of the client-server application.
 * 
 * @author Navjot Brar, Tyler Lam, Jofred Cayabyab
 * @since April 11, 2018
 * @version 1.0
 */
public class Server {

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
   * Constructor for the server
   */
  public Server(int portNumber) {
    try {
      serverSocket = new ServerSocket(portNumber);
      pool = Executors.newCachedThreadPool();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Communicates with the client
   */
  public void communicateClient(DatabaseController databaseControl) {
    System.out.println("Server is now running.");
    try {
      while (true) {
        Communication communication = new Communication(serverSocket.accept(), databaseControl);
        System.out.println("Connected to a client");
        pool.execute(communication);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}