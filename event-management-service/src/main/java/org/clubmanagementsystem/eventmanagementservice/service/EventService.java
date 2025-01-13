package org.clubmanagementsystem.eventmanagementservice.service;


import org.clubmanagementsystem.eventmanagementservice.model.Event;
import org.clubmanagementsystem.eventmanagementservice.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
    }
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
