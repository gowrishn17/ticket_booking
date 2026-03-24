package com.ticketbooking.model;

import com.ticketbooking.model.enums.SeatStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "seats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;

    private Integer rowNumber;
    private Integer seatNumber;
    private String seatLabel;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    private BigDecimal price;
}
