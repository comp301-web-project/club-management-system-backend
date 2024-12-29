package org.clubmanagementsystem.eventmanagementservice.repository;

import org.clubmanagementsystem.eventmanagementservice.model.Events;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Events, Long> {
}
