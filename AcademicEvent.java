package model;

public class AcademicEvent extends Event {

    public AcademicEvent(String eventName, String date, String location,
                         int maxAttendees, int attendees) {
        super(eventName, date, location, maxAttendees, attendees);
    }

    @Override
    public String toString() {
        return "Academic Event: " + super.toString();
    }
}