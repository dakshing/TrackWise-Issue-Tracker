package com.trackwise.ticket.service;

import com.trackwise.ticket.dto.TicketRequestDTO;
import com.trackwise.ticket.dto.TicketResponseDTO;
import com.trackwise.ticket.mapper.TicketMapper;
import com.trackwise.ticket.model.Ticket;
import com.trackwise.ticket.model.TicketStatus;
import com.trackwise.ticket.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    public TicketService(TicketRepository ticketRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
    }

    public TicketResponseDTO createTicketFromDto(TicketRequestDTO dto) {
        Ticket ticket = ticketMapper.toTicket(dto);
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setUpdatedAt(LocalDateTime.now());
        ticket.setDueAt(ticket.getCreatedAt().plusHours(dto.getSlaHours()));
        return ticketMapper.toResponseDTO(ticketRepository.save(ticket));
    }

    public List<TicketResponseDTO> getAllTickets() {
        return ticketRepository.findByDeletedFalse().stream()
                .map(ticketMapper::toResponseDTO)
                .toList();
    }

    public Optional<TicketResponseDTO> getTicketById(UUID id) {
        return ticketRepository.findByIdAndDeletedFalse(id)
                .map(ticketMapper::toResponseDTO);
    }

    public List<TicketResponseDTO> getTicketsByUser(UUID userId) {
        return ticketRepository.findByCreatedByAndDeletedFalse(userId).stream()
                .map(ticketMapper::toResponseDTO)
                .toList();
    }

    public List<TicketResponseDTO> getOverdueOpenTickets() {
        return ticketRepository.findByStatusAndDueAtBeforeAndDeletedFalse(TicketStatus.OPEN, LocalDateTime.now()).stream()
                .map(ticketMapper::toResponseDTO)
                .toList();
    }

    public TicketResponseDTO updateStatus(UUID ticketId, TicketStatus newStatus) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        ticket.setStatus(newStatus);
        ticket.setUpdatedAt(LocalDateTime.now());
        return ticketMapper.toResponseDTO(ticketRepository.save(ticket));
    }

    public void deleteTicket(UUID id) {
        Ticket ticket = ticketRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        ticket.setDeleted(true);
        ticket.setUpdatedAt(LocalDateTime.now());
        ticketRepository.save(ticket);
    }

}
