package views;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * The GUI that shows the list of Orders made.  
 * @author Navjot Brar, Jofred Cayabyab, Tyler Lam
 * @since April 11, 2019
 * @version 1.0
 */
public class OrderView extends JFrame {

  /**
   * The title for the order
   */
  JLabel orderTitle;
  /**
   * Where the orders will be displayed
   */
  JTextArea orderTextArea;
  /**
   * Scroll area that will scroll through the orders
   */
  JScrollPane textAreaScrollable;
  /**
   * Button to close the window
   */
  JButton closeBtn;

  /**
   * Constructs the order view
   * @param name the name of the frame
   */
  public OrderView(String name) {
    super(name);
    initTitle();
    initTextArea();
    initCloseButton();
    addAllToFrame();
  }

  /**
   * Initialiazes the title for the window
   */
  private void initTitle() {
    orderTitle = new JLabel("List of Orders");
    orderTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
    orderTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
  }

  /**
   * Initializes the text area for the window
   */
  private void initTextArea() {
    orderTextArea = new JTextArea(30, 60);
    orderTextArea.setEditable(false);
    textAreaScrollable = new JScrollPane(orderTextArea);
    textAreaScrollable.setAlignmentX(Component.CENTER_ALIGNMENT);
  }

  /**
   * Sets the text in the text area to the string passed in to the method
   * @param s the text to be displayed
   */
  public void setTextArea(String s) {
    orderTextArea.setText(s);
  }

  /**
   * Initializes the close button for the window
   */
  private void initCloseButton() {
    closeBtn = new JButton("Close window");
    closeBtn.setPreferredSize(new Dimension(500, 60));
    closeBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
    closeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

    closeBtn.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
      }
    });
  }

  /**
   * Adds all properties and objects to the frame
   */
  private void addAllToFrame() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    panel.add(orderTitle);
    panel.add(textAreaScrollable);
    panel.add(closeBtn);
    add(panel);
    pack();
  }
}