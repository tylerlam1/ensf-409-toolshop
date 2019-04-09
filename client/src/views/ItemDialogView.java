package views;

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
    private JTextField descriptionField;

    /**
     * text field for the item supplier ID
     */
    private JTextField supplierIdField;

    /**
     * Label for displaying messages
     */
    private JLabel label;
    /**
     * text field for item price
     */
    private JTextField priceField;

    /**
     * text field for item quantity
     */
    private JTextField quantityField;

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
        return quantityField.getText();
    }

    /**
     * get the item price from textfield
     * 
     * @return the item price
     */
    public String getPrice() {
        return priceField.getText();
    }

    /**
     * get the supplierId from textfield
     * 
     * @return the item supplier ID
     */
    public String getSupplierId() {
        return supplierIdField.getText();
    }

    /**
     * get the item description from textfield
     * 
     * @return the tool Description
     */
    public String getDescription() {
        return descriptionField.getText();
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
     * 
     * @param name the name in the Item Dialog
     */
    public ItemDialogView(String name) {
        super(name);
        descriptionField = new JTextField(6);
        supplierIdField = new JTextField(6);
        quantityField = new JTextField(3);
        priceField = new JTextField(5);

        createItemBtn = new JButton("Create Item");
        returnBtn = new JButton("Exit Dialog Window");

        addCloseListeners();

        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        JPanel firstPanel = new JPanel(new FlowLayout());
        JPanel secondPanel = new JPanel(new FlowLayout());
        JPanel thirdPanel = new JPanel(new FlowLayout());
        JPanel fourthPanel = new JPanel(new FlowLayout());

        label = new JLabel("Create a New Item");
        firstPanel.add(label);
        secondPanel.add(new JLabel("Item Description: "));
        secondPanel.add(descriptionField);
        secondPanel.add(new JLabel("Item Supplier ID: "));
        secondPanel.add(supplierIdField);
        thirdPanel.add(new JLabel("Item Quantity: "));
        thirdPanel.add(quantityField);
        thirdPanel.add(new JLabel("Item Price: "));
        thirdPanel.add(priceField);

        fourthPanel.add(createItemBtn);
        fourthPanel.add(returnBtn);

        itemPanel.add(firstPanel);
        itemPanel.add(secondPanel);
        itemPanel.add(thirdPanel);
        itemPanel.add(fourthPanel);

        add(itemPanel);
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