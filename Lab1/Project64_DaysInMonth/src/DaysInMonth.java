import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DaysInMonth {
    private static final Map<String, Integer> MONTH_MAP = new HashMap<String, Integer>();

    static {
        addMonth(1, "january", "jan.", "jan", "1");
        addMonth(2, "february", "feb.", "feb", "2");
        addMonth(3, "march", "mar.", "mar", "3");
        addMonth(4, "april", "apr.", "apr", "4");
        addMonth(5, "may", "5");
        addMonth(6, "june", "jun.", "jun", "6");
        addMonth(7, "july", "jul.", "jul", "7");
        addMonth(8, "august", "aug.", "aug", "8");
        addMonth(9, "september", "sept.", "sep", "sept", "9");
        addMonth(10, "october", "oct.", "oct", "10");
        addMonth(11, "november", "nov.", "nov", "11");
        addMonth(12, "december", "dec.", "dec", "12");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int month = readValidMonth(scanner);
        int year = readValidYear(scanner);

        int days = getDaysInMonth(month, year);

        System.out.println("Month " + month + " of year " + year + " has " + days + " days.");
        scanner.close();
    }

    private static void addMonth(int number, String... names) {
        for (String name : names) {
            MONTH_MAP.put(name.toLowerCase(), number);
        }
    }

    private static int readValidMonth(Scanner scanner) {
        while (true) {
            System.out.print("Enter month (name/abbr/number): ");
            String input = scanner.nextLine().trim().toLowerCase();
            Integer month = MONTH_MAP.get(input);
            if (month != null) {
                return month;
            }
            System.out.println("Invalid month. Please try again.");
        }
    }

    private static int readValidYear(Scanner scanner) {
        while (true) {
            System.out.print("Enter year (non-negative, full digits): ");
            String input = scanner.nextLine().trim();

            if (!input.matches("\\d+")) {
                System.out.println("Invalid year. Please enter digits only.");
                continue;
            }

            if (input.length() < 1) {
                System.out.println("Invalid year. Please try again.");
                continue;
            }

            try {
                int year = Integer.parseInt(input);
                if (year >= 0) {
                    return year;
                }
            } catch (NumberFormatException e) {
                // Very large number, ask user to input again.
            }

            System.out.println("Invalid year. Please try again.");
        }
    }

    private static int getDaysInMonth(int month, int year) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            default:
                return -1;
        }
    }

    private static boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        }
        if (year % 100 == 0) {
            return false;
        }
        return year % 4 == 0;
    }
}
