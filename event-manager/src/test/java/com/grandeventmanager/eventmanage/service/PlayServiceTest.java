package com.grandeventmanager.eventmanage.service;

import com.grandeventmanager.eventmanage.dto.EventDTO;
import com.grandeventmanager.eventmanage.model.Play;
import com.grandeventmanager.eventmanage.model.Reservation;
import com.grandeventmanager.eventmanage.respository.EventRepository;
import com.grandeventmanager.eventmanage.respository.PlayRepository;
import com.grandeventmanager.eventmanage.service.interfaces.ReservationServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayServiceTest {

    private PlayService playService;

    @Mock
    private PlayRepository playRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ReservationServiceInterface reservationServiceInterface;

    @BeforeEach
    void setUp() {
        playService = new PlayService(playRepository, eventRepository, reservationServiceInterface);
    }

    @Test
    void findAll() {
        List<Play> mockPlays = List.of(new Play(), new Play());
        when(playRepository.findByStatus("ACTIVE")).thenReturn(mockPlays);

        List<Play> result = playService.findAll();

        assertEquals(2, result.size());
        verify(playRepository).findByStatus("ACTIVE");
    }

    @Test
    void findById() {
        Play play = new Play();
        play.setId(1L);
        when(playRepository.findById(1L)).thenReturn(Optional.of(play));

        Optional<Play> result = playService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void save() {
        EventDTO dto = new EventDTO();
        dto.setEventName("Hamlet");
        dto.setEventDate(LocalDateTime.now());
        dto.setCapacity(10);

        when(playRepository.save(any(Play.class))).thenAnswer(i -> i.getArguments()[0]);

        Play saved = playService.save(dto);

        assertEquals("Hamlet", saved.getEventName());
        assertEquals("ACTIVE", saved.getStatus());
    }

    @Test
    void update() throws Exception {
        Play play = new Play();
        play.setId(1L);
        play.setEventDate(LocalDateTime.now().plusDays(5));

        EventDTO dto = new EventDTO();
        dto.setEventName("Nueva Obra");
        dto.setEventDate(LocalDateTime.now().plusDays(10));
        dto.setCapacity(10);

        when(playRepository.findById(1L)).thenReturn(Optional.of(play));
        when(eventRepository.existsReservationsAfterDate(1L, dto.getEventDate())).thenReturn(false);
        when(playRepository.save(any(Play.class))).thenAnswer(i -> i.getArguments()[0]);

        Play updated = playService.update(1L, dto);

        assertEquals("Nueva Obra", updated.getEventName());
        assertEquals(10, updated.getCapacity());
        assertEquals(dto.getEventDate(), updated.getEventDate());
    }

    @Test
    void deleteById() {
        Play play = new Play();
        play.setId(1L);
        play.setStatus("ACTIVE");

        Reservation r1 = new Reservation(); r1.setId(10L);
        Reservation r2 = new Reservation(); r2.setId(20L);
        play.setReservations(List.of(r1, r2));

        when(playRepository.findById(1L)).thenReturn(Optional.of(play));

        playService.deleteById(1L);

        assertEquals("INACTIVE", play.getStatus());
        verify(reservationServiceInterface).deleteById(10L);
        verify(reservationServiceInterface).deleteById(20L);
        verify(eventRepository).save(play);
    }

}
