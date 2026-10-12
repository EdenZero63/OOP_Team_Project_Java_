package model;

public class Event {
    private int eventID;
    private String eventName;
    private String date;
    private String time;
    private String location;
    private int maxCapacity;
    private int attendees;

    public Event(int eventID, String eventName, String date, String time,
                 String location, int maxCapacity, int attendees) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.date = date;
        this.time = time;
        this.location = location;
        this.maxCapacity = maxCapacity;
        this.attendees = attendees;
    }

    public int getEventID() {
        return eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getAttendees() {
        return attendees;
    }

    public int getAvailableAttendees() {
        return maxCapacity - attendees;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setAttendees(int attendees) {
        this.attendees = attendees;
    }

    @Override
    public String toString() {
        return eventID + " - " + eventName + " - " + date
                + " - " + time + " - " + location
                + " - Capacity: " + maxCapacity
                + " - Attendees: " + attendees
                + " - Available: " + getAvailableAttendees();
    }
}