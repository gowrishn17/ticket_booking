package com.ticketbooking.model;

import com.ticketbooking.model.enums.EventStatus;
import com.ticketbooking.model.enums.EventType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    private Integer durationMins;
    private String language;
    private String genre;
    private String posterUrl;
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (this.status == null) this.status = EventStatus.DRAFT;
        if (this.createdAt == null) this.createdAt = LocalDateTime.now();
    }
}
