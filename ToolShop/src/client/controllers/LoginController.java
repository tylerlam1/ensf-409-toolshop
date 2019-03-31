package client.controllers;

import client.views.LoginView;

/**
 * Controller for the login screen GUI that handles login screen actions
 * 
 * @author Navjot Brar, Jofred Cayabyab and Tyler Lam
 * @version 1.0.0
 * @since March 31, 2019
 */
public class LoginController {

  /**
   * the loginView object that holds the GUI for the login screen
   */
  LoginView loginView;

  /**
   * Communication object that allows for communication with server
   */
  Communication communication;

  /**
   * constructs the basic functionality of the loginController to handle the login
   * GUI
   * 
   * @param view          the LoginView object that holds the login screen GUI
   * @param communication the communication object that allows for client-server
   *                      interaction
   */
  public LoginController(LoginView view, Communication communication) {

  }

  /**
   * adds listeners to the login screen GUI buttons to enable responses to onClick
   * actions
   */
  public void addLoginListeners() {

  }
}