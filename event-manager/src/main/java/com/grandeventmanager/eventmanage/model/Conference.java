package com.grandeventmanager.eventmanage.model;

import com.grandeventmanager.eventmanage.enums.TicketType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@DiscriminatorValue("CONFERENCE")
public class Conference extends Event{

    public Conference() {
    }

    public Conference(Long id, String eventName, LocalDateTime eventDate, Integer capacity) {
        super(id, eventName, eventDate, capacity);
    }


    @Override
    public List<TicketType> getAllowedTickets() {
        return List.of(TicketType.STANDARD, TicketType.MEET_AND_GREET);
    }
}
