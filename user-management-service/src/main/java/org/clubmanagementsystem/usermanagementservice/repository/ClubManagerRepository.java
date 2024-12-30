package org.clubmanagementsystem.usermanagementservice.repository;

import org.clubmanagementsystem.usermanagementservice.model.ClubManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubManagerRepository extends JpaRepository<ClubManager, Long> {
}
