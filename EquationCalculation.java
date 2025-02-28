import javax.swing.JOptionPane;

public class EquationCalculation {
    public static void main(String[] args) {
        // String strNotification = "Result: \n";

        String choice;

        choice = JOptionPane.showInputDialog(
            null, "1. First-degree equation with one variable\n2. First-degree equations with two variables\n3. Second-degree equation with one variable\n4. Exit",
            "Choose the function", 
            JOptionPane.INFORMATION_MESSAGE);


        switch (choice) {
            case "1" -> {
                EquationCalculation eqCalc = new EquationCalculation();
                eqCalc.FirstFunction();
            }

            default -> System.exit(0);
        }
        System.exit(0);
    }

    public void FirstFunction() {

    }
}
