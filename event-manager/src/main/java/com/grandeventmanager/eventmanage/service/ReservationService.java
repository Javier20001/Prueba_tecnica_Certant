package com.grandeventmanager.eventmanage.service;


import com.grandeventmanager.eventmanage.dto.ReservationDTO;
import com.grandeventmanager.eventmanage.model.Attendee;
import com.grandeventmanager.eventmanage.model.Reservation;
import com.grandeventmanager.eventmanage.respository.AttendeeRepository;
import com.grandeventmanager.eventmanage.respository.EventRepository;
import com.grandeventmanager.eventmanage.respository.ReservationRepository;
import com.grandeventmanager.eventmanage.service.interfaces.ReservationServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class ReservationService implements ReservationServiceInterface {

    private final ReservationRepository reservationRepository;

    private final AttendeeRepository attendeeRepository;

    private final EventRepository eventRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              AttendeeRepository attendeeRepository,
                              EventRepository eventRepository) {
        this.reservationRepository = reservationRepository;
        this.attendeeRepository = attendeeRepository;
        this.eventRepository = eventRepository;
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    public Reservation save(ReservationDTO reservationDTO) throws Exception{
        Reservation reservation = mapperFromDTO(reservationDTO);
        setFreePass(reservation);
        if(reservationDTO.getReservationDate().isAfter(reservation.getEvent().getEventDate())) throw new IllegalStateException("Cannot save reservation date: this one is after than event date.");
        reservation.getEvent().reserveTicket();
        return reservationRepository.save(reservation);
    }

    public Reservation update(Long id, ReservationDTO reservationDTO) throws Exception {
        Optional<Reservation> reservation = findById(id);
        if(reservation.isEmpty()) throw new Exception("Not exist a reservation whit this id");

        if (reservationDTO.getTicketType() != null) {
            reservation.get().setTicketType(reservationDTO.getTicketType());
        }

        if (reservationDTO.getReservationDate() != null) {
            if(reservationDTO.getReservationDate().isAfter(reservation.get().getEvent().getEventDate())) throw new IllegalStateException("Cannot update reservation date: this one is after than event date.");
            reservation.get().setReservationDate(reservationDTO.getReservationDate());
        }

        return reservationRepository.save(reservation.get());
    }

    public List<ReservationDTO> findByAttendeeId(Long attendeeId) throws IllegalArgumentException {
        if (attendeeId == null) throw new IllegalArgumentException("Attendee ID cannot be null.");

        return reservationRepository.findByAttendeeIdAndStatus(attendeeId,"ACTIVE").stream()
                .map(reservation -> {
                    ReservationDTO dto = new ReservationDTO();
                    dto.setId(reservation.getId());
                    dto.setNombreEvent(reservation.getEvent().getEventName());
                    dto.setIdAttendee(reservation.getAttendee().getId());
                    dto.setReservationDate(reservation.getReservationDate());
                    dto.setTicketType(reservation.getTicketType());
                    dto.setIdEvent(reservation.getEvent().getId());
                    return dto;
                })
                .toList();
    }

    public void deleteById(Long id) throws NoSuchElementException{
        Optional<Reservation> reservation = findById(id);
        if(reservation.isEmpty()) throw new NoSuchElementException("Reservation not found");;
        reservation.get().setStatus("INACTIVE");
        reservation.get().getEvent().cancelTicket();
        reservationRepository.save(reservation.get());
    }

    private void setFreePass(Reservation reservation){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneYearAgo = now.minusYears(1);

        if (reservation.getAttendee().isHasFreePass()) {
            reservation.setFreePass(true);
            reservation.getAttendee().setHasFreePass(false);
        } else {
            int count = attendeeRepository.countReservationsLastYear(
                    reservation.getAttendee().getId(), oneYearAgo, now
            );

            if (count + 1 == 5) {
                reservation.getAttendee().setHasFreePass(true);
            }
        }
    }


    private Reservation mapperFromDTO(ReservationDTO reservationDTO){

        Optional<Attendee> attendee = attendeeRepository.findById(reservationDTO.getIdAttendee());
        if (attendee.isEmpty())  throw new IllegalArgumentException("Attendee is required and must exist.");

        Reservation reservation = new Reservation();
        reservation.setReservationDate(reservationDTO.getReservationDate());
        reservation.setAttendee(attendee.get());
        reservation.setTicketType(reservationDTO.getTicketType());
        reservation.setEvent(eventRepository.getReferenceById(reservationDTO.getIdEvent()));
        reservation.setStatus("ACTIVE");
        return reservation;
    }
}
