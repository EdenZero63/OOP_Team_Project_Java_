package service;

import java.util.ArrayList;
import model.Registration;
import model.Student;
import exception.EventFullException;
import exception.NotFoundException;

/* ---- WORK IN PROGRESS ---- */

public class EventRegistrationManager {
      private Registration[] registrations;
      private Event event;      // Each EventRegistrationManager would manage student registrations for the one event. Makes seaching registrations easier.
      private int numRegistrations;
      

      public EventRegistrationManager(Event event) {
            this.event = event;
            this.registraions = new Registration[event.getCapacity()];
            this.numRegistrations = 0;
      }

      public void registerStudent(String registrationID, Student student, String registrationDate) 
        throws NotFoundException, EventFullException {
              if (numRegistration > this.event.getCapacity()) { throw new EventFullException(this.event.getEventName() + " is full."); }
              for (int i = 0; i < this.registrations.size(); i++) {
                    if (student == this.registrations.get(i).getStudent()) {
                          throw new NotFoundException("Student already registered for Event.");
                    }
              }
              this.registrations.add(new Registration(registrationId, student, this.event, registrationDate));
              this.numRegistrations++;
      }

      public void cancelRegistration(Student student)
        throws NotFoundException {
              // code to delete registration
              for (int i = 0; i < this.registrations.size(); i++) {
                    if (student == this.registrations.get(i).getStudent()) {
                          this.registrations.remove(i);
                          this.numRegistrations--;
                          return;
                    }
              }
                  throw new NotFoundException("Student not registered for Event.");
        }

      public String getEventID() { return this.event.getEventID(); }
      public Event getEvent() { return this.event; }
}



public class RegistrationManager {

      private ArrayList<EventRegistrationManager> eventRegistrationManagers;      // Replace with ArrayList<EventRegistrationManager>
      private StudentManager studentManager;
      private EventManager eventManager;

      public RegistrationManager(StudentManager studentManager){
        this.studentManager = studentManager;
        this.eventManager = eventManager;
        this.eventRegistrationManagers = new ArrayList<>();
      }

      public void addEventManager(Event event) {      // To be called by the event manager each time a new event is created.
            this.eventRegistrationManagers.add(new EventRegistrationManager(event));
      }
      public void removeEventManager(Event event)
        throws NotFoundException {      // To be called by the event manager each time an event is removed.
            for (int i = 0; i < this.registrations.size(); i++) {
                    if (event == this.eventRegistrationManagers.get(i).getEvent()) {
                          this.eventRegistrationManagers.remove(i);
                          return;
                    }
              }
              throw new NotFoundException("Event does not have a manager set up.");
      }

      /*IP -- still need to check event -- currently only checks if student exsists! */
       public void registerStudent(String studentId, String eventId, String registrationDate)
        throws NotFoundException, EventFullException {
          Student student = studentManager.findStudentById(studentId);
          Event event = eventManager.findEventById(eventId);

          if (student == null) {
            throw new NotFoundException("Student not found.");
            }
          if (event == null) {
            throw new NotFoundException("Event not found.");
            }

              // code to search for if the student has registered for that event.
              for (int i = 0; i < this.eventRegistrationManagers.size(); i++) {
                    if (event == this.eventRegistrationManagers.get(i).getEvent()) {
                          // code to generate or input registrationId value.
                          // IP -- code to generate or input value for registrationId.
                          String registrationId = "";
                          this.eventRegistrationManagers.get(i).registerStudent(registrationId, student, registrationDate);
                          return;
                    }
              }
                  throw new NotFoundException("Event does not have a manager set up.");
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
                    if (event == this.eventRegistrationManagers.get(i).getEvent()) {
                          this.eventRegistrationManagers.get(i).cancelRegistration(student);
                          return;
                    }
              }
                  throw new NotFoundException("Event does not have a manager set up.");
                  
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
