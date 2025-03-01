import javax.swing.JOptionPane;

public class EquationCalculation {
    public static void main(String[] args) {

    }
        String choice;

        while(true){
            choice = JOptionPane.showInputDialog(
                    null, "1. First-degree equation with one variable\n2. First-degree equations with two variables\n3. Second-degree equation with one variable\n4. Exit",
                    "Choose the function",
                    JOptionPane.INFORMATION_MESSAGE);
            if (choice == null || choice.equals("5")) {break;}

            switch (choice) {
                case "1":
                    FirstFunction();
                    break;
                case "2":
                    SecondFunction();
                    break;
                case "3":
                    ThirdFunction();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice! Please choose again.");
        }
    }

    public static void FirstFunction() {
        String strA = JOptionPane.showInputDialog("Enter coefficient a (a ≠ 0):");
        String strB = JOptionPane.showInputDialog("Enter coefficient b:");
        try {
            double a = Double.parseDouble(strA);
            double b = Double.parseDouble(strB);

            if (a == 0) {
                JOptionPane.showMessageDialog(null, "Coefficient a cannot be 0.");
            } else {
                double x = -b / a;
                JOptionPane.showMessageDialog(null, "The solution is: x = " + x);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input! Please enter numeric values.");
        }
    }

    public void SecondFunction() {

    }

    public void ThirdFunction() {

    }
}
