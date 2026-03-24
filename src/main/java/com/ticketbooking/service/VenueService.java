package com.ticketbooking.service;

import com.ticketbooking.model.Venue;
import com.ticketbooking.repository.VenueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VenueService {

    private final VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Transactional(readOnly = true)
    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Venue getVenueById(Long id) {
        return venueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venue not found with id: " + id));
    }

    public Venue createVenue(Venue venue) {
        venue.setTotalCapacity(venue.getTotalRows() * venue.getSeatsPerRow());
        return venueRepository.save(venue);
    }

    public Venue updateVenue(Long id, Venue updated) {
        Venue existing = getVenueById(id);
        existing.setName(updated.getName());
        existing.setCity(updated.getCity());
        existing.setAddress(updated.getAddress());
        existing.setTotalRows(updated.getTotalRows());
        existing.setSeatsPerRow(updated.getSeatsPerRow());
        existing.setTotalCapacity(updated.getTotalRows() * updated.getSeatsPerRow());
        return venueRepository.save(existing);
    }

    public void deleteVenue(Long id) {
        venueRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Venue> findByCity(String city) {
        return venueRepository.findByCity(city);
    }
}
