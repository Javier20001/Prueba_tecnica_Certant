package com.grandeventmanager.eventmanage.service;

import com.grandeventmanager.eventmanage.dto.AttendeeDTO;
import com.grandeventmanager.eventmanage.model.Attendee;
import com.grandeventmanager.eventmanage.respository.AttendeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AttendeeServiceTest {

    private AttendeeService attendeeService;

    @Mock
    private AttendeeRepository attendeeRepository;

    @BeforeEach
    void setUp() {
        attendeeService = new AttendeeService(attendeeRepository);
    }

    @Test
    void findAll() {
        when(attendeeRepository.findAll()).thenReturn(List.of(new Attendee(), new Attendee()));

        List<Attendee> attendees = attendeeService.findAll();

        assertEquals(2, attendees.size());
        verify(attendeeRepository).findAll();
    }

    @Test
    void findById() {
        Attendee attendee = new Attendee();
        attendee.setId(1L);
        when(attendeeRepository.findById(1L)).thenReturn(Optional.of(attendee));

        Optional<Attendee> result = attendeeService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void save() {
        AttendeeDTO dto = new AttendeeDTO();
        dto.setAttendeeEmail("test@mail.com");
        dto.setAttendeeName("Test User");
        dto.setAttendeePassword("123456");

        when(attendeeRepository.save(any(Attendee.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Attendee saved = attendeeService.save(dto);

        assertEquals("test@mail.com", saved.getAttendeeEmail());
        assertEquals("Test User", saved.getAttendeeName());
        assertEquals("123456", saved.getAttendeePassword());
        assertEquals("USER", saved.getToken());
        assertEquals("ACTIVE", saved.getStatus());
    }

    @Test
    void update() throws Exception {
        Attendee attendee = new Attendee();
        attendee.setId(1L);
        attendee.setAttendeeName("Old Name");
        attendee.setAttendeeEmail("old@mail.com");

        AttendeeDTO dto = new AttendeeDTO();
        dto.setAttendeeEmail("new@mail.com");
        dto.setAttendeeName("New Name");

        when(attendeeRepository.findById(1L)).thenReturn(Optional.of(attendee));
        when(attendeeRepository.save(any(Attendee.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Attendee updated = attendeeService.update(1L, dto);

        assertEquals("New Name", updated.getAttendeeName());
        assertEquals("new@mail.com", updated.getAttendeeEmail());
    }

    @Test
    void deleteById() {
        Attendee attendee = new Attendee();
        attendee.setId(1L);
        attendee.setStatus("ACTIVE");

        when(attendeeRepository.findById(1L)).thenReturn(Optional.of(attendee));

        attendeeService.deleteById(1L);

        assertEquals("INACTIVE", attendee.getStatus());
        verify(attendeeRepository).save(attendee);
    }

}
