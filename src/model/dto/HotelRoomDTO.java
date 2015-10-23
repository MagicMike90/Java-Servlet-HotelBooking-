package model.dto;

import java.io.Serializable;

public class HotelRoomDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private HotelDTO hotel;
	private RoomTypeDTO roomType;
	private String status;
	
	public HotelRoomDTO(){}
	public HotelRoomDTO(HotelDTO hotel, RoomTypeDTO roomType, String status){
		this.hotel=hotel;
		this.roomType=roomType;
		this.status=status;
	}
	public int getId() {
		return id;
	}
	public HotelDTO getHotel() {
		return hotel;
	}
	public RoomTypeDTO getRoomType() {
		return roomType;
	}
	public String getStatus() {
		return status;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setHotel(HotelDTO hotel) {
		this.hotel = hotel;
	}
	public void setRoomType(RoomTypeDTO roomType) {
		this.roomType = roomType;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
