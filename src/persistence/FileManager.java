package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

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

                    if (data.length != 7) {
                        System.out.println(
                                "Skipping invalid event record.");
                        continue;
                    }

                    String type = decode(data[0]);
                    int eventID = Integer.parseInt(decode(data[1]));
                    String eventName = decode(data[2]);
                    String eventDate = decode(data[3]);
                    String eventLocation = decode(data[4]);
                    int capacity = Integer.parseInt(decode(data[5]));
                    String detail = decode(data[6]);

                    // Skip events that are already loaded.
                    if (system.findEventById(eventID) != null) {
                        continue;
                    }

                    Event event;

                    switch (type) {
                        case "AcademicEvent":
                            event = new AcademicEvent(
                                    eventID, eventName, eventDate,
                                    eventLocation, capacity, detail);
                            break;

                        case "ClubEvent":
                            event = new ClubEvent(
                                    eventID, eventName, eventDate,
                                    eventLocation, capacity, detail);
                            break;

                        case "CareerEvent":
                            event = new CareerEvent(
                                    eventID, eventName, eventDate,
                                    eventLocation, capacity, detail);
                            break;

                        case "SocialEvent":
                            event = new SocialEvent(
                                    eventID, eventName, eventDate,
                                    eventLocation, capacity, detail);
                            break;

                        default:
                            System.out.println(
                                    "Unknown event type: " + type);
                            continue;
                    }

                    system.addEvent(event);

                } catch (IllegalArgumentException e) {
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

                for (Event event : system.getEvents()) {
                    String type = event.getClass().getSimpleName();

                    String line =
                            encode(type) + "|" +
                            encode(String.valueOf(event.getEventID())) + "|" +
                            encode(event.getEventName()) + "|" +
                            encode(event.getEventDate()) + "|" +
                            encode(event.getEventLocation()) + "|" +
                            encode(String.valueOf(event.getCapacity())) + "|" +
                            encode(event.getSpecificDetail());

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