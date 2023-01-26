package GUI;

import Package1.Invoice;
import Package1.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class createInvoice extends JFrame implements ActionListener {
    private static JLabel Date, CustomerName;
    private static JTextField CustomerName_f, Date_f;
    private static JButton Create;
    private static JButton Cancel;
    public static ArrayList<Invoice> Invoices = new ArrayList<Invoice>();
    public static int Selected_Invoice = 0 ;
    public  static Invoice InvoiceTemp;

    public createInvoice() {
        super("Create Invoice");
        // creating a JPanel class
        JPanel panel = new JPanel();
        panel.setLayout(null);
        setLocation(new Point(500, 300));
        add(panel);
        setSize(new Dimension(400, 200));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CustomerName = new JLabel("Customer Name : ");
        CustomerName.setBounds(50, 30, 100, 20);
        panel.add(CustomerName);
        CustomerName_f = new JTextField();
        CustomerName_f.setBounds(150, 30, 193, 28);
        panel.add(CustomerName_f);
        ///////////////////////////////////////////////////
        Date = new JLabel("Date : ");
        Date.setBounds(50, 70, 40, 20);
        panel.add(Date);
        Date_f = new JTextField();
        Date_f.setBounds(150, 70, 193, 28);
        panel.add(Date_f);
        ///////////////////////////////////////////////////////////
        Create = new JButton("Create");
        Create.setBounds(100, 110, 90, 25);
        Create.setForeground(Color.WHITE);
        Create.setBackground(Color.BLACK);
        Create.addActionListener(this);
        panel.add(Create);

        Cancel = new JButton("Cancel");
        Cancel.setBounds(200, 110, 90, 25);
        Cancel.setForeground(Color.WHITE);
        Cancel.setBackground(Color.BLACK);
        Cancel.addActionListener(this);
        panel.add(Cancel);
        Invoices.add(null);
    }

    public  void addInvoice() {
        String Name = CustomerName_f.getText().toString();
        String date = Date_f.getText().toString();
        InvoiceTemp = new Invoice(Name,date);
        Selected_Invoice =  InvoiceTemp.Invoice_number;
        Invoices.add(Selected_Invoice,InvoiceTemp);
        Object[] newRecord = { InvoiceTemp.Invoice_number, Name, date , 0};
        Home.InvoiceModel.addRow(newRecord);
        Home.update(InvoiceTemp.Invoice_number,Date_f.getText(),CustomerName_f.getText(),0);
        CustomerName_f.setText(null);
        Date_f.setText(null);
        Home.createInvoice.setVisible(false);
        GUI.Home.ItemModel.setRowCount(0);

    }
    public static void loadInvoice(String Name , String date , Float Total)
    {


        InvoiceTemp = new Invoice(Name,date);
        Invoices.add(InvoiceTemp);
        Object[] newRecord = { InvoiceTemp.Invoice_number, Name, date , Total};
        Home.InvoiceModel.addRow(newRecord);
        Home.update(InvoiceTemp.Invoice_number,Date_f.getText(),CustomerName_f.getText(),Total);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(Create)) {
            addInvoice();

        } else if (e.getSource().equals(Cancel)) {
            setVisible(false);

        }
    }
}
