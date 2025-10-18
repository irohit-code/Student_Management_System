# Student Management System

## Project Overview
The **Student Management System** is a Java-based application for managing student records efficiently. It allows adding, deleting, updating, searching, displaying, and sorting student information. The system is designed for simplicity and uses a **Swing GUI** for user-friendly interaction, and **JSON files** for data persistence.

This project is ideal for beginners and intermediate Java developers to demonstrate skills in **Object-Oriented Programming (OOP), GUI design, data structures (HashMap), and file handling**.

---

## Features
1. **Add Student**
   - Store student details including:
     - ID (unique)
     - Full Name
     - Department (Computer Science, IT, AI & DS, Civil, Mechanical)
     - Year of Study (1-4)
     - CGPA (0.0 – 10.0)
   - Input validation for all fields.

2. **Delete Student**
   - Remove a student record by ID.

3. **Search Student**
   - Search by **ID** or **Full Name**.
   - Supports multiple matches for name-based search.

4. **Update Student**
   - Update all fields except student ID.
   - Validates inputs before updating.

5. **Display All Students**
   - View all student records in a **tabular format** (GUI or console).

6. **Sort Students**
   - Sort by **ID**, **Full Name**, or **CGPA**.

7. **Persistent Storage**
   - Student data is stored in **JSON files**, allowing data to persist between program runs.

---

## Tech Stack
- **Programming Language:** Java  
- **GUI Framework:** Swing  
- **Data Storage:** JSON  
- **Core Concepts Used:**  
  - Object-Oriented Programming (OOP)  
  - HashMap for fast student lookup  
  - ArrayList for maintaining student records  
  - Input validation  

---

## Folder Structure
```
StudentManagementSystem/
│
├─ src/
│  ├─ app/
│  │   └─ App.java                # Console-based interface
│  ├─ gui/
│  │   └─ MainFrame.java          # GUI-based interface
│  ├─ manager/
│  │   └─ StudentManager.java     # Core student management operations
│  ├─ model/
│  │   └─ Student.java            # Student class
│  └─ util/
│      └─ Validator.java          # Validation methods
│      └─ JsonUtils.java          # JSON file handling
│
├─ data/
│  └─ students.json               # Persistent student data
│
└─ README.md
```

---

## Installation
1. **Prerequisites:**
   - Java JDK 11 or higher installed.
   - IDE like IntelliJ IDEA, Eclipse, or VS Code (optional but recommended).

2. **Clone the Repository:**
   ```bash
   git clone <your-repo-url>
   cd StudentManagementSystem
   ```

3. **Build & Run:**
   - **GUI Version:** Run `MainFrame.java`  
   - **Console Version:** Run `App.java`

4. **Dependencies:**  
   - No external dependencies required. All functionality is built using **standard Java libraries**.

---

## Usage
### GUI Version
1. Launch `MainFrame.java`.
2. Use buttons on the left panel to perform actions:
   - Add, Delete, Search, Update, Display, Sort.
3. Student data will appear in the table.
4. All inputs are validated automatically.

### Console Version
1. Launch `App.java`.
2. Follow the menu options:
   ```
   1. Add Student
   2. Delete Student
   3. Search Student
   4. Update Student
   5. Display All Students
   6. Sort Students
   0. Exit
   ```
3. Enter data as prompted. Validation is performed automatically.

---

## Validation Rules
| Field         | Rules |
|---------------|-------|
| ID            | Must be unique, non-empty. |
| Full Name     | Only alphabets and spaces allowed. |
| Department    | Must be one of: Computer Science, IT, AI & DS, Civil, Mechanical |
| Year of Study | Integer between 1 and 4. |
| CGPA          | Decimal between 0.0 and 10.0 |

---

## JSON Storage Structure
```json
[
  {
    "id": "S101",
    "fullName": "John Doe",
    "department": "Computer Science",
    "yearOfStudy": 2,
    "cgpa": 8.5
  },
  {
    "id": "S102",
    "fullName": "Alice Smith",
    "department": "IT",
    "yearOfStudy": 3,
    "cgpa": 9.1
  }
]
```

---

## Future Enhancements
- Add **login system** for admin and users.
- Include **export to Excel or CSV** functionality.
- Implement **graphical charts** for student performance.
- Add **department-wise or CGPA-range filtering**.

---

## Author
**Rohit** – Recent AI & Data Science graduate, passionate about Java development and software projects for resume building.

---

## License
MIT License (free to use and modify)