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
       System.out.println("\\033[H\\033[2J");
        System.out.flush();
        System.out.println("VIEW STUDENT MENU");
            System.out.println("Enter ID of student: ");
            String studentID = input.nextLine();
            studentManage.displayStudentById(studentID);
        return;
    }
    public void createOrganizerMenu(){
        Scanner input= new Scanner(System.in);
        String orgID= "";
        String orgName= "";
        String email = "";
        String department = "";
        
        System.out.println("\\033[H\\033[2J");
        System.out.flush();
        System.out.println("ORGAINZER CREATE MENU");

        System.out.println("Do you wish to create an Organizer?(Y/N): ");
        char choice = input.next().charAt(0);
        if(choice =='Y'|| choice == 'y')
        {
        System.out.println("Enter Organizer ID: ");
            orgID = input.nextLine();
        System.out.println("Enter orgName: ");
        orgName = input.nextLine();
        System.out.println("Enter email: ");
        email = input.nextLine();
        System.out.println("Enter Department: ");
        department= input.nextLine();
      try{
        Organizer newOrganizer = new Organizer(orgID, orgName, email, department);
        orgManager.addOrganizer(newOrganizer);
        System.out.println("Organizer added.");
        }catch(DuplicateIdException e){
            System.out.println(e.getMessage());
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }
    public void createEventMenu()
    {
        Scanner input= new Scanner(System.in);
        boolean flagEvent = true;
        //event Inputs
        String eventID= "";
        String eventName= "";
        String eventDate ="";
        String eventTime = "";
        String eventLocation ="";
        int capacity = 0;
        do{
            System.out.println("EVENT CREATE MENU");

        System.out.println("What Type of Event is being created?");
        System.out.println("1.Academic Event");
        System.out.println("2.Club Event");
        System.out.println("3.Career Event");
        System.out.println("4.Social Event");
        char eventType= input.next().charAt(0);
        

         

                 
            switch(eventType)
            {
            case '1':
                System.out.println("Enter Event ID: ");
                    eventID =input.nextLine();   
                    
                System.out.println("Enter Name of Event: ");
                    eventName = input.nextLine();
                System.out.println("Enter Date of Event(MM/DD/YYYY): ");
                  eventDate = input.nextLine();

                 System.out.println("Enter Time(XX:YY PM/AM): ");
                eventTime = input.nextLine();
                
                System.out.println("Enter Location of Event: ");
                    eventLocation = input.nextLine();

                System.out.println("Enter capacity of  participants: ");
                     capacity  = input.nextInt();       
                System.out.println("Enter the major this event is associated with: ");
                     String  majorEvent = input.nextLine();
                     
                    eventMang = eventMang.AcademicEvent(eventID,eventName,eventDate,eventTime,eventLocation,capacity,majorEvent);
                    
                break;
            case '2':
                    System.out.println("Enter Event ID: ");
                        eventID =input.nextLine();   
                    
                    System.out.println("Enter Name of Event: ");
                        eventName = input.nextLine(); 
                    System.out.println("Enter the club hosting event: ");
                      String clubName = input.nextLine();
                    
                    System.out.println("Enter Date of Event(MM/DD/YYYY): ");
                         eventDate = input.nextLine();

                     System.out.println("Enter Time(XX:YY PM/AM): ");
                        eventTime = input.nextLine();
                
                    System.out.println("Enter Location of Event: ");
                        eventLocation = input.nextLine();

                    System.out.println("Enter capacity of  participants: ");
                        capacity  = input.nextInt();       
                      eventMang= eventMang.ClubEvent(eventID,eventName,eventDate,eventTime,eventLocation,capacity,clubName);
                     break;
            case '3':
                    System.out.println("Enter Event ID: ");
                        eventID =input.nextLine();   
                    
                    System.out.println("Enter Name of Event: ");
                        eventName = input.nextLine(); 
                     
                   System.out.println("Enter Date of Event(MM/DD/YYYY): ");
                      eventDate = input.nextLine();

                     System.out.println("Enter Time(XX:YY PM/AM): ");
                         eventTime = input.nextLine();
                
                    System.out.println("Enter Location of Event: ");
                        eventLocation = input.nextLine();

                    System.out.println("Enter capacity of  participants: ");
                        capacity  = input.nextInt();       
                    System.out.println("Enter the name of the company organizing Event: ");
                      String orgName= input.nextLine();

                    eventMang= eventMang.addEvent(eventID,eventName,eventDate,eventTime,eventLocation,capacity,orgName);
                      break;
            case '4':
                    System.out.println("Enter Event ID: ");
                        eventID =input.nextLine();   
                    
                    System.out.println("Enter Name of Event: ");
                        eventName = input.nextLine(); 
                      
                    System.out.println("Enter Date of Event(MM/DD/YYYY): ");
                        eventDate = input.nextLine();

                    System.out.println("Enter Time(XX:YY PM/AM): ");
                        eventTime = input.nextLine();
                
                    System.out.println("Enter Location of Event: ");
                        eventLocation = input.nextLine();

                    System.out.println("Enter capacity of  participants: ");
                        capacity  = input.nextInt();       
                    
                    System.out.println("What is the theme of the social event");
                        String theme = input.nextLine();
                    eventMang= eventMang.addEvent(eventId,eventName,eventDate,eventTime,eventLocation,capacity,orgName);
                    break;
            default:
                 System.out.println("Invalid Input please enter valid input.");
                 flagEvent = false;
                 
            }
        }while(flagEvent != false);

      
    }

    public void viewEventsMenu()
    {
       eventMang.displayAllEvents();
    }

    public void serachEventsMenu()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the ID for an event: ");
        int eventID = input.nextInt();

        System.out.println("DUMMY TEXT: INFO OF CHOSEN EVENT");
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
        Organizer currentOrganizer = orgManager.findOrganizerById(organizerID);
        
        String currentOrgName = currentOrganizer.getName();
        System.out.println("Are you" +currentOrgName+ "(Y/N)");
        char confirm = input.next().charAt(0);
        do{
        if(confirm == 'Y' || confirm == 'y')
        {
           try{ 
              orgManager.displayOrganizerById(organizerID);
           }
           catch(NotFoundException e){
             System.out.println(e.getMessage());
           }
              
        }
        
        System.out.println("Do you wish to exit menu?(Y/N)");

        confirm = input.next().charAt(0);
        if(confirm == 'Y'|| confirm == 'y')
        {
             flag = true;   
        }
        
    }while(flag!= true);
    }

    public void  systemsReportMenu()
    {
        boolean flag = false;
        Scanner input = new Scanner(System.in);
        System.out.println("REPORTS MENU");
        System.out.println("1. View Students Report");
        System.out.println("2. View Events Report");
        System.out.println("3. View Organizers Report");
        do{
        char choice =  input.next().charAt(0);

        switch(choice)
        {
            case'1':
                    System.out.println("STUDENTS REPORT");
                    flag =true;
                    break;
            case'2':
                    System.out.println("EVENTS REPORT");
                    flag = true;
                    break;
            case'3':
                    System.out.println("ORGANIZER REPORT");
                    flag = true;
                    break;
            default:
                    System.out.println("Incorrect input please enter proper value");
                    
        }
        }while(flag != true);
    
    }
}
