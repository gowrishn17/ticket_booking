package com.ticketbooking.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    private String customerName;
    private String customerEmail;
    private BigDecimal totalAmount;
    private LocalDateTime bookedAt;

    @PrePersist
    public void prePersist() {
        if (this.bookedAt == null) this.bookedAt = LocalDateTime.now();
    }
}
