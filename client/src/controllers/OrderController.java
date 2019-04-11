package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import utils.DataCodes;
import views.MainView;
import views.OrderView;

/**
 * OrderController
 */
public class OrderController implements DataCodes {
  private MainView mainView;
  private OrderView orderView;
  private Communication communication;

  public OrderController(MainView mainView, OrderView orderView, Communication communication) {
    this.mainView = mainView;
    this.orderView = orderView;
    this.communication = communication;

    addListener();
  }

  private void getOrders() {
    // String response = (String) communication.sendCode(GET_ORDERS);
    // orderView.setTextArea(response);

    orderView.setTextArea("Sample text");
  }

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