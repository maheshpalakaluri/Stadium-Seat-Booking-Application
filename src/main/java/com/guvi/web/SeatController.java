package com.guvi.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guvi.entity.Seat;
import com.guvi.service.SeatService;

@RestController
@RequestMapping("/api/seat")
@CrossOrigin(origins= {"http://localhost:5173"})
public class SeatController {
	
	@Autowired
	private SeatService stservice;

	@GetMapping("/getAllSeats")
	public ResponseEntity<List<Seat>> getAllSeats(){
		List<Seat> lseat=stservice.getAllSeats();
		if(lseat.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(lseat,HttpStatus.OK);
	}
	
	@GetMapping("/getAvailableSeats/{eventId}")
	public ResponseEntity<List<Seat>> getAvalilableSeats(@PathVariable int eventId){
		List<Seat> avlseat=stservice.getAvailableSeats(eventId);
		return new ResponseEntity<>(avlseat,HttpStatus.OK);
	}
}
