package model;

import java.util.ArrayList;

//organizer that creates and manages events, keeps its own list of events
public class Organizer{
    //fields
    private String organizerId;
    private String name;
    private String email;
    private String department;
    private ArrayList<Event> managedEvents;

    //constructor
    public Organizer(String organizerId, String name, String email, String department){
        if(organizerId == null || organizerId.trim().isEmpty()){
            throw new IllegalArgumentException("An organizer needs an ID.");
        }
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("An organizer needs a name.");
        }

        this.organizerId = organizerId.trim();
        this.name = name.trim();
        this.email = email == null ? "" : email.trim();
        this.department = department == null ? "" : department.trim();
        this.managedEvents = new ArrayList<Event>();
    }

    //getters
    public String getOrganizerId(){
        return organizerId;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getDepartment(){
        return department;
    }

    //setters
    public void setEmail(String email){
        this.email = email == null ? "" : email.trim();
    }

    public void setDepartment(String department){
        this.department = department == null ? "" : department.trim();
    }

    //link an event, skips null or already linked
    public void addManagedEvent(Event event){
        if(event == null || managedEvents.contains(event)){
            return;
        }
        managedEvents.add(event);
    }

    //unlink an event
    public boolean removeManagedEvent(Event event){
        return managedEvents.remove(event);
    }

    public boolean manages(Event event){
        return managedEvents.contains(event);
    }

    public int getManagedEventCount(){
        return managedEvents.size();
    }

    //return a copy so outside code cant change the list
    public ArrayList<Event> getManagedEvents(){
        return new ArrayList<Event>(managedEvents);
    }

    //display
    public void displayOrganizer(){
        System.out.println("Organizer ID : " + organizerId);
        System.out.println("Name         : " + name);
        System.out.println("Email        : " + email);
        System.out.println("Department   : " + department);
        System.out.println("Events       : " + managedEvents.size());
    }

    @Override
    public String toString(){
        return organizerId + " - " + name + " (" + department + ")";
    }
}
