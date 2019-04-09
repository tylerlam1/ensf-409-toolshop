package client.views;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
  private JButton loginBtn, closeBtn;

  /**
   * individual panel components for each part of the login GUI
   */
  private JPanel southPanel, mainPanel, northPanel, idPanel, passwordPanel;

  /**
   * Main login screen title and logo as well as ID and password text
   */
  private JLabel mainTitle, toolLogo;

  /**
   * text field areas for ID and password
   */
  private JTextField IDTextField;

  /**
   * text field for password
   */
  private JPasswordField passwordTextField;

  /**
   * constructs the login view by setting the basic titles, panels and frames
   * 
   * @param name the title name of the window
   */
  public LoginView(String name) {
    super(name);
    setPreferredSize(new Dimension(700, 550));
    setLayout(new BorderLayout());
    setPanels();
    setCredentialsText();
    setTitles();
    addButtons();
    addSubPanels();
    addPanels();
    pack();
    centreWindow();
    setResizable(false);
  }

  /**
   * add buttons
   */
  public void addButtons() {
    loginBtn = new JButton("Login");
    loginBtn.setFont(new Font("SansSerif", Font.PLAIN, 20));
    closeBtn = new JButton("Close Application");
    closeBtn.setFont(new Font("SansSerif", Font.PLAIN, 20));
    loginBtn.setPreferredSize(new Dimension(110, 55));
    closeBtn.setPreferredSize(new Dimension(200, 55));
  }

  /**
   * set the panels
   */
  public void setPanels() {
    southPanel = new JPanel();
    southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
    mainPanel = new JPanel();
    mainPanel.setBorder(new EmptyBorder(65, 20, 20, 20));
    idPanel = new JPanel();
    idPanel.setLayout(new FlowLayout());
    passwordPanel = new JPanel();
    passwordPanel.setLayout(new FlowLayout());
    passwordPanel.setBorder(new EmptyBorder(0, -65, 0, 0));
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    northPanel = new JPanel();
  }

  /**
   * sets the login main picture and title
   */
  public void setTitles() {
    ImageIcon icon = new ImageIcon("logo.png");
    Image img = icon.getImage();
    Image newImg = img.getScaledInstance(480, 201, java.awt.Image.SCALE_SMOOTH);
    icon = new ImageIcon(newImg);
    mainTitle = new JLabel("Welcome to the Toolshop");
    mainTitle.setFont(new Font("Serif", Font.BOLD, 24));
    mainTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    toolLogo = new JLabel();
    toolLogo.setIcon(icon);
    toolLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
  }

  /**
   * add everything to panels
   */
  public void addPanels() {
    add(northPanel, BorderLayout.NORTH);
    add(mainPanel, BorderLayout.CENTER);
    add(southPanel, BorderLayout.SOUTH);
  }

  public void addSubPanels() {
    mainPanel.add(idPanel);
    mainPanel.add(passwordPanel);
    northPanel.add(toolLogo);
    northPanel.add(mainTitle);
    southPanel.add(loginBtn);
    southPanel.add(closeBtn);
  }

  /**
   * set login credential text and fields
   */
  public void setCredentialsText() {
    JLabel idText = new JLabel("ID: ");
    idText.setFont(new Font("SansSerif", Font.PLAIN, 20));
    idPanel.add(idText);
    JLabel passwordText = new JLabel("Password: ");
    passwordText.setFont(new Font("SansSerif", Font.PLAIN, 20));
    passwordPanel.add(passwordText);
    IDTextField = new JTextField();
    IDTextField.setPreferredSize(new Dimension(300, 40));
    Font idFont = new Font("SansSerif", Font.PLAIN, 20);
    IDTextField.setFont(idFont);
    passwordTextField = new JPasswordField();
    passwordTextField.setPreferredSize(new Dimension(300, 40));
    Font passwordFont = new Font("SansSerif", Font.PLAIN, 20);
    passwordTextField.setFont(passwordFont);
    idPanel.add(IDTextField);
    passwordPanel.add(passwordTextField);
  }

  /**
   * opens the login window in the center of page
   */
  public void centreWindow() {
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dim.getWidth() - getWidth()) / 2);
    int y = (int) ((dim.getHeight() - getHeight()) / 2);
    setLocation(x, y);
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
    return new String(passwordTextField.getPassword());
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
   * adds a ActionListener to the close application button to close the window
   * upon having been clicked
   * 
   * @param l ActionListener used to handle clicking close application
   */
  public void addCloseAppListener(ActionListener l) {
    closeBtn.addActionListener(l);
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