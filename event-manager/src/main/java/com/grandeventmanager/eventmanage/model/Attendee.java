package com.grandeventmanager.eventmanage.model;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "attendee")
public class Attendee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attendee_name", nullable = false)
    private String attendeeName;

    @Column(name = "attendee_email", nullable = false)
    private String attendeeEmail;

    @Column(name = "attendee_password", nullable = false)
    private String attendeePassword;

    @OneToMany(mappedBy = "attendee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;

    @Column(name = "has_free_pass")
    private boolean hasFreePass = false;

    @Column(name =  "token")
    private String token;

    @Column(name = "status")
    private String status = "ACTIVE";

    public Attendee() {
    }

    public Attendee(Long id, String attendeeName, String attendeeEmail, String attendeePassword, List<Reservation> reservations, boolean hasFreePass, String token) {
        this.id = id;
        this.attendeeName = attendeeName;
        this.attendeeEmail = attendeeEmail;
        this.attendeePassword = attendeePassword;
        this.reservations = reservations;
        this.hasFreePass = hasFreePass;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttendeeName() {
        return attendeeName;
    }

    public void setAttendeeName(String attendeeName) {
        this.attendeeName = attendeeName;
    }

    public String getAttendeeEmail() {
        return attendeeEmail;
    }

    public void setAttendeeEmail(String attendeeEmail) {
        this.attendeeEmail = attendeeEmail;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public boolean isHasFreePass() {
        return hasFreePass;
    }

    public void setHasFreePass(boolean hasFreePass) {
        this.hasFreePass = hasFreePass;
    }

    public String getAttendeePassword() {
        return attendeePassword;
    }

    public void setAttendeePassword(String attendeePassword) {
        this.attendeePassword = attendeePassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
