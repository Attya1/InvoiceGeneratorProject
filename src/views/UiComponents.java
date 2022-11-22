package views;

import controller.InvoiceActionHandling;
import controller.InvoiceTableSelector;
import models.Invoice;
import models.InvoiceItem;
import models.InvoiceTableModel;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class UiComponents extends javax.swing.JFrame{

    public UiComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        invoiceTable = new javax.swing.JTable();
        invoiceTable.getSelectionModel().addListSelectionListener(invoiceTableSelector);
        newInvoiceButton = new javax.swing.JButton();
        newInvoiceButton.addActionListener(handle);
        deleteInvoiceButton = new javax.swing.JButton();
        deleteInvoiceButton.addActionListener(handle);
        newItemButton = new javax.swing.JButton();
        newItemButton.addActionListener(handle);
        deleteItemButton = new javax.swing.JButton();
        deleteItemButton.addActionListener(handle);
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        itemTable = new javax.swing.JTable();
        invoiceNumberLabel = new javax.swing.JLabel();
        invDateLabel = new javax.swing.JLabel();
        customerNameLabel = new javax.swing.JLabel();
        invTotalLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileContextMenu = new javax.swing.JMenu();
        loadFileMenuItem = new javax.swing.JMenuItem();
        loadFileMenuItem.addActionListener(handle);
        saveFileItem = new javax.swing.JMenuItem();
        saveFileItem.addActionListener(handle);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Invoices Table");

        invoiceTable.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{

        }, new String[]{

        }));
        jScrollPane1.setViewportView(invoiceTable);

        newInvoiceButton.setText("Create New Invoice");

        deleteInvoiceButton.setText("Delete Invoice");

        newItemButton.setText("New Line");
        newItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                /*NewItemButtonActionPerformed(evt);*/
            }
        });

        deleteItemButton.setText("Delete Line");

        jLabel2.setText("Invoice Number");

        jLabel3.setText("Invoice Date");

        jLabel4.setText("Customer Name");

        jLabel5.setText("Invoice Total");

        jLabel6.setText("Invoice Items");

        itemTable.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{

        }, new String[]{

        }));
        jScrollPane2.setViewportView(itemTable);

        invoiceNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        fileContextMenu.setText("File");

        loadFileMenuItem.setText("Load File");
        fileContextMenu.add(loadFileMenuItem);

        saveFileItem.setText("Save File");
        fileContextMenu.add(saveFileItem);

        jMenuBar1.add(fileContextMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel1)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(layout.createSequentialGroup().addGap(60, 60, 60).addComponent(newInvoiceButton).addGap(101, 101, 101).addComponent(deleteInvoiceButton))).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(167, 167, 167).addComponent(invTotalLabel)).addGroup(layout.createSequentialGroup().addGap(45, 45, 45).addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(layout.createSequentialGroup().addGap(157, 157, 157).addComponent(newItemButton).addGap(84, 84, 84).addComponent(deleteItemButton)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(54, 54, 54).addComponent(jLabel6).addGap(19, 19, 19)).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel3).addComponent(jLabel2).addComponent(jLabel4).addComponent(jLabel5)))).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(30, 30, 30).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(invDateLabel).addComponent(customerNameLabel))).addGroup(layout.createSequentialGroup().addGap(18, 18, 18).addComponent(invoiceNumberLabel))))))).addContainerGap(154, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(jLabel2).addComponent(invoiceNumberLabel)).addGap(13, 13, 13).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(newInvoiceButton).addComponent(deleteInvoiceButton)).addGap(34, 34, 34)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel3).addComponent(invDateLabel)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel4).addComponent(customerNameLabel)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel5).addComponent(invTotalLabel)).addGap(18, 32, Short.MAX_VALUE).addComponent(jLabel6).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(newItemButton).addComponent(deleteItemButton)).addContainerGap()))));

        pack();
    }

    private javax.swing.JLabel customerNameLabel;
    private javax.swing.JButton deleteInvoiceButton;
    private javax.swing.JButton deleteItemButton;
    private javax.swing.JMenu fileContextMenu;
    private javax.swing.JTable invoiceTable;
    private javax.swing.JLabel invDateLabel;
    private javax.swing.JLabel invoiceNumberLabel;
    private javax.swing.JLabel invTotalLabel;
    private javax.swing.JTable itemTable;
    private javax.swing.JMenuItem loadFileMenuItem;
    private javax.swing.JButton newInvoiceButton;
    private javax.swing.JButton newItemButton;
    private javax.swing.JMenuItem saveFileItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    private InvoiceActionHandling handle = new InvoiceActionHandling(this);
    private ArrayList<Invoice> invoicesArray;
    private ArrayList<InvoiceItem> itemsArray;
    private InvoiceTableModel invoiceTableModel;
    public static SimpleDateFormat DateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private InvoiceTableSelector invoiceTableSelector = new InvoiceTableSelector(this);

    public ArrayList<InvoiceItem> getItemsArray() {
        return itemsArray;
    }

    public void setItemsArray(ArrayList<InvoiceItem> LinesArray) {
        this.itemsArray = LinesArray;
    }


    public ArrayList<Invoice> getInvoicesArray() {
        return invoicesArray;
    }

    public void setInvoicesArray(ArrayList<Invoice> InvoicesArray) {
        this.invoicesArray = InvoicesArray;
    }

    public Invoice getInvoiceObject(int code) {
        for (Invoice inv : invoicesArray) {
            if (inv.getInvoiceNum() == code) {
                return inv;
            }
        }
        return null;
    }

    public InvoiceTableModel getInvoiceTableModel() {
        return invoiceTableModel;
    }

    public void setInvoiceTableModel(InvoiceTableModel HeaderTableModel) {
        this.invoiceTableModel = HeaderTableModel;
    }

    public JTable getInvoiceTable() {
        return invoiceTable;
    }

    public JTable getItemTable() {
        return itemTable;
    }

    public JLabel getCustomerNameLabel() {
        return customerNameLabel;
    }

    public JLabel getInvDateLabel() {
        return invDateLabel;
    }

    public JLabel getInvoiceNumberLabel() {
        return invoiceNumberLabel;
    }

    public JLabel getInvTotalLabel() {
        return invTotalLabel;
    }

    public InvoiceActionHandling getHandle() {
        return handle;
    }


}
