package ui;

import java.util.ArrayList;
import java.util.Scanner;
import exception.*;
import model.*;
import service.*;

//console menu
public class Menu{
    private Scanner input = new Scanner(System.in);

    private StudentManager studentManage;
    private EventManager eventMang;
    private OrganizerManager orgManager;
    private RegistrationManager regManager;

    public Menu(EventSystem system){
        this.studentManage = system.getStudentManager();
        this.eventMang = system.getEventManager();
        this.orgManager = system.getOrganizerManager();
        this.regManager = system.getRegistrationManager();
    }

    public void run(){
        String choice;
        do{
            System.out.println();
            System.out.println("=================================");
            System.out.println("CAMPUS EVENT MANAGEMENT SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Create Organizer");
            System.out.println("4. Create Event");
            System.out.println("5. View Events");
            System.out.println("6. Search Events");
            System.out.println("7. Register Student for Event");
            System.out.println("8. Cancel Registration");
            System.out.println("9. View Student Registrations");
            System.out.println("10. View Event Attendees");
            System.out.println("11. View Organizers");
            System.out.println("12. System Reports");
            System.out.println("0. Exit");
            System.out.print("Enter selection: ");
            choice = input.nextLine().trim();

            switch(choice){
                case "1":
                    studentAddMenu();
                    break;
                case "2":
                    viewStudentMenu();
                    break;
                case "3":
                    createOrganizerMenu();
                    break;
                case "4":
                    createEventMenu();
                    break;
                case "5":
                    viewEventsMenu();
                    break;
                case "6":
                    searchEventsMenu();
                    break;
                case "7":
                    registerForEventsMenu();
                    break;
                case "8":
                    cancelRegistrationMenu();
                    break;
                case "9":
                    viewSutdentRegsMenu();
                    break;
                case "10":
                    viewEventAttendsMenu();
                    break;
                case "11":
                    viewOrganizersMenu();
                    break;
                case "12":
                    systemsReportMenu();
                    break;
                case "0":
                    System.out.println("Saving and exiting...");
                    break;
                default:
                    System.out.println("Invalid selection, please try again.");
            }
        }while(!choice.equals("0"));
    }

    //Student Add menu 
    public  void studentAddMenu(){
        Scanner input = new Scanner(System.in);
        System.out.println("\\033[H\\033[2J");
        System.out.flush();
        System.out.println("Enter name of student: ");
        String studentName = input.nextLine();

        System.out.println("Enter Students ID: ");
        String studentID = input.nextLine();

        System.out.println("Enter Student email: ");
        String studentEmail = input.nextLine();

        System.out.println("Enter Student major: ");
        String studentMajor = input.nextLine();
        Student student= new Student(studentID, studentName,studentEmail, studentMajor);        
         studentManage.addStudent(student);
        return;

    }
    
    public void viewStudentMenu()
    {
       Scanner input = new Scanner(System.in);
        
            System.out.println("Enter ID of student: ");
            String studentID = input.nextLine();
            studentManage.findStudentById(studentID);

        return;

    }
    public void createOrganizerMenu(){
        Scanner input= new Scanner(System.in);
        String orgID= "";
        String orgName= "";
        String email = "";
        String department = "";
        
        System.out.println("ORGAINZER CREATE MENU");

        System.out.println("Do you wish to create an Organizer?(Y/N): ");
        char choice = input.next().charAt(0);
        if(choice =='Y'|| choice == 'y')
        {
        System.out.pritln("Enter Organizer ID: ");
            orgID = input.nextLine();
        System.out.println("Enter orgName: ");
        orgName = input.nextLine();
        System.out.println("Enter email: ");
        email = input.nextLine();
        System.out.println("Enter Department: ");
        department= input.nextLine();
        orgManager.Organizer(orgID,orgName,email,department);
        }

    }
    //asks details and builds event type
    public void createEventMenu(){
        System.out.println("CREATE EVENT");
        System.out.println("1. Academic Event");
        System.out.println("2. Club Event");
        System.out.println("3. Career Event");
        System.out.println("4. Social Event");
        System.out.print("Choose a type: ");
        String type = input.nextLine().trim();
        if(!type.equals("1") && !type.equals("2") && !type.equals("3") && !type.equals("4")){
            System.out.println("Invalid event type.");
            return;
        }

        System.out.print("Enter event ID: ");
        String eventID = input.nextLine().trim();
        if(eventMang.eventExists(eventID)){
            System.out.println("An event with that ID already exists.");
            return;
        }
        System.out.print("Enter event name: ");
        String eventName = input.nextLine();
        System.out.print("Enter description: ");
        String description = input.nextLine();
        System.out.print("Enter date (MM/DD/YYYY): ");
        String date = input.nextLine();
        System.out.print("Enter time (ex. 3:00 PM): ");
        String time = input.nextLine();
        System.out.print("Enter location: ");
        String location = input.nextLine();
        System.out.print("Enter capacity: ");
        int capacity;
        try{
            capacity = Integer.parseInt(input.nextLine().trim());
        }catch(NumberFormatException e){
            System.out.println("Capacity has to be a number.");
            return;
        }

        Event event = null;
        try{
            if(type.equals("1")){
                System.out.print("Enter major: ");
                String major = input.nextLine();
                event = new AcademicEvent(eventID, eventName, description, date, time, location, capacity, major);
            }else if(type.equals("2")){
                System.out.print("Enter club name: ");
                String clubName = input.nextLine();
                event = new ClubEvent(eventID, eventName, description, date, time, location, capacity, clubName);
            }else if(type.equals("3")){
                System.out.print("Enter company name: ");
                String companyName = input.nextLine();
                event = new CareerEvent(eventID, eventName, description, date, time, location, capacity, companyName);
            }else{
                System.out.print("Enter theme: ");
                String theme = input.nextLine();
                event = new SocialEvent(eventID, eventName, description, date, time, location, capacity, theme);
            }
            eventMang.addEvent(event);
        }catch(DuplicateIdException e){
            System.out.println(e.getMessage());
            return;
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            return;
        }

        //links organizer, optional
        System.out.print("Enter organizer ID (or press Enter to skip): ");
        String orgID = input.nextLine().trim();
        if(!orgID.isEmpty()){
            try{
                orgManager.assignEvent(orgID, event);
            }catch(NotFoundException e){
                System.out.println(e.getMessage() + " The event was still created.");
            }
        }

        System.out.println("Event created!");
        event.displayEvent();
    }

