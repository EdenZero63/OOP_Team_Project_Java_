package service;

import java.util.ArrayList;
import model.Registration;
import model.Student;
import exception.NotFoundException;

/* ---- WORK IN PROGRESS ---- */



public class RegistrationManager {

      private ArrayList<Registration> registrations;      // Replace with ArrayList<EventRegistrationManager>
      private StudentManager studentManager;
      private EventManager eventManager;

      public RegistrationManager(StudentManager studentManager, EventManager eventManager){
        this.studentManager = studentManager;
        this.eventManager = eventManager;
        this.registraions = new ArrayList<>();
      }

      /*IP -- still need to check event -- currently only checks if student exsists! */
      public void registerStudent(String studentId, String eventId, String registrationDate)
        throws NotFoundException {
          Student student = studentManager.findStudentById(studentId);
          Event event = eventManager.findEventById(eventId);

          if (student == null) {
            throw new NotFoundException("Student not found.");
            }
          if (event == null) {
            throw new NotFoundException("Event not found.");
            }

              // code to search for if the student has registered for that event.
              for (int i = 0; i < this.registrations.size(); i++) {
                    if ((student == this.registrations.get(i).getStudent())&&(event == this.registrations.get(i).getEvent())) {
                          throw new NotFoundException("Student already registered for Event.");
                    }
              }

              // code to generate or input registrationId value.
              // IP -- code to generate or input value for registrationId.
              String registrationId = "";
              
              this.registrations.add(new Registration(registrationId, student, event, registrationDate));
        }

      public void cancelRegistration(String studentId, String eventId)
            throws NotFoundException {
          Student student = studentManager.findStudentById(studentId);
          Event event = eventManager.findEventById(eventId);

          if (student == null) {
            throw new NotFoundException("Student not found.");
            }
          if (event == null) {
            throw new NotFoundException("Event not found.");
            }

              // code to delete registration
              for (int i = 0; i < this.registrations.size(); i++) {
                    if ((student == this.registrations.get(i).getStudent())&&(event == this.registrations.get(i).getEvent())) {
                          this.registrations.remove(i);
                          return;
                    }
              }
                  throw new NotFoundException("Student not registered for Event.");
                  
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
