package model;

//event run by a company
public class CareerEvent extends Event{
    private String companyName;

    public CareerEvent(String eventId, String eventName, String date, String time, String location, int capacity, String companyName){
        super(eventId, eventName, date, time, location, capacity);
        this.companyName = companyName;
    }

    public String getCompanyName(){
        return companyName;
    }

    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    @Override
    public void displayEvent(){
        super.displayEvent();
        System.out.println("Company    : " + companyName);
    }

    @Override
    public String toString(){
        return super.toString() + " - Company: " + companyName;
    }
}
