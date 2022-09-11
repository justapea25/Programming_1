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


    public static void main(Member member) {
        Scanner sc = new Scanner(System.in);
        // Import data
        ListOfProduct productList = new ListOfProduct();
        productList.readProducts();
        ListOfOrder orderList = new ListOfOrder();

        label: // Label to indicate the loop to break later
        while (true) {
            try {
                System.out.println("Welcome to our tech store: " + member.getUsername() + " (Membership: " + member.getMembership() + ")");
                printMenu();
                System.out.print("Please enter a number correspond to any action as shown below!\n");
                String n = sc.nextLine();
                switch (n) {
                    case "1":
                        productList.viewAllProduct();
                        break;
                    case "2":
                        productList.searchProductById().viewProduct("all");
                        break;
                    case "3":
                        productList.searchProductByCategory();
                        break;
                    case "4":
                        System.out.println("Please choose to sort products by price in ascending or descending order (asc/desc)");
                        String input = ValidateInput.inputPatternCheck("asc||desc", "Wrong input, please try again");
                        productList.sortProductByPrice(input);
                        break;
                    case "5":
                        Order.create_order(member);
                        member.updateMembership();
                        break;
                    case "6":
                        orderList.readOrder();
                        Order order = orderList.searchOrderByID();
                        if (order != null) {
                            order.viewOrder();
                        }
                        break;
                    case "7":
                        member.viewMember();
                        break;
                    case "8":
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
