package ui;

import service.EventSystem;
import java.util.Scanner;
public class Menu {
    public static void mainMenu(String[] args){
        char selector = '0';
        Scanner input = new Scanner(System.in);
        //header       
        do{
            System.out.println("\\033[H\\033[2J");
            system.out.flush();
        System.out.println("=================================");
        System.out.println("CAMPIUS EVENT MANAGMET SYSTEM");
        System.out.println("=================================");
        System.out.println(" ");

        //Menus
        System.out.println("1. Add Student");
        System.out.println("2. View Students");
        System.out.println("3. Create Event");
        System.out.println("4. View Events");
        System.out.println("5.Search Event");
        System.out.println("6. Register Student for event");
        System.out.println("7. Cancel Registration");
        System.out.println("8. View Student Registrations");
        System.out.println("9. View Event Attendees");
        System.out.println("10. View Organizers");
        System.out.println("11. System Reports");
        System.out.println("0. Exit");
        System.out.println(" ");
        System.out.println("Enter Selection: ");
        selector = input.next().charAt(0);

        switch(selector)
        {
            case '1':
                menu.studentAddMenu();
                break;
            case '2':
                menu.viewStudentMenu();
                break;
            case '3':
                menu.createEventMenu();
                break;
            case '4':
                menu.viewEventsMenu();
                break;
            case '5':
                menu.searchEventsMenu();
                break;
            case '6':
                menu.registerForEventsMenu();
                break;
            case '7':
                menu.cancelRegistrationMenu();
                break;
            case '8':
                menu.viewSutdentRegsMenu();
                break;
            case '9':
                menu.viewEventAttendsMenu();
                break;
            case '10':
                menu.viewOrganizersMenu();
                break;
            case '11':
                menu.systemsReportMenu();
                break;
            case '0':
                break;
            default:
                system.out.println("Invalid Input please select a valid option");

            
        }
        input.nextLine();
        }while(selector != 0;)
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

        System.out.println("DEMO TEXT Student has been created");
        return;

    }
    
    public void viewStudentMenu()
    {
       Scanner input = new Scanner(System.in);
        System.out.println("If looking for single student type '1'. If looking for all type '2'");
        char choice = input.next().charAt(0);

        if(choice == '1')
        {
            System.out.println("Enter ID of student: ");
            int studentID = input.nextInt();
        }
        else if(choice == '2')
        {
            System.out.println("DEMO TEXT ALL STUDENTS :D");
        }

        return;

    }

    public void createEventMenu()
    {
        Scanner input= new Scanner(System.in);
        boolean flagEvent = true;

        do{
        System.out.println("What Type of Event is being created?");
        System.out.println("1.Academic Event");
        System.out.println("2.Club Event");
        System.out.println("3.Career Event");
        System.out.println("4.Social Event");
        char eventType= input.next().charAt(0);
        
            switch(eventType)
            {
            case '1': System.out.println("Enter the major this event is associated with: ");
                      String  majorEvent = input.nextLine();
                break;
            case '2': System.out.println("Enter the club hosting event: ");
                      String clubName = input.nextLine();
                     break;
            case '3': System.out.println("Enter the name of the company organizing Event: ");
                      String orgName= input.nextLine();
                      break;
            case '4': System.out.println("UNSURE WHAT GOES HERE");
                    break;
            default:
                 System.out.println("Invalid Input please enter valid input.");
                 flagEvent = false;
                 
            }
        }while(flagEvent != false);

        System.out.println("Enter Name of Event: ");
        String eventName = input.nextLine();

        System.out.println("Enter Date of Event(name of Month and Day): ");
        String eventDate = input.nextLine();

        System.out.println("Enter Location of Event: ");
        String eventLocation = input.nextLine();

        System.out.println("Enter capacity of  participants: ");
        int  participants = input.nextInt();       

        System.out.println("Enter Event ID: ");
        int eventID =input.nextInt();
    }

    public void viewEventsMenu()
    {
        System.out.println("DUMMY TEXT: LIST OF EVENTS");
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
