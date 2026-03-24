package com.ticketbooking.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowDTO {
    private Long id;
    private Long eventId;
    private String eventTitle;
    private Long venueId;
    private String venueName;
    private LocalDateTime showTime;
    private BigDecimal basePrice;
    private Integer availableSeats;
}
