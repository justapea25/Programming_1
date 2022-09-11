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

        label: // Label to indicate the loop to break later
        while (true) {
            try {
                System.out.println("Welcome back: " + admin.getUsername());
                printMenu();
                System.out.print("Please enter a number correspond to any action as shown below!\n");
                String n = sc.nextLine();
                switch (n) {
                    case "1":
                        productList.viewAllProduct();
                        break;
                    case "2":
                        orderList.viewAllOrders();
                        break;
                    case "3":
                        memberList.viewAllMembers();
                        break;
                    case "4":
                        productList.searchProductById().viewProduct("all");
                        break;
                    case "5":
                        productList.addProduct();
                        break;
                    case "6":
                        productList.removeProduct();
                        break;
                    case "7":
                        ArrayList<Order> orders = orderList.searchOrderByMember();
                        if (orders != null) {
                            for (Order order : orders) {
                                order.viewOrder();
                            }
                        }
                        break;
                    case "8":
                        orderList.searchOrderByID().setStatus();
                        break;
                    case "9":
                        productList.searchProductById().setPrice();
                        break;
                    case "10":
                        System.out.println("Today's revenue: " + String.format("%,.0f", orderList.getTotalRevenue()) + " VND");
                        System.out.println("Today's total number of order: " + orderList.getTotalOrder());
                        break;
                    case "11":
                        break label; // break the labelled while loop
                    default:
                        System.out.println("No matching selection, please try again");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }

            // Press enter to go back to menu
            System.out.println("Press enter to continue");
            sc.nextLine();
        }
        // Write data to file
        productList.writeProductToFile();
    }
}
