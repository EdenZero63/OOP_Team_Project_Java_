package model;

public class SocialEvent extends Event {
    private String theme;

    public SocialEvent(int eventID, String eventName, String date, String time,
                       String location, int maxCapacity, int attendees,
                       String theme) {
        super(eventID, eventName, date, time, location, maxCapacity, attendees);
        this.theme = theme;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return super.toString() + " - Theme: " + theme;
    }
}