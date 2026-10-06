package service;

import java.util.ArrayList;
import model.Student;

public class StudentManager {
    private ArrayList<Student> students;

    public StudentManager() {
        this.students = new ArrayList<Student>();
    }

    public Student findStudentById(String studentId) {
        if (studentId == null) {
            return null;
        }

        for (int i = 0; i < students.size(); i++) {
            Student currentStudent = students.get(i);
            if (currentStudent.getStudentId().equalsIgnoreCase(studentId.trim())) {
                return currentStudent;
            }
        }
        return null;
    }

    public boolean addStudent(Student student) {
        if (student == null) {
            System.out.println("Registration Error: Student data cannot be empty.");
            return false;
        }

        if (findStudentById(student.getStudentId()) != null) {
            System.out.println("Error: Student ID '" + student.getStudentId() + "' already exists!");
            return false;
        }

        students.add(student);
        System.out.println("Student '" + student.getName() + "' added successfully.");
        return true;
    }

    public void displayStudentById(String studentId) {
        Student currentStudent = findStudentById(studentId);
        if (currentStudent != null) {
            currentStudent.displayStudent();
        } else {
            System.out.println("Record not found for student ID: " + studentId);
        }
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students are currently enrolled.");
            return;
        }

        System.out.println("========================================");
        System.out.println("     STUDENT INFORMATION DIRECTORY      ");
        System.out.println("========================================");
        for (int i = 0; i < students.size(); i++) {
            Student currentStudent = students.get(i);
            currentStudent.displayStudent();
            System.out.println("----------------------------------------");
        }
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }
}