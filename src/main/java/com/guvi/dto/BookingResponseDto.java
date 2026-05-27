package com.guvi.dto;

import java.time.LocalDateTime;
import java.util.List;

public class BookingResponseDto {
	private Integer bookingId;
	private String username;
	private String eventName;
	private LocalDateTime boookedAt;
	private List<String> seats;
	
	public BookingResponseDto() {
		super();
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public LocalDateTime getBoookedAt() {
		return boookedAt;
	}
	public void setBoookedAt(LocalDateTime boookedAt) {
		this.boookedAt = boookedAt;
	}

	public List<String> getSeats() {
		return seats;
	}

	public void setSeats(List<String> seats) {
		this.seats = seats;
	}
	
	
	
	
}
