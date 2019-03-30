package server.controllers;

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
  private UserInformation userInfo;

  public Communication(int portNumber) {

  }

  public void communicateWithClient() {
    
  }
}