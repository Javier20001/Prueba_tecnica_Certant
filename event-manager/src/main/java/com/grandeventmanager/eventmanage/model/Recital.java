package com.grandeventmanager.eventmanage.model;

import com.grandeventmanager.eventmanage.enums.TicketType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@DiscriminatorValue("RECITAL")
public class Recital extends Event{

    public Recital() {
    }

    public Recital(Long id, String eventName, LocalDateTime eventDate, Integer capacity) {
        super(id, eventName, eventDate, capacity);
    }

    @Override
    public List<TicketType> getAllowedTickets() {
        return List.of(TicketType.CAMPO, TicketType.PLATEA, TicketType.PALCO);
    }
}
