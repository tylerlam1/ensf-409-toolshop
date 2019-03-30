package client.controllers;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Communication
 */
public class Communication {

  private Socket aSocket;
  private BufferedReader socketIn;
  private PrintWriter socketOut;
  private LoginController loginController;
}