package com.trackwise.ticket.mapper;

import com.trackwise.ticket.dto.TicketRequestDTO;
import com.trackwise.ticket.dto.TicketResponseDTO;
import com.trackwise.ticket.model.Ticket;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    // Request DTO to Entity
    Ticket toTicket(TicketRequestDTO dto);

    // Entity to Response DTO
    TicketResponseDTO toResponseDTO(Ticket ticket);
}
