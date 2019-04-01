package client.controllers;

import java.awt.event.*;

import client.views.ItemDialogView;
import client.views.MainView;
import utils.DataCodes;

/**
 * Controller for the MainView GUI
 * 
 * @author Navjot Brar, Jofred Cayabyab and Tyler Lam
 * @version 1.0.0
 * @since March 31, 2019
 */
public class MainController implements DataCodes {

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
    mainView = view;
    this.communication = communication;
    addMainListeners();
  }

  /**
   * adds listeners for the mainView buttons that allows for responses for buttons
   * being clicked
   */
  public void addMainListeners() {
    mainView.addRestoreAllListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

      }
    });

    mainView.addCreateOrderListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ItemDialogView createItemPrompt = new ItemDialogView();
        createItemPrompt.pack();
        createItemPrompt.setVisible(true);

        createItemPrompt.addCreateItemListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            String description = createItemPrompt.getDescription();
            String supplierId = createItemPrompt.getSupplierId();
            String quantity = createItemPrompt.getQuantity();
            String price = createItemPrompt.getPrice();

            boolean hasEmptyField = description.length() == 0 || supplierId.length() == 0 || quantity.length() == 0
                || price.length() == 0;

            if (hasEmptyField) {
              createItemPrompt.setLabel("Please fill out all the fields.");
            } else {
              communication.sendItemInfo(description, quantity, price, supplierId);
              createItemPrompt.setVisible(false);
            }
          }
        });
      }
    });

    mainView.addDeleteItemListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

      }
    });

    mainView.addQuitListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

      }
    });
  }
}