package Product;

import Functions.validateInput;

import java.io.Serial;
import java.io.Serializable;
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = -4680854950341691008L;
    private String id;
    private String name;
    private String category;
    private String detail;
    private double price;

    public Product(String id, String name, String category, String detail, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.detail = detail;
        this.price = price;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }

    public double getPrice() { return price; }
    public void setPrice() {
        System.out.print("Enter new price: ");
        this.price = Double.parseDouble(validateInput.inputPatternCheck("[0-9]*", "Only enter numbers!"));
    }

    public void viewProduct() {
        System.out.println("ID: " + id + "\nName: " + name + " \n" + "Category: " + category + " \n" + "Detail: " + detail + " \n" + "Price: " + String.format("%,.0f", price) + " VND\n\n");
    }


}
