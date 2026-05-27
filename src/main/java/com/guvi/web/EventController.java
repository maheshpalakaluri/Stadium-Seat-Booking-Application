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

import com.guvi.entity.Event;
import com.guvi.service.EventService;

@RestController
@RequestMapping("api/events")
@CrossOrigin(origins = { "http://localhost:5173" })
public class EventController {
	
	@Autowired
	private EventService eservice;
	
	@GetMapping
	public ResponseEntity<List<Event>> getAllEvents(){
		return new ResponseEntity<>(eservice.getAllEvents(),HttpStatus.OK);
	}
	
	@GetMapping("/upcoming")
	public ResponseEntity<List<Event>> getUpcomingEvents(){
		return new ResponseEntity<>(eservice.getUpcomingEvents(),HttpStatus.OK);
	}
	
	@GetMapping("/live")
	public ResponseEntity<List<Event>> getLiveEvents(){
		return new ResponseEntity<>(eservice.getLiveEvents(),HttpStatus.OK);
	}
	
	@GetMapping("/finished")
	public ResponseEntity<List<Event>> getCompletedEvents(){
		List<Event> lst=eservice.getCompletedEvents();
		return new ResponseEntity<>(lst,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Event> getEventById(@PathVariable int id){
		Event event=eservice.getEventById(id);
		return new ResponseEntity<>(event,HttpStatus.OK);
	}
	
	

}
