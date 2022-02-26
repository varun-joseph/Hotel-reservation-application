import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class MainMenu {

    public static HotelResource hotelResource = HotelResource.getSINGLETON();
    
    public static void askForReserveRoom(Date checkIn, Date checkOut, Collection<IRoom> rooms)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to reserve room yes/no");
        String addRoom=scanner.next();
        boolean decide = false;
        while(!(addRoom.equals("yes") || addRoom.equals("no"))) {
            System.out.println("Enter yes or no");
            addRoom = scanner.next();
        }
        if(addRoom.equals("yes")) {
            System.out.println("Do you have an account? yes/no");
            String user = scanner.next();
            while(!(user.equals("yes")|| user.equals("no"))) {
            	String optional="Enter yes or no";
                System.out.println(optional);
                user = scanner.next();
            }
            customerfunction(user,decide,checkIn,checkOut,rooms);
        }
        else
        {
            mainMenu();
        }
        
    }
    public static void customerfunction(String user,boolean decide,Date checkIn, Date checkOut, Collection<IRoom> rooms) {
    	Scanner scanner = new Scanner(System.in);
    	if(user.equals("yes"))
        {
            System.out.println("Enter email ID: ");
            String mail = scanner.next();
            try {
            hotelResource.getCustomer(mail);
            String reserveroom="Enter room number to reserve:";
            System.out.println(reserveroom);
            String roomNo = scanner.next();
            Iterator<IRoom> it = rooms.iterator();
            while (it.hasNext()) {
            	if(it.next().getRoomNumber().equals(roomNo))
                {
            		IRoom emptyRoom = hotelResource.getRoom(roomNo);
                   decide = true;
                   Reservation reservation = hotelResource.bookARoom(mail,emptyRoom,checkIn,checkOut);
                   finaloutput(reservation);
                }
            }
            
            if(decide == false)
                {
                    System.out.println("room numer invalid.please select from available rooms");
                    askForReserveRoom(checkIn,checkOut,rooms);
                }
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Email not in list!! Create a new account");
            }
            
            mainMenu();
        }
    	else
        {
            System.out.println("Create an account!!");
            mainMenu();
        }
    }
    public static void finaloutput(Reservation reservation) {
    	System.out.println("Reservation completed .Here are the details.\n");
    	System.out.println("=====================================================");
        System.out.println(reservation);
        System.out.println("=====================================================");
    	
    }
    
    public static void findAndReserveARoom() {
        Scanner scanner = new Scanner(System.in);
        String dateinput="Enter Check in date: (dd-mm-yyyy)";
        System.out.println(dateinput);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String checkInDateStr = scanner.nextLine();
        Date checkInDate = null;
        try {
            checkInDate = dateFormat.parse(checkInDateStr);
        }
        catch(ParseException ex){
            System.out.println("Please enter in correct format dd-mm-yyyy");
            findAndReserveARoom();
        }
        String outdate="Enter Check out date: (dd-mm-yyyy)";
        System.out.println(outdate);
        String checkOutDateStr = scanner.nextLine();
        Date checkOutDate = null;
        try {
            checkOutDate = dateFormat.parse(checkOutDateStr);
        }
        catch (ParseException ex)
        {
            System.out.println("Please enter in correct format dd-mm-yyyy");
            findAndReserveARoom();
        }
        if(checkInDate.after(checkOutDate)){
            System.out.println("Please enter valid check In and check Out dates");
            findAndReserveARoom();
        }
        
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(checkInDate);
        calendar1.add(Calendar.DATE,7);
        Date alterCheckInDate=calendar1.getTime();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(checkOutDate);
        calendar2.add(Calendar.DATE,7);
        Date alterCheckOutDate = calendar2.getTime();
        Collection<IRoom> availableRooms = hotelResource.findARoom(checkInDate,checkOutDate);
        boolean checkavailability=availableRooms.isEmpty();
        if(checkavailability)
        {
        	String roomsavailability="Rooms are not available between "+ checkInDate+ " to " + checkOutDate + " searching for after 7 dates";
            System.out.println(roomsavailability);
            Collection<IRoom> roomsForAlternateDates = hotelResource.findARoom(alterCheckInDate,alterCheckOutDate);
            boolean roomsalternatedates=roomsForAlternateDates.isEmpty();
            if(roomsalternatedates)
            {
                System.out.println("rooms unavailable.hotel is fully occupied");
                mainMenu();
            }
            else
            {
                System.out.println("Rooms found : Check-in Date: "+alterCheckInDate+" Check out date: "+ alterCheckOutDate);
                Iterator<IRoom> it = roomsForAlternateDates.iterator();
                while (it.hasNext()) {
                    System.out.println(it.next());
                }
                askForReserveRoom(alterCheckInDate,alterCheckOutDate,roomsForAlternateDates);
            }
        }
        else
        {
            System.out.println("Available rooms: ");
            Iterator<IRoom> it = availableRooms.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }

            askForReserveRoom(checkInDate,checkOutDate,availableRooms);
        }
    }
    public static void seeMyReservations()
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter mail ID: ");
        Collection<Reservation> myReservations = Collections.emptyList();
        String email = scanner.nextLine();
        myReservations = hotelResource.getCustomersReservations(email);
        try {
        if(myReservations==null){
            System.out.println("No reservations found for that user");
            mainMenu();
        }
        else {
            Iterator<Reservation> it = myReservations.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }

            mainMenu();
        }
        }
        catch (IllegalArgumentException e){
        	System.out.print(e);
        }
    }
    public static void createAnAccount()
    {
    	try {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter email Id: ");
        String email = scanner.nextLine();
        System.out.println("Enter first name:");
        String fname = scanner.nextLine();
        System.out.println("Enter last name: ");
        String lname = scanner.nextLine();
        hotelResource.createACustomer(email,fname,lname);
        System.out.println("Customer account created!!");
        mainMenu();
    	}
    	catch (Exception e){
    		System.out.println("something went wrong");
    		mainMenu();
    	}
   
    }
    public static void printMainMenu()
    {
        System.out.print("\nWelcome to the Hotel Reservation Application\n" +
                "--------------------------------------------\n" +
                "1. Find and reserve a room\n" +
                "2. See my reservations\n" +
                "3. Create an Account\n" +
                "4. Admin\n" +
                "5. Exit\n" +
                "--------------------------------------------\n" +
                "Please select a number for the menu option:\n");
    }
    public static void mainMenu() {
    	printMainMenu();
        Scanner scanner = new Scanner(System.in);
        String mainpstr = scanner.nextLine();
        while(mainpstr.length()!=1){
            mainMenu();
        }
        int mainpStr=Integer.parseInt(mainpstr);
        if(mainpStr<1&mainpStr>5){
            System.out.println("please select valid option");
            mainMenu();
        }


        switch (mainpStr) {
            case 1:
            	System.out.println("finding a room.....");
                findAndReserveARoom();
                break;
            case 2:
            	System.out.println("fetching reservations....");
                seeMyReservations();
                break;
            case 3:
            	System.out.println("creating account....");
                createAnAccount();
                break;
            case 4:
            	System.out.println("fetching admin menu....");
                AdminMenu.adminMenu();
                break;
            case 5:
                System.out.println("Exit");
                break;
            default:
                System.out.println("please select valid option");
                break;
        }
    }

}