    public void viewEventsMenu(){
        System.out.print("Enter event ID (or press Enter to see all): ");
        String eventID = input.nextLine().trim();
        if(eventID.isEmpty()){
            eventMang.displayAllEvents();
            return;
        }

        try{
            eventMang.displayEventById(eventID);
        }catch(NotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    //search by part of name
    public void searchEventsMenu(){
        System.out.print("Enter part of the event name: ");
        String keyword = input.nextLine();
        ArrayList<Event> results = eventMang.searchEventsByName(keyword);
        if(results.isEmpty()){
            System.out.println("No events found.");
            return;
        }

        System.out.println(results.size() + " event(s) found:");
        for(int i = 0; i < results.size(); i++){
            System.out.println("----------------------------------------");
            results.get(i).displayEvent();
        }
    }


    public void  registerForEventsMenu()
    {
      
        boolean flag = false;
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter student ID");
        String studentID = input.nextLine();
        
        System.out.println("Are you [STUDENT NAME]?(Y/N)");
        Student currentStudent =  studentManage.findStudentById(studentID);

        char confirm = input.next().charAt(0);
        do{
        if(confirm == 'Y' || confirm == 'y')
        {
            System.out.println("Enter the ID for the Event you'd like to register for");
            String eventID = input.nextLine();

            System.out.println("REGISTERED FOR EVENT");
        }
        System.out.println("Do you wish to exit menu?(Y/N)");
        confirm = input.next().charAt(0);
        if(confirm == 'Y'|| confirm == 'y')
            {
                
                flag =true;

        }
    }while(flag!= true);
    }

    public void  cancelRegistrationMenu()
    {
        boolean flag = false;
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter student ID");
        String studentID = input.nextLine();

        System.out.println("Are you [STUDENT NAME]?(Y/N)");
        char confirm = input.next().charAt(0);
        do{
        if(confirm == 'Y' || confirm == 'y')
        {
            System.out.println("LIST EVENTS REGISTERED FOR");
            System.out.println("Enter name of Event you'd like to cancel: ");
            String eventName = input.nextLine(); 

            System.out.println("REMOVED FROM EVENT");

        }
        System.out.println("Do you wish to exit menu?(Y/N)");

        confirm = input.next().charAt(0);
         if(confirm == 'Y'|| confirm == 'y')
        {
                flag = true;
        }

        
    }while(flag!= true);
    }

     public void  viewSutdentRegsMenu()
    {
        boolean flag = false;
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter Event Id: ");
        String eventID = input.nextLine();
        
        System.out.println("Are you looking for [Event Name]?(Y/N)");
        char confirm = input.next().charAt(0);
        do{
        if(confirm == 'Y' || confirm == 'y')
        {
            System.out.println("LIST EVENT ATTENDES");
            
        }
        
        System.out.println("Do you wish to exit menu?(Y/N)");

        confirm = input.next().charAt(0);
        if(confirm == 'Y'|| confirm == 'y')
        {
             flag = true;   
        }

        
    }while(flag!= true);
    }
    public void  viewOrganizersMenu()
    {
        boolean flag = false;
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter Organizer ID:  ");
        String organizerID = input.nextLine();

        System.out.println("Are you [Organizer Name]?(Y/N)");
        char confirm = input.next().charAt(0);
        do{
        if(confirm == 'Y' || confirm == 'y')
        {
            System.out.println("LIST EVENTS Organizing");
        }
        
        System.out.println("Do you wish to exit menu?(Y/N)");

        confirm = input.next().charAt(0);
        if(confirm == 'Y'|| confirm == 'y')
        {
             flag = true;   
        }
        
    }while(flag!= true);
    }

    //reports submenu
    public void systemsReportMenu(){
        ReportGenerator reports = new ReportGenerator();
        ArrayList<Event> events = eventMang.getAllEvents();
        ArrayList<Registration> registrations = regManager.getAllRegistrations();

        System.out.println("SYSTEM REPORTS");
        System.out.println("1. Students registered per event");
        System.out.println("2. Events with available capacity");
        System.out.println("3. Full events");
        System.out.println("4. Events by organizer");
        System.out.print("Choose a report: ");
        String choice = input.nextLine().trim();

        switch(choice){
            case "1":
                reports.displayRegistrationCounts(events, registrations);
                break;
            case "2":
                reports.displayEventsWithSpace(events, registrations);
                break;
            case "3":
                reports.displayFullEvents(events, registrations);
                break;
            case "4":
                System.out.print("Enter organizer ID: ");
                String orgID = input.nextLine().trim();
                try{
                    reports.displayEventsByOrganizer(orgManager.getOrganizer(orgID));
                }catch(NotFoundException e){
                    System.out.println(e.getMessage());
                }
                break;
            default:
                System.out.println("Invalid report choice.");
        }
    }
}

