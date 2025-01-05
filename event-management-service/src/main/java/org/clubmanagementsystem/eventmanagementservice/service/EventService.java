package org.clubmanagementsystem.eventmanagementservice.service;


import org.clubmanagementsystem.eventmanagementservice.model.Event;
import org.clubmanagementsystem.eventmanagementservice.repository.EventRepository;
import org.springframework.stereotype.Service;

@Service

public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }
}
