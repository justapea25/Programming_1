package Menu;

import Account.Member;
import Functions.ValidateInput;
import Product.ListOfProduct;
import Order.Order;
import Order.ListOfOrder;

import java.util.Scanner;

public class MemberMenu extends GuestMenu {
    public static void printMenu() {
        System.out.println("1 - List all available products");
        System.out.println("2 - View product details");
        System.out.println("3 - Search products by a category");
        System.out.println("4 - Sort all products by price");
        System.out.println("5 - Create an order");
        System.out.println("6 - Search order by ID");
        System.out.println("7 - View profile");
        System.out.println("8 - Exit");
    }


    public static void main(Member member) throws Exception{
        Scanner sc = new Scanner(System.in);
        // Import data
        ListOfProduct productList = new ListOfProduct();
        productList.readProducts();
        ListOfOrder orderList = new ListOfOrder();

        while (true) {
            try {
                System.out.println("Welcome to our tech store: " + member.getUsername() + " (Membership: " + member.getMembership() + ")");
                printMenu();
                System.out.print("Please enter a number correspond to any action as shown below!\n");
                int n = Integer.parseInt(sc.nextLine());
                if (n == 1) {
                    productList.viewAllProduct();
                } else if (n == 2) {
                    productList.displayProductById();
                } else if (n == 3) {
                    searchProductByCategory(productList);
                } else if (n == 4) {
                    System.out.println("Please choose to sort products by price in ascending or descending order (asc/desc)");
                    String input = ValidateInput.inputPatternCheck("asc||desc", "Wrong input, please try again");
                    productList.sortProductByPrice(input);
                } else if (n == 5) {
                    Order.create_order(member);
                    member.updateMembership();
                } else if (n == 6) {
                    orderList.readOrder();
                    Order order = orderList.searchOrderByID();
                    if (order != null){
                        order.viewOrder();
                    } else {
                        System.out.println("There is no order with such ID.");
                    }
                } else if (n == 7) {
                    member.viewMember();
                } else if (n == 8) {
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
