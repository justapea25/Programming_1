package Product;
import java.io.*;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

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

    public void readProducts() throws IOException, ClassNotFoundException {
        String path = "product.txt";
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
        String path = "product.txt";

        try {
            File f = new File(path);
            FileWriter fw = new FileWriter(f);
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
//    public void sortProductByPrice() {
//        Collections.sort(productList, Product.compare);
//    }

    public ArrayList<Product> sortProductByPrice() {
        productList.sort(new Comparator<Product>() {
            @Override
            public int compare(Product first, Product second) {
                if (first.getPrice() != second.getPrice()) {
                    return Double.compare(first.getPrice(), second.getPrice());
                }
                return first.getName().compareTo(second.getName());
            }
        });

        return productList;
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