package com.grandeventmanager.eventmanage.service;

import com.grandeventmanager.eventmanage.dto.EventDTO;
import com.grandeventmanager.eventmanage.model.Recital;
import com.grandeventmanager.eventmanage.model.Reservation;
import com.grandeventmanager.eventmanage.respository.EventRepository;
import com.grandeventmanager.eventmanage.respository.RecitalRepository;
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
class RecitalServiceTest {

    private RecitalService recitalService;

    @Mock
    private RecitalRepository recitalRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ReservationServiceInterface reservationServiceInterface;

    @BeforeEach
    void setUp() {
        recitalService = new RecitalService(recitalRepository, eventRepository, reservationServiceInterface);
    }

    @Test
    void findAll() {
        List<Recital> mockRecitals = List.of(new Recital(), new Recital());
        when(recitalRepository.findByStatus("ACTIVE")).thenReturn(mockRecitals);

        List<Recital> result = recitalService.findAll();

        assertEquals(2, result.size());
        verify(recitalRepository).findByStatus("ACTIVE");
    }

    @Test
    void findById() {
        Recital recital = new Recital();
        recital.setId(1L);
        when(recitalRepository.findById(1L)).thenReturn(Optional.of(recital));

        Optional<Recital> result = recitalService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void save() {
        EventDTO dto = new EventDTO();
        dto.setEventName("Rock Night");
        dto.setEventDate(LocalDateTime.now());
        dto.setCapacity(10);

        when(recitalRepository.save(any(Recital.class))).thenAnswer(i -> i.getArguments()[0]);

        Recital saved = recitalService.save(dto);

        assertEquals("Rock Night", saved.getEventName());
        assertEquals("ACTIVE", saved.getStatus());
    }

    @Test
    void update() throws Exception {
        Recital recital = new Recital();
        recital.setId(1L);
        recital.setEventDate(LocalDateTime.now().plusDays(5));

        EventDTO dto = new EventDTO();
        dto.setEventName("Updated Name");
        dto.setEventDate(LocalDateTime.now().plusDays(6));
        dto.setCapacity(10);

        when(recitalRepository.findById(1L)).thenReturn(Optional.of(recital));
        when(eventRepository.existsReservationsAfterDate(1L, dto.getEventDate())).thenReturn(false);
        when(recitalRepository.save(any(Recital.class))).thenAnswer(i -> i.getArguments()[0]);

        Recital updated = recitalService.update(1L, dto);

        assertEquals("Updated Name", updated.getEventName());
        assertEquals(10, updated.getCapacity());
        assertEquals(dto.getEventDate(), updated.getEventDate());
    }

    @Test
    void deleteById() {
        Recital recital = new Recital();
        recital.setId(1L);
        recital.setStatus("ACTIVE");

        Reservation r1 = new Reservation(); r1.setId(10L);
        Reservation r2 = new Reservation(); r2.setId(20L);
        recital.setReservations(List.of(r1, r2));

        when(recitalRepository.findById(1L)).thenReturn(Optional.of(recital));

        recitalService.deleteById(1L);

        assertEquals("INACTIVE", recital.getStatus());
        verify(reservationServiceInterface).deleteById(10L);
        verify(reservationServiceInterface).deleteById(20L);
        verify(eventRepository).save(recital);
    }

}
