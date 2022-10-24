/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import models.Invoice;
import models.InvoiceItem;
import models.InvoiceItemTableModel;
import models.InvoiceTableModel;
import views.InvoiceItemMessage;
import views.InvoiceMessage;
import views.UiComponents;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class InvoiceActionHandling implements ActionListener {
    private UiComponents frame;
    private InvoiceMessage invoiceMessage;
    private InvoiceItemMessage LineMessage;

    public InvoiceActionHandling(UiComponents frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Action Handling called!!");
        switch (e.getActionCommand()) {
            case "Create New Invoice":
                System.out.println("Create New Invoice");
                CreateNewInvoice();
                break;
            case "Delete Invoice":
                System.out.println("Delete Invoice");
                DeleteInvoice();
                break;
            case "New Item":
                System.out.println("New Item");
                NewItem();
                break;
            case "Delete Item":
                System.out.println("Delete Item");
                DeleteLine();
                break;
            case "Load File":
                System.out.println("Load File");
                LoadFile();
                break;
            case "Save File":
                System.out.println("Save File");
                SaveFile();
                break;

            case "NewInvoiceOK":
                NewInvoiceMessageOK();
                break;
            case "NewInvoiceCancel":
                NewInvoiceMessageCancel();
                break;

            case "NewItemCancel":
                NewItemMessageCancel();
                break;
            case "NewItemOK":
                NewItemMessageOK();

        }

    }

    private void CreateNewInvoice() {
        invoiceMessage = new InvoiceMessage(frame);
        invoiceMessage.setVisible(true);

    }

    private void NewInvoiceMessageCancel() {
        invoiceMessage.setVisible(false);
        invoiceMessage.dispose();
        invoiceMessage = null;
    }

    private void NewInvoiceMessageOK() {
        invoiceMessage.setVisible(false);

        String CustomerName = invoiceMessage.getCustomerNameField().getText();
        String InvoiceDate = invoiceMessage.getInvoiceDateField().getText();
        Date D = new Date();

        try {
            D = UiComponents.DateFormat.parse(InvoiceDate);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(frame, "Correct date format is (dd-mm-yyyy), resetting to today's date.", "Invalid date format", JOptionPane.ERROR_MESSAGE);
        }
        int InvNum = 0;
        for (Invoice inv : frame.getInvoicesArray()) {
            if (inv.getInvoiceNum() > InvNum)
                InvNum = inv.getInvoiceNum();
        }
        InvNum++;

        Invoice Inv = new Invoice(InvNum, CustomerName, D);
        frame.getInvoicesArray().add(Inv);
        frame.getInvoiceTableModel().fireTableDataChanged();
        invoiceMessage.dispose();
        invoiceMessage = null;
    }


    private void DeleteInvoice() {
        int SelectedInvIndex = frame.getInvoiceTable().getSelectedRow();
        if (SelectedInvIndex != -1) {
            frame.getInvoicesArray().remove(SelectedInvIndex);
            frame.getInvoiceTableModel().fireTableDataChanged();
            frame.getItemTable().setModel(new InvoiceItemTableModel(null));

            frame.setItemsArray(null);
            frame.getCustomerNameLabel().setText("");
            frame.getInvoiceNumberLabel().setText("");
            frame.getInvTotalLabel().setText("");
            frame.getInvDateLabel().setText(null);
        }
    }


    private void NewItem() {
        LineMessage = new InvoiceItemMessage(frame);
        LineMessage.setVisible(true);

    }

    private void DeleteLine() {

        int SelectedInvoice = frame.getInvoiceTable().getSelectedRow();

        int SelectedLineIndex = frame.getItemTable().getSelectedRow();
        if (SelectedLineIndex != -1) {
            frame.getItemsArray().remove(SelectedLineIndex);
            InvoiceItemTableModel LineTableModel = (InvoiceItemTableModel) frame.getItemTable().getModel();
            LineTableModel.fireTableDataChanged();
            Invoice InvHeader = frame.getInvoicesArray().get(SelectedInvoice);                   //These statements show that the invoice total changed
            frame.getInvoiceTableModel().fireTableDataChanged();                                             //without having to click on  the invoice header again
            frame.getInvTotalLabel().setText("" + InvHeader.getInvoiceTotal());
        }
        frame.getInvoiceTable().setRowSelectionInterval(SelectedInvoice, SelectedInvoice);


        //System.exit(0);
    }

    private void LoadFile() {
        JFileChooser fc = new JFileChooser();
        try {
            int Result = fc.showOpenDialog(frame);
            if (Result == JFileChooser.APPROVE_OPTION) {
                File HeaderFile = fc.getSelectedFile();
                Path HeaderPath = Paths.get(HeaderFile.getAbsolutePath());
                List<String> invoicesLines = Files.readAllLines(HeaderPath);
                ArrayList<Invoice> Invoices = new ArrayList<>();
                for (String HeaderLine : invoicesLines) {
                    String[] invoiceInfo = HeaderLine.split("@");

                    String[] arr = invoiceInfo[0].split(",");
                    String str1 = arr[0];
                    String str2 = arr[1];
                    String str3 = arr[2];
                    int code = Integer.parseInt(str1);
                    Date InvoiceDate = UiComponents.DateFormat.parse(str2);
                    Invoice header = new Invoice(code, str3, InvoiceDate);
                    Invoices.add(header);
                    if(invoiceInfo.length>1){
                        String[] items = invoiceInfo[1].split("/");

                        ArrayList<InvoiceItem> InvoiceItems = new ArrayList<>();
                        for (String item : items) {
                            String[] itemDetails = item.split(",");
                            String itemNo = itemDetails[0];     // invoice number (int)
                            String itemName = itemDetails[1];     // item name (String)
                            String itemPrice = itemDetails[2];     // item price (double)
                            String itemCount = itemDetails[3];     // count  (int)
                            int InvoiceCode = Integer.parseInt(itemNo);
                            double price = Double.parseDouble(itemPrice);
                            int count = Integer.parseInt(itemCount);

                            InvoiceItem line = new InvoiceItem(header, count, itemName, price);
                            header.getLines().add(line);
                        }

                    }

                    InvoiceTableModel HeaderTableModel = new InvoiceTableModel(Invoices);
                    frame.setInvoiceTableModel(HeaderTableModel);
                    frame.getInvoiceTable().setModel(HeaderTableModel);
                }
                frame.setInvoicesArray(Invoices);
                InvoiceTableModel HeaderTableModel = new InvoiceTableModel(Invoices);
                frame.setInvoiceTableModel(HeaderTableModel);
                frame.getInvoiceTable().setModel(HeaderTableModel);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "The file you used is either missing or invalid.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void SaveFile() {
        ArrayList<Invoice> SaveInvoicesArray = frame.getInvoicesArray();
        JFileChooser fc = new JFileChooser();
        try {
            int res = fc.showSaveDialog(frame);
            if (res == JFileChooser.APPROVE_OPTION) {
                File HeaderSaveFile = fc.getSelectedFile();

                FileWriter HeaderFW = new FileWriter(HeaderSaveFile);
                String Headers = "";
                String lines = "";
                for (Invoice invoice : SaveInvoicesArray) {
                    Headers += invoice.toString();
                    if (invoice.getLines().size() > 0)
                        Headers += '@';

                    for (InvoiceItem line : invoice.getLines()) {
                        lines += line.toString();
                        lines += '/';
                    }
                    Headers+=lines;
                    Headers += '\n';

                }
                File LineSaveFile = fc.getSelectedFile();
                HeaderFW.write(Headers);
                HeaderFW.close();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "The file you used is either invalid or missing", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }

    private void NewItemMessageCancel() {
        LineMessage.setVisible(false);
        LineMessage.dispose();
        LineMessage = null;
    }

    private void NewItemMessageOK() {
        LineMessage.setVisible(false);

        String name = LineMessage.getItemNameField().getText();
        String CountText = LineMessage.getItemCountField().getText();
        String PriceText = LineMessage.getItemPriceField().getText();

        int count = 1;     //initializtion
        double price = 1;

        try {
            count = Integer.parseInt(CountText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Inconvertible Number", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }
        try {
            price = Double.parseDouble(PriceText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Inconvertible Price", "Invalid price format", JOptionPane.ERROR_MESSAGE);
        }

        if (price < 0) price *= -1;   //negative number fix
        if (count < 0) count *= -1;


        int SelectedInvoice = frame.getInvoiceTable().getSelectedRow();
        if (SelectedInvoice != -1) {
            Invoice InvHeader = frame.getInvoicesArray().get(SelectedInvoice);
            InvoiceItem InvLine = new InvoiceItem(InvHeader, count, name, price);
            //InvHeader.getLines().add(InvLine); this statement will also give the same result
            frame.getItemsArray().add(InvLine);
            InvoiceItemTableModel LineTableModel = (InvoiceItemTableModel) frame.getItemTable().getModel();
            LineTableModel.fireTableDataChanged();
            frame.getInvoiceTableModel().fireTableDataChanged();
            frame.getInvTotalLabel().setText("" + InvHeader.getInvoiceTotal());
        }
        frame.getInvoiceTable().setRowSelectionInterval(SelectedInvoice, SelectedInvoice);
        LineMessage.dispose();
        LineMessage = null;
    }


}
