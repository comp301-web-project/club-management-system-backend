package org.clubmanagementsystem.usermanagementservice.repository;

import org.clubmanagementsystem.usermanagementservice.model.ClubManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubManagerRepository extends JpaRepository<ClubManager, Long> {
    boolean existsByEmailAndPassword(String email, String password);

    Optional<ClubManager> findByEmail(String email);
}

