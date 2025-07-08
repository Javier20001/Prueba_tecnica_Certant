package com.grandeventmanager.eventmanage.controller;


import com.grandeventmanager.eventmanage.respository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    EventRepository repositoryEvent;

    @GetMapping
    public ResponseEntity<?> getAllEvents(){
        return new ResponseEntity<>(repositoryEvent.findAll(), HttpStatus.OK);
    }
}
