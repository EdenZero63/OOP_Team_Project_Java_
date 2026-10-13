package service;

import java.util.ArrayList;
import model.Registration;
import model.Student;
import exception.NotFoundException;

/* ---- WORK IN PROGRESS ---- */


public class RegistrationManager {

      private ArrayList<Registration> registrations;
      private StudentManager studentManager;

      public RegistrationManager(StudentManager studentManager){
        this.studentManager = studentManager;
        registraions = new ArrayList<>();
      }

      /*IP -- still need to check event -- currently only checks if student exsists! */
      public void registerStudent(String studentId, String eventId, String registrationDate)
        throws NotFoundException {
          Student student = studentManager.findStudentById(studentId);

          if (student == null) {
            throw new NotFoundException("Student not found.");
        }

        /*  NEED: 
          -EventManager.java  
          -EventSystem.java

          In progress:
          Check if event exists 
          Prevent double signups 
          Check capacity 
          Add registraions
        */
      } 
}