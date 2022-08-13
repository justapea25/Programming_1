package Menu;

import Account.Admin;
import Account.ListOfMember;
import Account.Member;
import Product.ListOfProduct;
import Product.Product;
import Functions.ValidateInput;

import java.util.*;

public class GuestMenu {
    public static void printMenu() {
        System.out.println("1 - Register for a new account");
        System.out.println("2 - Login to existing account");
        System.out.println("3 - List all available products");
        System.out.println("4 - View product details");
        System.out.println("5 - Search products by a category");
        System.out.println("6 - Sort all products by price");
        System.out.println("7 - Exit");
    }

    public static void main() throws Exception{
        Scanner sc = new Scanner(System.in);
        // Import data
        ListOfProduct productList = new ListOfProduct();
        productList.readProducts();
        ListOfMember listOfMember = new ListOfMember();
        listOfMember.readMembers();
        while (true) {
            try {
                System.out.println("Welcome to our tech store");
                printMenu();
                System.out.print("Please enter a number correspond to any action as shown below!\n");
                String n = sc.nextLine();
                if (n.equals("1")) {
                    Member member = listOfMember.register();
                    listOfMember.writeMemberToFile();
                    MemberMenu.main(member);
                    break;
                } else if (n.equals("2")) {
                    System.out.println("Do you want to login as admin or member? (admin/member)");
                    String input = ValidateInput.inputPatternCheck("admin||member", "Wrong input, please try again");
                    switch (input) {
                        case "member" -> {
                            Member member = listOfMember.memberLogin();
                            MemberMenu.main(member);
                        }
                        case "admin" -> {
                            Admin admin = Admin.adminLogin();
                            AdminMenu.main(admin);
                        }
                    }
                    break;
                } else if (n.equals("3")) {
                    productList.viewAllProduct();
                } else if (n.equals("4")) {
                    productList.searchProductById().viewProduct("all");
                } else if (n.equals("5")) {
                    productList.searchProductByCategory();
                } else if (n.equals("6")) {
                    System.out.println("Please choose to sort products by price in ascending or descending order (asc/desc)");
                    String input = ValidateInput.inputPatternCheck("asc||desc", "Wrong input, please try again");
                    productList.sortProductByPrice(input);
                } else if (n.equals("7")) {
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
    }
}
