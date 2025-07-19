package com.trackwise.ticket.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @Enumerated(EnumType.STRING)
    private TicketPriority priority; // e.g., LOW, MEDIUM, HIGH

    private UUID assignedTo;
    private UUID createdBy;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Integer slaHours;
    private LocalDateTime dueAt;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean deleted = false;
}

