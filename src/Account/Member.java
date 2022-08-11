package Account;
import Order.ListOfOrder;
import Order.Order;

import java.io.Serializable;
import java.util.Scanner;

public class Member  implements Serializable {
    private String id;
    private String username;
    private String password;
    private String name;
    private String address;
    private String membership;

    public Member(String id, String username, String password, String name, String address) throws Exception {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        updateMembership();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getMembership() {
        return membership;
    }
    public void setMembership(String membership) {
        this.membership = membership;
    }

    public double getTotal_spending() throws Exception {
        ListOfOrder listOfOrder = new ListOfOrder();
        listOfOrder.readOrder();
        double total_spending = 0;
        for (Order order : listOfOrder.getOrderList()){
            if(order.getCustomer().equals(this)){total_spending += order.getTotal_price();}
        }
        return total_spending;
    }
    public void updateMembership() throws Exception{
        double total_spending = this.getTotal_spending();
        if (this.getTotal_spending() < 5000000){
            this.membership = "none";
        } else if (5000000 < total_spending && total_spending <= 10000000){
            this.membership = "Silver";
        }else if (10000000 < total_spending && total_spending <= 25000000){
            this.membership = "Gold";
        } else if (25000000 < total_spending) {
            this.membership = "Platinum";
        }
    }
    public void viewMember(){
        System.out.println("ID: " + this.id + "\nName: " + this.name + " \nAddress " + this.address + " \nMembership: " + this.membership + "\n\n");
    }
}
