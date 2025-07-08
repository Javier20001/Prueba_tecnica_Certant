package com.grandeventmanager.eventmanage.service;

import com.grandeventmanager.eventmanage.dto.EventDTO;
import com.grandeventmanager.eventmanage.model.Conference;
import com.grandeventmanager.eventmanage.model.Play;
import com.grandeventmanager.eventmanage.respository.EventRepository;
import com.grandeventmanager.eventmanage.respository.PlayRepository;
import com.grandeventmanager.eventmanage.respository.ReservationRepository;
import com.grandeventmanager.eventmanage.service.interfaces.PlayServiceInterface;
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
public class PlayService implements PlayServiceInterface{

    private final PlayRepository playRepository;

    private final ReservationServiceInterface reservationServiceInterface;

    private final EventRepository eventRepository;

    public PlayService(PlayRepository playRepository, EventRepository eventRepository , ReservationServiceInterface reservationServiceInterface) {
        this.playRepository = playRepository;
        this.eventRepository = eventRepository;
        this.reservationServiceInterface = reservationServiceInterface;
    }

    public List<Play> findAll() {
        return playRepository.findByStatus("ACTIVE");
    }

    public Optional<Play> findById(Long id) {
        return playRepository.findById(id);
    }

    public Play save(EventDTO eventDTO) {
        return playRepository.save(mapperFromDTo(eventDTO));
    }

    public Play update(Long id, EventDTO eventDTO) throws Exception {
        Optional<Play> play = findById(id);
        if(play.isEmpty()) throw new Exception("Not exist a play whit this id");

        if (eventDTO.getEventName() != null) {
            play.get().setEventName(eventDTO.getEventName());
        }
        if (eventDTO.getEventDate() != null) {
            if(eventRepository.existsReservationsAfterDate(play.get().getId(),eventDTO.getEventDate())) throw new IllegalStateException("Cannot update event date: there are reservations after the new date.");
            play.get().setEventDate(eventDTO.getEventDate());
        }
        if (eventDTO.getCapacity() != null) {
            play.get().setCapacity(eventDTO.getCapacity());
        }

        return playRepository.save(play.get());
    }

    public void deleteById(Long id) throws NoSuchElementException {
        Optional<Play> play = findById(id);
        if(play.isEmpty()) throw new NoSuchElementException("Not exist a play whit this id");
        play.get().setStatus("INACTIVE");
        play.get().getReservations().forEach(reservation -> {
            reservationServiceInterface.deleteById(reservation.getId());
        });
        eventRepository.save(play.get());
    }

    private Play mapperFromDTo(EventDTO eventDTO){
        Play play =  new Play();
        play.setEventName(eventDTO.getEventName());
        play.setCapacity(eventDTO.getCapacity());
        play.setEventDate(eventDTO.getEventDate());
        play.setStatus("ACTIVE");

        return play;
    }
}
