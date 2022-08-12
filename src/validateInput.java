import java.util.regex.*;
import java.util.Scanner;

public class validateInput {
    public static String inputPatternCheck(String pattern, String message) {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.matches(pattern))
                return input;
            System.out.println(message); // print if invalid
        }
    }

    public static void main(String[] args) {
        System.out.println(inputPatternCheck("[0-9]*", "Only enter number!"));
    }
}
