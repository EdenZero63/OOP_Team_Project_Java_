package model;

//event hosted by a club
public class ClubEvent extends Event{
    private String clubName;

    public ClubEvent(String eventId, String eventName, String description, String date, String time, String location, int capacity, String clubName){
        super(eventId, eventName, description, date, time, location, capacity);
        this.clubName = clubName;
    }

    public String getClubName(){
        return clubName;
    }

    public void setClubName(String clubName){
        this.clubName = clubName;
    }

    @Override
    public void displayEvent(){
        super.displayEvent();
        System.out.println("Club       : " + clubName);
    }

    @Override
    public String toString(){
        return super.toString() + " - Club: " + clubName;
    }
}
