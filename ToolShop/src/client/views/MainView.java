package client.views;

import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import server.models.Item;

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
  JButton createOrderBtn, quitBtn, deleteItemBtn, restoreAllBtn, decreaseQuantityBtn, searchBarBtn;

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

    setLayout(new FlowLayout());

    northPanel = new JPanel();
    northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.PAGE_AXIS));

    createOrderBtn = new JButton("Create Order");
    quitBtn = new JButton("Quit");
    deleteItemBtn = new JButton("Delete Item");
    restoreAllBtn = new JButton("Restore deprived quantities to 50");
    decreaseQuantityBtn = new JButton("Decrease Item Quantity");
    searchBarBtn = new JButton("Search");

    ToolShopTableModel tableModel = new ToolShopTableModel();
    leftTextArea = new JTable(tableModel);
    JScrollPane scroll = new JScrollPane(leftTextArea);
    add(leftTextArea);
    add(scroll);
    add(deleteItemBtn);
    add(createOrderBtn);
    add(restoreAllBtn);
    add(decreaseQuantityBtn);
    add(quitBtn);

    String[] options = { "Description", "ID" };
    dropDownMenu = new JComboBox(options);
    searchBar = new JTextField(10);
    northPanel.add(dropDownMenu);
    northPanel.add(searchBar);
    northPanel.add(searchBarBtn);

    add(northPanel);

  }

  public JTextField getSearchArea() {
    return searchBar;
  }

  public void setTableData(ArrayList<Item> data) {
    ToolShopTableModel theModel = (ToolShopTableModel) leftTextArea.getModel();
    for (Item i : data) {
      System.out.println(i);
    }
    theModel.setData(data);
    theModel.fireTableDataChanged();
  }

  public JComboBox getDropdown() {
    return dropDownMenu;
  }

  // TODO: add your own private helper functions, I'll leave that to your
  // discretion and how you want to organize things

  // UML says this is void but should probably return string or JTextArea
  /**
   * returns the text area. Used for writing the list of tools to the text area.
   */
  public JTable getTextArea() {
    return leftTextArea;
  }

  /**
   * the error dialog used in the main toolshop view, which will be displayed in
   * the event of no input
   * 
   * @param msg   the message to be displayed on the message dialog
   * @param title the title on the dialog box
   */
  public void showErrorDialog(String msg, String title) {
    JOptionPane.showMessageDialog(null, msg, title, JOptionPane.ERROR_MESSAGE);
  }

  /**
   * shows a message dialog for information
   * 
   * @param msg the main message of the dialog box
   * @return the input message placed into input area on dialog box
   */
  public String createInputDialog(String message) {
    return JOptionPane.showInputDialog(null, message);
  }

  /**
   * add the listener for the 'create order' button. Creates a order for the
   * client
   * 
   * @param l the ActionListener object used to enable on-click functionality
   */
  public void addCreateOrderListener(ActionListener l) {
    createOrderBtn.addActionListener(l);
  }

  /**
   * adds a listener for a restock all button.
   * 
   * @param l the ActionListener object used to enable on-click functionality
   */
  public void addRestoreAllListener(ActionListener l) {
    restoreAllBtn.addActionListener(l);
  }

  /**
   * adds the 'quit' listener. Creates a QUIT button to exit from the main tool
   * shop screen back to the login screen
   * 
   * @param l the ActionListener object used to enable on-click functionality
   */
  public void addQuitListener(ActionListener l) {
    quitBtn.addActionListener(l);
  }

  /**
   * decreases the quantity of a selected item. Creates a DECREASE ITEM button
   * 
   * @param l the ActionListener object used to enable on-click functionality
   */
  public void addDecreaseQuantityListener(ActionListener l) {
    decreaseQuantityBtn.addActionListener(l);
  }

  /**
   * add the search bar listener. Depending on the options available on the
   * dropdown menu, the listener will get the input correspondingly
   * 
   * @param l the ActionListener object used to enable on-click functionality
   */
  public void addSearchBarListener(ActionListener l) {
    searchBarBtn.addActionListener(l);
  }

  /**
   * adds the 'delete item' listener. Creates a DELETE ITEM button used to delete
   * a particular item from the toolshop database of the client.
   * 
   * @param l the Actionlistener object used to enable on-click functionality
   */
  public void addDeleteItemListener(ActionListener l) {
    deleteItemBtn.addActionListener(l);
  }
}