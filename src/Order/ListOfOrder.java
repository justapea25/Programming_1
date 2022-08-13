package Order;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;
import java.io.File;

import Functions.ValidateInput;

public class ListOfOrder {
    private ArrayList<Order> orderList;
    public ListOfOrder(){
        this.orderList = new ArrayList<>();
    }
    public ArrayList<Order> getOrderList() {
        return this.orderList;
    }
    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    public void readOrder() throws IOException, ClassNotFoundException{
        File ordersPackage = new File("src/files/Orders");
        File[] orders = ordersPackage.listFiles();
        if (orders != null) {
            for (File f : orders) {
                FileInputStream fi = new FileInputStream(f.getAbsolutePath());
                ObjectInputStream orderIn = new ObjectInputStream(fi);
                Order order = (Order) orderIn.readObject();
                this.orderList.add(order);
            }
        }
        Collections.sort(this.orderList);
    }
    public Order searchOrderByID() {
        while (true) {
            System.out.print("Enter order ID: ");
            String id = ValidateInput.inputPatternCheck("O[0-9]*", "Order ID should start with O and followed by a number (e.g. O1)");
            for (Order order : this.getOrderList()) {
                if (id.equals(order.getId())) {
                    return order;
                }
            }
            System.out.println("Order does not exist. Try again.");
        }
    }
    public ArrayList<Order> searchOrderByMember() {
        System.out.print("Enter the member ID: ");
        String memberId = ValidateInput.inputPatternCheck("M[0-9]*", "Member ID should start with M and followed by a number (e.g. M3)");
        ArrayList<Order> orders = new ArrayList<>();
        boolean exist = false;
        for (Order order : this.getOrderList()){
            if (order.getCustomer().getId().equals(memberId)){
                orders.add(order);
                exist = true;
            }
        }
        if (!exist) {
            System.out.println("Member does not exist");
            return null;
        }
        else {return orders;}
    }

    public int getTotalOrder() {
        int count = 0;
        for (Order order : this.getOrderList()){
            if (order.getDate().toString().substring(4, 10).equals(new Date().toString().substring(4, 10))){
                count ++;
            }
        }
        return count;
    }

    public double getTotalRevenue() {
        double totalRevenue = 0;
        for (Order order : this.getOrderList()){
            if (order.getDate().toString().substring(4, 10).equals(new Date().toString().substring(4, 10))){
                totalRevenue += order.getTotal_price();
            }
        }
        return totalRevenue;
    }
    public void viewAllOrders() {
        for (Order order : orderList) {
            order.viewOrder();
        }
    }
}
