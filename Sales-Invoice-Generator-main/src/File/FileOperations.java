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

    public static  void saveFile(Invoice I , int size)
    {

        try {
            PrintWriter pw = new PrintWriter(InvoiceHeader);
            PrintWriter pw2 = new PrintWriter(InvoiceLine);
            pw.close();
            pw2.close();

            InvoiceHeader.append(I.getInvoiceNumber() + ",");
            InvoiceHeader.append(I.getCustomerName()+ ",");
            InvoiceHeader.append(I.getDate()+ ",");
            InvoiceHeader.append(I.getTotalPrice()+ ",");
            InvoiceHeader.append("\n");

            ////////////// Items
            for (int i=0;i<I.getItems().size();++i)
            {
                InvoiceLine.append(I.getInvoiceNumber() + ",");
                Item item = I.getItems().get(i);
                InvoiceLine.append(item.getName()+","+item.getPrice()+","+item.getCount()+","+item.getTotal() + "\n");

            }
            if (I.getInvoiceNumber()==size)
            {
                InvoiceHeader.close();
                InvoiceLine.close();
                System.out.println("close " + "num : " + I.getInvoiceNumber() + " Size : " + size);
            }


        }
        catch (Exception e) {
            e.getStackTrace();
        }

    }
    public static void loadFile (File InvoiceHeader ,File InvoiceLine )
    {
        try {
            File myObj = InvoiceHeader;
            Scanner myReader = new Scanner(myObj);
            Invoice.setInvoice_number(0);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String [] NData = data.split(",");
                createInvoice.loadInvoice(NData[1],NData[2] ,Float.parseFloat(NData[3]) );
                System.out.println("Invoice Number : " + NData[0] +" Invoice Date : " + NData[1] +" Customer Name : " + NData[2]);
            }
            myReader.close();
            File myObj2 = InvoiceLine;
            Scanner myReader2 = new Scanner(myObj2);
            while (myReader2.hasNextLine()) {
                String data = myReader2.nextLine();
                String [] NData = data.split(",");
                createItem.loadItem(Integer.parseInt(NData[0]),NData[1],NData[2],NData[3] ,NData[4]);

                System.out.println("Invoice Number : " + NData[0] +" Item Name : " + NData[1] +" Price : " + NData[2]+" Count : " + NData[3]);
            }
            myReader2.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }
}
