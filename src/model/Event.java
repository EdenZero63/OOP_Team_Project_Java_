package model;
public class Event{
    private String eventId;
    private String eventName;
    private String description;
    private String date;
    private String time;
    private String location;
    private int capacity;

    public Event(String eventId, String eventName, String description, String date, String time, String location, int capacity){
        if(eventId == null || eventId.trim().isEmpty()){
            throw new IllegalArgumentException("An event needs an ID.");
        }
        if(capacity <= 0){
            throw new IllegalArgumentException("Capacity must be at least 1.");
        }

        this.eventId = eventId.trim();
        this.eventName = eventName;
        this.description = description;
        this.date = date;
        this.time = time;
        this.location = location;
        this.capacity = capacity;
    }

    public String getEventId(){
        return eventId;
    }

    public String getEventName(){
        return eventName;
    }

    public String getDescription(){
        return description;
    }

    public String getDate(){
        return date;
    }

    public String getTime(){
        return time;
    }

    public String getLocation(){
        return location;
    }

    public int getCapacity(){
        return capacity;
    }

    public void setEventName(String eventName){
        this.eventName = eventName;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setTime(String time){
        this.time = time;
    }

    public void setLocation(String location){
        this.location = location;
    }

    //subclasses add their own line after this
    public void displayEvent(){
        System.out.println("Event ID   : " + eventId);
        System.out.println("Type       : " + getClass().getSimpleName());
        System.out.println("Name       : " + eventName);
        System.out.println("Description: " + description);
        System.out.println("Date       : " + date);
        System.out.println("Time       : " + time);
        System.out.println("Location   : " + location);
        System.out.println("Capacity   : " + capacity);
    }

    @Override
    public String toString(){
        return eventId + " - " + eventName + " - " + date + " - " + time + " - " + location;
    }
}
