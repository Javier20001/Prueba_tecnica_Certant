package com.grandeventmanager.eventmanage.service.interfaces;

import com.grandeventmanager.eventmanage.dto.EventDTO;
import com.grandeventmanager.eventmanage.model.Conference;
import com.grandeventmanager.eventmanage.model.Recital;
import com.grandeventmanager.eventmanage.respository.RecitalRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface RecitalServiceInterface {

    public List<Recital> findAll();

    public Optional<Recital> findById(Long id);

    public Recital save(EventDTO eventDTO);

    public Recital update(Long id, EventDTO eventDTO) throws Exception;

    public void deleteById(Long id) throws NoSuchElementException;
}
