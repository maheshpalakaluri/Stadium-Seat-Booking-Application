package com.guvi.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class BookingRequestDto {

	@NotNull
	private String userName;
	
	@NotNull
	private Integer eventId;
	
	@NotEmpty
	private List<Integer> seatIds;


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public List<Integer> getSeatIds() {
		return seatIds;
	}

	public void setSeatIds(List<Integer> seatIds) {
		this.seatIds = seatIds;
	}
	
}
