public class Member {
    private String id;
    private String username;
    private String password;
    private String name;
    private String address;
    private String membership;
    private double total_spending = 0;
    private static int count = 0;

    public Member(String id, String username, String password, String name, String address, String membership) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.membership = membership;
        this.id = "C" + ++count;
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
}
