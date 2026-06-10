package com.guvi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.guvi.entity.Ticket;
import com.guvi.entity.TicketStatus;
@Repository
public interface TicketRepo extends JpaRepository<Ticket,Integer> {
	
	List<Ticket> findByBooking_BookingId(Integer bookingId);
	boolean existsByEventEventIdAndSeatSeatIdAndStatus(
	        Integer eventId,
	        Integer seatId,
	        TicketStatus status
	);
	 
}
