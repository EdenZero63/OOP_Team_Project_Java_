package ui;

import java.util.Scanner;

import model.Event;
import model.AcademicEvent;
import model.ClubEvent;
import model.CareerEvent;
import model.SocialEvent;
import service.EventSystem;

public class Menu {

    private final Scanner input = new Scanner(System.in);
    // Keep one shared instance so created events can be viewed and searched later.
    private final EventSystem eventSystem = new EventSystem();

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.mainMenu();
    }

    public void mainMenu() {
        String selector;

        do {
            System.out.println("\n=================================");
            System.out.println("CAMPUS EVENT MANAGEMENT SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Create Event");
            System.out.println("4. View Events");
            System.out.println("5. Search Event");
            System.out.println("6. Register Student for Event");
            System.out.println("7. Cancel Registration");
            System.out.println("8. View Student Registrations");
            System.out.println("9. View Event Attendees");
            System.out.println("10. View Organizers");
            System.out.println("11. System Reports");
            System.out.println("0. Exit");
            System.out.print("Enter selection: ");

            selector = input.nextLine().trim();

            switch (selector) {
                case "1":
                    studentAddMenu();
                    break;
                case "2":
                    viewStudentMenu();
                    break;
                case "3":
                    createEventMenu();
                    break;
                case "4":
                    viewEventsMenu();
                    break;
                case "5":
                    searchEventsMenu();
                    break;
                case "6":
                    registerForEventsMenu();
                    break;
                case "7":
                    cancelRegistrationMenu();
                    break;
                case "8":
                    viewStudentRegsMenu();
                    break;
                case "9":
                    viewEventAttendeesMenu();
                    break;
                case "10":
                    viewOrganizersMenu();
                    break;
                case "11":
                    systemsReportMenu();
                    break;
                case "0":
                    System.out.println("Exiting Campus Event Management System.");
                    break;
                default:
                    System.out.println("Invalid input. Please select a valid option.");
            }
        } while (!selector.equals("0"));
    }

    public void studentAddMenu() {
        System.out.println("\n--- Add Student ---");
        System.out.print("Enter student name: ");
        String studentName = input.nextLine();
        System.out.print("Enter student ID: ");
        String studentID = input.nextLine();
        System.out.print("Enter student email: ");
        String studentEmail = input.nextLine();

        // TODO: Create and save the student using your StudentManager.
        System.out.println("Student information collected for: " + studentName);
        System.out.println("Connect this method to StudentManager to save the student.");
    }

    public void viewStudentMenu() {
        System.out.println("\n--- View Students ---");
        System.out.println("1. Find a single student");
        System.out.println("2. View all students");
        System.out.print("Enter choice: ");
        String choice = input.nextLine().trim();

        if (choice.equals("1")) {
            System.out.print("Enter student ID: ");
            String studentID = input.nextLine();
            // TODO: Find and display this student using StudentManager.
            System.out.println("Connect this lookup to StudentManager. ID: " + studentID);
        } else if (choice.equals("2")) {
            // TODO: Retrieve and display all students using StudentManager.
            System.out.println("Connect this option to StudentManager to display students.");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    public void createEventMenu() {
        System.out.println("\n--- Create Event ---");
        System.out.println("1. Academic Event");
        System.out.println("2. Club Event");
        System.out.println("3. Career Event");
        System.out.println("4. Social Event");

        String eventType = readChoice("Enter event type: ", "1", "2", "3", "4");

        System.out.print("Enter event name: ");
        String eventName = readRequiredText();
        System.out.print("Enter event date (month and day): ");
        String eventDate = readRequiredText();
        System.out.print("Enter event location: ");
        String eventLocation = readRequiredText();
        int capacity = readPositiveInt("Enter participant capacity: ");
        int eventID = readPositiveInt("Enter event ID: ");

        // Prevent duplicate IDs before creating an event object.
        if (eventSystem.findEventById(eventID) != null) {
            System.out.println("An event with ID " + eventID + " already exists.");
            System.out.println("Please create the event again with a different ID.");
            return;
        }

        Event event;
        String specificDetail;

        switch (eventType) {
            case "1":
                System.out.print("Enter the major associated with this event: ");
                specificDetail = readRequiredText();
                event = new AcademicEvent(
                    eventID, eventName, eventDate,
                    eventLocation, capacity, specificDetail
                );
                break;

            case "2":
                System.out.print("Enter the club hosting this event: ");
                specificDetail = readRequiredText();
                event = new ClubEvent(
                    eventID, eventName, eventDate,
                    eventLocation, capacity, specificDetail
                );
                break;

            case "3":
                System.out.print("Enter the company organizing this event: ");
                specificDetail = readRequiredText();
                event = new CareerEvent(
                    eventID, eventName, eventDate,
                    eventLocation, capacity, specificDetail
                );
                break;

            case "4":
                System.out.print("Enter a description of the social event: ");
                specificDetail = readRequiredText();
                event = new SocialEvent(
                    eventID, eventName, eventDate,
                    eventLocation, capacity, specificDetail
                );
                break;

            default:
                // readChoice only allows valid event types.
                System.out.println("Invalid event type.");
                return;
        }

        if (eventSystem.addEvent(event)) {
            System.out.println("Event created successfully!");
            System.out.println(event);
        } else {
            System.out.println("Event could not be added. Check whether its ID is unique.");
        }
    }

    public void viewEventsMenu() {
        System.out.println("\n--- View Events ---");
        eventSystem.displayAllEvents();
    }

    public void searchEventsMenu() {
        System.out.println("\n--- Search Event ---");
        int eventID = readPositiveInt("Enter event ID: ");
        Event event = eventSystem.findEventById(eventID);

        if (event == null) {
            System.out.println("No event found with ID " + eventID + ".");
        } else {
            System.out.println("Event found:");
            System.out.println(event);
        }
    }

    public void registerForEventsMenu() {
        System.out.println("\n--- Register Student for Event ---");
        System.out.print("Enter student ID: ");
        String studentID = input.nextLine();
        System.out.print("Confirm this is your student ID (Y/N): ");
        String confirm = input.nextLine().trim();

        if (confirm.equalsIgnoreCase("Y")) {
            int eventID = readPositiveInt("Enter the event ID to register for: ");
            Event event = eventSystem.findEventById(eventID);

            if (event == null) {
                System.out.println("No event found with ID " + eventID + ".");
            } else {
                // TODO: Verify the student and register them using your registration manager.
                System.out.println("Event found: " + event.getEventName());
                System.out.println("Registration still needs to be connected to your student/registration manager.");
                System.out.println("Student ID: " + studentID);
            }
        } else {
            System.out.println("Registration cancelled.");
        }
    }

    public void cancelRegistrationMenu() {
        System.out.println("\n--- Cancel Registration ---");
        System.out.print("Enter student ID: ");
        String studentID = input.nextLine();
        int eventID = readPositiveInt("Enter the event ID to cancel: ");
        Event event = eventSystem.findEventById(eventID);

        if (event == null) {
            System.out.println("No event found with ID " + eventID + ".");
        } else {
            // TODO: Remove registration through your registration manager.
            System.out.println("Found event: " + event.getEventName());
            System.out.println("Cancellation must be connected to your registration manager.");
            System.out.println("Student ID: " + studentID);
        }
    }

    public void viewStudentRegsMenu() {
        System.out.println("\n--- View Student Registrations ---");
        System.out.print("Enter student ID: ");
        String studentID = input.nextLine();
        // TODO: Retrieve this student's registrations from your registration manager.
        System.out.println("Connect this lookup to your registration manager. Student ID: " + studentID);
    }

    public void viewEventAttendeesMenu() {
        System.out.println("\n--- View Event Attendees ---");
        int eventID = readPositiveInt("Enter event ID: ");
        Event event = eventSystem.findEventById(eventID);

        if (event == null) {
            System.out.println("No event found with ID " + eventID + ".");
        } else {
            // TODO: Retrieve attendees from your registration manager.
            System.out.println("Event found: " + event.getEventName());
            System.out.println("Attendee listing needs to be connected to your registration manager.");
        }
    }

    public void viewOrganizersMenu() {
        System.out.println("\n--- View Organizers ---");
        System.out.print("Enter organizer ID: ");
        String organizerID = input.nextLine();
        // TODO: Find the organizer and list events associated with them.
        System.out.println("Connect this lookup to your organizer manager. Organizer ID: " + organizerID);
    }

    public void systemsReportMenu() {
        String choice;

        do {
            System.out.println("\n--- System Reports ---");
            System.out.println("1. View Students Report");
            System.out.println("2. View Events Report");
            System.out.println("3. View Organizers Report");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter choice: ");
            choice = input.nextLine().trim();

            switch (choice) {
                case "1":
                    // TODO: Generate student report.
                    System.out.println("Connect this report to StudentManager.");
                    break;
                case "2":
                    System.out.println("--- Events Report ---");
                    eventSystem.displayAllEvents();
                    break;
                case "3":
                    // TODO: Generate organizer report.
                    System.out.println("Connect this report to your organizer manager.");
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 0, 1, 2, or 3.");
            }
        } while (!choice.equals("0"));
    }

    private String readChoice(String prompt, String... choices) {
        while (true) {
            System.out.print(prompt);
            String value = input.nextLine().trim();
            for (String choice : choices) {
                if (value.equals(choice)) {
                    return value;
                }
            }
            System.out.println("Invalid choice. Please try again.");
        }
    }

    private String readRequiredText() {
        while (true) {
            String value = input.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.print("This field cannot be empty. Try again: ");
        }
    }

    private int readPositiveInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = input.nextLine().trim();
            try {
                int number = Integer.parseInt(value);
                if (number > 0) {
                    return number;
                }
            } catch (NumberFormatException ignored) {
                // Show validation message below.
            }
            System.out.println("Please enter a positive whole number.");
        }
    }
}