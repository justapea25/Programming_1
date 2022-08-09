import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private String id;
    private Member customer;
    private Order_Item[] items;
    private String status;
    private Date date;
    private static int count = 0;

    public Order(Member customer, Order_Item[] items, String status, Date date) {
        this.customer = customer;
        this.items = items;
        this.status = status;
        this.date = date;
        this.id = "O" + ++count;
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

    public Order_Item[] getItems() {
        return items;
    }
    public void setItems(Order_Item[] items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
