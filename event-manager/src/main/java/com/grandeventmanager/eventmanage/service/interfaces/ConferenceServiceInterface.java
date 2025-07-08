package com.grandeventmanager.eventmanage.service.interfaces;

import com.grandeventmanager.eventmanage.dto.EventDTO;
import com.grandeventmanager.eventmanage.model.Conference;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface ConferenceServiceInterface {

    public List<Conference> findAll();

    public Optional<Conference> findById(Long id);

    public Conference save(EventDTO eventDTO);

    public Conference update(Long id, EventDTO eventDTO) throws Exception;

    public void deleteById(Long id) throws NoSuchElementException;
}
