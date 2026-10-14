package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Base64;

import model.Student;
import model.Organizer;
import model.Event;
import model.AcademicEvent;
import model.ClubEvent;
import model.CareerEvent;
import model.SocialEvent;
import model.Registration;

import service.StudentManager;
import service.OrganizerManager;
import service.EventManager;
import service.RegistrationManager;

import exception.NotFoundException;
import exception.DuplicateRegistrationException;
import exception.EventFullException;

public class FileManager {

    private static final String DATA_FOLDER = "data";

    private static final String STUDENTS_FILE =
            DATA_FOLDER + "/students.txt";

    private static final String ORGANIZERS_FILE =
            DATA_FOLDER + "/organizers.txt";

    private static final String EVENTS_FILE =
            DATA_FOLDER + "/events.txt";

    private static final String REGISTRATIONS_FILE =
            DATA_FOLDER + "/registrations.txt";

    public FileManager() {
        new File(DATA_FOLDER).mkdirs();
    }

    // Encode text so that special characters do not break file columns.
    private String encode(String value) {
        if (value == null) {
            value = "";
        }

        return Base64.getEncoder().encodeToString(
                value.getBytes(StandardCharsets.UTF_8));
    }

    private String decode(String value) {
        return new String(
                Base64.getDecoder().decode(value),
                StandardCharsets.UTF_8);
    }

    private void writeLine(BufferedWriter writer, String... values)
            throws IOException {

        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                writer.write("|");
            }

