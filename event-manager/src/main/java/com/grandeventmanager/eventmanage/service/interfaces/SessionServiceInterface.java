package com.grandeventmanager.eventmanage.service.interfaces;


import com.grandeventmanager.eventmanage.dto.AttendeeDTO;
import com.grandeventmanager.eventmanage.dto.SessionDTO;

public interface SessionServiceInterface {
    public SessionDTO login(AttendeeDTO attendeeDTO) throws Exception;

    public SessionDTO register(AttendeeDTO attendeeDTO) throws Exception;
}
