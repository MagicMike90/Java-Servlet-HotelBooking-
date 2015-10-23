package model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BookingDTO implements Serializable, Comparable<BookingDTO>{
	private static final long serialVersionUID = 1L;
	private int id;
    private HotelDTO hotel;
    private int pin;
    private String url;
    private Calendar checkIn;
    private Calendar checkOut;
    private List<RoomBookingDTO> roomBookings;
    
    public BookingDTO(){}
    public BookingDTO(int pin, Calendar checkIn, Calendar checkOut){
    	this.pin=pin;
    	this.checkIn=checkIn;
    	this.checkOut=checkOut;
    	roomBookings = new ArrayList<RoomBookingDTO>();
    }
	public int getId() {
		return id;
	}
	public HotelDTO getHotel() {
		return hotel;
	}
	public int getPin() {
		return pin;
	}
	public Calendar getCheckIn() {
		return checkIn;
	}
	public Calendar getCheckOut() {
		return checkOut;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setHotel(HotelDTO hotel) {
		this.hotel = hotel;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public void setCheckIn(Calendar checkIn) {
		this.checkIn = checkIn;
	}
	public void setCheckOut(Calendar checkOut) {
		this.checkOut = checkOut;
	}
	
	public List<RoomBookingDTO> getRoomBookings() {
		return roomBookings;
	}
	public void setRoomBookings(List<RoomBookingDTO> roomBookings) {
		this.roomBookings = roomBookings;
		for(RoomBookingDTO rb : roomBookings){
		    rb.setBooking(this);
		}
	}
	public void addRoomBooking(RoomBookingDTO roomBooking){
	    	if(!roomBookings.contains(roomBooking)){
	    	    roomBookings.add(roomBooking);
	    	    roomBooking.setBooking(this);
	    	}
	}
	
	public RoomBookingDTO removeLastRoomBooking(){
		if(roomBookings.size()>0) return roomBookings.remove(roomBookings.size()-1);
		return null;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public int compareTo(BookingDTO o) {
	    return this.getCheckIn().compareTo(o.checkIn);
	}
}
