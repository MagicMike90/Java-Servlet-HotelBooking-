package beans;

import java.io.Serializable;
import java.util.ArrayList;

import model.dto.BookingDTO;
import model.dto.RoomBookingDTO;

public class BookingBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    BookingDTO booking;
    int numRooms;
    int currentRoomNumber = 1;
    double percentage = 0;
    boolean extraRooms = false;
    int minRoom;
    
    public int getMinRoom() {
        return minRoom;
    }

    public void setMinRoom(int minRoom) {
        this.minRoom = minRoom;
    }

    ArrayList<RoomBookingDTO> extraRoomsList = new ArrayList<RoomBookingDTO>();
    
    public ArrayList<RoomBookingDTO> getExtraRoomsList() {
        return extraRoomsList;
    }

    public void setExtraRoomsList(ArrayList<RoomBookingDTO> extraRoomsList) {
        this.extraRoomsList = extraRoomsList;
    }

    public boolean isExtraRooms() {
        return extraRooms;
    }

    public void setExtraRooms(boolean extraRooms) {
        this.extraRooms = extraRooms;
    }

    public BookingDTO getBooking() {
        return booking;
    }

    public void setBooking(BookingDTO booking) {
        this.booking = booking;
    }

    public void setPercentage(int currentRoomNumber) {
	this.percentage = (((currentRoomNumber) * 1.0 ) / numRooms) * 100;
    }
    
    public double getPercentage() {
	return percentage;
    }
    
    public void incrementCurrentRoomNumber() {
	currentRoomNumber++;
	setPercentage(currentRoomNumber);
    }
    
    public void decrementCurrentRoomNumber() {
	currentRoomNumber--;
	setPercentage(currentRoomNumber);
    }
    
    public int getCurrentRoomNumber() {
	return currentRoomNumber;
    }
    
    public void setCurrentRoomNumber(int value) {
	this.currentRoomNumber = value;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }
    
    public String getUrl() {
	return booking.getUrl();
    }
    
//    public SortedMap<Integer,RoomBookingDTO> getRoomBookingsSet() {
//	return booking.getRoomBookingsSet();
//    }
}
