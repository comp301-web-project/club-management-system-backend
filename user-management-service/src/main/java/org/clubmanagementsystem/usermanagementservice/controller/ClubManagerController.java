package org.clubmanagementsystem.usermanagementservice.controller;

import org.clubmanagementsystem.clubmanagementservice.dto.ClubManagerDTO;
import org.clubmanagementsystem.usermanagementservice.model.ClubManager;
import org.clubmanagementsystem.usermanagementservice.request.LoginRequest;
import org.clubmanagementsystem.usermanagementservice.service.ClubManagerService;
import org.clubmanagementsystem.usermanagementservice.util.DTOConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/clubManagers")
public class ClubManagerController {

    private final ClubManagerService clubManagerService;


    public ClubManagerController(ClubManagerService clubManagerService) {
        this.clubManagerService = clubManagerService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginClubManager(@RequestBody LoginRequest loginRequest) {
        boolean isValid = clubManagerService.loginClubManager(loginRequest.getEmail(), loginRequest.getPassword());
        if (isValid) {
            // JSON formatında response dönüyoruz
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Login successful");
            response.put("email", loginRequest.getEmail());
            response.put("id", clubManagerService.getClubManagerByEmail(loginRequest.getEmail()).getId());
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("status", "error");
            error.put("message", "Invalid credentials");
            return ResponseEntity.badRequest().body(error);
        }
    }


    @PostMapping
    public ResponseEntity<ClubManager> createClubManager(@RequestBody ClubManager clubManager) {
        ClubManager createdClubManager = clubManagerService.createClubManager(clubManager);
        return new ResponseEntity<>(createdClubManager, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubManagerDTO> getClubManagerById(@PathVariable Long id) {
        ClubManager clubManager = clubManagerService.getClubManagerById(id);
        ClubManagerDTO dto = DTOConverter.convertToDTO(clubManager);
        return ResponseEntity.ok(dto);
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
