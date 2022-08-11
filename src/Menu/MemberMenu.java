package Menu;

import Account.Member;
import Product.ListOfProduct;

import java.util.Scanner;

public class MemberMenu extends GuestMenu {
    public static void printMenu() {

    }
    public static void main(Member member) {
        Scanner sc = new Scanner(System.in);
        ListOfProduct productList = new ListOfProduct();
        productList.readProducts();
        while (true) {
            try {
                System.out.println("Welcome to our tech store: " + member.getUsername());
//                printMenu();
                System.out.print("Please enter a number correspond to any action as shown below!\n");
                int n = sc.nextInt();
                if (n == 1) {

                } else if (n == 2) {

                } else if (n == 3) {
                    searchProductByCategory(productList);
                } else if (n == 4) {

                } else if (n == 5) {

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
