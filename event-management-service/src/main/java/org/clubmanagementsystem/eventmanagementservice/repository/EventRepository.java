package org.clubmanagementsystem.eventmanagementservice.repository;

import org.clubmanagementsystem.eventmanagementservice.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByParticipantsContainingAndStartDateAfterOrderByStartDateAsc(Long studentId, Date currentDate);
    List<Event> findByClubId(Long clubId);
}
