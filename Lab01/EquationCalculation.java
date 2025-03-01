import javax.swing.JOptionPane;

public class EquationCalculation {
    public static void main(String[] args) {

        String choice;

        while(true){
            choice = JOptionPane.showInputDialog(
                    null, "1. First-degree equation with one variable\n2. First-degree equations with two variables\n3. Second-degree equation with one variable\n4. Exit",
                    "Choose the function",
                    JOptionPane.INFORMATION_MESSAGE);
            if (choice == null || choice.equals("4")) {break;}

            switch (choice) {
                case "1" -> FirstFunction();
                case "2" -> SecondFunction();
                case "3" -> ThirdFunction();
                default -> JOptionPane.showMessageDialog(null, "Invalid choice! Please choose again.");
            }
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

    public static void SecondFunction() {
        try {
            double a11 = Double.parseDouble(JOptionPane.showInputDialog("Enter a11:"));
            double a12 = Double.parseDouble(JOptionPane.showInputDialog("Enter a12:"));
            double b1 = Double.parseDouble(JOptionPane.showInputDialog("Enter b1:"));
            double a21 = Double.parseDouble(JOptionPane.showInputDialog("Enter a21:"));
            double a22 = Double.parseDouble(JOptionPane.showInputDialog("Enter a22:"));
            double b2 = Double.parseDouble(JOptionPane.showInputDialog("Enter b2:"));

            double D = a11 * a22 - a21 * a12;
            double D1 = b1 * a22 - b2 * a12;
            double D2 = a11 * b2 - a21 * b1;

            if (D == 0) {
                if (D1 == 0 && D2 == 0) {
                    JOptionPane.showMessageDialog(null, "The system has infinitely many solutions.");
                } else {
                    JOptionPane.showMessageDialog(null, "The system has no solution.");
                }
            } else {
                double x1 = D1 / D;
                double x2 = D2 / D;
                JOptionPane.showMessageDialog(null, "The solution is:\nx1 = " + x1 + "\nx2 = " + x2);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input! Please enter numeric values.");
        }
    }

    public static void ThirdFunction() {
        String strA = JOptionPane.showInputDialog("Enter coefficient a (a ≠ 0):");
        String strB = JOptionPane.showInputDialog("Enter coefficient b:");
        String strC = JOptionPane.showInputDialog("Enter coefficient c:");
        try {
            double a = Double.parseDouble(strA);
            double b = Double.parseDouble(strB);
            double c = Double.parseDouble(strC);

            if (a == 0) {
                JOptionPane.showMessageDialog(null, "Coefficient a cannot be 0.");
                return;
            }

            double delta = b * b - 4 * a * c;

            if (delta > 0) {
                double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                JOptionPane.showMessageDialog(null, "The solutions are:\nx1 = " + x1 + "\nx2 = " + x2);
            } else if (delta == 0) {
                double x = -b / (2 * a);
                JOptionPane.showMessageDialog(null, "The equation has one double root: x = " + x);
            } else {
                JOptionPane.showMessageDialog(null, "The equation has no real roots.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input! Please enter numeric values.");
        }
    }
}
