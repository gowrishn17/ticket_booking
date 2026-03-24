package com.ticketbooking.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "venues")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private String city;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(nullable = false)
    private Integer totalRows;

    @Column(nullable = false)
    private Integer seatsPerRow;

    @Column(nullable = false)
    private Integer totalCapacity;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void calcCapacity() {
        this.totalCapacity = this.totalRows * this.seatsPerRow;
        if (this.createdAt == null) this.createdAt = LocalDateTime.now();
    }
}
