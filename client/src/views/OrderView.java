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
 * OrderView
 */
public class OrderView extends JFrame {

  JLabel orderTitle;
  JTextArea orderTextArea;
  JScrollPane textAreaScrollable;
  JButton closeBtn;

  public OrderView(String name) {
    super(name);
    initTitle();
    initTextArea();
    initCloseButton();
    addAllToFrame();
  }

  private void initTitle() {
    orderTitle = new JLabel("List of Orders");
    orderTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
    orderTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
  }

  private void initTextArea() {
    orderTextArea = new JTextArea(30, 60);
    orderTextArea.setEditable(false);
    textAreaScrollable = new JScrollPane(orderTextArea);
    textAreaScrollable.setAlignmentX(Component.CENTER_ALIGNMENT);
  }

  public void setTextArea(String s) {
    orderTextArea.setText(s);
  }

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