package Product;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;
public class Product implements Serializable {
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
    public void setName(String name) {
        this.name = name;
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

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public void viewProduct() {
        System.out.println("ID: " + id + "\nName: " + name + " \n" + "Category: " + category + " \n" + "Detail: " + detail + " \n" + "Price: " + String.format("%,.0f", price) + " VND\n\n");
    }

    public void updatePrice() throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter item ID: ");
        String id = scanner.next();
        System.out.println("Enter new price: ");
        double newPrice = scanner.nextDouble();
        ListOfProduct productList = new ListOfProduct();
        productList.readProducts();
        productList.searchProductById(id).setPrice(newPrice);
    }
}
