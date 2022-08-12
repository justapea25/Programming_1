package Product;

import Functions.ValidateInput;

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
        this.price = Double.parseDouble(ValidateInput.inputPatternCheck("[0-9]*", "Only enter numbers!"));
    }

    public void viewProduct(String display) {
        switch (display) {
            case "all" ->
                    System.out.println("ID: " + id + "\nName: " + name + " \n" + "Category: " + category + " \n" + "Detail: " + detail + " \n" + "Price: " + String.format("%,.0f", price) + " VND\n\n");
            case "category" ->
                    System.out.println("ID: " + id + "\nName: " + name + "\n\n");
            case "price" ->
                    System.out.println("ID: " + id + "\nName: " + name + " \n" + "Price: " + String.format("%,.0f", price) + " VND\n\n");
            case "general" ->
                    System.out.println("ID: " + id + "\nName: " + name + " \n" + "Category: " + category + "\n\n");
        }

    }


}
