package Functions;

import java.util.Scanner;

public class ValidateInput {
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
}
