package model.dto;

import java.io.Serializable;

public class RoomTypeDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	int id;
	String name;
	String description;
	double price;
	double correctPrice;
	
	public RoomTypeDTO(){}

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public double getPrice() {
		return price;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public double getCorrectPrice() {
		return correctPrice;
	}

	public void setCorrectPrice(double correctPrice) {
		this.correctPrice = correctPrice;
	}
}
