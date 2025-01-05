package org.clubmanagementsystem.usermanagementservice.repository;

import org.clubmanagementsystem.usermanagementservice.model.ClubManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubManagerRepository extends JpaRepository<ClubManager, Long> {
}
