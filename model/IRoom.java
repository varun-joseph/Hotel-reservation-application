package model;

public interface IRoom {
    public String getRoomNumber();
    public boolean isFree();
    public Double getRoomPrice();
    public RoomType getRoomType();
}