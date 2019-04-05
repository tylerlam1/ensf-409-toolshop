package client.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
  private JButton loginBtn;

  /**
   * individual panel components for each part of the login GUI
   */
  private JPanel southPanel, mainPanel, northPanel, idPanel, passwordPanel;

  /**
   * text field areas for ID and password
   */
  private JTextField IDTextField, passwordTextField;

  /**
   * constructs the login view by setting the basic titles, panels and frames
   * 
   * @param name the title name of the window
   */
  public LoginView(String name) {
    super(name);
    setSize(300, 200);
    setLayout(new BorderLayout());

    loginBtn = new JButton("Login");

    southPanel = new JPanel();
    mainPanel = new JPanel();
    idPanel = new JPanel();
    idPanel.setLayout(new FlowLayout());
    passwordPanel = new JPanel();
    passwordPanel.setLayout(new FlowLayout());
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    northPanel = new JPanel();

    idPanel.add(new JLabel("ID: "));
    passwordPanel.add(new JLabel("Password: "));
    IDTextField = new JTextField(5);
    passwordTextField = new JTextField(5);

    idPanel.add(IDTextField);
    passwordPanel.add(passwordTextField);
    mainPanel.add(idPanel);
    mainPanel.add(passwordPanel);

    northPanel.add(new JLabel("Login Screen"));

    southPanel.add(loginBtn);

    add(northPanel, BorderLayout.NORTH);
    add(mainPanel, BorderLayout.CENTER);
    add(southPanel, BorderLayout.SOUTH);
    pack();
  }

  /**
   * returns the IDTextField
   * 
   * @return the ID Text Field
   */
  public String returnIDTextField() {
    return IDTextField.getText();
  }

  /**
   * returns the password text field
   * 
   * @return the password text field
   */
  public String returnPasswordTextField() {
    return passwordTextField.getText();
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
    JOptionPane.showMessageDialog(null, msg, title, JOptionPane.ERROR_MESSAGE);
  }
}