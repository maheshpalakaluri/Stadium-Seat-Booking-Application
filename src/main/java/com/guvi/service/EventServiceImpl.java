package com.guvi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guvi.entity.Event;
import com.guvi.exception.EventNotExistException;
import com.guvi.repo.EventRepo;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepo erepo;
	@Override
	public List<Event> getAllEvents() {
		return erepo.findAll();
	}

	@Override
	public List<Event> getCompletedEvents() {
		return erepo.findAll().
				stream().
				filter(e->e.getEventEndTime().
				isBefore(LocalDateTime.now()))
				.toList();
		
	}

	@Override
	public List<Event> getUpcomingEvents() {
		return erepo.findAll()
				.stream()
				.filter(e->e.getEventStartTime().isAfter(LocalDateTime.now()))
				.toList();
	}

	@Override
	public List<Event> getLiveEvents() {
		return erepo.findAll().stream().filter(e->e.getEventStartTime().isBefore(LocalDateTime.now())
				&& e.getEventEndTime().isAfter(LocalDateTime.now())).toList();
	}

	@Override
	public Event getEventById(int id) {
		Optional<Event> opt=erepo.findById(id);
		if(opt.isEmpty()) {
			throw new EventNotExistException("No event exist with the given Id");
		}
		Event event=opt.get();
		return event;
	}

	@Override
	public Event getEventByEventDesc(String eventName) {
		Optional<Event> opev=erepo.getEventByEventDesc(eventName);
		if(opev.isEmpty()) {
			throw new EventNotExistException("There is no event with that name");
		}
		return opev.get();
		 
	}

}
