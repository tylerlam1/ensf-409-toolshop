package controllers;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import utils.Item;
import utils.DataCodes;
import views.ItemDialogView;
import views.LoginView;
import views.MainView;
import views.OrderView;

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
  private MainView mainView;

  /**
   * An arrayList of all the items
   */
  private ArrayList<Item> itemCollection;

  /**
   * Communication object that allows for communication with server
   */
  private Communication communication;

  /**
   * the LoginView object used which will be opened upon quitting the Main view
   */
  private LoginView loginView;
  
  /**
   * the LoginView object used which will be opened upon quitting the Main view
   */
  private OrderView orderView;
  /**
   * Constructs the main controller by setting the MainView of the Controller as
   * well as the communication
   * 
   * @param view          the MainView object
   * @param communication the communication object
   */
  public MainController(MainView view, LoginView loginView, OrderView orderView, Communication communication) {
    mainView = view;
    this.loginView = loginView;
    this.orderView = orderView;
    this.communication = communication;

    itemCollection = (ArrayList<Item>) communication.sendCode(GET_TOOLS);
    mainView.setTableData(itemCollection);

    addMainListeners();
  }

  /**
   * adds listeners for the mainView buttons that allows for responses for buttons
   * being clicked
   */
  public void addMainListeners() {

    mainView.addCreateItemListener(new ActionListener() {
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
              Object temp = communication.sendItemInfo(description, quantity, price, supplierId);
              if (temp instanceof ArrayList<?>) {
                itemPrompt.setVisible(false);
                itemCollection = (ArrayList<Item>) temp;
                mainView.setTableData(itemCollection);
              } else {
                mainView.showErrorDialog("Cannot add item. Supplier not found or entries were invalid.", "Error Found");
                itemPrompt.setVisible(false);
                return;
              }
            }
          }
        });
      }
    });

    mainView.addRestockListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        itemCollection = (ArrayList<Item>) communication.sendCode(ORDER_ITEMS);
        mainView.setTableData(itemCollection);
        mainView.createMessageDialog("All items with quantity under 40 have been restocked to a quantity of 50.");
      }
    });

    mainView.addDeleteItemListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int row = mainView.getTextArea().getSelectedRow();
        if (row == -1) {
          mainView.showErrorDialog("Please select a item on the table to the left.", "Error Found");
          return;
        }
        Item deleteThisItem = itemCollection.get(row);
        itemCollection = (ArrayList<Item>) communication.sendObject(DELETE_ITEM, deleteThisItem);
        mainView.setTableData(itemCollection);
      }
    });

    mainView.addBuyListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int row = mainView.getTextArea().getSelectedRow();
        if (row == -1) {
          mainView.showErrorDialog("Please select a item on the table to the left.", "Error Found");
          return;
        }
        Item decreaseThisItem = itemCollection.get(row);
        String count = mainView.createInputDialog("How much quantity would you like to remove?");
        Object temp = communication.sendTwoObjects(DECREASE_ITEM, decreaseThisItem, count);
        if (temp instanceof ArrayList<?>) {
          itemCollection = (ArrayList<Item>) temp;
          mainView.setTableData(itemCollection);
        } else {
          mainView.showErrorDialog("Invalid Entry. Please try again!", "Error Found");
        }
      }
    });

    mainView.addSearchBarListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Item itemOfInterest = null;
        String searchChoice = (String) mainView.getDropdown().getSelectedItem();
        Object temporaryObject = null;
        if (searchChoice.equals("ID")) {
          temporaryObject = (Object) communication.sendObject(SEARCH_TOOL_ID, mainView.getSearchArea().getText());
        } else {
          temporaryObject = (Object) communication.sendObject(SEARCH_TOOL_NAME, mainView.getSearchArea().getText());
        }
        if (temporaryObject instanceof Item) {
          itemOfInterest = (Item) temporaryObject;
        } else {
          mainView.showErrorDialog("Item Not Found!", "Error Found");
          return;
        }
        int index = 0;
        for (Item a : itemCollection) {
          if (a.equals(itemOfInterest)) {
            break;
          }
          index++;
        }
        mainView.getTextArea().setRowSelectionInterval(index, index);
      }
    });

    mainView.addQuitListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        mainView.setVisible(false);
        loginView.setVisible(true);
      }
    });
    mainView.addSelectionListener(itemCollection);
  }

  /**
   * shows the main toolShop GUI
   */
  public void showView() {
    mainView.setVisible(true);
  }
}