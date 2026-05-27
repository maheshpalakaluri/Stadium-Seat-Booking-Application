package com.guvi.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guvi.dto.BookingResponseDto;
import com.guvi.dto.CreateEventDto;
import com.guvi.dto.SeatActionDto;
import com.guvi.entity.Event;
import com.guvi.service.AdminService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins= {"http://localhost:5173"})
public class AdminController {
	
	@Autowired
	private AdminService aservice;
	
	@PostMapping("/events")
    public ResponseEntity<Event> createEvent(
            @RequestBody CreateEventDto dto) {
        
        Event event = aservice.createEvent(dto);
        return new ResponseEntity<>(event,HttpStatus.OK);
    }
 
    @GetMapping("/events/{eventId}/bookings")
    public ResponseEntity<List<BookingResponseDto>> getBookingsByEvent(
            @PathVariable Integer eventId) {
 
        return new ResponseEntity<>(
                aservice.getBookingsByEvent(eventId),HttpStatus.OK
        );
    }
 
    @PostMapping("/seats/disable")
    public ResponseEntity<String> disableSeat(
            @RequestBody SeatActionDto dto) {
 
        aservice.disableSeat(dto);
        return new ResponseEntity<>("Seat disabled successfully",HttpStatus.OK);
    }

    @PostMapping("/seats/enable")
    public ResponseEntity<String> enableSeat(
            @RequestBody SeatActionDto dto) {
 
        aservice.enableSeat(dto);
        return new ResponseEntity<>("Seat enabled successfully",HttpStatus.OK);
    }
 

}
