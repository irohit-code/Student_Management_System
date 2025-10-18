package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import model.Student;

public class JsonUtils {

    private static final String FILE_PATH = "data/students.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Save list of students to JSON file
    public static void saveStudents(ArrayList<Student> students) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            gson.toJson(students, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    // Load list of students from JSON file
    public static ArrayList<Student> loadStudents() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // create directories if missing
                file.createNewFile();          // create empty file
                saveStudents(students);        // save empty list
            } else {
                FileReader reader = new FileReader(FILE_PATH);
                Type studentListType = new TypeToken<ArrayList<Student>>() {}.getType();
                ArrayList<Student> loadedStudents = gson.fromJson(reader, studentListType);
                reader.close();
                if (loadedStudents != null) {
                    students = loadedStudents;
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
        return students;
    }
}
