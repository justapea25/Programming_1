import java.util.regex.*;
import java.util.Scanner;

public class validateInput {
    public static String inputPatternCheck(String pattern) {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.matches(pattern))
                return input;
            System.out.println("Invalid Input, please try again: ");
        }
    }

    public static void main(String[] args) {
        System.out.println(inputPatternCheck("[a-zA-Z]*"));
    }
}
