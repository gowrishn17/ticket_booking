package com.ticketbooking.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VenueDTO {
    private Long id;
    private String name;
    private String city;
    private String address;
    private Integer totalRows;
    private Integer seatsPerRow;
    private Integer totalCapacity;
}
