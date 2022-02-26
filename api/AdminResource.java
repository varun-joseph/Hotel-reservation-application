package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;


public class AdminResource {
	public ReservationService reservationService = ReservationService.getSINGLETON();
    public CustomerService customerService = CustomerService.getSINGLETON();
    private AdminResource(){}
    private static final AdminResource adminresource = new AdminResource();
    public static AdminResource getSINGLETON()
    {
        return adminresource;
    }
    public void displayAllReservations()
    {
    	try {
        reservationService.printAllReservation();
    	}
    	catch(Exception e) {
    		System.out.println("something went wrong");
    	}
    }
    public Customer getCustomer(String email)
    {
        return customerService.getCustomer(email);
    	
    }
    public void addRoom(List<IRoom> rooms)
    {
    	for(int i=0;i<rooms.size();i++){
        	reservationService.addRoom(rooms.get(i));
        }
    }
    public Collection<IRoom> getAllRooms()
    {
        return reservationService.getAllRooms();
    }
    public Collection<Customer> getAllCustomers()
    {
        return customerService.getAllCustomers();
    }
    
}