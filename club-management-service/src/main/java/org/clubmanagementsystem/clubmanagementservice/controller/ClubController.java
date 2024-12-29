package org.clubmanagementsystem.clubmanagementservice.controller;


import lombok.RequiredArgsConstructor;
import org.clubmanagementsystem.clubmanagementservice.model.Clubs;
import org.clubmanagementsystem.clubmanagementservice.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/clubs")
@RestController
public class ClubController {

    @Autowired
    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping
    public ResponseEntity<Clubs> createClub(@RequestBody Clubs club) {
        return new ResponseEntity<>(clubService.createClub(club), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clubs> getClubById(@PathVariable Long id) {
        return ResponseEntity.ok(clubService.getClubById(id));
    }

    @GetMapping
    public ResponseEntity<List<Clubs>> getAllClubs() {
        return ResponseEntity.ok(clubService.getAllClubs());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clubs> updateClub(@PathVariable Long id, @RequestBody Clubs club) {
        return ResponseEntity.ok(clubService.updateClub(id, club));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable Long id) {
        clubService.deleteClub(id);
        return ResponseEntity.noContent().build();
    }
}


