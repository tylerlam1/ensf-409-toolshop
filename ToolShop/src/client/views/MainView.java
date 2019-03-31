package client.views;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * MainView
 */
public class MainView {

  JButton restoreAllBtn, createOrderBtn, quitBtn, deleteItemBtn, createItemBtn;
  JPanel leftPanel, southPanel, northPanel, centerPanel;
  JTextArea leftTextArea;
  JTextField searchBar;
  JComboBox dropDownMenu;

  public MainView(String name) {

  }

  //TODO: add your own private helper functions, I'll leave that to your discretion and how you want to organize things

  // UML says this is void but should probably return string or JTextArea
  public void getTextArea() {

  }

  public void showErrorDialog(String msg, String title) {

  }

  public void addRestoreAllListener(ActionListener l) {
    
  }
  public void addCreateOrderListener(ActionListener l) {

  }
  public void addQuitListener(ActionListener l) {

  }
  public void addDeleteItemListener(ActionListener l) {

  }
}