package com.grandeventmanager.eventmanage.service;


import com.grandeventmanager.eventmanage.dto.AttendeeDTO;
import com.grandeventmanager.eventmanage.dto.SessionDTO;
import com.grandeventmanager.eventmanage.model.Attendee;
import com.grandeventmanager.eventmanage.respository.AttendeeRepository;
import com.grandeventmanager.eventmanage.service.interfaces.AttendeeServiceInterface;
import com.grandeventmanager.eventmanage.service.interfaces.SessionServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class SessionService implements SessionServiceInterface {

    private AttendeeRepository attendeeRepository;

    private final AttendeeServiceInterface attendeeServiceInterface;

    public SessionService(AttendeeRepository attendeeRepository,
                          AttendeeServiceInterface attendeeServiceInterface) {
        this.attendeeRepository = attendeeRepository;
        this.attendeeServiceInterface = attendeeServiceInterface;
    }

    public SessionDTO login(AttendeeDTO attendeeDTO) throws Exception{
        Optional<Attendee> attendee = attendeeRepository.findByAttendeeEmailAndAttendeePassword(attendeeDTO.getAttendeeEmail(),attendeeDTO.getAttendeePassword());
        if(attendee.isEmpty()) throw new Exception("not exist a user with this credentials");
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setIdAttendee(attendee.get().getId());
        sessionDTO.setToken(attendee.get().getToken());
        return sessionDTO;
    }

    public SessionDTO register(AttendeeDTO attendeeDTO) throws Exception{
        Optional<Attendee> attendee = attendeeRepository.findByAttendeeEmailAndAttendeePassword(attendeeDTO.getAttendeeEmail(),attendeeDTO.getAttendeePassword());
        if(attendee.isPresent()) throw new Exception("Already exist a user with this credentials");
        Attendee newAttendee = attendeeServiceInterface.save(attendeeDTO);
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setIdAttendee(newAttendee.getId());
        sessionDTO.setToken(newAttendee.getToken());
        return sessionDTO;
    }

}
