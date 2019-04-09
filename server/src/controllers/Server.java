package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import models.*;

/**
 * Server
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
   * Input socket used to read from the client
   */

  ToolShop theShop;

  public Server(int portNumber, ToolShop theShop) {
    try {
      serverSocket = new ServerSocket(portNumber);
      pool = Executors.newCachedThreadPool();
      this.theShop = theShop;
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void communicateClient() {
    System.out.println("Server is now running.");
    try {
      while (true) {
        Communication communication = new Communication(theShop, serverSocket.accept());
        System.out.println("Connected to a client");
        pool.execute(communication);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * The main part of the application that will begin server side of application
   * 
   * @param args the command line argument that will not be used
   */
  public static void main(String[] args) {
    try {
      SupplierList suppliers = new SupplierList("suppliers.txt");
      ItemList items = new ItemList("items.txt", suppliers);
      ToolShop theShop = new ToolShop(suppliers, items);
      Server theServer = new Server(3000, theShop);
      theServer.communicateClient();
    } catch (FileNotFoundException e) {
      System.out.println("File(s) \"suppliers.txt\" and/or \"items.txt\" not found. Please try again.");
      System.exit(1);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}