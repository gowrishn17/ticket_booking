package com.ticketbooking.repository;

import com.ticketbooking.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findByEventId(Long eventId);
    List<Show> findByVenueId(Long venueId);
    List<Show> findByShowTimeAfter(LocalDateTime dateTime);
}
