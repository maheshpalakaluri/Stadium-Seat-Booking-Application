package com.guvi.service;

import com.guvi.entity.*;
import java.util.List;

public interface EventService {
	
	List<Event> getAllEvents();
	List<Event> getCompletedEvents();
	List<Event> getUpcomingEvents();
	List<Event> getLiveEvents();
	Event getEventById(int id);
	Event getEventByEventDesc(String eventName);
	

}
