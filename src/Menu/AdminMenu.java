package Menu;

import Account.Admin;
import Account.ListOfMember;
import Order.ListOfOrder;
import Order.Order;
import Product.ListOfProduct;

import java.util.Scanner;

public class AdminMenu extends GuestMenu {
    public static void printMenu() {
        System.out.println("1 - List all available products");
        System.out.println("2 - List all orders");
        System.out.println("3 - List all members");
        System.out.println("4 - View product details");
        System.out.println("5 - Add product");
        System.out.println("6 - Remove product");
        System.out.println("7 - List all orders of a specific customer");
        System.out.println("8 - Change status of order");
        System.out.println("9 - Update price");
        System.out.println("10 - See the total revenues and orders today");
        System.out.println("11 - Exit");
    }

    public static void main(Admin admin) throws Exception {
        Scanner sc = new Scanner(System.in);
        // Import all the data
        ListOfProduct productList = new ListOfProduct();
        productList.readProducts();
        ListOfOrder orderList = new ListOfOrder();
        orderList.readOrder();
        ListOfMember memberList = new ListOfMember();
        memberList.readMembers();
        while (true) {
            try {
                System.out.println("Welcome back: " + admin.getUsername());
                printMenu();
                System.out.print("Please enter a number correspond to any action as shown below!\n");
                int n = sc.nextInt();
                if (n == 1) {
                    productList.viewAllProduct();
                } else if (n == 2) {
                    orderList.viewAllOrders();
                } else if (n == 3) {
                    memberList.viewAllMembers();
                } else if (n == 4) {
                    productList.displayProductById();
                } else if (n == 5) {
                    productList.addProduct();
                } else if (n == 6) {
                    productList.removeProduct();
                } else if (n == 7) {
                    for (Order order : orderList.searchOrderByMember()){
                        order.viewOrder();
                    }
                } else if (n == 8) {
                    orderList.searchOrderByID().setStatus();
                } else if (n == 9) {
                    productList.searchProductById().setPrice();
                } else if (n == 10) {
                    System.out.println("Today's revenue: " + String.format("%,.0f", orderList.getTotalRevenue()) + " VND");
                    System.out.println("Today's total number of order: " + orderList.getTotalOrder());
                } else if (n == 11) {
                    break;
                } else {
                    System.out.println("No matching selection, please try again");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
        // Write data to file
        productList.writeProductToFile();
    }
}
