package model;

public class AcademicEvent extends Event {
    private String subject;

    public AcademicEvent(int eventID, String eventName, String date, String time,
                         String location, int maxCapacity, int attendees,
                         String subject) {
        super(eventID, eventName, date, time, location, maxCapacity, attendees);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return super.toString() + " - Subject: " + subject;
    }
}