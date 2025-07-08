package com.grandeventmanager.eventmanage.service;

import com.grandeventmanager.eventmanage.dto.ReservationDTO;
import com.grandeventmanager.eventmanage.enums.TicketType;
import com.grandeventmanager.eventmanage.model.Attendee;
import com.grandeventmanager.eventmanage.model.Event;
import com.grandeventmanager.eventmanage.model.Play;
import com.grandeventmanager.eventmanage.model.Reservation;
import com.grandeventmanager.eventmanage.respository.AttendeeRepository;
import com.grandeventmanager.eventmanage.respository.EventRepository;
import com.grandeventmanager.eventmanage.respository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    ReservationService reservationService;
    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private AttendeeRepository attendeeRepository;

    @Mock
    private EventRepository eventRepository;

    @BeforeEach
    void init() {
        reservationService = new ReservationService(reservationRepository , attendeeRepository,eventRepository );
    }

    @Test
    void findAll() {
       when(reservationRepository.findAll()).thenReturn(List.of(
               new Reservation(1l,new Attendee(), new Play(), LocalDateTime.now(), null),
                new Reservation(2l,new Attendee(), new Play(), LocalDateTime.now(), null)
       ));

     List<Reservation> reservations = reservationService.findAll();

     assertNotNull(reservations);

     assertEquals(2, reservations.size());
    }

    @Test
    void findById() {
        when(reservationRepository.findById(1L)).thenReturn(java.util.Optional.of(new Reservation(1l,new Attendee(), new Play(), LocalDateTime.now(), null)));

        var reservation = reservationService.findById(1L);

        assertTrue(reservation.isPresent());
        assertEquals(1L, reservation.get().getId());
    }

    @Test
    void save() {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setIdAttendee(1L);
        reservationDTO.setIdEvent(1L);
        reservationDTO.setReservationDate(LocalDateTime.now());

        Attendee attendee = new Attendee();
        attendee.setId(1L);

        Play play = new Play();
        play.setId(1L);
        play.setCapacity(100);
        play.setEventDate(LocalDateTime.now().plusDays(5));

        when(attendeeRepository.findById(1L)).thenReturn(Optional.of(attendee));
        when(eventRepository.getReferenceById(1L)).thenReturn(play);
        when(reservationRepository.save(any(Reservation.class)))
                .thenReturn(new Reservation(1L, attendee, play, reservationDTO.getReservationDate(), reservationDTO.getTicketType()));

        Reservation savedReservation = null;
        try {
            savedReservation = reservationService.save(reservationDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertNotNull(savedReservation);
        assertEquals(1L, savedReservation.getId());
    }

    @Test
    void update() {
        Long reservationId = 1L;
        LocalDateTime nuevaFecha = LocalDateTime.now().plusDays(1);

        ReservationDTO dto = new ReservationDTO();
        dto.setTicketType(TicketType.VIP);
        dto.setReservationDate(nuevaFecha);

        Event event = new Play();
        event.setEventDate(LocalDateTime.now().plusDays(5));

        Reservation existingReservation = new Reservation();
        existingReservation.setId(reservationId);
        existingReservation.setEvent(event);
        existingReservation.setTicketType(TicketType.GENERAL);
        existingReservation.setReservationDate(LocalDateTime.now());

        // Mocks
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(existingReservation));
        when(reservationRepository.save(any(Reservation.class))).thenReturn(
                new Reservation(reservationId, existingReservation.getAttendee(), event, nuevaFecha, TicketType.VIP)
        );

        // Act
        Reservation updated = null;
        try {
            updated = reservationService.update(reservationId, dto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertEquals(TicketType.VIP, updated.getTicketType());
        assertEquals(nuevaFecha, updated.getReservationDate());
    }

    @Test
    void deleteById() {
        Long reservationId = 1L;

        Event mockEvent = mock(Play.class);

        Reservation existingReservation = new Reservation();
        existingReservation.setId(reservationId);
        existingReservation.setEvent(mockEvent);
        existingReservation.setTicketType(TicketType.GENERAL);
        existingReservation.setStatus("ACTIVE");
        existingReservation.setReservationDate(LocalDateTime.now());

        when(reservationRepository.findById(reservationId))
                .thenReturn(Optional.of(existingReservation));

        reservationService.deleteById(reservationId);

        assertEquals("INACTIVE", existingReservation.getStatus());
        verify(mockEvent, times(1)).cancelTicket();
        verify(reservationRepository, times(1)).save(existingReservation);
    }
}