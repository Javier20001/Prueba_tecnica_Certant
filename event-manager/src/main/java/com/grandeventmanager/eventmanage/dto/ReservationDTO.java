package com.grandeventmanager.eventmanage.dto;

import com.grandeventmanager.eventmanage.enums.TicketType;

import java.time.LocalDateTime;


public class ReservationDTO {

    private Long id;
    private TicketType ticketType;
    private Long idEvent;
    private Long idAttendee;
    private LocalDateTime reservationDate;
    private String nameEvent;

    public ReservationDTO() {
    }

    public ReservationDTO(TicketType ticketType, Long idEvent, Long idAttendee, LocalDateTime reservatioDate) {
        this.ticketType = ticketType;
        this.idEvent = idEvent;
        this.idAttendee = idAttendee;
        this.reservationDate = reservatioDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public Long getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Long idEvent) {
        this.idEvent = idEvent;
    }

    public Long getIdAttendee() {
        return idAttendee;
    }

    public void setIdAttendee(Long idAttendee) {
        this.idAttendee = idAttendee;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservatioDate) {
        this.reservationDate = reservatioDate;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNombreEvent(String nombreEvent) {
        this.nameEvent = nombreEvent;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "ticketType=" + ticketType +
                ", idEvent=" + idEvent +
                ", idAttendee=" + idAttendee +
                ", reservatioDate=" + reservationDate +
                '}';
    }
}
