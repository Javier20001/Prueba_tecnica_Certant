package com.grandeventmanager.eventmanage.dto;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class EventDTO {

    private String eventName;

    private LocalDateTime eventDate;

    private Integer capacity;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
