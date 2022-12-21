package GUI;

import Package1.Invoice;
import Package1.Item;
import Package1.Main;
import org.w3c.dom.Text;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Home extends JFrame implements ActionListener {
    private JPanel LeftPanal ;
    public static DefaultTableModel InvoiceModel;
    public static DefaultTableModel ItemModel;

    public static JTable InvoiceTable ;
    private String [] InvoiceTableCols = {"No." ,"Date" , "Customer" , "Total"};
    private Object [][] InvoiceTableData = {

    } ;
    JButton CreateInvoice ;
    JButton DeleteInvoice ;

    ////////////// Right panel
    private JPanel RightPanal ;
    private JPanel RightComponent ;
    private JLabel NumLabel ;
    private static JTextField NumField;
    private JLabel DateLabel ;
    private static JTextField DateField;
    private JLabel CustomerLabel ;
    private static JTextField CustomerField;
    private JLabel TotalLabel ;
    private static JTextField TotalField;
    private JLabel InvoiceItems ;
    private JTable ItemsTable ;
    private JTable ItemsTableTemp ;
    private String [] ItemsTableCols = {"No." ,"Item Name" , "Item Price" , "Count" , "Item Total"};
    public static Object [][] ItemsTableData = {} ;
    JButton Add ;
    JButton Save ;
    JButton Cancel ;
    public static createInvoice  createInvoice = new createInvoice();
    public static createItem  createItem = new createItem();
    public static int RowIndex;



    public Home()
    {
        super("Sales Invoice Generator");

        setLayout(null);
        ////////////////// Left Panal
        LeftPanal = new JPanel();
        LeftPanal.setBackground(Color.CYAN);
        LeftPanal.setBounds(0,0,500,800);
        InvoiceModel = new DefaultTableModel(InvoiceTableData, InvoiceTableCols);
        InvoiceTable = new JTable(InvoiceModel);
        //InvoiceTable = new JTable(InvoiceTableData,InvoiceTableCols);
        CreateInvoice =new JButton("Create Invoice");
        DeleteInvoice =new JButton("Delete Invoice");
        CreateInvoice.addActionListener(this);
        DeleteInvoice.addActionListener(this);
        LeftPanal.add(new JScrollPane(InvoiceTable));
        LeftPanal.add(CreateInvoice);
        LeftPanal.add(DeleteInvoice);
        add(LeftPanal);

        ///////////////// Right Panal
        RightPanal = new JPanel();
        RightComponent = new JPanel();
        RightComponent.setLayout(new GridLayout(5,1) );
        RightPanal.setBackground(Color.LIGHT_GRAY);
        RightPanal.setBounds(500,0,500,800);
        NumLabel = new JLabel("Invoice Number : ");
        NumLabel.setLocation(450,200);
        NumField = new JTextField(15);
        NumField.setEditable(false);
        DateLabel = new JLabel("Invoice Date : ");
        DateField = new JTextField(15);
        CustomerLabel = new JLabel("Customer Name : ");
        CustomerField = new JTextField(15);
        TotalLabel = new JLabel("Total : ");
        TotalField = new JTextField(15);
        InvoiceItems = new JLabel("Invoice Items :");
        ItemModel = new DefaultTableModel(ItemsTableData,ItemsTableCols);
        ItemsTable = new JTable(ItemModel);


        Save = new JButton("Save");
        Add = new JButton("Add");
        Cancel = new JButton("Load");
        Save.addActionListener(this);
        Add.addActionListener(this);
        Cancel.addActionListener(this);
        RightComponent.add(NumLabel);
        RightComponent.add(NumField);
        RightComponent.add(DateLabel);
        RightComponent.add(DateField);
        RightComponent.add(CustomerLabel);
        RightComponent.add(CustomerField);
        RightComponent.add(TotalLabel);
        RightComponent.add(TotalField);
        RightComponent.add(InvoiceItems);
        RightPanal.add(RightComponent);
        RightPanal.add(new JScrollPane(ItemsTable));
        RightPanal.add(Add);
        RightPanal.add(Cancel);
        RightPanal.add(Save);
        add(RightPanal);


        setSize(1000,800);
        setLocation(50,50);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        InvoiceTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RowIndex =InvoiceTable.getSelectedRow();
                removeTable();
                getItems(RowIndex);


            }
        });




    }


    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource().equals(Add))
        {
            createItem.setVisible(true);

        }
        if (e.getSource().equals(Save))
        {

            for (int i = 0;i<Invoice.Invoice_number;++i)
            {
                System.out.println(i);
                Main.saveFile(GUI.createInvoice.Invoices.get(i));
            }

        }
        else if (e.getSource().equals(Cancel) )
        {
            Main.loadFile();

        }
        else if (e.getSource().equals(CreateInvoice) )
        {

            createInvoice.setVisible(true);
//

        }
        else if (e.getSource().equals(DeleteInvoice) )
        {
            deleteInvoice(RowIndex);

        }



    }
    public void removeTable()
    {
        ItemModel.setRowCount(0);

    }
    public void getItems (int Index)
    {

        Invoice I = GUI.createInvoice.Invoices.get(Index);
        GUI.createInvoice.InvoiceTemp=I;
        int Num=I.Invoice_number;
        NumField.setText(Integer.toString(Num));
        ArrayList<Item> Items = I.getItems();
        for (int i = 0 ; i<Items.size();++i)
        {
            String ItemName= Items.get(i).getName();
            int ItemPrice= Items.get(i).getPrice();
            int ItemCount= Items.get(i).getCount();
            int ItemTotal= Items.get(i).getTotal();
            Object[] newRecord = {i , ItemName, ItemPrice , ItemCount ,ItemTotal};
            ItemModel.addRow(newRecord);
        }
        update(Num,I.getDate(),I.getCustomerName(),I.getTotalPrice());



    }
    public static void update (int Index , String Date , String Name , float total)
    {
        NumField.setText(Integer.toString(Index));
        DateField.setText(Date);
        CustomerField.setText(Name);
        TotalField.setText(Float.toString(total));


    }
    public void deleteInvoice(int Index)
    {
        GUI.createInvoice.Invoices.remove(Index);
        InvoiceModel.removeRow(Index);



    }

}
