package com.grandeventmanager.eventmanage.service;

import com.grandeventmanager.eventmanage.dto.AttendeeDTO;
import com.grandeventmanager.eventmanage.model.Attendee;
import com.grandeventmanager.eventmanage.respository.AttendeeRepository;
import com.grandeventmanager.eventmanage.service.interfaces.AttendeeServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class AttendeeService implements AttendeeServiceInterface {

    private final AttendeeRepository attendeeRepository;

    public AttendeeService(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    public List<Attendee> findAll() {
        return attendeeRepository.findAll();
    }

    public Optional<Attendee> findById(Long id) {
        return attendeeRepository.findById(id);
    }

    public Attendee save(AttendeeDTO attendeeDto) {
        return attendeeRepository.save(mapperFromDTO(attendeeDto));
    }

    public Attendee update(Long id, AttendeeDTO attendeeDto) throws Exception {
        Optional<Attendee> attendee = findById(id);
        if(attendee.isEmpty()) throw new Exception("Not exist a attendee whit this id");

        if (attendeeDto.getAttendeeEmail() != null) {
            attendee.get().setAttendeeEmail(attendeeDto.getAttendeeEmail());
        }

        if (attendeeDto.geAttendeeName() != null) {
            attendee.get().setAttendeeName(attendeeDto.geAttendeeName());
        }
        return attendeeRepository.save(attendee.get());
    }

    public void deleteById(Long id) throws NoSuchElementException {
        Optional<Attendee> attendee = findById(id);
        if(attendee.isEmpty()) throw new NoSuchElementException("Not exist a attendee whit this id");
        attendee.get().setStatus("INACTIVE");
        attendeeRepository.save(attendee.get());
    }

    private Attendee mapperFromDTO(AttendeeDTO attendeeDTO){

        Attendee attendee = new Attendee();
        attendee.setAttendeeEmail(attendeeDTO.getAttendeeEmail());
        attendee.setAttendeeName(attendeeDTO.geAttendeeName());
        attendee.setAttendeePassword(attendeeDTO.getAttendeePassword());
        attendee.setToken("USER");
        attendee.setStatus("ACTIVE");
        return attendee;
    }

}

