package model;

import java.util.Date;
import java.util.Objects;

public class Reservation {
    private final Customer customer;
    private final  IRoom room;
    private final Date checkInDate;
    private  final Date checkOutDate;

    public Reservation(final Customer customers, final IRoom room, final Date checkInDate, final Date checkOutDate) {
        this.customer = customers;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
    public Date getCheckInDate() {
        return this.checkInDate;
    }

    public Date getCheckOutDate() {
    	
        return this.checkOutDate;
    }

    public Customer getCustomer() {
    	return this.customer;
    }
    public IRoom getRoom() {
        return this.room;
    }


    public String toString() {
    	String output="Customer:" + this.customer.toString() + "\nRoom:" + this.room.toString() + "\nCheck in date: " + this.checkInDate + "\nCheck out date: " + this.checkOutDate ;
        return "Reservation_details{"+output+"}";
    }


}

