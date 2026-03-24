package com.ticketbooking.controller;

import com.ticketbooking.model.Venue;
import com.ticketbooking.service.VenueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/venues")
public class AdminVenueController {

    private final VenueService venueService;

    public AdminVenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping
    public String listVenues(Model model) {
        model.addAttribute("venues", venueService.getAllVenues());
        return "admin/venues/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("venue", new Venue());
        return "admin/venues/create";
    }

    @PostMapping("/create")
    public String createVenue(@ModelAttribute Venue venue) {
        venueService.createVenue(venue);
        return "redirect:/admin/venues";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("venue", venueService.getVenueById(id));
        return "admin/venues/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateVenue(@PathVariable Long id, @ModelAttribute Venue venue) {
        venueService.updateVenue(id, venue);
        return "redirect:/admin/venues";
    }

    @PostMapping("/{id}/delete")
    public String deleteVenue(@PathVariable Long id) {
        venueService.deleteVenue(id);
        return "redirect:/admin/venues";
    }
}
