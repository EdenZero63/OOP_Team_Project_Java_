package model;

public class ClubEvent extends Event {

    public ClubEvent(String eventName, String date, String location,
                     int maxAttendees, int attendees) {
        super(eventName, date, location, maxAttendees, attendees);
    }

    @Override
    public String toString() {
        return "Club Event: " + super.toString();
    }
}