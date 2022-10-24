
package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Invoice {
    private int invoiceNum;
    private String customerName;
    private Date invoiceDate;
    private ArrayList<InvoiceItem> lines;
    private DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

    public Invoice(int invoiceNum, String customerName, Date invoiceDate) {
        this.invoiceNum = invoiceNum;
        this.customerName = customerName;
        this.invoiceDate = invoiceDate;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public int getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<InvoiceItem> getLines() {
        if (lines == null) {
            lines = new ArrayList<>();
        }
        return lines;
    }

    public void setLines(ArrayList<InvoiceItem> lines) {
        this.lines = lines;
    }

    public double getInvoiceTotal() {
        double total = 0.0;
        for (int i = 0; i < getLines().size(); i++) {
            total += getLines().get(i).getLineTotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return invoiceNum + ", " + df.format(invoiceDate) + ", " + customerName + "";
    }

}
    

