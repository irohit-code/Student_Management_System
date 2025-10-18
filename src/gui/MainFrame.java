package gui;

import manager.StudentManager;
import model.Student;
import util.Validator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    private JTable studentTable;
    private DefaultTableModel tableModel;

    public MainFrame() {
        setTitle("Student Management System");
        setSize(850, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        JButton addBtn = new JButton("Add Student");
        JButton deleteBtn = new JButton("Delete Student");
        JButton searchBtn = new JButton("Search Student");
        JButton updateBtn = new JButton("Update Student");
        JButton displayBtn = new JButton("Display All");
        JButton sortBtn = new JButton("Sort Students");

        buttonPanel.add(addBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(searchBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(displayBtn);
        buttonPanel.add(sortBtn);
        add(buttonPanel, BorderLayout.WEST);

        // Table for displaying students
        String[] columnNames = { "ID", "Full Name", "Department", "Year", "CGPA" };
        tableModel = new DefaultTableModel(columnNames, 0);
        studentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        add(scrollPane, BorderLayout.CENTER);

        // Action Listeners
        addBtn.addActionListener(e -> addStudent());
        deleteBtn.addActionListener(e -> deleteStudent());
        searchBtn.addActionListener(e -> searchStudent());
        updateBtn.addActionListener(e -> updateStudent());
        displayBtn.addActionListener(e -> displayAll());
        sortBtn.addActionListener(e -> sortStudents());

        // Initial load
        displayAll();
    }

    // -------------------- GUI Methods --------------------

    private void addStudent() {
        JTextField idField = new JTextField(5);
        JTextField nameField = new JTextField(15);
        String[] departments = { "Computer Science", "IT", "AI & DS", "Civil", "Mechanical" };
        JComboBox<String> deptCombo = new JComboBox<>(departments);
        JTextField yearField = new JTextField(5);
        JTextField cgpaField = new JTextField(5);

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Full Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Department:"));
        panel.add(deptCombo);
        panel.add(new JLabel("Year of Study (1-4):"));
        panel.add(yearField);
        panel.add(new JLabel("CGPA (0-10):"));
        panel.add(cgpaField);

        int result = JOptionPane.showConfirmDialog(this, panel,
                "Enter Student Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String id = idField.getText().trim();
            String fullName = nameField.getText().trim();
            String department = (String) deptCombo.getSelectedItem();
            String yearStr = yearField.getText().trim();
            String cgpaStr = cgpaField.getText().trim();

            // Validation
            if (!Validator.isValidId(id)) { showError("Invalid ID!"); return; }
            if (!Validator.isValidName(fullName)) { showError("Invalid Full Name!"); return; }
            if (!Validator.isValidDepartment(department)) { showError("Invalid Department!"); return; }
            if (!Validator.isValidYear(yearStr)) { showError("Invalid Year!"); return; }
            if (!Validator.isValidCGPA(cgpaStr)) { showError("Invalid CGPA!"); return; }

            Student student = new Student(
                    id,
                    fullName,
                    department,
                    Integer.parseInt(yearStr),
                    Double.parseDouble(cgpaStr)
            );

            if (StudentManager.addStudent(student)) {
                showMessage("Student added successfully!");
                displayAll();
            } else {
                showError("Student ID already exists!");
            }
        }
    }

    private void deleteStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) { showError("Select a student to delete!"); return; }
        String id = (String) tableModel.getValueAt(selectedRow, 0);
        if (StudentManager.deleteStudent(id)) {
            showMessage("Student deleted successfully!");
            displayAll();
        } else {
            showError("Student not found!");
        }
    }

    private void searchStudent() {
        String option = JOptionPane.showInputDialog(this, "Search by 1) ID 2) Full Name? Enter 1 or 2:");
        tableModel.setRowCount(0);

        if ("1".equals(option)) {
            String id = JOptionPane.showInputDialog(this, "Enter ID:");
            Student s = StudentManager.searchById(id);
            if (s != null) addStudentToTable(s);
            else showError("Student not found!");
        } else if ("2".equals(option)) {
            String fullName = JOptionPane.showInputDialog(this, "Enter Full Name:");
            ArrayList<Student> results = StudentManager.searchByName(fullName);
            if (results.isEmpty()) showError("No students found!");
            else results.forEach(this::addStudentToTable);
        } else {
            showError("Invalid option!");
        }
    }

    private void updateStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) { showError("Select a student to update!"); return; }

        String id = (String) tableModel.getValueAt(selectedRow, 0);
        Student s = StudentManager.searchById(id);
        if (s == null) { showError("Student not found!"); return; }

        JTextField nameField = new JTextField(s.getName(), 15);
        String[] departments = { "Computer Science", "IT", "AI & DS", "Civil", "Mechanical" };
        JComboBox<String> deptCombo = new JComboBox<>(departments);
        deptCombo.setSelectedItem(s.getDepartment());
        JTextField yearField = new JTextField(String.valueOf(s.getYear()), 5);
        JTextField cgpaField = new JTextField(String.valueOf(s.getMarks()), 5);

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.add(new JLabel("Full Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Department:"));
        panel.add(deptCombo);
        panel.add(new JLabel("Year of Study (1-4):"));
        panel.add(yearField);
        panel.add(new JLabel("CGPA (0-10):"));
        panel.add(cgpaField);

        int result = JOptionPane.showConfirmDialog(this, panel,
                "Update Student Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String fullName = nameField.getText().trim();
            String department = (String) deptCombo.getSelectedItem();
            String yearStr = yearField.getText().trim();
            String cgpaStr = cgpaField.getText().trim();

            if (!Validator.isValidName(fullName)) { showError("Invalid Full Name!"); return; }
            if (!Validator.isValidDepartment(department)) { showError("Invalid Department!"); return; }
            if (!Validator.isValidYear(yearStr)) { showError("Invalid Year!"); return; }
            if (!Validator.isValidCGPA(cgpaStr)) { showError("Invalid CGPA!"); return; }

            boolean updated = StudentManager.updateStudent(
                    id,
                    fullName,
                    department,
                    Integer.parseInt(yearStr),
                    Double.parseDouble(cgpaStr)
            );

            if (updated) {
                showMessage("Student updated successfully!");
                displayAll();
            } else {
                showError("Update failed!");
            }
        }
    }

    private void displayAll() {
        tableModel.setRowCount(0);
        ArrayList<Student> students = StudentManager.getAllStudents();
        students.forEach(this::addStudentToTable);
    }

    private void sortStudents() {
        String field = JOptionPane.showInputDialog(this, "Sort by (id/name/cgpa):");
        ArrayList<Student> sorted = StudentManager.getSortedStudents(field);
        tableModel.setRowCount(0);
        sorted.forEach(this::addStudentToTable);
    }

    // -------------------- Helper Methods --------------------
    private void addStudentToTable(Student s) {
        tableModel.addRow(new Object[] {
                s.getId(),
                s.getName(),
                s.getDepartment(),
                s.getYear(),
                s.getMarks()
        });
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // -------------------- Main --------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}