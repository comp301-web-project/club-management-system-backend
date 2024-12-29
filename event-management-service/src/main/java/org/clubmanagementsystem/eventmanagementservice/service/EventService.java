package org.clubmanagementsystem.eventmanagementservice.service;


import org.clubmanagementsystem.eventmanagementservice.model.Events;
import org.clubmanagementsystem.eventmanagementservice.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class EventService {

    @Autowired
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Events createEvent(Events event) {
        return eventRepository.save(event);
    }
}
