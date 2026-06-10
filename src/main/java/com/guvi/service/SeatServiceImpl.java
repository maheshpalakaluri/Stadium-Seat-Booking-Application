package com.guvi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guvi.dto.SeatStatusDto;
import com.guvi.entity.Event;
import com.guvi.entity.Seat;
import com.guvi.entity.TicketStatus;
import com.guvi.exception.EventNotExistException;
import com.guvi.repo.EventRepo;
import com.guvi.repo.SeatRepo;
import com.guvi.repo.TicketRepo;
@Service
public class SeatServiceImpl implements SeatService {

	@Autowired
	private SeatRepo srepo;
	
	@Autowired
	private EventRepo erepo;
	
	@Autowired
	private TicketRepo trepo;
	
	@Override
	public List<Seat> getAllSeats() {
		return srepo.findByEnabledTrue();
	}

	@Override
	public List<SeatStatusDto> getAvailableSeats(Integer eventId) {

	    Event event = erepo.findById(eventId)
	            .orElseThrow(() -> new EventNotExistException("Event not found"));

	    List<Seat> seats = srepo.findByEnabledTrue();

	    return seats.stream()
	            .map(seat -> {

	                boolean booked =
	                        trepo.existsByEventEventIdAndSeatSeatIdAndStatus(
	                                eventId,
	                                seat.getSeatId(),
	                                TicketStatus.BOOKED);

	                return new SeatStatusDto(
	                        seat.getSeatId(),
	                        seat.getStands(),
	                        seat.getRowLabel(),
	                        seat.getSeatNo(),
	                        seat.getEnabled(),
	                        booked
	                );
	            })
	            .toList();
	}

}
