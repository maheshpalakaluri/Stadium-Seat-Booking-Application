package com.guvi.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity

@Table(
    name = "tickets",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"event_id", "seat_id"})
    })

public class Ticket {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer ticketId;
	
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="event_id",nullable=false)
	private Event event;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="seat_id",nullable=false)
	private Seat seat;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="booking_id",nullable=false)
	private Booking booking;
	
	@CreationTimestamp
	@Column(nullable=false)
	private LocalDateTime bookedAt=LocalDateTime.now();
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private TicketStatus status=TicketStatus.BOOKED;
	
	@Column
	private LocalDateTime cancelledAt;

	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public LocalDateTime getBookedAt() {
		return bookedAt;
	}

	public void setBookedAt(LocalDateTime bookedAt) {
		this.bookedAt = bookedAt;
	}

	public TicketStatus getStatus() {
		return status;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public LocalDateTime getCancelledAt() {
		return cancelledAt;
	}

	public void setCancelledAt(LocalDateTime cancelledAt) {
		this.cancelledAt = cancelledAt;
	}

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", user=" + user + ", seat=" + seat + ", bookedAt=" + bookedAt
				+ ", status=" + status + ", cancelledAt=" + cancelledAt + "]";
	}
	
	
	

}
