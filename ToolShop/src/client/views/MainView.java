package client.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Main Tool Shop GUI used for handling client requests and actions
 * 
 * @author Navjot Brar, Jofred Cayabyab and Tyler Lam
 * @version 1.0.0
 * @since March 31, 2019
 */
public class MainView extends JFrame {

  /**
   * buttons available on the main tool shop menu. This includes restore all -
   * used for restoring the basic tools available on the database. Also Create
   * order- which Creates an Order for the client. Additionally, QUIT - which is
   * used to quit from the main menu back into the login screen. A DELETE ITEM
   * button - which is used for deleting a particular item. A CREATE ITEM - which
   * is used for creating a item to add into the tool shop.
   */
  JButton buyBtn, quitBtn, deleteItemBtn, createItemBtn;

  /**
   * Panels used for separating the main menu into different components. Each
   * component will be displaying a different aspect of the toolshop menu
   */
  JPanel leftPanel, southPanel, northPanel, centerPanel;

  /**
   * this is the Table, which will have the list of tools in the toolshop for the
   * client
   */
  JTable leftTextArea;

  /**
   * Search bar available for the client for a variety of functions, including
   * searching, etc.
   */
  JTextField searchBar;

  /**
   * Drop-Down menu available used for different functions in the search bar
   */
  JComboBox dropDownMenu;

  public MainView(String name) {
    super(name);
    setSize(1000,1000);

    buyBtn = new JButton("Order");
    quitBtn = new JButton("Quit");
    deleteItemBtn = new JButton("Delete Item");
    createItemBtn = new JButton("Create Item");
    searchBar = new JTextField("Search for a item");

    centerPanel = new JPanel(new FlowLayout());
    leftPanel = new JPanel();
    leftPanel.setPreferredSize(new Dimension(400,1000));
    leftPanel.setBackground(Color.CYAN);
    northPanel = new JPanel();
    northPanel.add(searchBar);
    northPanel.setBackground(Color.GRAY);
    southPanel = new JPanel();
    southPanel.setPreferredSize(new Dimension(600, 700));
    southPanel.setBackground(Color.MAGENTA);

    centerPanel.add(leftPanel);
    centerPanel.add(northPanel);
    centerPanel.add(southPanel);
    add(centerPanel);
  }

  // TODO: add your own private helper functions, I'll leave that to your
  // discretion and how you want to organize things

  // UML says this is void but should probably return string or JTextArea
  /**
   * returns the text area. Used for writing the list of tools to the text area.
   */
  public void getTextArea() {

  }

  /**
   * the error dialog used in the main toolshop view, which will be displayed in
   * the event of no input
   * 
   * @param msg   the message to be displayed on the message dialog
   * @param title the title on the dialog box
   */
  public void showErrorDialog(String msg, String title) {

  }

  /**
   * add the listener for the 'create order' button. Creates a order for the
   * client
   * 
   * @param l the ActionListener object used to enable on-click functionality
   */
  public void addCreateOrderListener(ActionListener l) {

  }

  /**
   * adds the 'quit' listener. Creates a QUIT button to exit from the main tool
   * shop screen back to the login screen
   * 
   * @param l the ActionListener object used to enable on-click functionality
   */
  public void addQuitListener(ActionListener l) {

  }

  /**
   * adds the 'delete item' listener. Creates a DELETE ITEM button used to delete
   * a particular item from the toolshop database of the client.
   * 
   * @param l the Actionlistener object used to enable on-click functionality
   */
  public void addDeleteItemListener(ActionListener l) {

  }
}