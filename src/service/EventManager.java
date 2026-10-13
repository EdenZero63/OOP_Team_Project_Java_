package service;

import java.util.ArrayList;

import exception.DuplicateIdException;
import exception.NotFoundException;
import model.Event;

// Handles events: add, find, search, display
// Errors are thrown so Menu can print them
public class EventManager{
    private ArrayList<Event> events;

    public EventManager()
    {
        this.events = new ArrayList<Event>();
    }

    // Find by ID, returns null if missing
    public Event findEventById (String eventId)
    {
        if (eventId == null) {
            return null;
        }

        String wanted = eventId.trim();
        for (int i = 0; i < events.size(); i++) {
            Event current = events.get(i);
            if(current.getEventId().equalsIgnoreCase(wanted)){
                return current;
            }
        }
        return null;
    }

    // Same as find but throws if missing
    public Event getEvent (String eventId) throws NotFoundException
    {
        Event event = findEventById(eventId);
        if (event == null) {
            throw new NotFoundException("No event with ID '" + eventId + "'.");
        }
        return event;
    }

    public boolean eventExists(String eventId)
    {
        return findEventById(eventId) != null;
    }

    // Add and remove
    public void addEvent(Event event) throws DuplicateIdException
    {
        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null.");
        }
        if (eventExists(event.getEventId())) {
            throw new DuplicateIdException("Event ID '" + event.getEventId() + "' already exists.");
        }
        events.add(event);
    }

    public Event removeEvent(String eventId) throws NotFoundException
    {
        Event event = getEvent(eventId);
        events.remove(event);
        return event;
    }

    // Search by part of the name, ignores case
    public ArrayList<Event> searchEventsByName(String keyword)
    {
        ArrayList<Event> matches = new ArrayList<Event>();
        if(keyword == null || keyword.trim().isEmpty()){
            return matches;
        }

        String wanted = keyword.trim().toLowerCase();
        for(int i = 0; i < events.size(); i++){
            Event current = events.get(i);
            if(current.getEventName() != null && current.getEventName().toLowerCase().contains(wanted)){
                matches.add(current);
            }
        }
        return matches;
    }

    //display
    public void displayEventById(String eventId) throws NotFoundException
    {
        getEvent(eventId).displayEvent();
    }

    public void displayAllEvents()
    {
        if (events.isEmpty()) {
            System.out.println("No events on file.");
            return;
        }

        System.out.println("========================================");
        System.out.println("             EVENT DIRECTORY            ");
        System.out.println("========================================");
        for (int i = 0; i < events.size(); i++) {
            events.get(i).displayEvent();
            System.out.println("----------------------------------------");
        }
    }

    public int getEventCount()
    {
        return events.size();
    }

    // Live list for FileManager and reports
    public ArrayList<Event> getAllEvents()
    {
        return events;
    }
}
