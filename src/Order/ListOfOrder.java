package Order;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.io.File;
import java.util.Date;

public class ListOfOrder {
    private ArrayList<Order> orderList;
    public ArrayList<Order> getOrderList() {
        return orderList;
    }
    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }
    public void readOrder() throws IOException, ClassNotFoundException{
        File ordersPackage = new File("src/files/Orders");
        File[] orders = ordersPackage.listFiles();
        for (File f : orders){
            FileInputStream fi = new FileInputStream(f.getAbsolutePath());
            ObjectInputStream orderIn = new ObjectInputStream(fi);
            Order order = (Order) orderIn.readObject();
            this.orderList.add(order);
        }
    }
    public void searchOrder(String id) throws IOException, ClassNotFoundException{
        ListOfOrder listOfOrder = new ListOfOrder();
        listOfOrder.readOrder();
        for (Order order : listOfOrder.getOrderList()){
            if (id.equals(order.getId())){
                // code
            }
        }
    }
    public double getTotalRevenue() throws IOException, ClassNotFoundException{
        ListOfOrder listOfOrder = new ListOfOrder();
        listOfOrder.readOrder();
        double totalRevenue = 0;
        for (Order order : listOfOrder.getOrderList()){
            if (order.getDate().equals(new Date())){
                totalRevenue += order.getTotal_price();
            }
        }
        return totalRevenue;
    }
}
