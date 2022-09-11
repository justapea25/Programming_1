package Order;

import Product.Product;

import java.io.Serializable;
//Used for storing a product and its quantity in a specific order
public class Order_Item implements Serializable {
    private Product product;
    private int quantity;

    public Order_Item(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
