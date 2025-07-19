package com.trackwise.ticket.controller;

import com.trackwise.ticket.dto.TicketRequestDTO;
import com.trackwise.ticket.dto.TicketResponseDTO;
import com.trackwise.ticket.model.TicketStatus;
import com.trackwise.ticket.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketResponseDTO> createTicket(@Valid @RequestBody TicketRequestDTO dto) {
        return ResponseEntity.ok(ticketService.createTicketFromDto(dto));
    }

    @GetMapping
    public ResponseEntity<List<TicketResponseDTO>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> getTicketById(@PathVariable("id") UUID id) {
        return ticketService.getTicketById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/created-by/{userId}")
    public ResponseEntity<List<TicketResponseDTO>> getTicketsByUser(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(ticketService.getTicketsByUser(userId));
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<TicketResponseDTO>> getOverdueTickets() {
        return ResponseEntity.ok(ticketService.getOverdueOpenTickets());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<TicketResponseDTO> updateStatus(
            @PathVariable("id") UUID id,
            @RequestParam TicketStatus status
    ) {
        return ResponseEntity.ok(ticketService.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable("id") UUID id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }

}
