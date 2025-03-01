import javax.swing.JOptionPane;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        HashMap<String, Integer> daysInMonths = new HashMap<>();
        daysInMonths.put("January", 31);
        daysInMonths.put("February", 28);
        daysInMonths.put("March", 31);
        daysInMonths.put("April", 30);
        daysInMonths.put("May", 31);
        daysInMonths.put("June", 30);
        daysInMonths.put("July", 31);
        daysInMonths.put("August", 31);
        daysInMonths.put("September", 30);
        daysInMonths.put("October", 31);
        daysInMonths.put("November", 30);
        daysInMonths.put("December", 31);

        while (true) {
            // nhap thang
            String monthInput = JOptionPane.showInputDialog(null, "Enter the month (e.g., January, Jan., Jan, or 1):");
            String month = normalizeMonth(monthInput);

            if (month == null || !daysInMonths.containsKey(month)) {
                JOptionPane.showMessageDialog(null, "Invalid month. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            // nhap nam
            String yearInput = JOptionPane.showInputDialog(null, "Enter the year (a non-negative integer):");
            if (!isValidYear(yearInput)) {
                JOptionPane.showMessageDialog(null, "Invalid year. Please enter a non-negative integer.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            int year = Integer.parseInt(yearInput);
            int days = daysInMonths.get(month);

            // xu ly nam nhuan
            if (month.equals("February") && isLeapYear(year)) {
                days = 29;
            }

            //output
            JOptionPane.showMessageDialog(null, "Month: " + month + "\nYear: " + year + "\nDays: " + days, "Result", JOptionPane.INFORMATION_MESSAGE);
            break;
        }
    }

    // xu ly input thang
    public static String normalizeMonth(String input) {
        if (input == null) return null;

        input = input.trim().toLowerCase();
        return switch (input) {
            case "1", "jan", "jan.", "january" -> "January";
            case "2", "feb", "feb.", "february" -> "February";
            case "3", "mar", "mar.", "march" -> "March";
            case "4", "apr", "apr.", "april" -> "April";
            case "5", "may" -> "May";
            case "6", "jun", "jun.", "june" -> "June";
            case "7", "jul", "jul.", "july" -> "July";
            case "8", "aug", "aug.", "august" -> "August";
            case "9", "sep", "sep.", "september" -> "September";
            case "10", "oct", "oct.", "october" -> "October";
            case "11", "nov", "nov.", "november" -> "November";
            case "12", "dec", "dec.", "december" -> "December";
            default -> null;
        };
    }

    // xu ly input nam
    public static boolean isValidYear(String year) {
        if (year == null || year.isEmpty()) return false;
        for (char c : year.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    // check nam nhuan
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
