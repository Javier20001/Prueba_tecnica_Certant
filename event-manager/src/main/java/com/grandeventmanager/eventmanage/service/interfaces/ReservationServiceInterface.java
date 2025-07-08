package com.grandeventmanager.eventmanage.service.interfaces;

import com.grandeventmanager.eventmanage.dto.ReservationDTO;
import com.grandeventmanager.eventmanage.model.Reservation;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface ReservationServiceInterface {
    public List<Reservation> findAll();

    public Optional<Reservation> findById(Long id);

    public Reservation save(ReservationDTO reservationDTO) throws Exception;

    public Reservation update(Long id, ReservationDTO reservationDTO) throws Exception;

    public List<ReservationDTO> findByAttendeeId(Long attendeeId);

    public void deleteById(Long id) throws NoSuchElementException;
}
