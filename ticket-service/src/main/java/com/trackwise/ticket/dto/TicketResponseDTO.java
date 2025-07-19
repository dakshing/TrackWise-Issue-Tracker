package com.trackwise.ticket.dto;

import com.trackwise.ticket.model.TicketPriority;
import com.trackwise.ticket.model.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseDTO {
    private UUID id;
    private String title;
    private String description;
    private TicketPriority priority;
    private TicketStatus status;
    private UUID createdBy;
    private UUID assignedTo;
    private LocalDateTime createdAt;
    private LocalDateTime dueAt;
    private LocalDateTime updatedAt;
}
