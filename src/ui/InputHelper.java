package ui;

import java.util.Scanner;

import model.Event;
import model.AcademicEvent;
import model.ClubEvent;
import model.CareerEvent;
import model.SocialEvent;

public class InputHelper {

    public static Event createEvent(
            Scanner input, int eventID) {

        System.out.println("\nChoose Event Type:");
        System.out.println("1. Academic Event");
        System.out.println("2. Club Event");
        System.out.println("3. Career Event");
        System.out.println("4. Social Event");

        String choice = readChoice(
                input, "Enter choice: ", "1", "2", "3", "4");

        String name = readRequiredText(input, "Enter event name: ");
        String date = readRequiredText(input, "Enter event date: ");
        String location = readRequiredText(input, "Enter event location: ");
        int capacity = readPositiveInt(input, "Enter event capacity: ");

        switch (choice) {
            case "1":
                String major = readRequiredText(
                        input, "Enter major or academic subject: ");

                return new AcademicEvent(
                        eventID, name, date, location, capacity, major);

            case "2":
                String clubName = readRequiredText(
                        input, "Enter club name: ");

                return new ClubEvent(
                        eventID, name, date, location, capacity, clubName);

            case "3":
                String companyName = readRequiredText(
                        input, "Enter company name: ");

                return new CareerEvent(
                        eventID, name, date, location, capacity, companyName);

            case "4":
                String description = readRequiredText(
                        input, "Enter event description: ");

                return new SocialEvent(
                        eventID, name, date, location, capacity, description);

            default:
                return null;
        }
    }

    public static String readRequiredText(
            Scanner input, String prompt) {

        while (true) {
            System.out.print(prompt);
            String value = input.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("Input cannot be empty. Try again.");
        }
    }

    public static int readInt(
            Scanner input, String prompt) {

        while (true) {
            System.out.print(prompt);
            String value = input.nextLine().trim();

            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.out.println(
                        "Please enter a valid whole number.");
            }
        }
    }

    public static int readPositiveInt(
            Scanner input, String prompt) {

        while (true) {
            int value = readInt(input, prompt);

            if (value > 0) {
                return value;
            }

            System.out.println(
                    "Please enter a number greater than zero.");
        }
    }

    public static String readChoice(
            Scanner input, String prompt, String... choices) {

        while (true) {
            System.out.print(prompt);
            String value = input.nextLine().trim();

            for (String choice : choices) {
                if (choice.equalsIgnoreCase(value)) {
                    return value;
                }
            }

            System.out.println("Invalid choice. Try again.");
        }
    }
}