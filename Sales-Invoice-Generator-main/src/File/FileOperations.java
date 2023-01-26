package File;

import Package1.Invoice;
import Package1.Item;
import GUI.*;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


public class FileOperations  {
    public static FileWriter InvoiceHeader ;
    public static FileWriter InvoiceLine ;
    public static String FilePath;
    public FileOperations()  {
        try {
            InvoiceHeader = new FileWriter("InvoiceHeader.csv" , true);
            InvoiceLine = new FileWriter("InvoiceLine.csv" , true);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static  void saveFile()
    {
        PrintWriter writer = new PrintWriter(InvoiceHeader);
        PrintWriter writer2 = new PrintWriter(InvoiceLine);
        writer.print("");
        writer2.print("");


        try {
            for (int i=1;i<createInvoice.Invoices.size();++i)
            {
                Invoice I = createInvoice.Invoices.get(i);
                InvoiceHeader.append(I.getInvoiceNumber() + ",");
                InvoiceHeader.append(I.getCustomerName()+ ",");
                InvoiceHeader.append(I.getDate()+ ",");
                InvoiceHeader.append(I.getTotalPrice()+ ",");
                InvoiceHeader.append("\n");
                ////////////// Items

                for (int j=0;j<I.getItems().size();++j)
                {
                    InvoiceLine.append(I.getInvoiceNumber() + ",");
                    Item item = I.getItems().get(j);
                    InvoiceLine.append(item.getName()+","+item.getPrice()+","+item.getCount()+","+item.getTotal() + "\n");

                }

            }


                InvoiceHeader.close();
                InvoiceLine.close();
                writer.close();
                writer2.close();



        }
        catch (Exception e) {
            e.getStackTrace();
        }

    }
    public static void loadFile (File InvoiceHeader ,File InvoiceLine )
    {
        try {
            createInvoice.Invoices.add(null);
            File myObj = InvoiceHeader;
            Scanner myReader = new Scanner(myObj);
            Invoice.setInvoice_number(0);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String [] NData = data.split(",");
                createInvoice.loadInvoice(NData[1],NData[2] ,Float.parseFloat(NData[3]) );
                System.out.println("Invoice Number : " + NData[0] +" Invoice Name : " + NData[1] +" Customer Date : " + NData[2] + " Total: " +NData[3] );
            }
            myReader.close();
            File myObj2 = InvoiceLine;
            Scanner myReader2 = new Scanner(myObj2);
            int Index = 1 ;
            while (myReader2.hasNextLine()) {
                String data = myReader2.nextLine();
                String [] NData = data.split(",");
                createItem.loadItem(Integer.parseInt(NData[0]),NData[1],NData[2],NData[3] ,NData[4]);

                System.out.println("Invoice Number : " + NData[0] +" Item Name : " + NData[1] +" Price : " + NData[2]+" Count : " + NData[3] + " :" +NData[4]);
            }
            myReader2.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }
}
