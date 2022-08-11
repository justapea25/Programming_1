package Menu;

import Account.Member;
import Product.ListOfProduct;
import Order.Order;
import Order.ListOfOrder;

import java.util.Scanner;

public class MemberMenu extends GuestMenu {
    public static void printMenu() {
        System.out.println("1 - List all available products"); // Then customer can see a product detail
        System.out.println("2 - Search products by a category");
        System.out.println("3 - Sort all products by price");
        System.out.println("4 - Create an order");
        System.out.println("5 - Search order by ID");
        System.out.println("6 - Exit");
    }


    public static void main(Member member) throws Exception{
        Scanner sc = new Scanner(System.in);
        ListOfProduct productList = new ListOfProduct();
        productList.readProducts();
        ListOfOrder orderList = new ListOfOrder();
        orderList.readOrder();

        while (true) {
            try {
                System.out.println("Welcome to our tech store: " + member.getUsername());
                printMenu();
                System.out.print("Please enter a number correspond to any action as shown below!\n");
                int n = sc.nextInt();
                if (n == 1) {
                    productList.viewAllProduct();
                } else if (n == 2) {
                    searchProductByCategory(productList);
                } else if (n == 3) {
                    productList.sortProductByPrice();
                } else if (n == 4) {
                    Order.create_order(member);
                } else if (n == 5) {
                    orderList.searchOrderByID().viewOrder();
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
