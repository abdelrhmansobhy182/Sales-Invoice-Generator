package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class createItem extends JFrame implements ActionListener {
    private static JLabel ItemName, Price , Count;
    private static JTextField Item_T , Price_T , Count_T;
    private static JButton Create;
    private static JButton Cancel;

    public createItem()
    {
        super("Create Item");
        // creating a JPanel class
        JPanel panel = new JPanel();
        panel.setLayout(null);
        setLocation(new Point(500, 300));
        add(panel);
        setSize(new Dimension(400, 400));
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        ItemName = new JLabel("Item Name : ");
        ItemName.setBounds(50, 50, 100, 20);
        panel.add(ItemName);
        Item_T = new JTextField();
        Item_T.setBounds(150, 50, 193, 28);
        panel.add(Item_T);
        ///////////////////////////////////////////////////
        Price = new JLabel("Price : ");
        Price.setBounds(50, 100, 70, 20);
        panel.add(Price);
        Price_T = new JTextField();
        Price_T.setBounds(150, 100, 193, 28);
        panel.add(Price_T);
        ///////////////////////////////////////////////////
        Count = new JLabel("Count : ");
        Count.setBounds(50, 150, 70, 20);
        panel.add(Count);
        Count_T = new JTextField();
        Count_T.setBounds(150, 150, 193, 28);
        panel.add(Count_T);
        ///////////////////////////////////////////////////////////
        Create = new JButton("Create");
        Create.setBounds(100, 200, 90, 25);
        Create.setForeground(Color.WHITE);
        Create.setBackground(Color.BLACK);
        Create.addActionListener(this);
        panel.add(Create);

        Cancel = new JButton("Cancel");
        Cancel.setBounds(200, 200, 90, 25);
        Cancel.setForeground(Color.WHITE);
        Cancel.setBackground(Color.BLACK);
        Cancel.addActionListener(this);
        panel.add(Cancel);


    }

    public void createItem()
    {
        String Item = Item_T.getText().toString();
        String Price = Price_T.getText().toString();
        String Count = Count_T.getText().toString();
        int CurrentInvoice=0;
        if (Home.selected)
            CurrentInvoice = Home.RowIndex +1 ;
        else CurrentInvoice = createInvoice.Selected_Invoice;

        int ItemIndex= createInvoice.Invoices.get(CurrentInvoice).getItems().size();
        createInvoice.Invoices.get(CurrentInvoice).addItems(Item,Integer.parseInt(Price),Integer.parseInt(Count));
        int total = Integer.parseInt(Price)*Integer.parseInt(Count);
        Object[] newRecord = {ItemIndex , Item, Integer.parseInt(Price) , Integer.parseInt(Count) ,total};
        Home.ItemModel.addRow(newRecord);
        createInvoice.Invoices.get(CurrentInvoice).calculateTotalPrice();
        Home.InvoiceModel.setValueAt(createInvoice.Invoices.get(CurrentInvoice).getTotal_price(),CurrentInvoice-1,3);
        Item_T.setText(null);
        Price_T.setText(null);
        Count_T.setText(null);
        Home.createItem.setVisible(false);


    }
    public static void loadItem(int InvoiceNumber , String Item , String Price , String Count , String Total)
    {


        //System.out.println("Load Invoice : " + (InvoiceNumber-1) );
        createInvoice.Invoices.get(InvoiceNumber).addItems(Item,Integer.parseInt(Price),Integer.parseInt(Count));
        //createInvoice.InvoiceTemp.addItems(Item,Integer.parseInt(Price),Integer.parseInt(Count));
        Object[] newRecord = {'0' , Item, Integer.parseInt(Price) , Integer.parseInt(Count) ,Integer.parseInt(Total)};
        Home.ItemModel.addRow(newRecord);
        //createInvoice.InvoiceTemp.calculateTotalPrice();
       // Home.InvoiceModel.setValueAt(createInvoice.InvoiceTemp.getTotal_price(),createInvoice.InvoiceTemp.Invoice_number-1,3);

    }





    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(Create)) {
            createItem();
//            for (int i = 1 ; i< createInvoice.Invoices.size();++i)
//            {
//                System.out.println("Invoice Number : " + createInvoice.Invoices.get(i).getInvoiceNumber());
//                createInvoice.Invoices.get(i).printItems();
//            }


        } else if (e.getSource().equals(Cancel)) {
            setVisible(false);

        }

    }
}
