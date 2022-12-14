package Product;

import Functions.ValidateInput;

import java.io.*;
import java.util.*;

public class ListOfProduct {
    private ArrayList<Product> productList;
    public ListOfProduct() {
        productList = new ArrayList<>();
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }
    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }
    public void addProductToList(Product p) {
        this.productList.add(p);
    }

    // Read all product from the file
    public void readProducts() {
        this.productList.clear();
        String path = "src/files/product.txt";
		try {
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String line;

        while ((line = br.readLine()) != null) {
            String[] data = line.split("\\t+");
            String productID = data[0];
            String productName = data[1];
            String productCategory = data[2];
            String productDetail = data[3];
            double productPrice = Double.parseDouble(data[4]);
            Product p = new Product(productID, productName, productCategory, productDetail, productPrice);
            this.productList.add(p);
        }
        br.close();
        fr.close();
        } catch (IOException e) {
        System.out.println("Error: " + e.getMessage());
        e.printStackTrace();
        }
    }

    // Write product data back to file
    public void writeProductToFile() {
        String path = "src/files/product.txt";

        try {
            FileWriter fw = new FileWriter(path);
            BufferedWriter bw = new BufferedWriter(fw);
            for(Product product : productList) {
                bw.write(product.getId() + "\t");
                bw.write(product.getName() + "\t");
                bw.write(product.getCategory() + "\t");
                bw.write(product.getDetail() + "\t");
                bw.write(String.format("%.2f",product.getPrice()) + "\t");
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Add new product to the product list
    public void addProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name of product: ");
        String name = scanner.nextLine();
        System.out.print("Category of product: ");
        String category = scanner.nextLine();
        System.out.print("Detail of product: ");
        String detail = scanner.nextLine();
        System.out.print("Price of product: ");
        double price = scanner.nextDouble();
        String id;
        // Auto generate ID
        if (productList.size() < 1) {
            id = "P1";
        } else {
            String lastID = this.productList.get(this.productList.size()-1).getId();
            id = "P"+(Integer.parseInt(lastID.substring(1)) + 1);
        }

        // Add generated product to the list
        Product product = new Product(id, name, category, detail, price);
        this.addProductToList(product);
    }

    // Remove the product based on its id
    public void removeProduct() {
        Product product = this.searchProductById();
        this.productList.remove(product);
        System.out.println("Product removed!");
    }

    // VIew all the product in the list
    public void viewAllProduct() {
        for (Product product : productList) {
            product.viewProduct("general");
        }
    }

    // Search a product based on its id
    public Product searchProductById() {
        while (true) {
            System.out.print("Enter product ID: ");
            String id = ValidateInput.inputPatternCheck("P[0-9]*", "Product ID should start with P and followed by a number (e.g. P3)").toUpperCase();
            for (Product product : this.productList) {
                if (product.getId().equals(id)) {
                    return product;
                }
            }
            System.out.println("Invalid ID. Please try again.");
        }
    }

    // Search products based on their category
    public void searchProductByCategory() {
        // Print all the categories of product
        System.out.println("Choose the category you want to see products");
        Set<String> categories = new HashSet<>();
        for (Product product : this.productList) {
            categories.add(product.getCategory());
        }
        for (String category : categories) {
            System.out.println(category);
        }
        String chosenCategory;
        Scanner s = new Scanner(System.in);

        // Get the category user want to see
        while (true){
            chosenCategory = s.nextLine();
            if (categories.contains(chosenCategory)){
                break;
            }
            System.out.println("Please enter a valid category");
        }
        for (Product product : this.productList) {
            if(product.getCategory().equals(chosenCategory)) {
                product.viewProduct("category");
            }
        }
    }

    // Sort all product by price
    public void sortProductByPrice(String order) {
        ArrayList<Product> tempList = new ArrayList<>(productList);

        // Sort the list in ascending order by price
        tempList.sort((p1, p2) -> {
            if (p1.getPrice() != p2.getPrice())
                return (int) (p1.getPrice() - p2.getPrice());
            return p1.getName().compareTo(p2.getName());
        });
        // Sort the list in descending order by price
        if (order.equals("desc")) {
            Collections.reverse(tempList);
        }

        for (Product product : tempList) {
            product.viewProduct("price");
        }
    }
}