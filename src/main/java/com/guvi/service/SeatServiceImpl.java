package com.guvi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guvi.entity.Event;
import com.guvi.entity.Seat;
import com.guvi.exception.EventNotExistException;
import com.guvi.repo.EventRepo;
import com.guvi.repo.SeatRepo;
@Service
public class SeatServiceImpl implements SeatService {

	@Autowired
	private SeatRepo srepo;
	
	@Autowired
	private EventRepo erepo;
	@Override
	public List<Seat> getAllSeats() {
		return srepo.findByEnabledTrue();
	}

	@Override
	public List<Seat> getAvailableSeats(Integer eventId) {

	    Event event = erepo.findById(eventId)
	            .orElseThrow(() -> new EventNotExistException("Event not found"));
	 
	    if (event.getEventEndTime().isBefore(LocalDateTime.now())) {
	        return List.of(); 
	    }
	    return srepo.findAvailableSeats(eventId);
	}

}
