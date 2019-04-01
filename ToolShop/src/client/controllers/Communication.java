package client.controllers;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Client class that allows for interaction with the server
 * 
 * @author Navjot Brar, Jofred Cayabyab and Tyler Lam
 * @version 1.0.0
 * @since March 31, 2019
 */
public class Communication {

  /**
   * Main socket used for basic communication between client and server
   */
  private Socket aSocket;

  /**
   * Input reader socket that allows the client to read in from the server
   */
  private BufferedReader socketIn;

  /**
   * the output reader socket that allows for writing out to the server
   */
  private PrintWriter socketOut;

  /**
   * holds the main login controller object which is used for controlling the
   * 'behind-the-scenes' of the login controller GUI
   */
  private LoginController loginController;

  /**
   * holds the main Tool Shop view controller object which is used for controlling
   * the 'behind-the-scenes' of the toolshop GUI
   */
  private MainController mainController;

  /**
   * Constructs the basic functionality (port number, etc.) of the client
   * communicator
   * 
   * @param serverName the name of the server
   * @param portNumber the port number used for communicating with server
   * @param lc         the login controller object
   * @param mc         the main view controller object
   */
  public Communication(String serverName, String portNumber, LoginController lc, MainController mc) {

  }

  public void communicateServer() {

  }
}