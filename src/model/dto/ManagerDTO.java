package model.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class ManagerDTO implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private String username;
    private String password;
    private Set<HotelDTO> hotels;
    
    public ManagerDTO() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<HotelDTO> getHotels() {
	return hotels;
    }

    public void setHotels(Set<HotelDTO> hotels) {
	this.hotels = hotels;
    }
    
}
