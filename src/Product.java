public class Product {
    private String id;
    private String name;
    private String category;
    private String detail;
    private double price;
    private static int count = 0;

    public Product(String name, String category, String detail, double price) {
        this.name = name;
        this.category = category;
        this.detail = detail;
        this.price = price;
        this.id = "P" + ++count;
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
}
