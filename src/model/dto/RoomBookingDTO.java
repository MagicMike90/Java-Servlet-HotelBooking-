package model.dto;

import java.io.Serializable;

public class RoomBookingDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private BookingDTO booking;
	private RoomTypeDTO roomType;
	private boolean extraBed;
	private HotelRoomDTO hotelRoom;
	private int localId;
	
	public RoomBookingDTO() {}
	public RoomBookingDTO(boolean extraBed) {
		this.extraBed=extraBed;
	}
	public int getLocalId() {
	    return localId;
	}
	public void setLocalId(int localId) {
	    this.localId = localId;
	}
	public RoomTypeDTO getRoomType() {
		return roomType;
	}
	public boolean isExtraBed() {
		return extraBed;
	}
	public HotelRoomDTO getHotelRoom() {
		return hotelRoom;
	}
	public void setRoomType(RoomTypeDTO roomType) {
		this.roomType = roomType;
	}
	public void setExtraBed(boolean extraBed) {
		this.extraBed = extraBed;
	}
	public void setHotelRoom(HotelRoomDTO hotelRoom) {
		this.hotelRoom = hotelRoom;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getExtraBedString() {
	    return (extraBed == true) ? "Yes" : "No";
	}
	
	public BookingDTO getBooking() {
		return booking;
	}
	public void setBooking(BookingDTO booking) {
	    this.booking = booking;
	}
}
