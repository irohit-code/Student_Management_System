package util;

public class Validator {

    // Validate ID (non-empty, alphanumeric)
    public static boolean isValidId(String id) {
        return id != null && id.matches("[A-Za-z0-9]+");
    }

    // Validate Full Name (only letters and spaces)
    public static boolean isValidName(String name) {
        return name != null && name.matches("[A-Za-z ]+");
    }

    // Validate Department (must be one of predefined)
    public static boolean isValidDepartment(String dept) {
        if (dept == null) return false;
        String[] departments = { "Computer Science", "IT", "AI & DS", "Civil", "Mechanical" };
        for (String d : departments) {
            if (d.equalsIgnoreCase(dept)) return true;
        }
        return false;
    }

    // Validate Year (1-4)
    public static boolean isValidYear(String yearStr) {
        try {
            int year = Integer.parseInt(yearStr);
            return year >= 1 && year <= 4;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validate CGPA (0.0 - 10.0)
    public static boolean isValidCGPA(String cgpaStr) {
        try {
            double cgpa = Double.parseDouble(cgpaStr);
            return cgpa >= 0.0 && cgpa <= 10.0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
