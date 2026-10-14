package service;

import java.time.LocalDate;
import java.util.ArrayList;
import model.Event;
import model.Registration;
import model.Student;
import exception.DuplicateRegistrationException;
import exception.EventFullException;
import exception.NotFoundException;

public class RegistrationManager {

      private ArrayList<Registration> registrations;
      private StudentManager studentManager;
      private EventManager eventManager;

      public RegistrationManager(StudentManager studentManager, EventManager eventManager){
        this.studentManager = studentManager;
        this.eventManager = eventManager;
        this.registrations = new ArrayList<>();
      }

      //register dated today
      public Registration registerStudent(String registrationId, String studentId, String eventId)
        throws NotFoundException, DuplicateRegistrationException, EventFullException {
          return registerStudent(registrationId, studentId, eventId, LocalDate.now());
      }

      //checks student, event, double sign-up and capacity before adding
      public Registration registerStudent(String registrationId, String studentId, String eventId, LocalDate registrationDate)
        throws NotFoundException, DuplicateRegistrationException, EventFullException {
          Student student = studentManager.findStudentById(studentId);
          Event event = eventManager.findEventById(eventId);

          if (student == null) {
            throw new NotFoundException("Student not found.");
            }
          if (event == null) {
            throw new NotFoundException("Event not found.");
            }

              int studentsRegistered = 0;
              // code to search for if the student has registered for that event.
              for (int i = 0; i < this.registrations.size(); i++) {
                    if (event == this.registrations.get(i).getEvent()) {
                          if (student == this.registrations.get(i).getStudent()) { throw new DuplicateRegistrationException(student.getName() + " is already registered for " + event.getEventName() + "."); }
                          studentsRegistered++;
                    }
              }
              if (studentsRegistered >= event.getCapacity()) { throw new EventFullException(event.getEventName() + " is full."); }

              Registration registration = new Registration(registrationId, student, event, registrationDate);
              this.registrations.add(registration);
              return registration;
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

      public void cancelRegistration(String registrationId)
            throws NotFoundException {
              // code to delete registration
              for (int i = 0; i < this.registrations.size(); i++) {
                    if (registrationId.equals(this.registrations.get(i).getRegistrationId())) {
                          this.registrations.remove(i);
                          return;
                    }
              }
                  throw new NotFoundException("Registration not found.");

      }

      //events one student signed up for (menu option 8)
      public ArrayList<Event> getEventsForStudent(String studentId)
            throws NotFoundException {
          Student student = studentManager.findStudentById(studentId);

          if (student == null) {
            throw new NotFoundException("Student not found.");
            }

              ArrayList<Event> events = new ArrayList<>();
              for (int i = 0; i < this.registrations.size(); i++) {
                    if (student == this.registrations.get(i).getStudent()) {
                          events.add(this.registrations.get(i).getEvent());
                    }
              }
              return events;
      }

      //students signed up for one event (menu option 9)
      public ArrayList<Student> getStudentsForEvent(String eventId)
            throws NotFoundException {
          Event event = eventManager.findEventById(eventId);

          if (event == null) {
            throw new NotFoundException("Event not found.");
            }

              ArrayList<Student> students = new ArrayList<>();
              for (int i = 0; i < this.registrations.size(); i++) {
                    if (event == this.registrations.get(i).getEvent()) {
                          students.add(this.registrations.get(i).getStudent());
                    }
              }
              return students;
      }

      public ArrayList<Registration> getRegistrations() { return this.registrations; }
}
