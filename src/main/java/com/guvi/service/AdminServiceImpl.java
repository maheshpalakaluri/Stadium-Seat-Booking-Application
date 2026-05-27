package com.guvi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guvi.dto.BookingResponseDto;
import com.guvi.dto.CreateEventDto;
import com.guvi.dto.SeatActionDto;
import com.guvi.entity.Booking;
import com.guvi.entity.Event;
import com.guvi.entity.Seat;
import com.guvi.entity.Ticket;
import com.guvi.exception.EventNotExistException;
import com.guvi.exception.SeatUnavailableException;
import com.guvi.repo.BookingRepo;
import com.guvi.repo.EventRepo;
import com.guvi.repo.SeatRepo;
import com.guvi.repo.TicketRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private EventRepo erepo;
	
	@Autowired
	private SeatRepo srepo;
	
	@Autowired
	private BookingRepo brepo;
	
	@Autowired
	private TicketRepo trepo;
	
	@Autowired
	private BookingMapper bookingMapper;

	@Override
	public Event createEvent(CreateEventDto eventDto) {
		Event event=new Event();
		event.setEventDesc(eventDto.getEventdesc());
		event.setEventStartTime(eventDto.getEventEndTime());
		event.setEventEndTime(eventDto.getEventEndTime());
		return erepo.save(event);
	}

	@Override
	public void disableSeat(SeatActionDto seatDto) {
		Optional<Seat> opseat=srepo.findByStandAndRowLabelAndSeatNo(seatDto.getStand(),
				seatDto.getRowLabel(), seatDto.getSeatNo());
		if(opseat.isEmpty()) {
			throw new SeatUnavailableException("seat is not available");
		}
		Seat seat=opseat.get();
		seat.setEnabled(false);
		srepo.save(seat);
		
	}

	@Override
	public void enableSeat(SeatActionDto seatDto) {
		Optional<Seat> opseat=srepo.findByStandAndRowLabelAndSeatNo(seatDto.getStand(),
				seatDto.getRowLabel(), seatDto.getSeatNo());
		if(opseat.isEmpty()) {
			throw new SeatUnavailableException("seat is not available");
		}
		Seat seat=opseat.get();
		seat.setEnabled(true);
		srepo.save(seat);
		
	}

	@Override
	public List<BookingResponseDto> getBookingsByEvent(Integer eventId) {
	 
	    Event event = erepo.findById(eventId)
	            .orElseThrow(() -> new EventNotExistException("Event not found"));
	 
	    List<Booking> bookings = brepo.findByEventEventId(eventId);
	 
	    return bookings.stream()
	            .map(booking -> {
	                List<Ticket> tickets =
	                        trepo.findByBooking_BookingId(booking.getBookingId());
	                return bookingMapper.toBookingResponse(booking, tickets);
	            })
	            .toList();
	}
	

}
