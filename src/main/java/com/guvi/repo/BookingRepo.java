package com.guvi.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guvi.entity.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {
	
	List<Booking> findByUserUserName(String Username);
	List<Booking> findByEventEventId(Integer userId);
	
}
