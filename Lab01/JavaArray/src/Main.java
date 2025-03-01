import javax.swing.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog(null, "Enter the number of elements in the array:", "Input", JOptionPane.QUESTION_MESSAGE);
        int n;

        try {
            n = Integer.parseInt(input);
            if (n <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a positive integer.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double[] array = new double[n];
        for (int i = 0; i < n; i++) {
            while (true) {
                try {
                    input = JOptionPane.showInputDialog(null, "Enter element " + (i + 1) + ":", "Input", JOptionPane.QUESTION_MESSAGE);
                    array[i] = Double.parseDouble(input);
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        Arrays.sort(array);

        double sum = 0;
        for (double num : array) {
            sum += num;
        }
        double average = sum / n;

        // output
        StringBuilder result = new StringBuilder("Sorted Array: ");
        result.append(Arrays.toString(array)).append("\n");
        result.append("Sum: ").append(sum).append("\n");
        result.append("Average: ").append(average);

        JOptionPane.showMessageDialog(null, result.toString(), "Results", JOptionPane.INFORMATION_MESSAGE);

        System.exit(0);
    }
}