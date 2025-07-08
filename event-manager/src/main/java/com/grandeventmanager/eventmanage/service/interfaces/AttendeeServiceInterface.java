package com.grandeventmanager.eventmanage.service.interfaces;

import com.grandeventmanager.eventmanage.dto.AttendeeDTO;
import com.grandeventmanager.eventmanage.model.Attendee;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface AttendeeServiceInterface {
    public List<Attendee> findAll();

    public Optional<Attendee> findById(Long id);

    public Attendee save(AttendeeDTO attendeeDto);

    public Attendee update(Long id, AttendeeDTO attendeeDto) throws Exception;

    public void deleteById(Long id) throws NoSuchElementException;
}
