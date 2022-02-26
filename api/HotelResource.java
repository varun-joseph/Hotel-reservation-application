package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.regex.Pattern;

public class HotelResource {
    
    private static final HotelResource hotelresource = new HotelResource();
    public CustomerService customerService = CustomerService.getSINGLETON();
    public String emailRegEx = "^(.+)@(.+).(.+)$";
    private HotelResource(){}
    public ReservationService reservationService = ReservationService.getSINGLETON();

    public static HotelResource getSINGLETON()
    {
        return hotelresource;
    }
    public Customer getCustomer(String email)
    {
        return customerService.getCustomer(email);
    }
    public Collection<Reservation> getCustomersReservations(String customerEmail)
    {
        if (isvalid_email(customerEmail)==true) {
        	Customer customer = getCustomer(customerEmail);
            if(customer == null) {
                return Collections.emptyList();
            }
            try {
            return reservationService.getCustomersReservation(getCustomer(customerEmail));
            }
            catch(Exception e) {
            	return Collections.emptyList();
            }
        }
        else {
        	throw new IllegalArgumentException("Invalid mail ID!.Please enter valid foramt");
        }
        
    }
    public void createACustomer(String email, String firstName, String lastName)
    {
    	try {
        customerService.addCustomer(email,firstName,lastName);
    	}
        catch(Exception e) {
    		System.out.println("something went wrong");
    	}
    }
    
    public Collection<IRoom> findARoom(Date checkIn, Date checkOut)
    {
        return reservationService.findRooms(checkIn,checkOut);
    }
    
    public IRoom getRoom(String roomNumber)
    {
        return reservationService.getARoom(roomNumber);
    }
    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate)
    {
        return reservationService.reserveARoom(getCustomer(customerEmail),room,checkInDate,checkOutDate);
    }
    public boolean isvalid_email(String customerEmail) {
    	Pattern pattern = Pattern.compile(emailRegEx);
        if(!pattern.matcher(customerEmail).matches()){
            return false;
        }
        else {
        	return true;
        }  	
    }
   
    
}