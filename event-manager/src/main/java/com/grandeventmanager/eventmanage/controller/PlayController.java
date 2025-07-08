package com.grandeventmanager.eventmanage.controller;

import com.grandeventmanager.eventmanage.dto.EventDTO;
import com.grandeventmanager.eventmanage.model.Play;
import com.grandeventmanager.eventmanage.service.interfaces.PlayServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/play")
@CrossOrigin(origins = "http://localhost:5173")
public class PlayController {

    @Autowired
    private PlayServiceInterface playServiceInterface;

    @GetMapping
    public ResponseEntity<List<Play>> getAllPlays() {
        return ResponseEntity.ok(playServiceInterface.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Play> getPlayById(@PathVariable Long id) {
        return playServiceInterface.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Play> createPlay(@RequestBody EventDTO eventDTO) {
        return ResponseEntity.ok(playServiceInterface.save(eventDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePlay(@PathVariable Long id, @RequestBody EventDTO eventDTO) {
        try {
            return ResponseEntity.ok(playServiceInterface.update(id,eventDTO));
        }catch (IllegalArgumentException e) {
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
    public ResponseEntity<String> deletePlay(@PathVariable Long id) {
        try {
            playServiceInterface.deleteById(id);
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

