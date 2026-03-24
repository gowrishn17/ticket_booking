package com.ticketbooking.service;

import com.ticketbooking.dto.ShowDTO;
import com.ticketbooking.exception.ShowNotFoundException;
import com.ticketbooking.model.Event;
import com.ticketbooking.model.Seat;
import com.ticketbooking.model.Show;
import com.ticketbooking.model.Venue;
import com.ticketbooking.model.enums.SeatStatus;
import com.ticketbooking.repository.SeatRepository;
import com.ticketbooking.repository.ShowRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ShowService {

    private final ShowRepository showRepository;
    private final SeatRepository seatRepository;
    private final EventService eventService;
    private final VenueService venueService;

    public ShowService(ShowRepository showRepository, SeatRepository seatRepository,
                       EventService eventService, VenueService venueService) {
        this.showRepository = showRepository;
        this.seatRepository = seatRepository;
        this.eventService = eventService;
        this.venueService = venueService;
    }

    @Transactional(readOnly = true)
    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Show getShowById(Long id) {
        return showRepository.findById(id)
                .orElseThrow(() -> new ShowNotFoundException(id));
    }

    public Show createShow(ShowDTO dto) {
        Event event = eventService.getEventById(dto.getEventId());
        Venue venue = venueService.getVenueById(dto.getVenueId());

        Show show = Show.builder()
                .event(event)
                .venue(venue)
                .showTime(dto.getShowTime())
                .basePrice(dto.getBasePrice())
                .availableSeats(venue.getTotalCapacity())
                .build();

        Show savedShow = showRepository.save(show);
        generateSeatsForShow(savedShow);
        return savedShow;
    }

    public Show updateShow(Long id, ShowDTO dto) {
        Show existing = getShowById(id);
        Event event = eventService.getEventById(dto.getEventId());
        Venue venue = venueService.getVenueById(dto.getVenueId());

        existing.setEvent(event);
        existing.setVenue(venue);
        existing.setShowTime(dto.getShowTime());
        existing.setBasePrice(dto.getBasePrice());
        return showRepository.save(existing);
    }

    public void deleteShow(Long id) {
        showRepository.deleteById(id);
    }

    public void generateSeatsForShow(Show show) {
        Venue venue = show.getVenue();
        List<Seat> seats = new ArrayList<>();

        for (int row = 1; row <= venue.getTotalRows(); row++) {
            for (int seatNum = 1; seatNum <= venue.getSeatsPerRow(); seatNum++) {
                String label = (char) ('A' + row - 1) + String.valueOf(seatNum);
                Seat seat = Seat.builder()
                        .show(show)
                        .venue(venue)
                        .rowNumber(row)
                        .seatNumber(seatNum)
                        .seatLabel(label)
                        .status(SeatStatus.AVAILABLE)
                        .price(show.getBasePrice())
                        .build();
                seats.add(seat);
            }
        }
        seatRepository.saveAll(seats);
    }

    @Transactional(readOnly = true)
    public List<Show> getUpcomingShowsForEvent(Long eventId, LocalDateTime after) {
        return showRepository.findByEventIdAndShowTimeAfter(eventId, after);
    }

    @Transactional(readOnly = true)
    public List<Seat> getSeatLayoutForShow(Long showId) {
        return seatRepository.findByShowId(showId);
    }
}
