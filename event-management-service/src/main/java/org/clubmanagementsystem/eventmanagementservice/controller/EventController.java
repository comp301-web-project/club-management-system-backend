package org.clubmanagementsystem.eventmanagementservice.controller;

import org.clubmanagementsystem.eventmanagementservice.model.Events;
import org.clubmanagementsystem.eventmanagementservice.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/events")
@RestController

public class EventController {

    @Autowired
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Events> createEvent(@RequestBody Events event) {
        return new ResponseEntity<>(eventService.createEvent(event), HttpStatus.CREATED);
    }

}
