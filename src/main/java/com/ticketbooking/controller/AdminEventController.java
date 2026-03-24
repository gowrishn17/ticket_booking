package com.ticketbooking.controller;

import com.ticketbooking.dto.EventDTO;
import com.ticketbooking.model.enums.EventType;
import com.ticketbooking.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/events")
public class AdminEventController {

    private final EventService eventService;

    public AdminEventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public String listEvents(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "admin/events/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("eventDTO", new EventDTO());
        model.addAttribute("eventTypes", EventType.values());
        return "admin/events/create";
    }

    @PostMapping("/create")
    public String createEvent(@ModelAttribute EventDTO eventDTO) {
        eventService.createEvent(eventDTO);
        return "redirect:/admin/events";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("event", eventService.getEventById(id));
        model.addAttribute("eventTypes", EventType.values());
        return "admin/events/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateEvent(@PathVariable Long id, @ModelAttribute EventDTO eventDTO) {
        eventService.updateEvent(id, eventDTO);
        return "redirect:/admin/events";
    }

    @PostMapping("/{id}/delete")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "redirect:/admin/events";
    }

    @PostMapping("/{id}/publish")
    public String publishEvent(@PathVariable Long id) {
        eventService.publishEvent(id);
        return "redirect:/admin/events";
    }

    @PostMapping("/{id}/cancel")
    public String cancelEvent(@PathVariable Long id) {
        eventService.cancelEvent(id);
        return "redirect:/admin/events";
    }
}
