package com.guvi.service;
import java.util.List;

import com.guvi.entity.Seat;

public interface SeatService {
	
	List<Seat> getAllSeats();
	List<Seat> getAvailableSeats(Integer eventId);
}
