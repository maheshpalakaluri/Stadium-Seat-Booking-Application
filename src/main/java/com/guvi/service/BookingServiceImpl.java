package com.guvi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.guvi.dto.BookingRequestDto;
import com.guvi.dto.BookingResponseDto;
import com.guvi.entity.Booking;
import com.guvi.entity.Event;
import com.guvi.entity.Seat;
import com.guvi.entity.Ticket;
import com.guvi.entity.TicketStatus;
import com.guvi.entity.User;
import com.guvi.exception.BookingNotFoundException;
import com.guvi.exception.EventNotExistException;
import com.guvi.exception.SeatUnavailableException;
import com.guvi.repo.BookingRepo;
import com.guvi.repo.EventRepo;
import com.guvi.repo.SeatRepo;
import com.guvi.repo.TicketRepo;
import com.guvi.repo.UserRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

	@Autowired
	private SeatRepo srepo;

	@Autowired
	private UserRepo urepo;

	@Autowired
	private TicketRepo trepo;

	@Autowired
	private BookingRepo brepo;

	@Autowired
	private EventRepo erepo;

	@Override
	public BookingResponseDto bookSeats(BookingRequestDto request) {
		try {
			User user = urepo.findById(request.getUserName()).orElseThrow(() -> new RuntimeException("User not found"));
			Event event = erepo.findById(request.getEventId())
					.orElseThrow(() -> new RuntimeException("Event not found"));
			if (event.getEventEndTime().isBefore(LocalDateTime.now())) {
				throw new EventNotExistException("Event already completed");
			}
			List<Seat> seats = srepo.findAllById(request.getSeatIds());
			if (seats.size() != request.getSeatIds().size()) {
				throw new SeatUnavailableException("Invalid seat selection");
			}
			Booking booking = new Booking();
			booking.setUser(user);
			booking.setEvent(event);
			booking.setBookedAt(LocalDateTime.now());
			Booking savedBooking = brepo.save(booking);
			List<Ticket> tickets = seats.stream().map(seat -> {
				Ticket ticket = new Ticket();
				ticket.setUser(user);
				ticket.setBooking(savedBooking);
				ticket.setEvent(event);
				ticket.setSeat(seat);
				ticket.setStatus(TicketStatus.BOOKED);
				return ticket;
			}).toList();
			trepo.saveAll(tickets);
			return buildBookingResponse(savedBooking, tickets);
		} catch (DataIntegrityViolationException ex) {
			throw new SeatUnavailableException("Seat is already booked");
		}
	}

	@Override
	public void cancelBooking(Integer bookingId) {
		Optional<Booking> optbook = brepo.findById(bookingId);
		Booking booking = optbook.get();
		if (booking == null) {
			throw new BookingNotFoundException("Selected Booking doesn't exist");
		}
		List<Ticket> tickets = trepo.findByBooking_BookingId(bookingId);
		for (Ticket ticket : tickets) {
			ticket.setStatus(TicketStatus.CANCELLED);
			ticket.setCancelledAt(LocalDateTime.now());
		}
		trepo.saveAll(tickets);

	}

	@Override
	public List<BookingResponseDto> getUserBookings(String username) {
		List<Booking> bookings = brepo.findByUserUserName(username);
		return bookings.stream().map(b -> {
			List<Ticket> tickets = trepo.findByBooking_BookingId(b.getBookingId());
			return buildBookingResponse(b, tickets);
		}).toList();
	}
	
	

	@Override
	public List<BookingResponseDto> getPastBookings(String Username) {
		LocalDateTime now = LocalDateTime.now();
		List<Booking> bookings = brepo.findByUserUserName(Username);
		return bookings.stream().filter(b -> b.getEvent().getEventEndTime().isBefore(now)).map(b -> {
			List<Ticket> tickets = trepo.findByBooking_BookingId(b.getBookingId());
			return buildBookingResponse(b, tickets);
		}).toList();
	}

	public BookingResponseDto buildBookingResponse(
	        Booking booking,
	        List<Ticket> tickets) {
	 
		BookingResponseDto response = new BookingResponseDto();
	 
	    response.setBookingId(booking.getBookingId());
	    response.setUsername(booking.getUser().getUserName());
	    response.setEventName(booking.getEvent().getEventDesc());
	    response.setBoookedAt(booking.getBookedAt());
	 
	    // Map tickets → seat descriptions
	    response.setSeats(
	        tickets.stream()
	               .map(ticket ->
	                    ticket.getSeat().getStands() + "-" +
	                    ticket.getSeat().getRowLabel() + "-" +
	                    ticket.getSeat().getSeatNo()
	               )
	               .toList()
	    );
	 
	    return response;
	}
	 

	@Override
	public List<BookingResponseDto> getUpcomingBookings(String username) {

	    LocalDateTime now = LocalDateTime.now();

	    List<Booking> bookings = brepo.findByUserUserName(username);

	    return bookings.stream()
	        .filter(b -> b.getEvent().getEventEndTime().isAfter(now))
	        .filter(b -> trepo.findByBooking_BookingId(b.getBookingId())
	                .stream()
	                .anyMatch(t -> t.getStatus() == TicketStatus.BOOKED))
	        .map(b -> {
	            List<Ticket> tickets =
	                    trepo.findByBooking_BookingId(b.getBookingId());

	            return buildBookingResponse(b, tickets);
	        })
	        .toList();
	}

	

}
