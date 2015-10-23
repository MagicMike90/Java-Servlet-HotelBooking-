package model.dto;

import java.io.Serializable;
import java.util.SortedSet;

public class HotelDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String city;
	private SortedSet<BookingDTO> bookings;
	
	public SortedSet<BookingDTO> getBookings() {
	    return bookings;
	}

	public void setBookings(SortedSet<BookingDTO> bookings) {
	    this.bookings = bookings;
	}

	public HotelDTO() {}

	public int getId() {
		return id;
	}
	public String getCity() {
		return city;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
