package Product;

import java.io.*;
import java.util.ArrayList;

public class ListOfProduct {
    private ArrayList<Product> productList;
    public ListOfProduct() {
        productList = new ArrayList<Product>();
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

    public void readProducts() {
        String path = "src/files/product.txt";
		try {
        FileReader fr = new FileReader(new File(path));
        BufferedReader br = new BufferedReader(fr);
        String line;

        while ((line = br.readLine()) != null) {
            String data[] = line.split("\\t+");
            String productID = data[0];
            String productName = data[1];
            String productCategory = data[2];
            String productDetail = data[3];
            Double productPrice = Double.parseDouble(data[4]);
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
    public void writeProductToFile() throws IOException, ClassNotFoundException {
        String path = "src/files/product.txt";

        try {
            FileWriter fw = new FileWriter(new File(path));
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
    public void viewAllProduct() {
        for (int i=0; i<productList.size(); i++) {
            productList.get(i).viewProduct();
        }
    };
    public Product searchProductById(String id) {
        for(Product product : productList) {
            if(product.getId() == id) {
                return product;
            }
        }
        return null;
    }
    public void sortProductByPrice() {
        ArrayList<Product> tempList = new ArrayList<Product>(productList);

        tempList.sort((p1, p2) -> {
            if (p1.getPrice() != p2.getPrice())
                return (int) (p1.getPrice() - p2.getPrice());
            return p1.getName().compareTo(p2.getName());
        });

        for (Product product : tempList) {
            product.viewProduct();
        }
    }


    public static void main(String[] args) throws Exception {
        ListOfProduct productList = new ListOfProduct();

//        productList.writeProductToFile();
        productList.readProducts();
        productList.sortProductByPrice();
        productList.viewAllProduct();
//        productList.viewAllProduct();
//        Product.addProduct();

    }
}