package com.ticketbooking.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalyticsDTO {
    private long totalBookings;
    private long totalVenues;
    private long totalEvents;
    private long totalShows;
    private List<Object[]> revenuePerEvent;
    private List<Object[]> bookingCountPerEvent;
}
