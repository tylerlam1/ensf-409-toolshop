package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import utils.DataCodes;
import views.MainView;
import views.OrderView;

/**
 * The controller for the order GUI.   
 * @author Navjot Brar, Jofred Cayabyab, Tyler Lam
 * @since April 11, 2019
 * @version 1.0
 */
public class OrderController implements DataCodes {
  /**
   * The main GUI
   */
  private MainView mainView;
  /**
   * The order GUI
   */
  private OrderView orderView;
  /**
   * Communication object that allows the controller to communicate with the server
   */
  private Communication communication;

  /**
   * Constructor for the order controller
   * @param mainView the main view to be initialized
   * @param orderView the order view to be initialized
   * @param communication the communication between the controller and server to be initialized
   */
  public OrderController(MainView mainView, OrderView orderView, Communication communication) {
    this.mainView = mainView;
    this.orderView = orderView;
    this.communication = communication;

    addListener();
  }

  /**
   * Gets the orders from the server and displays it on the text area
   */
  private void getOrders() {
    String response = (String) communication.sendCode(GET_ORDERS);
    orderView.setTextArea(response);
  }

  /**
   * Listens for a click on the view orders button on the main view and displays the window
   */
  private void addListener() {
    mainView.addViewOrdersListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        orderView.setVisible(true);
        getOrders();
      }
    });
  }
}