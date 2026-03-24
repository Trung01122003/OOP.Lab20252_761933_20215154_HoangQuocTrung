import javax.swing.JOptionPane;

public class EquationSolver {
    public static void main(String[] args) {
        String[] options = {
            "Linear equation (ax + b = 0)",
            "Linear system (2 variables)",
            "Quadratic equation (ax^2 + bx + c = 0)"
        };

        String choice = (String) JOptionPane.showInputDialog(
            null,
            "Choose equation type:",
            "Equation Solver",
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );

        if (choice == null) {
            System.exit(0);
        }

        if (choice.equals(options[0])) {
            solveLinearEquation();
        } else if (choice.equals(options[1])) {
            solveLinearSystem();
        } else {
            solveQuadraticEquation();
        }

        System.exit(0);
    }

    private static void solveLinearEquation() {
        double a = inputDouble("Enter a:");
        double b = inputDouble("Enter b:");

        if (a == 0) {
            if (b == 0) {
                JOptionPane.showMessageDialog(null, "Infinitely many solutions.");
            } else {
                JOptionPane.showMessageDialog(null, "No solution.");
            }
            return;
        }

        double x = -b / a;
        JOptionPane.showMessageDialog(null, "Solution: x = " + x);
    }

    private static void solveLinearSystem() {
        double a11 = inputDouble("Enter a11:");
        double a12 = inputDouble("Enter a12:");
        double b1 = inputDouble("Enter b1:");
        double a21 = inputDouble("Enter a21:");
        double a22 = inputDouble("Enter a22:");
        double b2 = inputDouble("Enter b2:");

        double d = a11 * a22 - a21 * a12;
        double d1 = b1 * a22 - b2 * a12;
        double d2 = a11 * b2 - a21 * b1;

        if (d == 0) {
            if (d1 == 0 && d2 == 0) {
                JOptionPane.showMessageDialog(null, "Infinitely many solutions.");
            } else {
                JOptionPane.showMessageDialog(null, "No solution.");
            }
            return;
        }

        double x1 = d1 / d;
        double x2 = d2 / d;
        JOptionPane.showMessageDialog(null, "x1 = " + x1 + "\nx2 = " + x2);
    }

    private static void solveQuadraticEquation() {
        double a = inputDouble("Enter a:");
        double b = inputDouble("Enter b:");
        double c = inputDouble("Enter c:");

        if (a == 0) {
            if (b == 0) {
                if (c == 0) {
                    JOptionPane.showMessageDialog(null, "Infinitely many solutions.");
                } else {
                    JOptionPane.showMessageDialog(null, "No solution.");
                }
            } else {
                double x = -c / b;
                JOptionPane.showMessageDialog(null, "This is linear. Solution: x = " + x);
            }
            return;
        }

        double delta = b * b - 4 * a * c;

        if (delta < 0) {
            JOptionPane.showMessageDialog(null, "No real root.");
        } else if (delta == 0) {
            double x = -b / (2 * a);
            JOptionPane.showMessageDialog(null, "Double root: x1 = x2 = " + x);
        } else {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            JOptionPane.showMessageDialog(null, "x1 = " + x1 + "\nx2 = " + x2);
        }
    }

    private static double inputDouble(String message) {
        while (true) {
            String value = JOptionPane.showInputDialog(message);
            if (value == null) {
                System.exit(0);
            }
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid number. Try again.");
            }
        }
    }
}
