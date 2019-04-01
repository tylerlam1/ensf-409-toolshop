package client.views;

import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.*;

/**
 * Displays the Dialog Box view GUI for the Delete/Create item. Necessary for a
 * multi-input dialog box
 * 
 * @author Navjot Brar, Jofred Cayabyab and Tyler Lam
 * @version 1.0.0
 * @since March 31, 2019
 */
public class ItemDialogView extends JFrame {

    /**
     * text field for the item description
     */
    private JTextField itemDescription;

    /**
     * text field for the item supplier ID
     */
    private JTextField itemSupplierId;

    /**
     * Label for displaying messages
     */
    private JLabel label;
    /**
     * text field for item price
     */
    private JTextField itemPrice;

    /**
     * text field for item quantity
     */
    private JTextField itemQuantity;

    /**
     * the Create Item button
     */
    private JButton createItemBtn;

    /**
     * the return button
     */
    private JButton returnBtn;

    /**
     * get the item quantity from textfield
     * 
     * @return the item quantity
     */
    public String getQuantity() {
        return itemQuantity.getText();
    }

    /**
     * get the item price from textfield
     * 
     * @return the item price
     */
    public String getPrice() {
        return itemPrice.getText();
    }

    /**
     * get the supplierId from textfield
     * 
     * @return the item supplier ID
     */
    public String getSupplierId() {
        return itemSupplierId.getText();
    }

    /**
     * get the item description from textfield
     * 
     * @return the tool Description
     */
    public String getDescription() {
        return itemDescription.getText();
    }

    /**
     * the 'Create item' action listener for the button
     * 
     * @param l the action listener for clicking the 'Create item' button
     */
    public void addCreateItemListener(ActionListener l) {
        createItemBtn.addActionListener(l);
    }

    /**
     * closes the actionlisteners
     */
    private void addCloseListeners() {
        ActionListener l = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        };
        returnBtn.addActionListener(l);
    }

    /**
     * constructs the panels and Create Item window dialog with different components
     */
    public ItemDialogView() {
        itemDescription = new JTextField(6);
        itemSupplierId = new JTextField(6);
        itemQuantity = new JTextField(3);
        itemPrice = new JTextField(5);

        createItemBtn = new JButton("Create Item");
        returnBtn = new JButton("Exit Dialog Window");

        addCloseListeners();

        JPanel createItemPanel = new JPanel();
        createItemPanel.setLayout(new BoxLayout(createItemPanel, BoxLayout.Y_AXIS));
        JPanel firstPanel = new JPanel(new FlowLayout());
        JPanel secondPanel = new JPanel(new FlowLayout());
        JPanel thirdPanel = new JPanel(new FlowLayout());
        JPanel fourthPanel = new JPanel(new FlowLayout());

        JLabel title = new JLabel("Create a New Item");
        firstPanel.add(title);
        secondPanel.add(new JLabel("Item Description: "));
        secondPanel.add(itemDescription);
        secondPanel.add(new JLabel("Item Supplier ID: "));
        secondPanel.add(itemSupplierId);
        thirdPanel.add(new JLabel("Item Quantity: "));
        thirdPanel.add(itemQuantity);
        thirdPanel.add(new JLabel("Item Price: "));
        thirdPanel.add(itemPrice);

        fourthPanel.add(createItemBtn);
        fourthPanel.add(returnBtn);

        createItemPanel.add(firstPanel);
        createItemPanel.add(secondPanel);
        createItemPanel.add(thirdPanel);
        createItemPanel.add(fourthPanel);

        add(createItemPanel);
    }

    /**
     * Change the label text of the dialog.
     * 
     * @param labelText the text to set to label to display
     */
    public void setLabel(String labelText) {
        this.label.setText(labelText);
    }
}