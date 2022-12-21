package Package1;

public class Item {
    private String Name ;
    private int Price ;
    private int Count ;
    private int Total;
    Item (String Name , int Price , int Count)
    {
        this.Name = Name;
        this.Price = Price;
        this.Count = Count ;
        this.Total = Calculate_total();
    }



    private int Calculate_total()
    {
        return Price*Count;
    }

    public String getName() {
        return Name;
    }

    public int getPrice() {
        return Price;
    }

    public int getCount() {
        return Count;
    }

    public void printItem ()
    {
        System.out.println("Item name : " + Name + " Item Price : " + Price + " Item Count : " + Count + " Total : " + Total);
    }

    public void setTotal(int total) {
        Total = total;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public void setCount(int count) {
        Count = count;
    }
    public int getTotal() {
        return Total;
    }
}
