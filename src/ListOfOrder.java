import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.io.File;

public class ListOfOrder {
    private ArrayList<Order> orderList;
    public ArrayList<Order> getOrderList() {
        return orderList;
    }
    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }
    public void readOrders() throws Exception{
        File ordersPackage = new File("Orders");
        File[] orders = ordersPackage.listFiles();
        for (File f : orders){
            FileInputStream fi = new FileInputStream(f.getAbsolutePath());
            ObjectInputStream orderIn = new ObjectInputStream(fi);
            Order order = (Order) orderIn.readObject();
            this.orderList.add(order);
        }
    }
}
