package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

import exception.DuplicateIdException;
import model.Event;
import model.AcademicEvent;
import model.ClubEvent;
import model.CareerEvent;
import model.SocialEvent;
import service.EventSystem;

public class FileManager {

    private static final Path FILE_PATH =
            Path.of("data", "events.txt");

    public void loadAll(EventSystem system) {
        if (system == null) {
            return;
        }

        if (!Files.exists(FILE_PATH)) {
            System.out.println("No saved event file found.");
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(
                FILE_PATH, StandardCharsets.UTF_8)) {

            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    String[] data = line.split("\\|", -1);

                    if (data.length != 9) {
                        System.out.println(
                                "Skipping invalid event record.");
                        continue;
                    }

                    String type = decode(data[0]);
                    String eventID = decode(data[1]);
                    String eventName = decode(data[2]);
                    String description = decode(data[3]);
                    String eventDate = decode(data[4]);
                    String eventTime = decode(data[5]);
                    String eventLocation = decode(data[6]);
                    int capacity = Integer.parseInt(decode(data[7]));
                    String detail = decode(data[8]);

                    // Skip events that are already loaded.
                    if (system.getEventManager().findEventById(eventID) != null) {
                        continue;
                    }

                    Event event;

                    switch (type) {
                        case "AcademicEvent":
                            event = new AcademicEvent(
                                    eventID, eventName, description, eventDate,
                                    eventTime, eventLocation, capacity, detail);
                            break;

                        case "ClubEvent":
                            event = new ClubEvent(
                                    eventID, eventName, description, eventDate,
                                    eventTime, eventLocation, capacity, detail);
                            break;

                        case "CareerEvent":
                            event = new CareerEvent(
                                    eventID, eventName, description, eventDate,
                                    eventTime, eventLocation, capacity, detail);
                            break;

                        case "SocialEvent":
                            event = new SocialEvent(
                                    eventID, eventName, description, eventDate,
                                    eventTime, eventLocation, capacity, detail);
                            break;

                        default:
                            System.out.println(
                                    "Unknown event type: " + type);
                            continue;
                    }

                    system.getEventManager().addEvent(event);

                } catch (IllegalArgumentException | DuplicateIdException e) {
                    System.out.println(
                            "Skipping invalid saved event.");
                }
            }

            System.out.println("Events loaded successfully.");

        } catch (IOException e) {
            System.out.println(
                    "Error loading events: " + e.getMessage());
        }
    }

    public void saveAll(EventSystem system) {
        if (system == null) {
            return;
        }

        try {
            Files.createDirectories(FILE_PATH.getParent());

            try (BufferedWriter writer = Files.newBufferedWriter(
                    FILE_PATH, StandardCharsets.UTF_8)) {

                for (Event event : system.getEventManager().getAllEvents()) {
                    String type = event.getClass().getSimpleName();

                    String line =
                            encode(type) + "|" +
                            encode(event.getEventId()) + "|" +
                            encode(event.getEventName()) + "|" +
                            encode(event.getDescription()) + "|" +
                            encode(event.getDate()) + "|" +
                            encode(event.getTime()) + "|" +
                            encode(event.getLocation()) + "|" +
                            encode(String.valueOf(event.getCapacity())) + "|" +
                            encode(getSpecificDetail(event));

                    writer.write(line);
                    writer.newLine();
                }
            }

            System.out.println("Events saved successfully.");

        } catch (IOException e) {
            System.out.println(
                    "Error saving events: " + e.getMessage());
        }
    }

    // The one extra field each event type adds.
    private String getSpecificDetail(Event event) {
        if (event instanceof AcademicEvent) {
            return ((AcademicEvent) event).getMajor();
        }
        if (event instanceof ClubEvent) {
            return ((ClubEvent) event).getClubName();
        }
        if (event instanceof CareerEvent) {
            return ((CareerEvent) event).getCompanyName();
        }
        if (event instanceof SocialEvent) {
            return ((SocialEvent) event).getTheme();
        }
        return "";
    }

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
}