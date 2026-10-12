package model;

public class CareerEvent extends Event {
    private String companyName;

    public CareerEvent(int eventID, String eventName, String date, String time,
                       String location, int maxCapacity, int attendees,
                       String companyName) {
        super(eventID, eventName, date, time, location, maxCapacity, attendees);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return super.toString() + " - Company: " + companyName;
    }
}