package server.controllers;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 * Communication
 */
public class Communication {

  private ServerSocket serverSocket;
  private Socket aSocket;
  private ExecutorService pool;
  ObjectInputStream socketIn;
  ObjectOutputStream socketOut;

  public Communication(int portNumber) {

  }

  public void communicateWithClient() {
    
  }
}