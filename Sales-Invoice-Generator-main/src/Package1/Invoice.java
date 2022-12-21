package Package1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Invoice {
    public static int Invoice_number = 0 ;
    private String Date;
    private String CustomerName;
    private float TotalPrice = 0 ;
    private int ItemIndex = 0;
    ArrayList<Item> Items = new ArrayList<Item>();
    private int InvoiceNumber = 0;

    public ArrayList<Item> getItems() {
        return Items;
    }

    public int getInvoiceNumber() {
        return InvoiceNumber;
    }

    public Invoice(String Name , String date)
    {
        Invoice_number++;
        this.CustomerName = Name;
        this.Date = date;
        this.InvoiceNumber=Invoice_number;

    }

    public String getCustomerName() {
        return CustomerName;
    }

    public float getTotalPrice() {
        return TotalPrice;
    }

    public void calculateTotalPrice()
    {
        float temp = 0;
        for (int i = 0 ; i<Items.size() ; ++i)
        {
            temp+=Items.get(i).getTotal();

        }
        this.TotalPrice = temp;

    }

    public static int getInvoice_number() {
        return Invoice_number;
    }

    public String getDate() {
        return Date;
    }

    public float getTotal_price() {
        return TotalPrice;
    }

    public void addItems (String Name , int Price , int Count)
    {
        Items.add(new Item(Name , Price , Count)) ;
        ItemIndex++;
    }
    public void printItems ()
    {
        System.out.println("Customer Name : " + CustomerName );
        System.out.println("Date  : " + Date );
        System.out.println("Total  : " + TotalPrice );

        for (int i = 0 ; i< Items.size();++i)
        {
            Items.get(i).printItem();
        }
    }

}
