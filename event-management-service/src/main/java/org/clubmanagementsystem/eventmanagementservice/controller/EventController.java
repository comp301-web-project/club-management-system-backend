package org.clubmanagementsystem.eventmanagementservice.controller;

import org.clubmanagementsystem.eventmanagementservice.model.Event;
import org.clubmanagementsystem.eventmanagementservice.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/api/event")
@RestController
@CrossOrigin(origins = "*")

public class EventController {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);
    private static final ZoneId ISTANBUL_ZONE = ZoneId.of("Europe/Istanbul");

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/createEvent")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        return new ResponseEntity<>(eventService.createEvent(event), HttpStatus.CREATED);
    }

    @GetMapping("/getEventById/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @GetMapping("/allEvents")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/upcomingEvents")
    public ResponseEntity<?> getUpcomingEventsLast24Hour() {
        try {
            LocalDateTime now = LocalDateTime.now(ISTANBUL_ZONE).truncatedTo(ChronoUnit.MINUTES);
            LocalDateTime twentyFourHoursFromNow = now.plusHours(24);
            logger.info("Current Istanbul time: {}, Twenty-four hours from now: {}", now, twentyFourHoursFromNow);

            List<Event> upcomingEvents = eventService.getUpcomingEventsLast24Hour();

            logger.info("Found {} upcoming events", upcomingEvents.size());
            upcomingEvents.forEach(event -> {
                LocalDateTime eventStartDate = LocalDateTime.ofInstant(event.getStartDate().toInstant(), ISTANBUL_ZONE)
                        .truncatedTo(ChronoUnit.MINUTES);
                logger.info("Event: id={}, startDate={}", event.getId(), eventStartDate);
            });

            if (upcomingEvents.isEmpty()) {
                return ResponseEntity.ok().body(Map.of("message", "No upcoming events found in the next 24 hours"));
            }
            return ResponseEntity.ok(upcomingEvents);
        } catch (Exception e) {
            logger.error("Error fetching upcoming events", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Error fetching upcoming events: " + e.getMessage()));
        }
    }

    @PostMapping("/{eventId}/register/{studentId}")
    public ResponseEntity<?> registerStudentForEvent(@PathVariable Long eventId, @PathVariable Long studentId) {
        try {
            Event event = eventService.getEventById(eventId);
            event.getParticipants().add(studentId);
            eventService.updateEvent(eventId, event);
            return ResponseEntity.ok(Map.of("message", "Student successfully registered for the event"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Error registering student for event: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
        try {
            Event updatedEvent = eventService.updateEvent(id, eventDetails);
            return ResponseEntity.ok(updatedEvent);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Error updating event: " + e.getMessage()));
        }
    }

    @GetMapping("/getEventsByClubId/{clubId}")
    public ResponseEntity<?> getEventsByClubId(@PathVariable Long clubId) {
        try {
            List<Event> events = eventService.getEventsByClubId(clubId);
            if (events.isEmpty()) {
                return ResponseEntity.ok().body(Map.of("message", "No events found for this club"));
            }
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Error fetching events: " + e.getMessage()));
        }
    }

}
