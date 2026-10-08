package service;

import java.util.ArrayList;
import model.Event;

public class EventManager {
    private ArrayList<Event> events;

    public EventManager() {
        this.events = new ArrayList<Event>();
    }

    public Event findEventById(int eventID) {
        for (int i = 0; i < events.size(); i++) {
            Event currentEvent = events.get(i);

            if (currentEvent.getEventID() == eventID) {
                return currentEvent;
            }
        }

        return null;
    }

    public boolean addEvent(Event event) {
        if (event == null) {
            System.out.println("Registration Error: Event data cannot be empty.");
            return false;
        }

        if (findEventById(event.getEventID()) != null) {
            System.out.println("Error: Event ID '" + event.getEventID()
                    + "' already exists!");
            return false;
        }

        events.add(event);

        System.out.println("Event '" + event.getEventName()
                + "' added successfully.");

        return true;
    }

    public void displayEventById(int eventID) {
        Event currentEvent = findEventById(eventID);

        if (currentEvent != null) {
            System.out.println(currentEvent);
        } else {
            System.out.println("Record not found for event ID: " + eventID);
        }
    }

    public void displayAllEvents() {
        if (events.isEmpty()) {
            System.out.println("No events are currently available.");
            return;
        }

        System.out.println("========================================");
        System.out.println("          EVENT INFORMATION             ");
        System.out.println("========================================");

        for (int i = 0; i < events.size(); i++) {
            Event currentEvent = events.get(i);

            System.out.println(currentEvent);
            System.out.println("----------------------------------------");
        }
    }

    public ArrayList<Event> getAllEvents() {
        return events;
    }
}