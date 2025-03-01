import javax.swing.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog(null, "Enter the length of matrix:", "Input", JOptionPane.QUESTION_MESSAGE);
        int n;
        try {
            n = Integer.parseInt(input);
            if (n <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a positive integer.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        input = JOptionPane.showInputDialog(null, "Enter the width of matrix:", "Input", JOptionPane.QUESTION_MESSAGE);
        int m;
        try {
            m = Integer.parseInt(input);
            if (m <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a positive integer.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double[][] array1 = new double[n][m];
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                while (true) {
                    try {
                        input = JOptionPane.showInputDialog(null, "Enter element [" + (i + 1) +"]" + "[" + (j + 1) +"] of first matrix:", "Input first array", JOptionPane.QUESTION_MESSAGE);
                        array1[i][j] = Double.parseDouble(input);
                        break;
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }

        double[][] array2 = new double[n][m];
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                while (true) {
                    try {
                        input = JOptionPane.showInputDialog(null, "Enter element [" + (i + 1) +"]" + "[" + (j + 1) +"] of second matrix:", "Input second array", JOptionPane.QUESTION_MESSAGE);
                        array2[i][j] = Double.parseDouble(input);
                        break;
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }

        double[][] result_array = new double[n][m];
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                result_array[i][j] = array1[i][j] + array2[i][j];
            }
        }


        // output
        StringBuilder result = new StringBuilder();
        for (double[] row : result_array) {
            result.append(Arrays.toString(row)).append("\n");
        }

        JOptionPane.showMessageDialog(null, "Sum of two matrices: \n" + result.toString(), "Results", JOptionPane.INFORMATION_MESSAGE);

        System.exit(0);
    }
}