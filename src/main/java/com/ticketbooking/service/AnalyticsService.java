package com.ticketbooking.service;

import com.ticketbooking.dto.AnalyticsDTO;
import com.ticketbooking.repository.BookingRepository;
import com.ticketbooking.repository.EventRepository;
import com.ticketbooking.repository.ShowRepository;
import com.ticketbooking.repository.VenueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AnalyticsService {

    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;
    private final ShowRepository showRepository;
    private final VenueRepository venueRepository;

    public AnalyticsService(BookingRepository bookingRepository, EventRepository eventRepository,
                            ShowRepository showRepository, VenueRepository venueRepository) {
        this.bookingRepository = bookingRepository;
        this.eventRepository = eventRepository;
        this.showRepository = showRepository;
        this.venueRepository = venueRepository;
    }

    public AnalyticsDTO getAnalytics() {
        return AnalyticsDTO.builder()
                .totalBookings(getTotalBookings())
                .totalVenues(venueRepository.count())
                .totalEvents(eventRepository.count())
                .totalShows(showRepository.count())
                .revenuePerEvent(getRevenuePerEvent())
                .bookingCountPerEvent(getBookingCountPerEvent())
                .build();
    }

    public long getTotalBookings() {
        return bookingRepository.count();
    }

    public List<Object[]> getRevenuePerEvent() {
        return bookingRepository.getRevenueByEvent();
    }

    public List<Object[]> getBookingCountPerEvent() {
        return bookingRepository.getBookingCountByEvent();
    }
}
