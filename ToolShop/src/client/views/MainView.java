package client.views;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Main Tool Shop GUI used for handling client requests and actions
 * 
 * @author Navjot Brar, Jofred Cayabyab and Tyler Lam
 * @version 1.0.0
 * @since March 31, 2019
 */
public class MainView {

  JButton restoreAllBtn, createOrderBtn, quitBtn, deleteItemBtn, createItemBtn;
  JPanel leftPanel, southPanel, northPanel, centerPanel;
  JTextArea leftTextArea;
  JTextField searchBar;
  JComboBox dropDownMenu;

  public MainView(String name) {

  }

  // TODO: add your own private helper functions, I'll leave that to your
  // discretion and how you want to organize things

  // UML says this is void but should probably return string or JTextArea
  public void getTextArea() {

  }

  /**
   * 
   * @param msg
   * @param title
   */
  public void showErrorDialog(String msg, String title) {

  }

  /**
   * 
   * @param l
   */
  public void addRestoreAllListener(ActionListener l) {

  }

  /**
   * 
   * @param l
   */
  public void addCreateOrderListener(ActionListener l) {

  }

  /**
   * 
   * @param l
   */
  public void addQuitListener(ActionListener l) {

  }

  /**
   * 
   * @param l
   */
  public void addDeleteItemListener(ActionListener l) {

  }
}