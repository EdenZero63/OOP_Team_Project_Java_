package service;

import java.util.ArrayList;

import exception.DuplicateIdException;
import exception.NotFoundException;
import model.Event;
import model.Organizer;

//handles organizers: add, find, display, link to events
//errors are thrown so Menu can print them
public class OrganizerManager{
    private ArrayList<Organizer> organizers;

    public OrganizerManager() {
        this.organizers = new ArrayList<Organizer>();
    }

    //find by ID, returns null if missing
    public Organizer findOrganizerById(String organizerId){
        if(organizerId == null) {
            return null;
        }

        String wanted = organizerId.trim();
        for(int i = 0; i < organizers.size(); i++){
            Organizer current = organizers.get(i);
            if(current.getOrganizerId().equalsIgnoreCase(wanted)){
                return current;
            }
        }
        return null;
    }

    //same as find but throws if missing
    public Organizer getOrganizer(String organizerId) throws NotFoundException{
        Organizer organizer = findOrganizerById(organizerId);
        if(organizer == null){
            throw new NotFoundException("No organizer with ID '" + organizerId + "'.");
        }
        return organizer;
    }

    public boolean organizerExists(String organizerId){
        return findOrganizerById(organizerId) != null;
    }

    //add and remove
    public void addOrganizer(Organizer organizer) throws DuplicateIdException{
        if(organizer == null) {
            throw new IllegalArgumentException("Organizer cannot be null.");
        }
        if(organizerExists(organizer.getOrganizerId())){
            throw new DuplicateIdException(
                "Organizer ID '" + organizer.getOrganizerId() + "' already exists.");
        }
        organizers.add(organizer);
    }

    //remove organizer, its events stay in EventManager
    public Organizer removeOrganizer(String organizerId) throws NotFoundException{
        Organizer organizer = getOrganizer(organizerId);
        organizers.remove(organizer);
        return organizer;
    }

    //event links
    public void assignEvent(String organizerId, Event event) throws NotFoundException{
        if(event == null) {
            throw new IllegalArgumentException("Event cannot be null.");
        }
        getOrganizer(organizerId).addManagedEvent(event);
    }

    public void unassignEvent(String organizerId, Event event) throws NotFoundException{
        if(event == null) {
            throw new IllegalArgumentException("Event cannot be null.");
        }
        getOrganizer(organizerId).removeManagedEvent(event);
    }

    public ArrayList<Event> getEventsByOrganizer(String organizerId) throws NotFoundException{
        return getOrganizer(organizerId).getManagedEvents();
    }

    //find which organizer manages an event
    public Organizer findOrganizerOfEvent(Event event) {
        if(event == null) {
            return null;
        }
        for(int i = 0; i < organizers.size(); i++) {
            Organizer current = organizers.get(i);
            if (current.manages(event)) {
                return current;
            }
        }
        return null;
    }

    //display
    public void displayOrganizerById(String organizerId) throws NotFoundException{
        getOrganizer(organizerId).displayOrganizer();
    }

    public void displayAllOrganizers(){
        if(organizers.isEmpty()) {
            System.out.println("No organizers on file.");
            return;
        }

        System.out.println("========================================");
        System.out.println("           ORGANIZER DIRECTORY          ");
        System.out.println("========================================");
        for(int i = 0; i < organizers.size(); i++){
            organizers.get(i).displayOrganizer();
            System.out.println("----------------------------------------");
        }
    }

    public int getOrganizerCount(){
        return organizers.size();
    }

    //live list for FileManager and reports
    public ArrayList<Organizer> getAllOrganizers(){
        return organizers;
    }
}
