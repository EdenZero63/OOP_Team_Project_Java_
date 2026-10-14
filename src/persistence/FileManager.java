package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Base64;

import model.Student;
import model.Organizer;
import model.Registration;
import model.Event;

import service.StudentManager;
import service.OrganizerManager;
import service.EventManager;
import service.RegistrationManager;

import exception.DuplicateRegistrationException;
import exception.EventFullException;
import exception.NotFoundException;

public class FileManager {

    private static final Path DATA_FOLDER = Path.of("data");
    private static final Path STUDENTS_FILE =
            DATA_FOLDER.resolve("students.txt");
    private static final Path ORGANIZERS_FILE =
            DATA_FOLDER.resolve("organizers.txt");
    private static final Path REGISTRATIONS_FILE =
            DATA_FOLDER.resolve("registrations.txt");

    public void saveAll(
            StudentManager studentManager,
            OrganizerManager organizerManager,
            RegistrationManager registrationManager) {

        try {
            Files.createDirectories(DATA_FOLDER);

            // Save students
            try (BufferedWriter writer = Files.newBufferedWriter(
                    STUDENTS_FILE, StandardCharsets.UTF_8)) {

                for (Student student : studentManager.getStudents()) {
                    writer.write(
                            encode(student.getStudentId()) + "|" +
                            encode(student.getName()) + "|" +
                            encode(student.getEmail()));
                    writer.newLine();
                }
            }

            // Save organizers
            try (BufferedWriter writer = Files.newBufferedWriter(
                    ORGANIZERS_FILE, StandardCharsets.UTF_8)) {

                for (Organizer organizer : organizerManager.getOrganizers()) {
                    writer.write(
                            encode(organizer.getOrganizerId()) + "|" +
                            encode(organizer.getName()) + "|" +
                            encode(organizer.getEmail()) + "|" +
                            encode(organizer.getDepartment()));
                    writer.newLine();
                }
            }

            // Save registrations
            try (BufferedWriter writer = Files.newBufferedWriter(
                    REGISTRATIONS_FILE, StandardCharsets.UTF_8)) {

                for (Registration registration
                        : registrationManager.getRegistrations()) {
                    writer.write(
                            encode(registration.getRegistrationId()) + "|" +
                            encode(registration.getStudent().getStudentId()) + "|" +
                            encode(registration.getEvent().getEventId()) + "|" +
                            encode(registration.getRegistrationDate().toString()));
                    writer.newLine();
                }
            }

            System.out.println("Students, organizers, and registrations saved.");

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public void loadAll(
            StudentManager studentManager,
            OrganizerManager organizerManager,
            EventManager eventManager,
            RegistrationManager registrationManager) {

        // Load students first, so registrations can find them.
        if (Files.exists(STUDENTS_FILE)) {
            try (BufferedReader reader = Files.newBufferedReader(
                    STUDENTS_FILE, StandardCharsets.UTF_8)) {

                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                        String[] fields = line.split("\\|", -1);
                        if (fields.length != 3) {
                            continue;
                        }

                        String studentId = decode(fields[0]);
                        if (studentManager.findStudentById(studentId) == null) {
                            Student student = new Student(
                                    studentId,
                                    decode(fields[1]),
                                    decode(fields[2]));
                            studentManager.addStudent(student);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Skipping invalid student record.");
                    }
                }
            } catch (IOException e) {
                System.out.println("Error loading students: " + e.getMessage());
            }
        }

        // Load organizers
        if (Files.exists(ORGANIZERS_FILE)) {
            try (BufferedReader reader = Files.newBufferedReader(
                    ORGANIZERS_FILE, StandardCharsets.UTF_8)) {

                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                        String[] fields = line.split("\\|", -1);
                        if (fields.length != 4) {
                            continue;
                        }

                        String organizerId = decode(fields[0]);
                        if (organizerManager.findOrganizerById(organizerId) == null) {
                            Organizer organizer = new Organizer(
                                    organizerId,
                                    decode(fields[1]),
                                    decode(fields[2]),
                                    decode(fields[3]));
                            organizerManager.addOrganizer(organizer);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Skipping invalid organizer record.");
                    }
                }
            } catch (IOException e) {
                System.out.println("Error loading organizers: " + e.getMessage());
            }
        }

        // Load registrations after students and events are available.
        if (Files.exists(REGISTRATIONS_FILE)) {
            try (BufferedReader reader = Files.newBufferedReader(
                    REGISTRATIONS_FILE, StandardCharsets.UTF_8)) {

                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                        String[] fields = line.split("\\|", -1);
                        if (fields.length != 4) {
                            continue;
                        }

                        String registrationId = decode(fields[0]);
                        String studentId = decode(fields[1]);
                        String eventId = decode(fields[2]);
                        LocalDate registrationDate =
                                LocalDate.parse(decode(fields[3]));

                        registrationManager.registerStudent(
                                registrationId,
                                studentId,
                                eventId,
                                registrationDate);

                    } catch (IllegalArgumentException
                            | NotFoundException
                            | DuplicateRegistrationException
                            | EventFullException e) {
                        System.out.println("Skipping invalid registration record.");
                    }
                }
            } catch (IOException e) {
                System.out.println(
                        "Error loading registrations: " + e.getMessage());
            }
        }

        System.out.println("Saved data loaded.");
    }

    private String encode(String value) {
        return Base64.getEncoder().encodeToString(
                value.getBytes(StandardCharsets.UTF_8));
    }

    private String decode(String value) {
        return new String(
                Base64.getDecoder().decode(value),
                StandardCharsets.UTF_8);
    }
}