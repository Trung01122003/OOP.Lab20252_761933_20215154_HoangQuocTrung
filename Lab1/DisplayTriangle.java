import java.util.Scanner;

public class DisplayTriangle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;

        do {
            System.out.print("Enter triangle height n (>0): ");
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter an integer: ");
                scanner.next();
            }
            n = scanner.nextInt();
        } while (n <= 0);

        for (int i = 1; i <= n; i++) {
            int spaces = n - i;
            int stars = 2 * i - 1;

            for (int s = 0; s < spaces; s++) {
                System.out.print(" ");
            }
            for (int j = 0; j < stars; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        scanner.close();
    }
}
