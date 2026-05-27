package com.guvi.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.guvi.dto.BookingResponseDto;
import com.guvi.entity.Booking;
import com.guvi.entity.Ticket;

@Component
public class BookingMapper {

	public BookingResponseDto toBookingResponse(Booking booking, List<Ticket> tickets) {

		BookingResponseDto dto = new BookingResponseDto();
		dto.setBookingId(booking.getBookingId());
		dto.setUsername(booking.getUser().getUserName());
		dto.setEventName(booking.getEvent().getEventDesc());
		dto.setBoookedAt(booking.getBookedAt());

		dto.setSeats(tickets.stream().map(ticket -> ticket.getSeat().getSeatDesc()).toList());

		return dto;
	}
}