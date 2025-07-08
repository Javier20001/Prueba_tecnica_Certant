package com.grandeventmanager.eventmanage.controller;


import com.grandeventmanager.eventmanage.dto.AttendeeDTO;
import com.grandeventmanager.eventmanage.model.Attendee;
import com.grandeventmanager.eventmanage.service.interfaces.AttendeeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/attendee")
public class AttendeeController {

    @Autowired
    private AttendeeServiceInterface attendeeServiceInterface;

    @GetMapping
    public ResponseEntity<List<Attendee>> getAllAttendee(){
        return  ResponseEntity.ok(attendeeServiceInterface.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendee> getAttendeeByID(@PathVariable Long id){
         return attendeeServiceInterface.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Attendee> createAttendee(@RequestBody AttendeeDTO attendeeDTO) {
        return ResponseEntity.ok(attendeeServiceInterface.save(attendeeDTO));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> updateAttendee(@PathVariable Long id, @RequestBody AttendeeDTO attendeeDTO) {
        try {
            return ResponseEntity.ok(attendeeServiceInterface.update(id, attendeeDTO));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAttendee(@PathVariable Long id) {
        try {
            attendeeServiceInterface.deleteById(id);
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
