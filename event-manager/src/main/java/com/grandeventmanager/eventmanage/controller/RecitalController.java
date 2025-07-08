package com.grandeventmanager.eventmanage.controller;

import com.grandeventmanager.eventmanage.dto.EventDTO;
import com.grandeventmanager.eventmanage.model.Recital;
import com.grandeventmanager.eventmanage.service.interfaces.RecitalServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/recital")
@CrossOrigin(origins = "http://localhost:5173")
public class RecitalController {

    @Autowired
    private RecitalServiceInterface recitalServiceInterface;

    @GetMapping
    public ResponseEntity<List<Recital>> getAllRecitals() {
        return ResponseEntity.ok(recitalServiceInterface.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recital> getRecitalById(@PathVariable Long id) {
        return recitalServiceInterface.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Recital> createRecital(@RequestBody EventDTO eventDTO) {
        return ResponseEntity.ok(recitalServiceInterface.save(eventDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateRecital(@PathVariable Long id, @RequestBody EventDTO eventDTO) {
        try {
            return ResponseEntity.ok(recitalServiceInterface.update(id,eventDTO));
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
    public ResponseEntity<String> deleteRecital(@PathVariable Long id) {
        try {
            recitalServiceInterface.deleteById(id);
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
