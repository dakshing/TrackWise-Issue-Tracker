package com.trackwise.ticket.scheduler;

import com.trackwise.ticket.model.Ticket;
import com.trackwise.ticket.model.TicketStatus;
import com.trackwise.ticket.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SlaBreachScheduler {

    private final TicketRepository ticketRepository;

    @Scheduled(fixedRate = 300000) // every 5 minutes
    public void checkSlaBreaches() {
        LocalDateTime now = LocalDateTime.now();
        List<Ticket> breachedTickets = ticketRepository.findByStatusAndDueAtBeforeAndDeletedFalse(TicketStatus.OPEN, now);

        if (!breachedTickets.isEmpty()) {
            log.warn("SLA Breach Detected! {} tickets breached SLA:", breachedTickets.size());
            breachedTickets.forEach(ticket ->
                    log.warn("Ticket ID: {}, Due At: {}, Title: {}",
                            ticket.getId(), ticket.getDueAt(), ticket.getTitle())
            );

            // TODO escalate, notify, tag, etc.
        } else {
            log.info("No SLA breaches at {}", now);
        }
    }
}
