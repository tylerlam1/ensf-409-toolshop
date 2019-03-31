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

  private Socket aSocket;
  private BufferedReader socketIn;
  private PrintWriter socketOut;
  private LoginController loginController;
  private MainController mainController;

  /**
   * 
   * @param serverName
   * @param portNumber
   * @param lc
   * @param mc
   */
  public Communication(String serverName, String portNumber, LoginController lc, MainController mc) {

  }

  public void communicateServer() {

  }
}