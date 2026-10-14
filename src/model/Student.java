package model;

public class Student {
    private String studentId;
    private String name;
    private String email;
    private String major;

    public Student(String studentId, String name, String email, String major) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("A student needs an ID.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("A student needs a name.");
        }

        this.studentId = studentId.trim();
        this.name = name.trim();
        this.email = email == null ? "" : email.trim();
        this.major = major == null ? "" : major.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMajor() {
        return major;
    }

    public void displayStudent() {
        System.out.println("Student ID : " + studentId);
        System.out.println("Name       : " + name);
        System.out.println("Email      : " + email);
        System.out.println("Major      : " + major);
    }
}