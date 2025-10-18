package manager;

import model.Student;
import util.JsonUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class StudentManager {

    // Main storage: HashMap for O(1) ID lookup
    private static HashMap<String, Student> studentMap = new HashMap<>();

    // Load students from JSON on startup
    static {
        ArrayList<Student> students = JsonUtils.loadStudents();
        for (Student s : students) {
            studentMap.put(s.getId(), s);
        }
    }

    // Add student
    public static boolean addStudent(Student student) {
        if (studentMap.containsKey(student.getId())) {
            return false; // ID already exists
        }
        studentMap.put(student.getId(), student);
        save();
        return true;
    }

    // Delete student by ID
    public static boolean deleteStudent(String id) {
        if (studentMap.containsKey(id)) {
            studentMap.remove(id);
            save();
            return true;
        }
        return false;
    }

    // Search by ID
    public static Student searchById(String id) {
        return studentMap.get(id);
    }

    // Search by Full Name (can have multiple)
    public static ArrayList<Student> searchByName(String fullName) {
        ArrayList<Student> results = new ArrayList<>();
        for (Student s : studentMap.values()) {
            if (s.getName().equalsIgnoreCase(fullName)) {
                results.add(s);
            }
        }
        return results;
    }

    // Update student (fields except ID)
    public static boolean updateStudent(String id, String fullName, String department, int yearOfStudy, double cgpa) {
        Student student = studentMap.get(id);
        if (student != null) {
            student.setName(fullName);
            student.setDepartment(department);
            student.setYear(yearOfStudy);
            student.setMarks(cgpa);
            save();
            return true;
        }
        return false;
    }

    // Display all students
    public static ArrayList<Student> getAllStudents() {
        return new ArrayList<>(studentMap.values());
    }

    // Sort students by Full Name, ID, or CGPA
    public static ArrayList<Student> getSortedStudents(String field) {
        ArrayList<Student> list = getAllStudents();
        switch (field.toLowerCase()) {
            case "name":
                Collections.sort(list, Comparator.comparing(Student::getName));
                break;
            case "id":
                Collections.sort(list, Comparator.comparing(Student::getId));
                break;
            case "cgpa":
                Collections.sort(list, Comparator.comparingDouble(Student::getMarks).reversed());
                break;
            default:
                break;
        }
        return list;
    }

    // Save current state to JSON
    private static void save() {
        JsonUtils.saveStudents(getAllStudents());
    }
}
