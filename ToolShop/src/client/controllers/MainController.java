package client.controllers;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import server.models.Item;
import client.views.ItemDialogView;
import client.views.LoginView;
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
   * An arrayList of all the items
   */
  ArrayList<Item> itemCollection;

  /**
   * Communication object that allows for communication with server
   */
  Communication communication;

  /**
   * the LoginView object used which will be opened upon quitting the Main view
   */
  LoginView loginView;

  /**
   * Constructs the main controller by setting the MainView of the Controller as
   * well as the communication
   * 
   * @param view          the MainView object
   * @param communication the communication object
   */
  public MainController(MainView view, LoginView loginView, Communication communication) {
    mainView = view;
    this.loginView = loginView;
    this.communication = communication;

    ArrayList<Item> items = (ArrayList<Item>) communication.sendCode(GET_TOOLS);

    mainView.setTableData(items);

    addMainListeners();
  }

  /**
   * adds listeners for the mainView buttons that allows for responses for buttons
   * being clicked
   */
  public void addMainListeners() {

    mainView.addCreateOrderListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ItemDialogView itemPrompt = new ItemDialogView("Create Item");
        itemPrompt.pack();
        itemPrompt.setVisible(true);

        itemPrompt.addCreateItemListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            String description = itemPrompt.getDescription();
            String supplierId = itemPrompt.getSupplierId();
            String quantity = itemPrompt.getQuantity();
            String price = itemPrompt.getPrice();

            boolean hasEmptyField = description.length() == 0 || supplierId.length() == 0 || quantity.length() == 0
                || price.length() == 0;

            if (hasEmptyField) {
              itemPrompt.setLabel("Please fill out all the fields.");
            } else {
              itemCollection = (ArrayList<Item>) communication.sendItemInfo(description, quantity, price, supplierId);
              itemPrompt.setVisible(false);
            }
          }
        });
      }
    });

    mainView.addDeleteItemListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // int row = mainView.getTextArea().getSelectedRow();
        // Item deleteThisItem = itemCollection.get(row);
        // i
        // itemCollection = (ArrayList<Item>)communication.sendObject(DELETE_ITEM,
        // deleteThisItem);
      }
    });

    mainView.addDecreaseQuantityListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // int row = mainView.getTextArea().getSelectedRow();
        // Item decreaseThisItem = itemCollection.get(row);
        // String count = JOptionPane.showInputDialog(null, "How much quantity would you
        // like to remove?");
        // itemCollection =
        // (ArrayList<Item>)communication.sendObjectTwoObjects(DECREASE_ITEM,
        // decreaseThisItem, count);
      }
    });

    mainView.addSearchBarListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Item itemOfInterest = null;
        // String searchChoice = (String) comboBox.getSelectedItem();
        // if (searchChoice.equals("ID")) {
        // Object itemOfInterest = communication.send(SEARCH_TOOL_ID,
        // mainView.getSearchArea());
        // if(itemOfInterest instanceof Item) {
        // Item item = (Item) itemOfInterest;
        // }
        // } else {
        // itemOfInterest = communication.send(SEARCH_TOOL_NAME,
        // mainView.getSearchArea());
        // }
        // if (itemOfInterest == null) {
        // JOptionPane.showMessageDialog(null, "Item not found!");
        // }
        // JOptionPane.showMessageDialog(null, itemOfInterest);
      }
    });

    mainView.addQuitListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        mainView.setVisible(false);
        loginView.setVisible(true);
      }
    });
  }

  /**
   * shows the main toolShop GUI
   */
  public void showView() {
    mainView.setVisible(true);
  }
}