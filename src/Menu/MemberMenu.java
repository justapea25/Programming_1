package Menu;

import Account.Member;
import Product.ListOfProduct;

import java.util.Scanner;

public class MemberMenu {
    public static void main(Member member) {
        Scanner sc = new Scanner(System.in);
        ListOfProduct productList = new ListOfProduct();
        productList.readProducts();
    }
}
