package com.guvi.service;
import java.util.List;

import com.guvi.dto.BookingRequestDto;
import com.guvi.dto.BookingResponseDto;


public interface BookingService {
	
	BookingResponseDto bookSeats(BookingRequestDto request);
    void cancelBooking(Integer bookingId);
    List<BookingResponseDto> getUserBookings(String Username);
    List<BookingResponseDto> getPastBookings(String Username);
    List<BookingResponseDto> getUpcomingBookings(String Username);

}