            writer.write(encode(values[i]));
        }

        writer.newLine();
    }

    private String[] readLine(String line) {
        String[] parts = line.split("\\|", -1);

        for (int i = 0; i < parts.length; i++) {
            parts[i] = decode(parts[i]);
        }

        return parts;
    }

    // =====================================================
    // SAVE ALL DATA
    // =====================================================

    public void saveAll(
            StudentManager studentManager,
            OrganizerManager organizerManager,
            EventManager eventManager,
            RegistrationManager registrationManager) {

        saveStudents(studentManager);
        saveOrganizers(organizerManager);
        saveEvents(eventManager);
        saveRegistrations(registrationManager);
    }

    // =====================================================
    // LOAD ALL DATA
    // =====================================================

    public void loadAll(
            StudentManager studentManager,
            OrganizerManager organizerManager,
            EventManager eventManager,
            RegistrationManager registrationManager) {

        loadStudents(studentManager);
        loadOrganizers(organizerManager);

        // Events must be loaded before registrations.
        loadEvents(eventManager);

        loadRegistrations(registrationManager);
    }

    // =====================================================
    // STUDENTS
    // =====================================================

    private void saveStudents(StudentManager manager) {
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(STUDENTS_FILE))) {

            for (Student student : manager.getStudents()) {
                writeLine(writer,
                        student.getStudentId(),
                        student.getName(),
                        student.getEmail());
            }

        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    private void loadStudents(StudentManager manager) {
        File file = new File(STUDENTS_FILE);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    String[] data = readLine(line);

                    if (data.length != 3) {
                        continue;
                    }

                    if (manager.findStudentById(data[0]) == null) {
                        Student student =
                                new Student(data[0], data[1], data[2]);

                        manager.addStudent(student);
                    }

                } catch (Exception e) {
                    System.out.println("Skipping invalid student record.");
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
    }

    // =====================================================
    // ORGANIZERS
    // =====================================================

    private void saveOrganizers(OrganizerManager manager) {
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(ORGANIZERS_FILE))) {

            for (Organizer organizer : manager.getOrganizers()) {
                writeLine(writer,
                        organizer.getOrganizerId(),
                        organizer.getName(),
                        organizer.getEmail(),
                        organizer.getDepartment());
            }

        } catch (IOException e) {
            System.out.println("Error saving organizers: " + e.getMessage());
        }
    }

    private void loadOrganizers(OrganizerManager manager) {
        File file = new File(ORGANIZERS_FILE);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    String[] data = readLine(line);

                    if (data.length != 4) {
                        continue;
                    }

                    if (manager.findOrganizerById(data[0]) == null) {
                        Organizer organizer =
                                new Organizer(data[0], data[1],
                                              data[2], data[3]);

                        manager.addOrganizer(organizer);
                    }

                } catch (Exception e) {
                    System.out.println("Skipping invalid organizer record.");
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading organizers: " + e.getMessage());
        }
    }

    // =====================================================
    // EVENTS
    // =====================================================

    private void saveEvents(EventManager manager) {
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(EVENTS_FILE))) {

            for (Event event : manager.getEvents()) {
                String type = "";
                String extra = "";

                if (event instanceof AcademicEvent) {
                    type = "ACADEMIC";
                    // Replace this getter if your class uses a different name.
                    extra = ((AcademicEvent) event).getMajor();

                } else if (event instanceof ClubEvent) {
                    type = "CLUB";
                    extra = ((ClubEvent) event).getClubName();

                } else if (event instanceof CareerEvent) {
                    type = "CAREER";
                    extra = ((CareerEvent) event).getCompanyName();

                } else if (event instanceof SocialEvent) {
                    type = "SOCIAL";
                    extra = ((SocialEvent) event).getTheme();
                }

                writeLine(writer,
                        type,
                        event.getEventId(),
                        event.getEventName(),
                        event.getDate().toString(),
                        event.getLocation(),
                        String.valueOf(event.getCapacity()),
                        extra);
            }

        } catch (IOException e) {
            System.out.println("Error saving events: " + e.getMessage());
        }
    }

    private void loadEvents(EventManager manager) {
        File file = new File(EVENTS_FILE);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    String[] data = readLine(line);

                    if (data.length != 7) {
                        continue;
                    }

                    String type = data[0];
                    String eventId = data[1];

                    if (manager.findEventById(eventId) != null) {
                        continue;
                    }

                    String name = data[2];
                    LocalDate date = LocalDate.parse(data[3]);
                    String location = data[4];
                    int capacity = Integer.parseInt(data[5]);
                    String extra = data[6];

                    Event event = null;

                    switch (type) {
                        case "ACADEMIC":
                            event = new AcademicEvent(
                                    eventId, name, date, location,
                                    capacity, extra);
                            break;

                        case "CLUB":
                            event = new ClubEvent(
                                    eventId, name, date, location,
                                    capacity, extra);
                            break;

                        case "CAREER":
                            event = new CareerEvent(
                                    eventId, name, date, location,
                                    capacity, extra);
                            break;

                        case "SOCIAL":
                            event = new SocialEvent(
                                    eventId, name, date, location,
                                    capacity, extra);
                            break;

                        default:
                            System.out.println(
                                    "Unknown event type: " + type);
                    }

                    if (event != null) {
                        manager.addEvent(event);
                    }

                } catch (Exception e) {
                    System.out.println("Skipping invalid event record: "
                            + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading events: " + e.getMessage());
        }
    }

    // =====================================================
    // REGISTRATIONS
    // =====================================================

    private void saveRegistrations(RegistrationManager manager) {
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(REGISTRATIONS_FILE))) {

            for (Registration registration : manager.getRegistrations()) {
                writeLine(writer,
                        registration.getRegistrationId(),
                        registration.getStudent().getStudentId(),
                        registration.getEvent().getEventId(),
                        registration.getRegistrationDate().toString());
            }

        } catch (IOException e) {
            System.out.println("Error saving registrations: "
                    + e.getMessage());
        }
    }

    private void loadRegistrations(RegistrationManager manager) {
        File file = new File(REGISTRATIONS_FILE);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    String[] data = readLine(line);

                    if (data.length != 4) {
                        continue;
                    }

                    manager.registerStudent(
                            data[0],
                            data[1],
                            data[2],
                            LocalDate.parse(data[3]));

                } catch (NotFoundException
                         | DuplicateRegistrationException
                         | EventFullException e) {
                    System.out.println(
                            "Skipping registration: " + e.getMessage());

                } catch (Exception e) {
                    System.out.println("Skipping invalid registration record.");
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading registrations: "
                    + e.getMessage());
        }
    }
}