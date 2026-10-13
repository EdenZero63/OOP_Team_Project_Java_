package model;

//general social event with a theme
public class SocialEvent extends Event{
    private String theme;

    public SocialEvent(String eventId, String eventName, String description, String date, String time, String location, int capacity, String theme){
        super(eventId, eventName, description, date, time, location, capacity);
        this.theme = theme;
    }

    public String getTheme(){
        return theme;
    }

    public void setTheme(String theme){
        this.theme = theme;
    }

    @Override
    public void displayEvent(){
        super.displayEvent();
        System.out.println("Theme      : " + theme);
    }

    @Override
    public String toString(){
        return super.toString() + " - Theme: " + theme;
    }
}
