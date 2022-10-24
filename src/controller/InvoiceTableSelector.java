/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;



import models.Invoice;
import models.InvoiceItem;
import models.InvoiceItemTableModel;
import views.UiComponents;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;

public class InvoiceTableSelector implements ListSelectionListener {
    
    private UiComponents frame;

    public InvoiceTableSelector(UiComponents frame) {
        this.frame = frame;
    }
    
    
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int SelectedInvoiceIndex = frame.getInvoiceTable().getSelectedRow();
       // System.out.println("Invoice Selected: " + SelectedInvoiceIndex );
        if(SelectedInvoiceIndex!=-1){
       Invoice SelectedInv= frame.getInvoicesArray().get(SelectedInvoiceIndex);
       ArrayList<InvoiceItem> lines = SelectedInv.getLines();
       InvoiceItemTableModel LineTableModel = new InvoiceItemTableModel(lines);
       frame.setItemsArray(lines);
       frame.getItemTable().setModel(LineTableModel);
       frame.getCustomerNameLabel().setText(SelectedInv.getCustomerName());
       frame.getInvoiceNumberLabel().setText(""+SelectedInv.getInvoiceNum());
       frame.getInvTotalLabel().setText(""+SelectedInv.getInvoiceTotal());
       frame.getInvDateLabel().setText(UiComponents.DateFormat.format(SelectedInv.getInvoiceDate()));
        System.out.println(SelectedInv + " " + SelectedInv.getLines());
    }
        
    }
    
}
