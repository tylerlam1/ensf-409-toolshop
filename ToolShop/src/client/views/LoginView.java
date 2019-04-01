package client.views;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Displays the Login view GUI for the user
 * 
 * @author Navjot Brar, Jofred Cayabyab and Tyler Lam
 * @version 1.0.0
 * @since March 31, 2019
 */
public class LoginView extends JFrame {
  /**
   * login button used to allow for a client to login
   */
  JButton loginBtn;

  /**
   * individual panel components for each part of the login GUI
   */
  JPanel southPanel, mainPanel, northPanel;

  /**
   * constructs the login view by setting the basic titles, panels and frames
   * 
   * @param name the title name of the window
   */
  public LoginView(String name) {
    super(name);
    setLayout(new FlowLayout());
    loginBtn = new JButton("Test");
    add(loginBtn);
    pack();
  }

  /**
   * adds a ActionListener to the login button to correctly handle a login
   * operation upon having a client click the login button
   * 
   * @param l ActionListener used to handle login being clicked
   */
  public void addLoginListener(ActionListener l) {
    loginBtn.addActionListener(l);
  }

  /**
   * error dialog used for handling no input
   * 
   * @param msg   the message being displayed in the dialog box
   * @param title the title of the dialog box
   */
  public void showErrorDialog(String msg, String title) {

  }
}