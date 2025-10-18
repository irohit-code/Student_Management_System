package app;

import manager.StudentManager;
import model.Student;
import util.Validator;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": addStudent(); break;
                case "2": deleteStudent(); break;
                case "3": searchStudent(); break;
                case "4": updateStudent(); break;
                case "5": displayAll(); break;
                case "6": sortStudents(); break;
                case "0": 
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default: System.out.println("Invalid choice! Try again.");
            }
        }
        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n=== Student Management System ===");
        System.out.println("1. Add Student");
        System.out.println("2. Delete Student");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Display All Students");
        System.out.println("6. Sort Students");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    private static void addStudent() {
        System.out.println("\n-- Add Student --");

        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        if (!Validator.isValidId(id)) { System.out.println("Invalid ID!"); return; }

        System.out.print("Enter Full Name: ");
        String name = scanner.nextLine();
        if (!Validator.isValidName(name)) { System.out.println("Invalid Name!"); return; }

        System.out.print("Enter Department (Computer Science / IT / AI & DS / Civil / Mechanical): ");
        String dept = scanner.nextLine();
        if (!Validator.isValidDepartment(dept)) { System.out.println("Invalid Department!"); return; }

        System.out.print("Enter Year of Study (1-4): ");
        String yearStr = scanner.nextLine();
        if (!Validator.isValidYear(yearStr)) { System.out.println("Invalid Year!"); return; }

        System.out.print("Enter CGPA (0.0 - 10.0): ");
        String cgpaStr = scanner.nextLine();
        if (!Validator.isValidCGPA(cgpaStr)) { System.out.println("Invalid CGPA!"); return; }

        Student student = new Student(id, name, dept, Integer.parseInt(yearStr), Double.parseDouble(cgpaStr));
        if (StudentManager.addStudent(student)) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Student ID already exists!");
        }
    }

    private static void deleteStudent() {
        System.out.print("\nEnter Student ID to delete: ");
        String id = scanner.nextLine();
        if (StudentManager.deleteStudent(id)) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void searchStudent() {
        System.out.print("\nSearch by (1) ID or (2) Name? Enter 1 or 2: ");
        String option = scanner.nextLine();
        if ("1".equals(option)) {
            System.out.print("Enter ID: ");
            String id = scanner.nextLine();
            Student s = StudentManager.searchById(id);
            if (s != null) System.out.println(s);
            else System.out.println("Student not found!");
        } else if ("2".equals(option)) {
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            ArrayList<Student> results = StudentManager.searchByName(name);
            if (results.isEmpty()) System.out.println("No students found!");
            else results.forEach(System.out::println);
        } else {
            System.out.println("Invalid option!");
        }
    }

    private static void updateStudent() {
        System.out.print("\nEnter Student ID to update: ");
        String id = scanner.nextLine();
        Student s = StudentManager.searchById(id);
        if (s == null) { System.out.println("Student not found!"); return; }

        System.out.print("Enter New Full Name (" + s.getName() + "): ");
        String name = scanner.nextLine();
        if (name.isEmpty()) name = s.getName();
        else if (!Validator.isValidName(name)) { System.out.println("Invalid Name!"); return; }

        System.out.print("Enter New Department (" + s.getDepartment() + "): ");
        String dept = scanner.nextLine();
        if (dept.isEmpty()) dept = s.getDepartment();
        else if (!Validator.isValidDepartment(dept)) { System.out.println("Invalid Dept!"); return; }

        System.out.print("Enter New Year of Study (" + s.getYear() + "): ");
        String yearStr = scanner.nextLine();
        int year = s.getYear();
        if (!yearStr.isEmpty()) {
            if (!Validator.isValidYear(yearStr)) { System.out.println("Invalid Year!"); return; }
            year = Integer.parseInt(yearStr);
        }

        System.out.print("Enter New CGPA (" + s.getMarks() + "): ");
        String cgpaStr = scanner.nextLine();
        double cgpa = s.getMarks();
        if (!cgpaStr.isEmpty()) {
            if (!Validator.isValidCGPA(cgpaStr)) { System.out.println("Invalid CGPA!"); return; }
            cgpa = Double.parseDouble(cgpaStr);
        }

        if (StudentManager.updateStudent(id, name, dept, year, cgpa)) {
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Update failed!");
        }
    }

    private static void displayAll() {
        System.out.println("\n-- All Students --");
        ArrayList<Student> students = StudentManager.getAllStudents();
        if (students.isEmpty()) System.out.println("No students found!");
        else students.forEach(System.out::println);
    }

    private static void sortStudents() {
        System.out.print("\nSort by (id/name/cgpa): ");
        String field = scanner.nextLine();
        ArrayList<Student> sorted = StudentManager.getSortedStudents(field);
        if (sorted.isEmpty()) System.out.println("No students found!");
        else sorted.forEach(System.out::println);
    }
}
