package com.trackwise.ticket.dto;

import com.trackwise.ticket.model.TicketPriority;
import jakarta.validation.constraints.*;

import lombok.Data;

import java.util.UUID;

@Data
public class TicketRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Priority is required")
    @Pattern(regexp = "LOW|MEDIUM|HIGH", message = "Priority must be LOW, MEDIUM, or HIGH")
    private TicketPriority priority;

    @NotNull(message = "CreatedBy (user ID) is required")
    private UUID createdBy;

    @NotNull(message = "AssignedTo (user ID) is required")
    private UUID assignedTo;

    @NotNull(message = "SLA Hours must be provided")
    @Min(value = 1, message = "SLA must be at least 1 hour")
    @Max(value = 168, message = "SLA cannot exceed 168 hours (1 week)")
    private Integer slaHours;
}
