package com.guvi.service;

import java.util.List;

import com.guvi.dto.BookingResponseDto;
import com.guvi.dto.CreateEventDto;
import com.guvi.dto.SeatActionDto;
import com.guvi.entity.Event;
public interface AdminService {
	
	Event createEvent(CreateEventDto eventDto);
	void disableSeat(SeatActionDto seatDto);
	void enableSeat(SeatActionDto seatDto);
	List<BookingResponseDto> getBookingsByEvent(Integer eventId);

}
