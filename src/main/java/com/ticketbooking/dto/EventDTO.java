package com.ticketbooking.dto;

import com.ticketbooking.model.enums.EventStatus;
import com.ticketbooking.model.enums.EventType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDTO {
    private Long id;
    private String title;
    private String description;
    private EventType eventType;
    private EventStatus status;
    private Integer durationMins;
    private String language;
    private String genre;
    private String posterUrl;
}
