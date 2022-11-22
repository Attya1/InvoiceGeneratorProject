/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javax.swing.*;
import java.awt.*;


public class InvoiceItemMessage extends JDialog {
    private JTextField ItemNameField;
    private JTextField ItemCountField;
    private JTextField ItemPriceField;
    private JLabel ItemNameLabel;
    private JLabel ItemCountLabel;
    private JLabel ItemPriceLabel;
    private JButton OKButton;
    private JButton CancelButton;

    public InvoiceItemMessage(UiComponents frame) {
        ItemNameField = new JTextField(20);
        ItemNameLabel = new JLabel("Item Name");

        ItemCountField = new JTextField(20);
        ItemCountLabel = new JLabel("Item Count");

        ItemPriceField = new JTextField(20);
        ItemPriceLabel = new JLabel("Item Price");

        OKButton = new JButton("OK");
        CancelButton = new JButton("Cancel");

        OKButton.setActionCommand("NewLineOK");
        CancelButton.setActionCommand("NewLineCancel");
        OKButton.addActionListener(frame.getHandle());
        CancelButton.addActionListener(frame.getHandle());
        setLayout(new GridLayout(4, 2));

        add(ItemNameLabel);
        add(ItemNameField);
        add(ItemCountLabel);
        add(ItemCountField);
        add(ItemPriceLabel);
        add(ItemPriceField);
        add(OKButton);
        add(CancelButton);
        pack();


    }

    public JTextField getItemNameField() {
        return ItemNameField;
    }

    public JTextField getItemCountField() {
        return ItemCountField;
    }

    public JTextField getItemPriceField() {
        return ItemPriceField;
    }


}
