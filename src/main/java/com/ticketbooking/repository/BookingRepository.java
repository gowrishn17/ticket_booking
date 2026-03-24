package com.ticketbooking.repository;

import com.ticketbooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b.show.event.title, SUM(b.totalAmount) FROM Booking b GROUP BY b.show.event.title")
    List<Object[]> getRevenueByEvent();

    @Query("SELECT b.show.event.title, COUNT(b) FROM Booking b GROUP BY b.show.event.title ORDER BY COUNT(b) DESC")
    List<Object[]> getBookingCountByEvent();
}
