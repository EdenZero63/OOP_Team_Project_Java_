package service;

import java.util.ArrayList;
import model.Registration;
import model.Student;
import exception.NotFoundException;

/* ---- WORK IN PROGRESS ---- */

public class EventRegistrationManager {
      private ArrayList<Registration> registrations;
      private Event event;      // Each EventRegistrationManager would manage student registrations for the one event. Makes seaching registrations easier.

      public EventRegistrationManager(Event event) {
            this.event = event;
            registraions = new ArrayList<>();
      }

      public void registerStudent(String registrationID, Student student) {
            Registration registration = new Registration(registrationID, student, this.event);
      }

      public void cancleRegistration(Student student)
        throws NotFoundException {}

      public boolean getEventID() { return this.event.getEventID(); }

      public boolean studentRegistered(Student student) {
            for (Registration registration: this.registrations) {
                  if (student.equals(registration.getStudent()) { return true; }
            }
            return false;
      }
}



public class RegistrationManager {

      private ArrayList<Registration> registrations;      // Replace with ArrayList<EventRegistrationManager>
      private StudentManager studentManager;
      private EventManager eventManager;

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
              // dito for event

              // code to search for if the student has registered for that event.
        }

      public void cancelRegistration(String studentId, String eventId)
            throws NotFoundException {
          Student student = studentManager.findStudentById(studentId);

          if (student == null) {
            throw new NotFoundException("Student not found.");
            }
              // dito for event

            // code to delete registration
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
