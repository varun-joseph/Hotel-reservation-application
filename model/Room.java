package model;

import java.util.Objects;

public class Room implements IRoom {
    public String roomNumber;
    public Double price;
    public RoomType enumeration;

    public Room(final String roomNum, final Double price, final RoomType enumber) {

        this.roomNumber = roomNum;
        this.price = price;
        this.enumeration = enumber;
    }

    public String getRoomNumber() {

        return roomNumber;
    }

    public Double getRoomPrice() {
        return price;
    }

    public String toString() {
    	String output="Room Number: " + this.roomNumber + "\nprice: " + this.price + "\nRoom type: " + this.enumeration + "\n";
        return "Room_details{"+output+"}";
    }
    
    public RoomType getRoomType() {
        return enumeration;
    }

    public boolean isFree() {
        return price.equals(0.0);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        else if (o == null || getClass() != o.getClass()) return false;
        else{Room room = (Room) o;
        return Objects.equals(roomNumber, room.roomNumber) && Objects.equals(price, room.price) && enumeration == room.enumeration;
    }
    }

}


