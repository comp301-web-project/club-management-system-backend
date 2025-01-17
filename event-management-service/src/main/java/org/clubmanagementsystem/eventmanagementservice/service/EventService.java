package org.clubmanagementsystem.eventmanagementservice.service;


import org.clubmanagementsystem.eventmanagementservice.model.Event;
import org.clubmanagementsystem.eventmanagementservice.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class EventService {

    private static final ZoneId ISTANBUL_ZONE = ZoneId.of("Europe/Istanbul");
    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

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

    public List<Event> getUpcomingEventsByStudentId(Long studentId) {
        Date currentDate = new Date();
        return eventRepository.findByParticipantsContainingAndStartDateAfterOrderByStartDateAsc(studentId, currentDate);
    }

    public Event updateEvent(Long id, Event eventDetails) {
        Event event = getEventById(id);

        // Güncellenmesi gereken alanları burada set edin
        event.setName(eventDetails.getName());
        event.setDescription(eventDetails.getDescription());
        event.setStartDate(eventDetails.getStartDate());
        event.setEndDate(eventDetails.getEndDate());
        event.setClubId(eventDetails.getClubId());
        event.setParticipants(eventDetails.getParticipants());
        event.setUpdatedDate(new Date());

        return eventRepository.save(event);
    }

    public List<Event> getUpcomingEventsLast24Hour() {
        LocalDateTime now = LocalDateTime.now(ISTANBUL_ZONE).truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime twentyFourHoursFromNow = now.plusHours(24);

        return eventRepository.findAll().stream()
                .filter(event -> {
                    LocalDateTime eventStartDate = LocalDateTime.ofInstant(event.getStartDate().toInstant(), ISTANBUL_ZONE)
                            .truncatedTo(ChronoUnit.MINUTES);
                    return !eventStartDate.isBefore(now) && !eventStartDate.isAfter(twentyFourHoursFromNow);
                })
                .collect(Collectors.toList());
    }

    public List<Event> getEventsByClubId(Long clubId) {
        return eventRepository.findByClubId(clubId);
    }
}
