package com.guvi.repo;

import java.util.List; 
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.guvi.entity.Seat;
import com.guvi.entity.Stand;
import com.guvi.entity.TicketStatus;
@Repository
public interface SeatRepo extends JpaRepository<Seat,Integer> {
	
	@Query("""
			SELECT s FROM Seat s
			WHERE s.enabled = true
			AND s.seatId NOT IN (
			    SELECT t.seat.seatId
			    FROM Ticket t
			    WHERE t.event.eventId = :eventId
			    AND t.status = 'BOOKED'
			)
			""")
			List<Seat> findAvailableSeats(@Param("eventId") Integer eventId);
	List<Seat> findByEnabledTrue();
	Optional<Seat> findByStandAndRowLabelAndSeatNo(Stand stand, String rowLabel, Integer seatNo);
	
	

}
