package com.guvi.repo;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.guvi.entity.Event;
@Repository
public interface EventRepo extends JpaRepository<Event,Integer> {
	Optional<Event> getEventByEventDesc(String eventName);
}
