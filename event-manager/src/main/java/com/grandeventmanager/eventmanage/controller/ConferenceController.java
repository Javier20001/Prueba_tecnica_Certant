package com.grandeventmanager.eventmanage.controller;

import com.grandeventmanager.eventmanage.dto.EventDTO;
import com.grandeventmanager.eventmanage.model.Conference;
import com.grandeventmanager.eventmanage.service.interfaces.ConferenceServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/conference")
@CrossOrigin(origins = "http://localhost:5173")
public class ConferenceController {

    @Autowired
    private ConferenceServiceInterface conferenceServiceInterface;

    @GetMapping
    public ResponseEntity<List<Conference>> getAllConferences() {
        return ResponseEntity.ok(conferenceServiceInterface.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conference> getConferenceById(@PathVariable Long id) {
        return conferenceServiceInterface.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Conference> createConference(@RequestBody EventDTO eventDTO) {
        return ResponseEntity.ok(conferenceServiceInterface.save(eventDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateConference(@PathVariable Long id, @RequestBody EventDTO eventDTO) {
        try {
            return ResponseEntity.ok(conferenceServiceInterface.update(id,eventDTO));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteConference(@PathVariable Long id) {
        try {
            conferenceServiceInterface.deleteById(id);
            return ResponseEntity.ok("Borrado con exito");
        } catch (NoSuchElementException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
