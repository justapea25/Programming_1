package Order;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.io.File;
import java.util.Date;
import java.util.Scanner;

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
        String id = scanner.nextLine();
        for (Order order : this.getOrderList()){
            if (id.equals(order.getId())){
                return order;
            }
        }
        return null;
    }
    public Order searchOrderByMember() throws IOException, ClassNotFoundException{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the member ID: ");
        String memberId = scanner.nextLine();
        for (Order order : this.getOrderList()){
            if (order.getCustomer().getId().equals(memberId)){
                return order;
            }
        }
        return null;
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
