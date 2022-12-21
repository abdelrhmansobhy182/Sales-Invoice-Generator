package Package1;


import GUI.Home;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static FileWriter fw ;
    public static void main(String[] args) throws IOException {
        Home H = new Home();
        H.setVisible(true);
         fw = new FileWriter("Data.txt" , true);

    }
    public static void saveFile(Invoice I)
    {
        try {

            fw.append(I.getInvoiceNumber()+"\n");
            fw.append(I.getCustomerName()+"\n");
            fw.append(I.getDate()+"\n");
            fw.append(I.getTotalPrice()+"\n");
            fw.append(I.getItems().size()+"\n");
            for (int i=0;i<I.getItems().size();++i)
            {
                Item item = I.getItems().get(i);
                fw.append(item.getName()+","+item.getPrice()+","+item.getCount()+","+item.getTotal() + "\n");

            }

            fw.close();
        }
        catch (Exception e) {
            e.getStackTrace();
        }

    }
    public static void loadFile ()
    {
        try {
            File myObj = new File("Data.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }


}
