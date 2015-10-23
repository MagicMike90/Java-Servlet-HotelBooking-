package model.dto;

import java.util.Calendar;

public class PeakPeriodDTO {
	private int id;
	private Calendar startDate;
	private Calendar endDate;
	private int premiumPercentage;
	
	public PeakPeriodDTO() {}

	public int getId() {
		return id;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public int getPremiumPercentage() {
		return premiumPercentage;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public void setPremiumPercentage(int premiumPercentage) {
		this.premiumPercentage = premiumPercentage;
	}
}
