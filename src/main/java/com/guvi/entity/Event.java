package com.guvi.entity;

import java.time.LocalDateTime; 

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Event {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer eventId;
	
	@Column(nullable=false)
	private String eventDesc;
	
	@Column(nullable=false)
	private LocalDateTime eventStartTime;
	
	@Column(nullable=false)
	private LocalDateTime eventEndTime;

	public Event() {
		super();
	}

	public Event(Integer eventId, String eventDesc, LocalDateTime eventStartTime, LocalDateTime eventEndTime) {
		super();
		this.eventId = eventId;
		this.eventDesc = eventDesc;
		this.eventStartTime = eventStartTime;
		this.eventEndTime = eventEndTime;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getEventDesc() {
		return eventDesc;
	}

	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
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
