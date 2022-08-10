import java.io.Serializable;

public class Member  implements Serializable {
    private String id;
    private String username;
    private String password;
    private String name;
    private String address;
    private String membership;
    private double total_spending;

    public Member(String id, String username, String password, String name, String address) {
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

    public double getTotal_spending() {
        return total_spending;
    }
    public void updateMembership(){
        //
        //load data from order.obj to calculate total spending
        //
        if (this.total_spending < 5000000){
            this.membership = "none";
        } else if (5000000 < this.total_spending && this.total_spending <= 10000000){
            this.membership = "Silver";
        }else if (10000000 < this.total_spending && this.total_spending <= 25000000){
            this.membership = "Gold";
        } else if (25000000 < this.total_spending) {
            this.membership = "Platinum";
        }
    }
}
