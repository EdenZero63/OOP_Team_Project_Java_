package model;

public class CareerEvent extends Event {

    public CareerEvent(String eventName, String date, String location,
                       int maxAttendees, int attendees) {
        super(eventName, date, location, maxAttendees, attendees);
    }

    @Override
    public String toString() {
        return "Career Event: " + super.toString();
    }
}