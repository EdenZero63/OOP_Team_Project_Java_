public abstract class Event {

    private int eventId;
    private String name;
    private int capacity;
    private int attendeeCount;

    // Organizer assigned to this event
    private Organizer organizer;

    public Event(int eventId, String name, int capacity) {
        this.eventId = eventId;
        this.name = name;
        this.capacity = capacity;
        this.attendeeCount = 0;
    }

    public int getEventId() {
        return eventId;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAttendeeCount() {
        return attendeeCount;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public void addAttendee() {
        if (attendeeCount < capacity) {
            attendeeCount++;
        } else {
            System.out.println("Event is full.");
        }
    }
}