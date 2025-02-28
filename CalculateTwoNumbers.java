import javax.swing.JOptionPane;

public class CalculateTwoNumbers {
    public static void main(String[] args) {
        String strNotification = "Result: \n";
        String strNum1, strNum2;

        strNum1 = JOptionPane.showInputDialog(
            null, 
            "Please input the first number: ", 
            "Input the first number",
            JOptionPane.INFORMATION_MESSAGE);
        //strNotification += strNum1 + " and ";

        strNum2 = JOptionPane.showInputDialog(
            null, 
            "Please input the second number: ", 
            "Input the second number",
            JOptionPane.INFORMATION_MESSAGE);
        //strNotification += strNum2;

        double num1 = Double.parseDouble(strNum1);
        double num2 = Double.parseDouble(strNum2);

        strNotification += "Sum: " + (num1+num2) +"\n";
        strNotification += "Difference: " + (num1-num2) +"\n";
        strNotification += "Product: " + (num1*num2) +"\n";
        if(num2 == 0) {
            JOptionPane.showMessageDialog(
                null, "Divisor not valid",
                "Warning", 
                JOptionPane.INFORMATION_MESSAGE);
        }
        else strNotification += "Quotient: " + (num1/num2) +"\n";


        JOptionPane.showMessageDialog(
            null, strNotification,
            "Calculation Result", 
            JOptionPane.INFORMATION_MESSAGE);

        System.exit(0);
    }
}