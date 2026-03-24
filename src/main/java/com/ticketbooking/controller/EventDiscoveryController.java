package com.ticketbooking.controller;

import com.ticketbooking.service.EventService;
import com.ticketbooking.service.ShowService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;

@Controller
public class EventDiscoveryController {

    private final EventService eventService;
    private final ShowService showService;

    public EventDiscoveryController(EventService eventService, ShowService showService) {
        this.eventService = eventService;
        this.showService = showService;
    }

    @GetMapping("/events")
    public String browseEvents(Model model) {
        model.addAttribute("events", eventService.getPublishedEvents());
        return "events/browse";
    }

    @GetMapping("/events/{id}")
    public String eventDetail(@PathVariable Long id, Model model) {
        model.addAttribute("event", eventService.getEventById(id));
        model.addAttribute("upcomingShows",
                showService.getUpcomingShowsForEvent(id, LocalDateTime.now()));
        return "events/detail";
    }

    @GetMapping("/shows/{id}/seats")
    public String seatLayout(@PathVariable Long id, Model model) {
        model.addAttribute("show", showService.getShowById(id));
        model.addAttribute("seats", showService.getSeatLayoutForShow(id));
        return "events/seat-layout";
    }
}
