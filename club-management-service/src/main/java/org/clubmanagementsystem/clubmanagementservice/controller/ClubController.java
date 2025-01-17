package org.clubmanagementsystem.clubmanagementservice.controller;


import org.clubmanagementsystem.clubmanagementservice.model.Club;
import org.clubmanagementsystem.clubmanagementservice.service.ClubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RequestMapping("/api/club")
@RestController
public class ClubController {

    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping("/createClub")
    public ResponseEntity<Club> createClub(@RequestBody Club club) {
        return new ResponseEntity<>(clubService.createClub(club), HttpStatus.CREATED);
    }

    @GetMapping("/getClubById/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable Long id) {
        return ResponseEntity.ok(clubService.getClubById(id));
    }

    @GetMapping("/getAllClubs")
    public ResponseEntity<List<Club>> getAllClubs() {
        return ResponseEntity.ok(clubService.getAllClubs());
    }

    @PutMapping("/updateClub/{id}")
    public ResponseEntity<Club> updateClub(@PathVariable Long id, @RequestBody Club club) {
        return ResponseEntity.ok(clubService.updateClub(id, club));
    }

    @DeleteMapping("/deleteClub/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable Long id) {
        clubService.deleteClub(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("getClubByStudentId/{id}")
    public ResponseEntity<Optional<Club>> getClubByStudentId(@PathVariable Long id) {
        return ResponseEntity.ok(clubService.getClubByStudentId(id));
    }

    @GetMapping("/getClubByClubManagerId/{id}")
    public ResponseEntity<?> getClubByClubManagerId(@PathVariable Long id) {
        try {
            Club club = clubService.getClubByClubManagerId(id);
            return ResponseEntity.ok(club);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("has no associated club")) {
                return ResponseEntity.ok().body(Map.of("message", "Club Manager has no associated club"));
            }
            throw e;
        }
    }

    @GetMapping("/getClubsByStudentId/{id}")
    public ResponseEntity<?> getClubsByStudentId(@PathVariable Long id) {
        try {
            List<Club> clubs = clubService.getClubsByStudentId(id);
            if (clubs.isEmpty()) {
                return ResponseEntity.ok().body(Map.of("message", "No clubs found for the student"));
            }
            return ResponseEntity.ok(clubs);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        }
    }

}


