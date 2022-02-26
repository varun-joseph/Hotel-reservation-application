package model;

public class FreeRoom extends Room{

    public FreeRoom(final String roomNum, final RoomType enumber)
    {
        super(roomNum,0.00,enumber);
    }


    @Override
    public String toString() {
    	if(super.toString()=="") {
    		return "No FreeRoom";
    	}
    	else {
        return "free_room" + super.toString() ;
    	}
    }


}