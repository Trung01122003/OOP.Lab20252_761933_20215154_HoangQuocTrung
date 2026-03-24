import javax.swing.JOptionPane;

public class HelloNameDialog {
    public static void main(String[] args) {
        String result;
        String str = JOptionPane.showInputDialog("Please enter your name:");
        result = "Hi " + str + "!";
        JOptionPane.showMessageDialog(null, result);
        System.exit(0);
    }
}
