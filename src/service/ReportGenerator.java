package service;

import java.util.ArrayList;

import model.Event;
import model.Organizer;
import model.Registration;

//system reports for menu
public class ReportGenerator{

    //# of registrations for event
    public int countRegistrations(Event event, ArrayList<Registration> registrations){
        int count = 0;
        for(int i = 0; i < registrations.size(); i++){
            if(registrations.get(i).getEvent().getEventId().equals(event.getEventId())){
                count++;
            }
        }
        return count;
    }

    public int getSpacesRemaining(Event event, ArrayList<Registration> registrations){
        return event.getCapacity() - countRegistrations(event, registrations);
    }

    public boolean isFull(Event event, ArrayList<Registration> registrations){
        return getSpacesRemaining(event, registrations) <= 0;
    }

    //report 1-students registered per event
    public void displayRegistrationCounts(ArrayList<Event> events, ArrayList<Registration> registrations){
        printHeader("STUDENTS REGISTERED PER EVENT");
        if(events.isEmpty()){
            System.out.println("No events on file.");
            return;
        }

        for(int i = 0; i < events.size(); i++){
            Event event = events.get(i);
            System.out.println("Event: " + event.getEventName());
            System.out.println("Date: " + event.getDate());
            System.out.println("Location: " + event.getLocation());
            System.out.println("Capacity: " + event.getCapacity());
            System.out.println("Registered: " + countRegistrations(event, registrations));
            System.out.println("Spaces Remaining: " + getSpacesRemaining(event, registrations));
            System.out.println("----------------------------------------");
        }
    }

    //report 2-events with open spaces
    public void displayEventsWithSpace(ArrayList<Event> events, ArrayList<Registration> registrations){
        printHeader("EVENTS WITH AVAILABLE CAPACITY");
        int count = 0;
        for(int i = 0; i < events.size(); i++){
            Event event = events.get(i);
            if(!isFull(event, registrations)){
                count++;
                System.out.println(count + ". " + event.getEventName() + " (" + getSpacesRemaining(event, registrations) + " spaces left)");
            }
        }
        if(count == 0){
            System.out.println("No events with open spaces.");
        }
    }

    //report 3-events at capacity
    public void displayFullEvents(ArrayList<Event> events, ArrayList<Registration> registrations){
        printHeader("FULL EVENTS");
        int count = 0;
        for(int i = 0; i < events.size(); i++){
            Event event = events.get(i);
            if(isFull(event, registrations)){
                count++;
                System.out.println(count + ". " + event.getEventName() + " (" + event.getCapacity() + " registered)");
            }
        }
        if(count == 0){
            System.out.println("No full events.");
        }
    }

    //report 4-events run by one organizer
    public void displayEventsByOrganizer(Organizer organizer){
        printHeader("EVENTS BY ORGANIZER");
        System.out.println("Organizer: " + organizer.getName());
        System.out.println();
        System.out.println("Managed Events:");

        ArrayList<Event> managed = organizer.getManagedEvents();
        if(managed.isEmpty()){
            System.out.println("None");
        }
        for(int i = 0; i < managed.size(); i++){
            System.out.println((i + 1) + ". " + managed.get(i).getEventName());
        }
    }

    private void printHeader(String title){
        System.out.println("========================================");
        System.out.println(title);
        System.out.println("========================================");
    }
}
