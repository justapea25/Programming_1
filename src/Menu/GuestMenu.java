package Menu;

import Account.Member;
import Product.ListOfProduct;
import Product.Product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GuestMenu {
    public static void printMenu() {
        System.out.println("1 - Register for a new account");
        System.out.println("2 - Login to existing account");
        System.out.println("3 - List all available products"); // Then customer can see a product detail
        System.out.println("4 - Search products by a category");
        System.out.println("5 - Sort all products by price");
        System.out.println("6 - Exit");
    }

    public static void searchProductByCategory(ListOfProduct productList) {

        // Print all the categories of product
        System.out.println("Choose the category you want to see products");
        Set<String> categories = new HashSet<>();
        for (Product product : productList.getProductList()) {
            categories.add(product.getCategory());
        }
        for (String category : categories) {
            System.out.println(category);
        }

        // Get the category user want to see
        Scanner s = new Scanner(System.in);
        String chosenCategory = s.next();
        productList.filterProductByCategory(chosenCategory);
    }


    public static void main() {
        Scanner sc = new Scanner(System.in);
        ListOfProduct productList = new ListOfProduct();
        productList.readProducts();

        while (true) {
            try {
                System.out.println("Welcome to our tech store");
                printMenu();
                System.out.print("Please enter a number correspond to any action as shown below!\n");
                int n = sc.nextInt();
                if (n == 1) {
                    Member member = Member.register();
                    MemberMenu.main(member);
                } else if (n == 2) {
                    Member member = Member.login();
                    MemberMenu.main(member);
                } else if (n == 3) {
                    productList.viewAllProduct();
                } else if (n == 4) {
                    searchProductByCategory(productList);
                } else if (n == 5) {
                    productList.sortProductByPrice();
                } else if (n == 6) {
                    break;
                } else {
                    System.out.println("No matching selection, please try again");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}