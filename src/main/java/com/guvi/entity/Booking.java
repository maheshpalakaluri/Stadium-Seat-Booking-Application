package com.guvi.entity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity 
public class Booking {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer bookingId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="username",nullable=false)
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="event_id",nullable=false)
	private Event event;

	@Column(nullable=false)
	private LocalDateTime bookedAt;
	
	/*
	 * @Column(nullable=false) private BookingStatus status=BookingStatus.BOOKED;
	 */
	
	@Column(nullable=true)
	private LocalDateTime cancelledAt;

	
	public Booking() {
	}

	public Booking(Integer bookingId, User user, Event event, LocalDateTime bookedAt, LocalDateTime cancelledAt) {
		super();
		this.bookingId = bookingId;
		this.user = user;
		this.event = event;
		this.bookedAt = bookedAt;
		this.cancelledAt = cancelledAt;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public LocalDateTime getBookedAt() {
		return bookedAt;
	}

	public void setBookedAt(LocalDateTime bookedAt) {
		this.bookedAt = bookedAt;
	}

	public LocalDateTime getCancelledAt() {
		return cancelledAt;
	}

	public void setCancelledAt(LocalDateTime cancelledAt) {
		this.cancelledAt = cancelledAt;
	}
	
	
	

}
