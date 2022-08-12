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
        System.out.println("3 - List all available products"); // Then customer can see a product detail
        System.out.println("4 - View product details");
        System.out.println("5 - Search products by a category");
        System.out.println("6 - Sort all products by price");
        System.out.println("7 - Exit");
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
                int n = sc.nextInt();
                if (n == 1) {
                    Member member = listOfMember.register();
                    listOfMember.writeMemberToFile();
                    MemberMenu.main(member);
                    break;
                } else if (n == 2) {
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
                } else if (n == 3) {
                    productList.viewAllProduct();
                } else if (n == 4) {
                    productList.displayProductById();
                } else if (n == 5) {
                    searchProductByCategory(productList);
                } else if (n == 6) {
                    System.out.println("Please choose to sort products by price in ascending or descending order (asc/desc)");
                    String input = ValidateInput.inputPatternCheck("asc||desc", "Wrong input, please try again");
                    productList.sortProductByPrice(input);
                } else if (n == 7) {
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
