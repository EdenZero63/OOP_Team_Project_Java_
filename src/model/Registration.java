package model;

import java.time.LocalDate;

public class Registration{
    //fields
    private String registrationId;
    private Student student;
    private Event event;
    private LocalDate registrationDate;

    //constructors, date defaults to today
    public Registration(String registrationId, Student student, Event event){
        this(registrationId, student, event, LocalDate.now());
    }

    public Registration(String registrationId, Student student, Event event, LocalDate registrationDate){
        if (student == null || event == null) {
            throw new IllegalArgumentException("A registration needs both a student and an event.");
        }

        this.registrationId = registrationId;
        this.student = student;
        this.event = event;
        this.registrationDate = registrationDate;
    }

    //getters
    public String getRegistrationId(){
        return registrationId;
    }

    public Student getStudent(){
        return student;
    }

    public Event getEvent(){
        return event;
    }

    public LocalDate getRegistrationDate(){
        return registrationDate;
    }

    //display
    public void displayRegistration(){
        System.out.println("Registration ID : " + registrationId);
        System.out.println("Student         : " + student.getName() + " (" + student.getStudentId() + ")");
        System.out.println("Event Type      : " + event.getClass().getSimpleName());
        System.out.println("Date            : " + registrationDate);
    }
}
