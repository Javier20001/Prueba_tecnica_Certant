package com.grandeventmanager.eventmanage.model;

import com.grandeventmanager.eventmanage.enums.TicketType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@DiscriminatorValue("PLAY")
public class Play extends Event{
    public Play() {
    }

    public Play(Long id, String eventName, LocalDateTime eventDate, Integer capacity) {
        super(id, eventName, eventDate, capacity);
    }
    @Override
    public List<TicketType> getAllowedTickets() {
        return List.of(TicketType.GENERAL, TicketType.VIP);
    }
}
