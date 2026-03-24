package com.ticketbooking.controller;

import com.ticketbooking.dto.ShowDTO;
import com.ticketbooking.service.EventService;
import com.ticketbooking.service.ShowService;
import com.ticketbooking.service.VenueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/shows")
public class AdminShowController {

    private final ShowService showService;
    private final EventService eventService;
    private final VenueService venueService;

    public AdminShowController(ShowService showService, EventService eventService,
                               VenueService venueService) {
        this.showService = showService;
        this.eventService = eventService;
        this.venueService = venueService;
    }

    @GetMapping
    public String listShows(Model model) {
        model.addAttribute("shows", showService.getAllShows());
        return "admin/shows/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("showDTO", new ShowDTO());
        model.addAttribute("events", eventService.getAllEvents());
        model.addAttribute("venues", venueService.getAllVenues());
        return "admin/shows/create";
    }

    @PostMapping("/create")
    public String createShow(@ModelAttribute ShowDTO showDTO) {
        showService.createShow(showDTO);
        return "redirect:/admin/shows";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("show", showService.getShowById(id));
        model.addAttribute("events", eventService.getAllEvents());
        model.addAttribute("venues", venueService.getAllVenues());
        return "admin/shows/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateShow(@PathVariable Long id, @ModelAttribute ShowDTO showDTO) {
        showService.updateShow(id, showDTO);
        return "redirect:/admin/shows";
    }

    @PostMapping("/{id}/delete")
    public String deleteShow(@PathVariable Long id) {
        showService.deleteShow(id);
        return "redirect:/admin/shows";
    }
}
