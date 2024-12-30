package org.clubmanagementsystem.eventmanagementservice.repository;

import org.clubmanagementsystem.eventmanagementservice.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
