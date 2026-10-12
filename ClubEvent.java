package model;

public class ClubEvent extends Event {
    private String clubName;

    public ClubEvent(int eventID, String eventName, String date, String time,
                     String location, int maxCapacity, int attendees,
                     String clubName) {
        super(eventID, eventName, date, time, location, maxCapacity, attendees);
        this.clubName = clubName;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    @Override
    public String toString() {
        return super.toString() + " - Club: " + clubName;
    }
}