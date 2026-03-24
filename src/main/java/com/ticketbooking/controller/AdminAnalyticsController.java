package com.ticketbooking.controller;

import com.ticketbooking.service.AnalyticsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/analytics")
public class AdminAnalyticsController {

    private final AnalyticsService analyticsService;

    public AdminAnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("analytics", analyticsService.getAnalytics());
        return "admin/analytics/dashboard";
    }
}
