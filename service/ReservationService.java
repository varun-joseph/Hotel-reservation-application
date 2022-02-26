package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {

    private static final ReservationService SINGLETON = new ReservationService();
    public Map<String,IRoom> rooms = new HashMap<>();
    public Map<String,Collection<Reservation>> reservations = new HashMap<String,Collection<Reservation>>();
    public static ReservationService getSINGLETON() {
        return SINGLETON;
    }
    public void addRoom(IRoom room)
    {
        rooms.put(room.getRoomNumber(),room);
    }
    public IRoom getARoom(String roomId)
    {
        return rooms.get(roomId);
    }
    public Collection<IRoom> getAllRooms()
    {
        return rooms.values();
    }

    public Collection<IRoom> findRooms(Date checkInDate,Date checkOutDate)
    {
        Collection<Reservation> allReservations = new ArrayList<Reservation>();
        Collection<IRoom> roomNotAvailable = new ArrayList<IRoom>();
        Collection<IRoom> roomAvailable = new HashSet<>();

        reservations.forEach((k,v) ->allReservations.addAll(v));
        Collection<IRoom> rooms = getAllRooms();
        
        for(IRoom r:rooms){
            if(!(isReserved(r)))
            {
                roomAvailable.add(r);
            }
        }
        Iterator it = allReservations.iterator(); 
        while (it.hasNext()) {
        	if(checkInDate.before(((Reservation) it.next()).getCheckOutDate()) && checkOutDate.after(((Reservation) it.next()).getCheckInDate())) {
                roomNotAvailable.add(((Reservation) it.next()).getRoom());
            }
            else{
                roomAvailable.add(((Reservation) it.next()).getRoom());
            }
        }
        
        return roomAvailable;
    }
    public Collection<Reservation> getCustomersReservation(Customer customer)
    {
        return reservations.get(customer.email);
    }
    public void printAllReservation()
    {
        Collection<Reservation> allReservations = new ArrayList<Reservation>();
        reservations.forEach((k,v) ->allReservations.addAll(v));
        if(allReservations.isEmpty()){
            System.out.println("Reservations list is Empty.");
        }
        else {
        	Iterator it = allReservations.iterator(); 
            while (it.hasNext()) {
                System.out.print(it.next() + " ");
        }
       
        }
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate)
    {
        Collection<Reservation> reservationCollection = getCustomersReservation(customer);
        if(reservationCollection == null) {
            reservationCollection = new ArrayList<>();
        }
        Reservation reservation = new Reservation(customer,room,checkInDate,checkOutDate);
        reservationCollection.add(reservation);
        reservations.put(customer.getEmail(),reservationCollection);
        return reservation;
    }
    boolean isReserved(IRoom room) {
        Collection<Reservation> allReservations = new ArrayList<Reservation>();
        reservations.forEach((k,v) ->allReservations.addAll(v));
        Iterator it = allReservations.iterator(); 
        while (it.hasNext()) {
        	if (((Reservation) it.next()).getRoom().equals(room)) {
                return true;
            }
        }
        
        return false;

    }
}