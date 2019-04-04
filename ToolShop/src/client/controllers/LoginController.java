package client.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import client.views.LoginView;
import client.views.MainView;
import utils.DataCodes;

/**
 * Controller for the login screen GUI that handles login screen actions
 * 
 * @author Navjot Brar, Jofred Cayabyab and Tyler Lam
 * @version 1.0.0
 * @since March 31, 2019
 */
public class LoginController implements DataCodes {

  /**
   * the loginView object that holds the GUI for the login screen
   */
  LoginView loginView;

  /**
   * Communication object that allows for communication with server
   */
  Communication communication;

  /**
   * the mainView object that holds the GUI used to opened upon login
   */
  MainView mainView;

  /**
   * constructs the basic functionality of the loginController to handle the login
   * GUI, including showing the view, calling helper functions to create
   * listeners, etc.
   * 
   * @param view          the LoginView object that holds the login screen GUI
   * @param communication the communication object that allows for client-server
   *                      interaction
   */
  public LoginController(LoginView view, MainView mainView, Communication communication) {
    loginView = view;
    this.mainView = mainView;
    this.communication = communication;
    addLoginListeners();
  }

  /**
   * adds listeners to the login screen GUI buttons to enable responses to onClick
   * actions
   */
  private void addLoginListeners() {
    loginView.addLoginListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Button clicked");
        // TODO: Change test login listener
        communication.send(SEND_USERDATA, "test");
        // TODO: we need to validate user here
        loginView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainView.setVisible(true);
      }
    });
  }

  public void showView() {
    mainView.setVisible(true);
  }

  /**
   * The main function of the client. Since the login screen is shown first, main
   * is run through here as an entry point.
   */
  public static void main(String[] args) {
    LoginView loginView = new LoginView("Login");
    MainView mainView = new MainView("Main");
    Communication communication = new Communication("localhost", 3000);

    LoginController loginController = new LoginController(loginView, mainView, communication);
    MainController mainController = new MainController(mainView, loginView, communication);
    loginController.showView();
  }
}