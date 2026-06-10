package com.guvi.service;
import java.util.List;

import com.guvi.dto.SeatStatusDto;
import com.guvi.entity.Seat;

public interface SeatService {
	
	List<Seat> getAllSeats();
	List<SeatStatusDto> getAvailableSeats(Integer eventId);
}
