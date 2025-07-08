package com.grandeventmanager.eventmanage.dto;

public class SessionDTO {
    private String token;
    private Long idAttendee;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getIdAttendee() {
        return idAttendee;
    }

    public void setIdAttendee(Long idAttendee) {
        this.idAttendee = idAttendee;
    }
}
