package org.clubmanagementsystem.usermanagementservice.controller;

import org.clubmanagementsystem.usermanagementservice.model.ClubManager;
import org.clubmanagementsystem.usermanagementservice.service.ClubManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clubManagers")
public class ClubManagerController {
    private final ClubManagerService clubManagerService;

    @Autowired
    public ClubManagerController(ClubManagerService clubManagerService) {
        this.clubManagerService = clubManagerService;
    }

    @PostMapping
    public ResponseEntity<ClubManager> createClubManager(@RequestBody ClubManager clubManager) {
        ClubManager createdClubManager = clubManagerService.createClubManager(clubManager);
        return new ResponseEntity<>(createdClubManager, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubManager> getClubManagerById(@PathVariable Long id) {
        ClubManager clubManager = clubManagerService.getClubManagerById(id);
        return ResponseEntity.ok(clubManager);
    }

    @GetMapping
    public ResponseEntity<List<ClubManager>> getAllClubManagers() {
        List<ClubManager> clubManagers = clubManagerService.getAllClubManagers();
        return ResponseEntity.ok(clubManagers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClubManager> updateClubManager(@PathVariable Long id, @RequestBody ClubManager clubManager) {
        ClubManager updatedClubManager = clubManagerService.updateClubManager(id, clubManager);
        return ResponseEntity.ok(updatedClubManager);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClubManager(@PathVariable Long id) {
        clubManagerService.deleteClubManager(id);
        return ResponseEntity.noContent().build();
    }
}
