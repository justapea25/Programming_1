package Order;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Account.Member;
import Functions.ValidateInput;
import Product.*;

public class Order implements Serializable {
    private static final long serialVersionUID = -3831393805083874427L;
    private String id;
    private Member customer;
    private ArrayList<Order_Item> items;
    private String status;
    private Date date;
    private double total_price;

    public Order(Member customer, ArrayList<Order_Item> items) throws Exception{
        this.customer = customer;
        this.items = items;
        this.status = "Placed";
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
        // Generate auto ID
        ListOfOrder listOfOrder = new ListOfOrder();
        listOfOrder.readOrder();
        String lastID = listOfOrder.getOrderList().get(listOfOrder.getOrderList().size() - 1).getId();
        this.id = "O" + (Integer.parseInt(lastID.substring(1)) + 1);
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
        return this.items;
    }
    public void setItems(ArrayList<Order_Item> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter new status: ");
        this.status = sc.nextLine();
        this.exportOrder();
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
        ArrayList<Order_Item> order_items = new ArrayList<>();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("What do you want to buy? (enter product ID) ");
            String item = ValidateInput.inputPatternCheck("P[0-9]*", "Product ID should start with P and followed by a number (e.g. P3)");
            System.out.print("Quantity? ");
            int quantity = Integer.parseInt(ValidateInput.inputPatternCheck("[0-9]*", "Only enter numbers!"));
            for (Product product : listOfProduct.getProductList()) {
                if (item.equals(product.getId())) {
                    Order_Item order_item = new Order_Item(product, quantity);
                    order_items.add(order_item);
                }
            }
            System.out.print("Do you want to buy anything else? (y/n): ");
            String response = ValidateInput.inputPatternCheck("y||n", "Only enter y or n");
            if (response.equals("n")) {break;}
        }
        Order order = new Order(customer, order_items);
        order.viewOrder();
        customer.updateMembership();
        order.exportOrder();
    }
    public void exportOrder() throws Exception{
        FileOutputStream fo = new FileOutputStream("src/files/Orders/"+this.id+".obj");
        ObjectOutputStream orderOut = new ObjectOutputStream(fo);
        orderOut.writeObject(this);
        orderOut.close();
    }
    public void viewOrder() {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        System.out.println("\n----------------------");
        System.out.println("ID: " + this.id);
        System.out.println("Customer name: " + this.customer.getName());
        System.out.println("Date: " + simpleDateFormat.format(this.getDate()));
        System.out.println("Status: " + this.status);
        System.out.println("Products: ");
        for (Order_Item item : this.items) {
            System.out.println("\t" + item.getProduct().getName() + " : " + item.getQuantity());
        }
        System.out.println("Total price: " + String.format("%,.0f", this.total_price) + " VND");
        System.out.println("----------------------\n");
    }

}

