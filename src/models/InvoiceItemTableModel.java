/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;


public class InvoiceItemTableModel extends AbstractTableModel {

    private ArrayList<InvoiceItem> LinesArray;
    private String[] columns={"No.","Item Name","Item Price","Count","Total"};   

    public InvoiceItemTableModel(ArrayList<InvoiceItem> LinesArray) {
        this.LinesArray = LinesArray;
    }
          
    @Override
    public int getRowCount() {
        return LinesArray == null ? 0 :LinesArray.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(LinesArray==null)
            return "";
        else{
        
        InvoiceItem line= LinesArray.get(rowIndex);
        switch(columnIndex){ 
            case 0: return line.getInvoice().getInvoiceNum();
            case 1: return line.getItemName();
            case 2: return line.getItemPrice();
            case 3: return line.getCount();
            case 4: return line.getLineTotal();
            default: return "";
            
        }
      }
    }
     
    @Override
    public String getColumnName(int column){
           return columns[column];
    }
    
}
