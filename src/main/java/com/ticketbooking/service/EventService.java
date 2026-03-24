package com.ticketbooking.service;

import com.ticketbooking.dto.EventDTO;
import com.ticketbooking.exception.EventNotFoundException;
import com.ticketbooking.factory.EventFactory;
import com.ticketbooking.model.Event;
import com.ticketbooking.model.enums.EventStatus;
import com.ticketbooking.repository.EventRepository;
import com.ticketbooking.repository.ShowRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventService {

    private final EventRepository eventRepository;
    private final ShowRepository showRepository;

    public EventService(EventRepository eventRepository, ShowRepository showRepository) {
        this.eventRepository = eventRepository;
        this.showRepository = showRepository;
    }

    @Transactional(readOnly = true)
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
    }

    public Event createEvent(EventDTO dto) {
        Event event = EventFactory.createEvent(
                dto.getEventType(),
                dto.getTitle(),
                dto.getDescription(),
                dto.getDurationMins(),
                dto.getLanguage(),
                dto.getGenre()
        );
        event.setPosterUrl(dto.getPosterUrl());
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, EventDTO dto) {
        Event existing = getEventById(id);
        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setEventType(dto.getEventType());
        existing.setDurationMins(dto.getDurationMins());
        existing.setLanguage(dto.getLanguage());
        existing.setGenre(dto.getGenre());
        existing.setPosterUrl(dto.getPosterUrl());
        return eventRepository.save(existing);
    }

    public void deleteEvent(Long id) {
        if (!showRepository.findByEventId(id).isEmpty()) {
            throw new IllegalStateException("Cannot delete event: it has scheduled shows.");
        }
        eventRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Event> getPublishedEvents() {
        return eventRepository.findByStatus(EventStatus.PUBLISHED);
    }

    public Event publishEvent(Long id) {
        Event event = getEventById(id);
        event.setStatus(EventStatus.PUBLISHED);
        return eventRepository.save(event);
    }

    public Event cancelEvent(Long id) {
        Event event = getEventById(id);
        event.setStatus(EventStatus.CANCELLED);
        return eventRepository.save(event);
    }
}
