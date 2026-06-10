package com.guvi.web;
import com.guvi.dto.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.guvi.dto.BookingResponseDto;
import com.guvi.exception.BookingNotFoundException;
import com.guvi.repo.BookingRepo;
import com.guvi.service.BookingService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
@RestController
@RequestMapping("api/bookings")
@CrossOrigin(origins= {"http://localhost:5173"})
@SecurityRequirement(name = "BearerAuth")

public class BookingController {
	
	@Autowired
	private BookingService bservice;
	
	@Autowired
	private BookingRepo brepo;
	
	@PostMapping
	public ResponseEntity<BookingResponseDto> bookSeats(@Valid @RequestBody BookingRequestDto request){
		BookingResponseDto response=bservice.bookSeats(request);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PutMapping("/cancel/{bookingId}")
	public ResponseEntity<String> cancelBooking(@PathVariable Integer bookingId) {
		bservice.cancelBooking(bookingId);
		return new ResponseEntity<>("Booking Cancelled successfully",HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/user/{username}")
	public ResponseEntity<List<BookingResponseDto>> getUserBookings(@PathVariable String username){
		List<BookingResponseDto> bookings=bservice.getUserBookings(username);
		return new ResponseEntity<>(bookings,HttpStatus.OK);
	}
	
	@GetMapping("/user/{username}/upcoming")
	public ResponseEntity<List<BookingResponseDto>> getUpcomingBookings(@PathVariable String username){
		List<BookingResponseDto> ubookings=bservice.getUpcomingBookings(username);
		return new ResponseEntity<>(ubookings,HttpStatus.OK);
	}
	
	@GetMapping("/user/{username}/past")
	public ResponseEntity<List<BookingResponseDto>> getPastBookings(@PathVariable String username){
		List<BookingResponseDto> pbookings=bservice.getPastBookings(username);
		return new ResponseEntity<>(pbookings,HttpStatus.OK);
	}
	
	
	
	
	

}
