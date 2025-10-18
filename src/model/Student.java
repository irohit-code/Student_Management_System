package model;

public class Student {
    private String id;
    private String name;           // Full Name
    private String department;
    private int year;              // Year of study
    private double marks;          // CGPA

    public Student(String id, String name, String department, int year, double marks) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.year = year;
        this.marks = marks;
    }

    // -------------------- Getters --------------------
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public int getYear() { return year; }
    public double getMarks() { return marks; }

    // -------------------- Setters --------------------
    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setYear(int year) { this.year = year; }
    public void setMarks(double marks) { this.marks = marks; }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Dept: %s | Year: %d | CGPA: %.2f",
                id, name, department, year, marks);
    }
}
