package service;

//holds every manager so Main, Menu and FileManager share the same data
public class EventSystem{
    private StudentManager studentManager;
    private OrganizerManager organizerManager;
    private EventManager eventManager;
    private RegistrationManager registrationManager;

    public EventSystem(){
        this.studentManager = new StudentManager();
        this.organizerManager = new OrganizerManager();
        this.eventManager = new EventManager();
        this.registrationManager = new RegistrationManager();
    }

    //getters
    public StudentManager getStudentManager(){
        return studentManager;
    }

    public OrganizerManager getOrganizerManager(){
        return organizerManager;
    }

    public EventManager getEventManager(){
        return eventManager;
    }

    public RegistrationManager getRegistrationManager(){
        return registrationManager;
    }
}
