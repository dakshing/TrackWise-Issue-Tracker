package com.trackwise.ticket.repository;

import com.trackwise.ticket.model.Ticket;
import com.trackwise.ticket.model.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    List<Ticket> findByDeletedFalse();

    Optional<Ticket> findByIdAndDeletedFalse(UUID id);

    List<Ticket> findByCreatedByAndDeletedFalse(UUID userId);

    List<Ticket> findByStatusAndDueAtBeforeAndDeletedFalse(TicketStatus status, LocalDateTime time);
}
