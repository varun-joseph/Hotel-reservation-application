import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.*;
import java.util.regex.Pattern;

public class AdminMenu {
    public static AdminResource adminResource = AdminResource.getSINGLETON();
    public static void checkAllCustomers()
    {
        Collection<Customer> customerCollections = adminResource.getAllCustomers();
        if(customerCollections.isEmpty())
        {
            System.out.println("Customers list is empty");
            MainMenu.mainMenu();
        }
        else
        {
            Iterator<Customer> it = customerCollections.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }

            adminMenu();
        }
    }
    public static void checkAllRooms()
    {
        Collection<IRoom> rooms = adminResource.getAllRooms();

        if(rooms.isEmpty())
        {
            System.out.println("No rooms available");
            MainMenu.mainMenu();
        }
        else
        {
            Iterator<IRoom> it = rooms.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }

            adminMenu();
        }
    }
    public static void checkAllReservations()
    {
        adminResource.displayAllReservations();
        MainMenu.mainMenu();
    }
    public static void addARoom()
    {
 
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter room number: ");
        String room_no = scanner.nextLine();
        System.out.println("Enter the price per night:");
        String roomCostStr = scanner.next();
        double roomCost=0.0;
        try {
        	 roomCost= Double.parseDouble(roomCostStr);
        }
        catch(Exception e) {
        	System.out.println("Invalid cost!!!!! enter valid Ex: 00.00");
            addARoom();
        }
        String roomtypeoutput="Enter the room type: SINGLE for single bed, DOUBLE for double bed";
        System.out.println(roomtypeoutput);
        String roomTypeStr = scanner.next();
        if(!(roomTypeStr.contentEquals("SINGLE"))&!(roomTypeStr.contentEquals("DOUBLE"))){
        	String invalidoutput="INVALID room type!! Please enter SINGLE or DOUBLE and try again!!";
            System.out.println(invalidoutput);
            addARoom();
        }
        RoomType room_type = RoomType.valueOf(roomTypeStr);
        Room room = new Room(room_no,roomCost,room_type);
        System.out.println("Room added successfully");
        adminResource.addRoom(Collections.singletonList(room));
        try {
        	opinion();
        }catch (StringIndexOutOfBoundsException ex) {
            opinion();
        }
        
    }
    public static void opinion() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Would you like to add another room y/n");
    	String opinionstr1 = scanner.next();
    	char opinion=opinionstr1.charAt(0);
        while(opinion != 'y' && opinion != 'n') {
            System.out.println("Enter y (yes) or n (no)");
            opinion = scanner.next().charAt(0);
        }
        if(opinion == 'y') {
            addARoom();
        }
        else if(opinion == 'n') {
            adminMenu();

        }
    }
    private static void printMenu() {
        System.out.print("\nAdmin Menu\n" +
                "--------------------------------------------\n" +
                "1. See all Customers\n" +
                "2. See all Rooms\n" +
                "3. See all Reservations\n" +
                "4. Add a Room\n" +
                "5. Back to Main Menu\n" +
                "--------------------------------------------\n" +
                "Please select a number for the menu option:\n");
    }
    public static void adminMenu() {
        printMenu();
        Scanner scanner = new Scanner(System.in);
        String optionstr = scanner.nextLine();
        int optionStr=Integer.parseInt(optionstr);
        if(optionStr<1&optionStr>5){
        	String str2="INVALID!!!!! select valid option";
            System.out.println(str2);
            adminMenu();
        }

        switch (optionStr)
        {
            case 1:
            	System.out.println("checking customers...");
                checkAllCustomers();
                break;
            case 2:
            	System.out.println("checking rooms.....");
                checkAllRooms();
                break;
            case 3:
            	System.out.println("checking reservations....");
                checkAllReservations();
                break;
            case 4:
            	System.out.print("adding room....");
                addARoom();
                break;
            case 5:
                MainMenu.mainMenu();
                break;
            default:
                System.out.println("INVALID option! Select valid");
                AdminMenu.adminMenu();
        }
    }
}