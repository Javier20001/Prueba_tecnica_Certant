package com.grandeventmanager.eventmanage.service.interfaces;

import com.grandeventmanager.eventmanage.dto.EventDTO;
import com.grandeventmanager.eventmanage.model.Conference;
import com.grandeventmanager.eventmanage.model.Play;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface PlayServiceInterface {

    public List<Play> findAll();

    public Optional<Play> findById(Long id);

    public Play save(EventDTO eventDTO);

    public Play update(Long id, EventDTO eventDTO) throws Exception;

    public void deleteById(Long id) throws NoSuchElementException;
}
