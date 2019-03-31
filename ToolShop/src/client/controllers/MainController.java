package client.controllers;

import client.views.MainView;

/**
 * Controller for the MainView GUI
 * 
 * @author Navjot Brar, Jofred Cayabyab and Tyler Lam
 * @version 1.0.0
 * @since March 31, 2019
 */
public class MainController {

  /**
   * the MainView object used which will be used to control the main GUI screen
   */
  MainView mainView;

  /**
   * Communication object that allows for communication with server
   */
  Communication communication;

  /**
   * Constructs the main controller by setting the MainView of the Controller as
   * well as the communication
   * 
   * @param view          the MainView object
   * @param communication the communication object
   */
  public MainController(MainView view, Communication communication) {

  }

  /**
   * adds listeners for the mainView buttons that allows for responses for buttons
   * being clicked
   */
  public void addMainListeners() {

  }
}