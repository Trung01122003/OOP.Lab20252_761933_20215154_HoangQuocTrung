import java.util.Arrays;
import java.util.Scanner;

public class ArraySortSumAverage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n;
        do {
            System.out.print("Enter number of elements n (>0): ");
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter an integer: ");
                scanner.next();
            }
            n = scanner.nextInt();
        } while (n <= 0);

        double[] arr = new double[n];
        double sum = 0;

        for (int i = 0; i < n; i++) {
            System.out.print("arr[" + i + "] = ");
            while (!scanner.hasNextDouble()) {
                System.out.print("Invalid number. Enter arr[" + i + "] again: ");
                scanner.next();
            }
            arr[i] = scanner.nextDouble();
            sum += arr[i];
        }

        Arrays.sort(arr);
        double average = sum / n;

        System.out.println("Sorted array: " + Arrays.toString(arr));
        System.out.println("Sum = " + sum);
        System.out.println("Average = " + average);

        scanner.close();
    }
}
