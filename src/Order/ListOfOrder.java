package Order;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.io.File;
import java.util.Date;
import java.util.Scanner;
import Functions.validateInput;

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
        for (File f : orders) {
            FileInputStream fi = new FileInputStream(f.getAbsolutePath());
            ObjectInputStream orderIn = new ObjectInputStream(fi);
            Order order = (Order) orderIn.readObject();
            this.orderList.add(order);
        }
    }
    public Order searchOrderByID() throws IOException, ClassNotFoundException{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter order ID: ");
        String id = validateInput.inputPatternCheck("O[0-9]*", "Order ID should start with O and followed by a number (e.g. O1)");
        for (Order order : this.getOrderList()){
            if (id.equals(order.getId())){
                return order;
            }
        }
        return null;
    }
    public ArrayList<Order> searchOrderByMember() throws IOException, ClassNotFoundException{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the member ID: ");
        String memberId = validateInput.inputPatternCheck("M[0-9]*", "Member ID should start with M and followed by a number (e.g. M3)");
        ArrayList<Order> orders = new ArrayList<Order>();
        for (Order order : this.getOrderList()){
            if (order.getCustomer().getId().equals(memberId)){
                orders.add(order);
            }
        }
        return orders;
    }

    public int getTotalOrder() throws IOException, ClassNotFoundException {
        int count = 0;
        for (Order order : this.getOrderList()){
            if (order.getDate().toString().substring(4, 10).equals(new Date().toString().substring(4, 10))){
                count ++;
            }
        }
        return count;
    }

    public double getTotalRevenue() throws IOException, ClassNotFoundException{
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
