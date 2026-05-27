package com.guvi.dto;

import java.time.LocalDateTime;

public class CreateEventDto {
	
	private String eventdesc;
	private LocalDateTime eventStartTime;
	private LocalDateTime eventEndTime;
	
	public String getEventdesc() {
		return eventdesc;
	}
	public void setEventdesc(String eventdesc) {
		this.eventdesc = eventdesc;
	}
	public LocalDateTime getEventStartTime() {
		return eventStartTime;
	}
	public void setEventStartTime(LocalDateTime eventStartTime) {
		this.eventStartTime = eventStartTime;
	}
	public LocalDateTime getEventEndTime() {
		return eventEndTime;
	}
	public void setEventEndTime(LocalDateTime eventEndTime) {
		this.eventEndTime = eventEndTime;
	}
	
	
	
}
