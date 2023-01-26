package GUI;
import File.FileOperations;
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
import java.io.File;
import java.util.ArrayList;

public class Home extends JFrame implements ActionListener {
    private JPanel LeftPanal ;
    public static DefaultTableModel InvoiceModel;
    public static DefaultTableModel ItemModel;

    public static JTable InvoiceTable ;
    private String [] InvoiceTableCols = {"No." ,"Customer" , "Date" , "Total"};
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
    JButton DeleteItem ;
    JButton Save ;
    JButton Load ;
    public static createInvoice  createInvoice = new createInvoice();
    public static createItem  createItem = new createItem();
    public static int RowIndex;
    public static int ItemIndex;
    public FileOperations fileOperations = new FileOperations()  ;
    private boolean Read = true;
    JFileChooser fileChooser1;
    JFileChooser fileChooser2;
    File Path1,Path2;
    public static boolean selected =false;




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
        Add = new JButton("Add Item");
        DeleteItem = new JButton("Delete Item");
        Load = new JButton("Load");
        Save.addActionListener(this);
        Add.addActionListener(this);
        DeleteItem.addActionListener(this);
        Load.addActionListener(this);
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
        RightPanal.add(DeleteItem);
        RightPanal.add(Load);
        RightPanal.add(Save);
        add(RightPanal);
        fileChooser1 =new JFileChooser();
        fileChooser1.setCurrentDirectory(new File("."));
        fileChooser2 =new JFileChooser();
        fileChooser2.setCurrentDirectory(new File("."));


        setSize(1000,800);
        setLocation(50,50);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        InvoiceTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RowIndex =InvoiceTable.getSelectedRow();
                System.out.println("Row index : "+ RowIndex);
                removeTable();
                getItems(RowIndex+1);
                selected= true;
                GUI.createInvoice.Selected_Invoice=RowIndex+1;




            }
        });
        ItemsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ItemIndex = ItemsTable.getSelectedRow();
                System.out.println("Item index : "+ ItemIndex);


            }
        });




    }


    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource().equals(Add) && GUI.createInvoice.Selected_Invoice!=0)
        {
            createItem.setVisible(true);

        }
        if (e.getSource().equals(Save))
        {
            Read=true;
            FileOperations.saveFile();

        }
        else if (e.getSource().equals(Load) && Read==true)
        {
            Home.InvoiceModel.setRowCount(0);
            Home.ItemModel.setRowCount(0);
            GUI.createInvoice.Invoices.clear();
            int res = fileChooser1.showDialog(null,"Choose Invoice Header");
            if (res== JFileChooser.APPROVE_OPTION)
            {
                Path1 = new File(fileChooser1.getSelectedFile().getAbsolutePath());
            }
             res = fileChooser1.showDialog(null,"Choose Invoice Line ");
            if (res== JFileChooser.APPROVE_OPTION)
            {
                Path2 = new File(fileChooser1.getSelectedFile().getAbsolutePath());
            }
            fileOperations.loadFile(Path1,Path2);
            Read=false;



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
        else if (e.getSource().equals(DeleteItem) )
        {
            deleteItem(ItemIndex);

        }



    }
    public void removeTable()
    {
        ItemModel.setRowCount(0);

    }
    public void getItems (int Index)
    {

        Invoice I = GUI.createInvoice.Invoices.get(Index);
        NumField.setText(Integer.toString(Index+1));
        TotalField.setText(Float.toString(I.getTotalPrice()));
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
        update(Index+1,I.getDate(),I.getCustomerName(),I.getTotal_price());



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
        GUI.createInvoice.Invoices.remove(Index+1);
        InvoiceModel.removeRow(Index);

    }
    public void deleteItem(int index)
    {
        int SelectInvoice= GUI.createInvoice.Selected_Invoice;
        System.out.println("selected : " + SelectInvoice );
        System.out.println("Index : " + index );
        GUI.createInvoice.Invoices.get(SelectInvoice).Items.remove(index);
        GUI.createInvoice.Invoices.get(SelectInvoice).calculateTotalPrice();
       InvoiceModel.setValueAt(createInvoice.Invoices.get(SelectInvoice).getTotal_price(),SelectInvoice-1,3);


        ItemModel.removeRow(index);
    }

}
