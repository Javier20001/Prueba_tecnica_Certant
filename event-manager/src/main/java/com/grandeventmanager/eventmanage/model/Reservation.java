package com.grandeventmanager.eventmanage.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grandeventmanager.eventmanage.enums.TicketType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reservation_date", nullable = false)
    private LocalDateTime reservationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_type", nullable = false)
    private TicketType ticketType;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = true)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "attendee_id", nullable = false)
    @JsonIgnore
    private Attendee attendee;

    @Column(name = "free_pass", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean freePass = false;

    @Column(name = "status")
    private String status = "ACTIVE";

    public Reservation() {
    }

    public Reservation(Long id, Attendee attendee, Event event, LocalDateTime reservationDate, TicketType ticketType) {
        this.id = id;
        this.attendee = attendee;
        this.reservationDate = reservationDate;
        this.ticketType = ticketType;
        this.event= event;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public Event getEvent() {return event;}

    public void setEvent(Event event) {
        this.event = event;
    }

    public boolean isTicketTypeValid() {
        return event.getAllowedTickets().contains(this.ticketType);
    }

    public Attendee getAttendee() {
        return attendee;
    }
    public void setAttendee(Attendee attendee) {
        this.attendee = attendee;
    }

    public boolean isFreePass() {
        return freePass;
    }

    public void setFreePass(boolean freePass) {
        this.freePass = freePass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}