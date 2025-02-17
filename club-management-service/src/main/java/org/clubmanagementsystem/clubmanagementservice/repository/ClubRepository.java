package org.clubmanagementsystem.clubmanagementservice.repository;

import org.clubmanagementsystem.clubmanagementservice.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
}
