package com.grandeventmanager.eventmanage.service;

import com.grandeventmanager.eventmanage.dto.EventDTO;
import com.grandeventmanager.eventmanage.model.Conference;
import com.grandeventmanager.eventmanage.model.Reservation;
import com.grandeventmanager.eventmanage.respository.ConferenceRepository;
import com.grandeventmanager.eventmanage.respository.EventRepository;
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
class ConferenceServiceTest {

    private ConferenceService conferenceService;

    @Mock
    private ConferenceRepository conferenceRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ReservationServiceInterface reservationServiceInterface;

    @BeforeEach
    void setUp() {
        conferenceService = new ConferenceService(conferenceRepository, eventRepository, reservationServiceInterface);
    }

    @Test
    void findAll() {
        List<Conference> conferences = List.of(new Conference(), new Conference());
        when(conferenceRepository.findByStatus("ACTIVE")).thenReturn(conferences);

        List<Conference> result = conferenceService.findAll();

        assertEquals(2, result.size());
        verify(conferenceRepository).findByStatus("ACTIVE");
    }

    @Test
    void findById() {
        Conference conference = new Conference();
        conference.setId(1L);
        when(conferenceRepository.findById(1L)).thenReturn(Optional.of(conference));

        Optional<Conference> result = conferenceService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void save() {
        EventDTO dto = new EventDTO();
        dto.setEventName("Tech Summit");
        dto.setEventDate(LocalDateTime.now());
        dto.setCapacity(10);

        when(conferenceRepository.save(any(Conference.class))).thenAnswer(i -> i.getArguments()[0]);

        Conference saved = conferenceService.save(dto);

        assertEquals("Tech Summit", saved.getEventName());
        assertEquals("ACTIVE", saved.getStatus());
    }

    @Test
    void update() throws Exception {
        Conference conference = new Conference();
        conference.setId(1L);
        conference.setEventDate(LocalDateTime.now().plusDays(5));

        EventDTO dto = new EventDTO();
        dto.setEventName("New Conference Name");
        dto.setEventDate(LocalDateTime.now().plusDays(10));
        dto.setCapacity(10);

        when(conferenceRepository.findById(1L)).thenReturn(Optional.of(conference));
        when(eventRepository.existsReservationsAfterDate(1L, dto.getEventDate())).thenReturn(false);
        when(conferenceRepository.save(any(Conference.class))).thenAnswer(i -> i.getArguments()[0]);

        Conference updated = conferenceService.update(1L, dto);

        assertEquals("New Conference Name", updated.getEventName());
        assertEquals(10, updated.getCapacity());
        assertEquals(dto.getEventDate(), updated.getEventDate());
    }


    @Test
    void deleteById() {
        Conference conference = new Conference();
        conference.setId(1L);
        conference.setStatus("ACTIVE");

        Reservation r1 = new Reservation(); r1.setId(10L);
        Reservation r2 = new Reservation(); r2.setId(20L);
        conference.setReservations(List.of(r1, r2));

        when(conferenceRepository.findById(1L)).thenReturn(Optional.of(conference));

        conferenceService.deleteById(1L);

        assertEquals("INACTIVE", conference.getStatus());
        verify(reservationServiceInterface).deleteById(10L);
        verify(reservationServiceInterface).deleteById(20L);
        verify(eventRepository).save(conference);
    }

}
