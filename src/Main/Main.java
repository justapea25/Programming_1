package Main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        // Print welcome screen
        System.out.println("COSC2081 GROUP ASSIGNMENT");
        System.out.println("STORE ORDER MANAGEMENT SYSTEM ");
        System.out.println("Instructor: Mr. Minh Vu ");
        System.out.println("Group: Group 1 ");
        System.out.println("s3925997, Le Trinh Quoc Huynh ");
        System.out.println("s3926201, Dau Hoang Minh ");
        System.out.println("s3927234, Dang Thai Hoang ");
        System.out.println("s3924345, Doan Huy ");
        System.out.println("--------------------------------------------------------------");

        // A loop that let users do multiple actions before they quit
        while (true) {
            System.out.println("Please enter a number correspond to any action as shown below!");
            System.out.println("1 - Register/ Login to existing account"); // Then choose to register or login
            System.out.println("2 - List all available products"); // Then customer can see a product detail
            System.out.println("3 - Search products by a category");
            System.out.println("4 - Sort all products by price");
            System.out.println("5 - Exit");
//            int choice = s.nextInt(); // Need to validate input
//            switch (choice) {
//                case 1:
//            }
            break;

        }


    }
}
