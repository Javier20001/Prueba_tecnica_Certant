package com.grandeventmanager.eventmanage.controller;

import com.grandeventmanager.eventmanage.dto.AttendeeDTO;
import com.grandeventmanager.eventmanage.dto.SessionDTO;
import com.grandeventmanager.eventmanage.service.interfaces.SessionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/session")
@CrossOrigin(origins = "http://localhost:5173")
public class SesionController {

    @Autowired
    private SessionServiceInterface sessionServiceInterface;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AttendeeDTO attendeeDTO){
        try {
            return ResponseEntity.ok(sessionServiceInterface.login(attendeeDTO));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error: " + e.getMessage());
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AttendeeDTO attendeeDTO){
        try {
            return ResponseEntity.ok(sessionServiceInterface.register(attendeeDTO));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error: " + e.getMessage());
        }
    }

}
