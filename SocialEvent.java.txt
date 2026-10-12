package model;

public class SocialEvent extends Event {

    public SocialEvent(String eventName, String date, String location,
                       int maxAttendees, int attendees) {
        super(eventName, date, location, maxAttendees, attendees);
    }

    @Override
    public String toString() {
        return "Social Event: " + super.toString();
    }
}