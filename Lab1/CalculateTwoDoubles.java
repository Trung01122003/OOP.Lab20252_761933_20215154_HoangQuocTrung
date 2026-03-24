import javax.swing.JOptionPane;

public class CalculateTwoDoubles {
    public static void main(String[] args) {
        double num1, num2;
        String strNum1, strNum2;

        strNum1 = JOptionPane.showInputDialog(null, "Please input the first number:", "Input first number", JOptionPane.INFORMATION_MESSAGE);
        strNum2 = JOptionPane.showInputDialog(null, "Please input the second number:", "Input second number", JOptionPane.INFORMATION_MESSAGE);

        try {
            num1 = Double.parseDouble(strNum1);
            num2 = Double.parseDouble(strNum2);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            return;
        }

        double sum = num1 + num2;
        double diff = num1 - num2;
        double product = num1 * num2;

        StringBuilder message = new StringBuilder();
        message.append("Sum: ").append(sum).append("\n");
        message.append("Difference: ").append(diff).append("\n");
        message.append("Product: ").append(product).append("\n");

        if (num2 == 0) {
            message.append("Quotient: Cannot divide by zero");
        } else {
            double quotient = num1 / num2;
            message.append("Quotient: ").append(quotient);
        }

        JOptionPane.showMessageDialog(null, message.toString(), "Calculation result", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}
