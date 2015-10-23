package model.dto;

import java.io.Serializable;
import java.util.Calendar;


public class DiscountDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private RoomTypeDTO roomType;
	private HotelDTO hotel;
	private Calendar startDate;
	private Calendar endDate;
	private int discountPercentage;
	
	public DiscountDTO(){}
	public DiscountDTO(RoomTypeDTO roomType, HotelDTO hotel, Calendar startDate, Calendar endDate, int discountPercentage){
		this.roomType=roomType;
		this.hotel=hotel;
		this.startDate=startDate;
		this.endDate=endDate;
		this.discountPercentage=discountPercentage;
	}
	public int getId() {
		return id;
	}
	public RoomTypeDTO getRoomType() {
		return roomType;
	}
	public HotelDTO getHotel() {
		return hotel;
	}
	public Calendar getStartDate() {
		return startDate;
	}
	public Calendar getEndDate() {
		return endDate;
	}
	public int getDiscountPercentage() {
		return discountPercentage;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setRoomType(RoomTypeDTO roomType) {
		this.roomType = roomType;
	}
	public void setHotel(HotelDTO hotel) {
		this.hotel = hotel;
	}
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
}
