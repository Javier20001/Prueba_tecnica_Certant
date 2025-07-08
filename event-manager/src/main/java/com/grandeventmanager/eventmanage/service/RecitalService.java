package com.grandeventmanager.eventmanage.service;

import com.grandeventmanager.eventmanage.dto.EventDTO;
import com.grandeventmanager.eventmanage.model.Play;
import com.grandeventmanager.eventmanage.model.Recital;
import com.grandeventmanager.eventmanage.respository.EventRepository;
import com.grandeventmanager.eventmanage.respository.RecitalRepository;
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
public class RecitalService implements RecitalServiceInterface {

    private RecitalRepository recitalRepository;

    private final ReservationServiceInterface reservationServiceInterface;
    private final EventRepository eventRepository;

    public RecitalService(RecitalRepository recitalRepository, EventRepository eventRepository, ReservationServiceInterface reservationServiceInterface) {
        this.recitalRepository = recitalRepository;
        this.eventRepository = eventRepository;
        this.reservationServiceInterface = reservationServiceInterface;
    }

    public List<Recital> findAll() {
        return recitalRepository.findByStatus("ACTIVE");
    }

    public Optional<Recital> findById(Long id) {
        return recitalRepository.findById(id);
    }


    public Recital save(EventDTO eventDTO) {
        return recitalRepository.save(mapperFromDTo(eventDTO));
    }

    public Recital update(Long id, EventDTO eventDTO) throws Exception {
        Optional<Recital> recital = findById(id);
        if(recital.isEmpty()) throw new Exception("Not exist a recital whit this id");

        if (eventDTO.getEventName() != null) {
            recital.get().setEventName(eventDTO.getEventName());
        }
        if (eventDTO.getEventDate() != null) {
            if(eventRepository.existsReservationsAfterDate(recital.get().getId(),eventDTO.getEventDate())) throw new IllegalStateException("Cannot update event date: there are reservations after the new date.");
            recital.get().setEventDate(eventDTO.getEventDate());
        }
        if (eventDTO.getCapacity() != null) {
            recital.get().setCapacity(eventDTO.getCapacity());
        }

        return recitalRepository.save(recital.get());
    }

    public void deleteById(Long id) throws NoSuchElementException {
        Optional<Recital> recital = findById(id);
        if(recital.isEmpty()) throw new NoSuchElementException("Not exist a recital whit this id");
        recital.get().setStatus("INACTIVE");
        recital.get().getReservations().forEach(reservation -> {
            reservationServiceInterface.deleteById(reservation.getId());
        });
        eventRepository.save(recital.get());
    }

    private Recital mapperFromDTo(EventDTO eventDTO){
        Recital recital =  new Recital();
        recital.setEventName(eventDTO.getEventName());
        recital.setCapacity(eventDTO.getCapacity());
        recital.setEventDate(eventDTO.getEventDate());
        recital.setStatus("ACTIVE");
        return recital;
    }
}
