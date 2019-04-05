package client.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
  JButton buyBtn, quitBtn, deleteItemBtn, createItemBtn, searchBarBtn, restoreBtn;

  /**
   * Panels used for separating the main menu into different components. Each
   * component will be displaying a different aspect of the toolshop menu
   */
  JPanel leftPanel, southPanel, northPanel, centerPanel, rightPanel;

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

  /**
   * The scrolling pane for the leftTextArea JTable
   */
  JScrollPane tableScroll;

  /**
   * The titles for the Search area and Table
   */
  JLabel searchPrompt, tableTitle, mainTitle, toolLogo;

  public MainView(String name) {
    super(name);
    
    addButtons();
    addSearchArea();
    addTable();
    addPanels();
    addMainInfo();

    leftPanel.add(tableTitle);
    leftPanel.add(tableScroll);
    leftPanel.add(searchPrompt);
    leftPanel.add(dropDownMenu);
    leftPanel.add(searchBar);
    leftPanel.add(searchBarBtn);

    southPanel.add(buyBtn);
    southPanel.add(createItemBtn);
    southPanel.add(restoreBtn);
    southPanel.add(deleteItemBtn);
    southPanel.add(quitBtn);

    northPanel.add(toolLogo);
    northPanel.add(mainTitle);

    rightPanel.add(northPanel, BorderLayout.NORTH);
    rightPanel.add(southPanel, BorderLayout.SOUTH);
    
    centerPanel.add(leftPanel, BorderLayout.WEST);
    centerPanel.add(rightPanel, BorderLayout.CENTER);

    add(centerPanel);

    pack();
  }

  /**
   * Adds the logo and a welcome message to the GUI
   */
  public void addMainInfo(){
    ImageIcon icon = new ImageIcon("56377101_2186558651413414_7823467754991648768_n.png");
    Image img = icon.getImage();
    Image newImg = img.getScaledInstance(345, 255, java.awt.Image.SCALE_SMOOTH);
    icon = new ImageIcon(newImg);

    mainTitle = new JLabel("Welcome to the Toolshop");
    mainTitle.setFont(new Font("Serif", Font.BOLD, 24));
    mainTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

    toolLogo = new JLabel();
    toolLogo.setIcon(icon);
    toolLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
  }

  /**
   * Creates the panels that make up the Main GUI
   */
  public void addPanels(){
    centerPanel = new JPanel(new BorderLayout());
    
    leftPanel = new JPanel();
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
    leftPanel.setPreferredSize(new Dimension(450,1000));

    northPanel = new JPanel();
    northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.PAGE_AXIS));
    northPanel.setPreferredSize(new Dimension(550,300));
    northPanel.setBackground(Color.WHITE);

    southPanel = new JPanel();
    southPanel.setPreferredSize(new Dimension(550, 700));
    southPanel.setBackground(Color.WHITE);

    rightPanel = new JPanel();
    rightPanel.setBackground(Color.WHITE);
    rightPanel.setPreferredSize(new Dimension(550,1000));
  }

  /**
   * Creates the table for the tool information in the Main GUI
   */
  public void addTable(){
    ToolShopTableModel tableModel = new ToolShopTableModel();
    leftTextArea = new JTable(tableModel);
    leftTextArea.getColumnModel().getColumn(4).setPreferredWidth(280);
    leftTextArea.getColumnModel().getColumn(4).setMaxWidth(280);
    leftTextArea.getColumnModel().getColumn(1).setPreferredWidth(150);
    leftTextArea.getColumnModel().getColumn(1).setMaxWidth(150);

    tableScroll = new JScrollPane(leftTextArea);
    tableScroll.setPreferredSize(new Dimension(450,600));

    tableTitle = new JLabel("Items currently in the inventory");
    tableTitle.setFont(new Font("Serif", Font.BOLD, 18));
    tableTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
  }
  
  /**
   * Creates the combinational box and search bar for searching for items
   */
  public void addSearchArea(){
    String[] options= {"Description", "ID"};

    searchBar = new JTextField("Search for a item");
    searchBar.setPreferredSize(new Dimension(800,100));
    searchBar.setMaximumSize(searchBar.getPreferredSize());

    dropDownMenu = new JComboBox(options);
    dropDownMenu.setPreferredSize(new Dimension(800, 100));
    dropDownMenu.setMaximumSize(dropDownMenu.getPreferredSize());

    searchPrompt = new JLabel("Search for an item by Description or ID");
    searchPrompt.setFont(new Font("Serif", Font.BOLD, 18));
    searchPrompt.setAlignmentX(Component.CENTER_ALIGNMENT);
  }

  /**
   * Creates the buttons for the Main GUI
   */
  public void addButtons(){
    buyBtn = new JButton("Buy");
    buyBtn.setPreferredSize(new Dimension(225,225));

    quitBtn = new JButton("Quit");
    quitBtn.setPreferredSize(new Dimension(450,225));

    deleteItemBtn = new JButton("Delete Item");
    deleteItemBtn.setPreferredSize(new Dimension(225,225));

    createItemBtn = new JButton("Create Item");
    createItemBtn.setPreferredSize(new Dimension(225,225));

    restoreBtn = new JButton("Restock");
    restoreBtn.setPreferredSize(new Dimension(225,225));

    searchBarBtn = new JButton("Search");
    searchBarBtn.setPreferredSize(new Dimension(800, 70));
    searchBarBtn.setMaximumSize(searchBarBtn.getPreferredSize());
    searchBarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
  }

  /**
   * Sets the table data for the table containing the tool information
   * @param data The data for the table
   */
  public void setTableData(ArrayList<Item> data) {
    ToolShopTableModel theModel = (ToolShopTableModel) leftTextArea.getModel();
    theModel.setData(data);
    theModel.fireTableDataChanged();
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
   * decreases the quantity of a selected item. Creates a DECREASE ITEM button
   * 
   * @param l the ActionListener object used to enable on-click functionality
   */
  public void addDecreaseQuantityListener(ActionListener l) {

  }

  /**
   * add the search bar listener. Depending on the options available on the
   * dropdown menu, the listener will get the input correspondingly
   * 
   * @param l the ActionListener object used to enable on-click functionality
   */
  public void addSearchBarListener(ActionListener l) {

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