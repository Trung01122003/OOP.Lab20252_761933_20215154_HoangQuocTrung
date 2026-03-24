import java.util.Scanner;

public class AddTwoMatrices {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = readPositiveInt(scanner, "Enter number of rows: ");
        int cols = readPositiveInt(scanner, "Enter number of columns: ");

        double[][] matrixA = new double[rows][cols];
        double[][] matrixB = new double[rows][cols];
        double[][] sum = new double[rows][cols];

        System.out.println("Enter elements of Matrix A:");
        inputMatrix(scanner, matrixA, "A");

        System.out.println("Enter elements of Matrix B:");
        inputMatrix(scanner, matrixB, "B");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sum[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }

        System.out.println("Matrix A:");
        printMatrix(matrixA);
        System.out.println("Matrix B:");
        printMatrix(matrixB);
        System.out.println("A + B:");
        printMatrix(sum);

        scanner.close();
    }

    private static int readPositiveInt(Scanner scanner, String prompt) {
        int value;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter an integer: ");
                scanner.next();
            }
            value = scanner.nextInt();
        } while (value <= 0);
        return value;
    }

    private static void inputMatrix(Scanner scanner, double[][] matrix, String name) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(name + "[" + i + "][" + j + "] = ");
                while (!scanner.hasNextDouble()) {
                    System.out.print("Invalid number. Enter " + name + "[" + i + "][" + j + "] again: ");
                    scanner.next();
                }
                matrix[i][j] = scanner.nextDouble();
            }
        }
    }

    private static void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
