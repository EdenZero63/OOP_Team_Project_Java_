package model;

//event tied to a major
public class AcademicEvent extends Event{
    private String major;

    public AcademicEvent(String eventId, String eventName, String date, String time, String location, int capacity, String major)
    {
        super(eventId, eventName, date, time, location, capacity);
        this.major = major;
    }

    public String getMajor()
    {
        return major;
    }

    public void setMajor(String major)
    {
        this.major = major;
    }

    @Override
    public void displayEvent()
    {
        super.displayEvent();
        System.out.println("Major      : " + major);
    }

    @Override
    public String toString()
    {
        return super.toString() + " - Major: " + major;
    }
}
