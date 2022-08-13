package Menu;

import Account.Admin;
import Account.ListOfMember;
import Order.ListOfOrder;
import Order.Order;
import Product.ListOfProduct;

import java.util.ArrayList;
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
                String n = sc.nextLine();
                if (n.equals("1")) {
                    productList.viewAllProduct();
                } else if (n.equals("2")) {
                    orderList.viewAllOrders();
                } else if (n.equals("3")) {
                    memberList.viewAllMembers();
                } else if (n.equals("4")) {
                    productList.searchProductById().viewProduct("all");
                } else if (n.equals("5")) {
                    productList.addProduct();
                } else if (n.equals("6")) {
                    productList.removeProduct();
                } else if (n.equals("7")) {
                    ArrayList<Order> orders = orderList.searchOrderByMember();
                    if (orders != null) {
                        for (Order order : orders) {
                            order.viewOrder();
                        }
                    }
                } else if (n.equals("8")) {
                    orderList.searchOrderByID().setStatus();
                } else if (n.equals("9")) {
                    productList.searchProductById().setPrice();
                } else if (n.equals("10")) {
                    System.out.println("Today's revenue: " + String.format("%,.0f", orderList.getTotalRevenue()) + " VND");
                    System.out.println("Today's total number of order: " + orderList.getTotalOrder());
                } else if (n.equals("11")) {
                    break;
                } else {
                    System.out.println("No matching selection, please try again");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
            System.out.println("Press enter to continue");
            String back = sc.nextLine();
        }
        // Write data to file
        productList.writeProductToFile();
    }
}
