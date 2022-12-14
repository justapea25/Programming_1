package Account;

import Order.ListOfOrder;
import Order.Order;

import java.io.Serial;
import java.io.Serializable;

public class Member  implements Serializable {
    @Serial
    private static final long serialVersionUID = -6959344184946949782L;
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

    // Reads all orders made by the member and returns the total spending
    public double getTotal_spending() throws Exception {
        ListOfOrder listOfOrder = new ListOfOrder();
        listOfOrder.readOrder();
        double total_spending = 0;
        for (Order order : listOfOrder.getOrderList()){
            if(order.getCustomer().getId().equals(this.id)){total_spending += order.getTotal_price();}
        }
        return total_spending;
    }
    // Change the membership of a member based on their total spending
    public void updateMembership() throws Exception{
        double total_spending = this.getTotal_spending();
        if (this.getTotal_spending() < 5000000){
            this.membership = "Normal";
        } else if (5000000 < total_spending && total_spending <= 10000000){
            this.membership = "Silver";
        }else if (10000000 < total_spending && total_spending <= 25000000){
            this.membership = "Gold";
        } else if (25000000 < total_spending) {
            this.membership = "Platinum";
        }
    }
    // Prints out all details of a member
    public void viewMember() throws Exception {
        System.out.println("Member ID: " + this.id + "\nName: " + this.name + " \nAddress: " + this.address +
                " \nMembership: " + this.membership + "\nTotal spending: " + String.format("%,.0f",this.getTotal_spending()) + " VND" + "\n\n");
    }
}
