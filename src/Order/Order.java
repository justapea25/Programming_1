package Order;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Account.Member;
import Product.*;

public class Order implements Serializable {
    private String id;
    private Member customer;
    private ArrayList<Order_Item> items;
    private String status;
    private Date date;
    private double total_price;

    public Order(Member customer, ArrayList<Order_Item> items) {
        this.customer = customer;
        this.items = items;
        this.status = "pending";
        this.date = new Date();
        this.total_price = 0;
        for (Order_Item item : this.items){
            this.total_price = this.total_price + item.getProduct().getPrice() *item.getQuantity();
        }
        switch (this.customer.getMembership()){
            case "Silver": this.total_price = this.total_price * 0.95;
            case "Gold": this.total_price = this.total_price * 0.9;
            case "Platinum": this.total_price = this.total_price * 0.85;
        }
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public Member getCustomer() {
        return customer;
    }
    public void setCustomer(Member customer) {
        this.customer = customer;
    }

    public ArrayList<Order_Item> getItems() {
        return items;
    }
    public void setItems(ArrayList<Order_Item> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal_price() { return total_price; }

    public static void create_order(Member customer) throws Exception {
        ListOfProduct listOfProduct = new ListOfProduct();
        listOfProduct.readProducts();
        ArrayList<Order_Item> order_items = new ArrayList<Order_Item>();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("What do you want to buy? (enter product ID) ");
            String item = scanner.nextLine();
            System.out.print("Quantity? ");
            int quantity = scanner.nextInt();
            for (Product product : listOfProduct.getProductList()) {
                if (item.equals(product.getId())) {
                    Order_Item order_item = new Order_Item(product, quantity);
                    order_items.add(order_item);
                }
            }
            System.out.println("Do you want to buy anything else? (y/n) ");
            String response = scanner.nextLine();
            if (response.equals("n")) {break;}
        }
        Order order = new Order(customer, order_items);
        order.exportOrder();
    }
    public void exportOrder() throws Exception{
        FileOutputStream fo = new FileOutputStream("Orders"+File.pathSeparator+this.id+".obj");
        ObjectOutputStream orderOut = new ObjectOutputStream(fo);
        orderOut.writeObject(this);
    }
}

