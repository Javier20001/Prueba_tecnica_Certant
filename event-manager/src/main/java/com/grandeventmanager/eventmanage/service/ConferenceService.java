package com.grandeventmanager.eventmanage.service;

import com.grandeventmanager.eventmanage.dto.EventDTO;
import com.grandeventmanager.eventmanage.model.Attendee;
import com.grandeventmanager.eventmanage.model.Conference;
import com.grandeventmanager.eventmanage.model.Event;
import com.grandeventmanager.eventmanage.respository.ConferenceRepository;
import com.grandeventmanager.eventmanage.respository.EventRepository;
import com.grandeventmanager.eventmanage.respository.ReservationRepository;
import com.grandeventmanager.eventmanage.service.interfaces.ConferenceServiceInterface;
import com.grandeventmanager.eventmanage.service.interfaces.RecitalServiceInterface;
import com.grandeventmanager.eventmanage.service.interfaces.ReservationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class ConferenceService implements ConferenceServiceInterface {


    private final ConferenceRepository conferenceRepository;

    private final ReservationServiceInterface reservationServiceInterface;

    private final EventRepository eventRepository;

    public ConferenceService(ConferenceRepository conferenceRepository, EventRepository eventRepository, ReservationServiceInterface reservationServiceInterface) {
        this.conferenceRepository = conferenceRepository;
        this.eventRepository = eventRepository;
        this.reservationServiceInterface = reservationServiceInterface;
    }

    public List<Conference> findAll() {
        return conferenceRepository.findByStatus("ACTIVE");
    }

    public Optional<Conference> findById(Long id) {
        return conferenceRepository.findById(id);
    }

    public Conference save(EventDTO eventDTO) {
        return conferenceRepository.save(mapperFromDTo(eventDTO));
    }

    public Conference update(Long id, EventDTO eventDTO) throws Exception {
        Optional<Conference> conference = findById(id);
        if(conference.isEmpty()) throw new Exception("Not exist a conference whit this id");

        if (eventDTO.getEventName() != null) {
            conference.get().setEventName(eventDTO.getEventName());
        }
        if (eventDTO.getEventDate() != null ) {
            if(eventRepository.existsReservationsAfterDate(conference.get().getId(),eventDTO.getEventDate())) throw new IllegalStateException("Cannot update event date: there are reservations after the new date.");
            conference.get().setEventDate(eventDTO.getEventDate());
        }
        if (eventDTO.getCapacity() != null) {
            conference.get().setCapacity(eventDTO.getCapacity());
        }

        return conferenceRepository.save(conference.get());
    }

    public void deleteById(Long id) throws NoSuchElementException {
        Optional<Conference> conference = findById(id);
        if(conference.isEmpty()) throw new NoSuchElementException("Not conference a attendee whit this id");
        conference.get().setStatus("INACTIVE");
        conference.get().getReservations().forEach(reservation -> {
            reservationServiceInterface.deleteById(reservation.getId());
        });
        eventRepository.save(conference.get());
    }

    private Conference mapperFromDTo(EventDTO eventDTO){
        Conference conference =  new Conference();
        conference.setEventName(eventDTO.getEventName());
        conference.setCapacity(eventDTO.getCapacity());
        conference.setEventDate(eventDTO.getEventDate());
        conference.setStatus("ACTIVE");
        return conference;
    }
}
