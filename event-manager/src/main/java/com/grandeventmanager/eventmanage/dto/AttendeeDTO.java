package com.grandeventmanager.eventmanage.dto;

public class AttendeeDTO {

    private String attendeeEmail;

    private String AttendeeName;

    private String attendeePassword;

    public String getAttendeeEmail() {
        return attendeeEmail;
    }

    public void setAttendeeEmail(String attendeeEmail) {
        this.attendeeEmail = attendeeEmail;
    }

    public String geAttendeeName() {
        return AttendeeName;
    }

    public void setAttendeeName(String getAttendeeName) {
        this.AttendeeName = getAttendeeName;
    }

    public String getAttendeePassword() {
        return attendeePassword;
    }

    public void setAttendeePassword(String attendeePassword) {
        this.attendeePassword = attendeePassword;
    }

    @Override
    public String toString() {
        return "AttendeeDTO{" +
                "attendeeEmail='" + attendeeEmail + '\'' +
                ", getAttendeeName='" + AttendeeName + '\'' +
                '}';
    }
}
