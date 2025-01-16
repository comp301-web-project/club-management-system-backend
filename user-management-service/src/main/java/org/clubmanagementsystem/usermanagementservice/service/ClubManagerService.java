package org.clubmanagementsystem.usermanagementservice.service;

import org.clubmanagementsystem.clubmanagementservice.dto.ClubManagerDTO;
import org.clubmanagementsystem.usermanagementservice.model.ClubManager;
import org.clubmanagementsystem.usermanagementservice.repository.ClubManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubManagerService {

    private final ClubManagerRepository clubManagerRepository;


    public ClubManagerService(ClubManagerRepository clubManagerRepository) {
        this.clubManagerRepository = clubManagerRepository;
    }

    public ClubManager createClubManager(ClubManager clubManager) {
        return clubManagerRepository.save(clubManager);
    }

    public ClubManager getClubManagerById(Long id) {
        return clubManagerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Club Manager not found with id: " + id));
    }

    public List<ClubManager> getAllClubManagers() {
        return clubManagerRepository.findAll();
    }

    public ClubManager updateClubManager(Long id, ClubManager clubManagerDetails) {
        ClubManager clubManager = getClubManagerById(id);

        // Update the fields you want to allow updating
        clubManager.setName(clubManagerDetails.getName());
        clubManager.setDescription(clubManagerDetails.getDescription());
        // Update other fields as necessary

        return clubManagerRepository.save(clubManager);
    }

    public void deleteClubManager(Long id) {
        ClubManager clubManager = getClubManagerById(id);
        clubManagerRepository.delete(clubManager);
    }

    public boolean loginClubManager(String email, String password) {
        return clubManagerRepository.existsByEmailAndPassword(email, password);
    }

    public ClubManager getClubManagerByEmail(String email) {
        return clubManagerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Club Manager not found with email: " + email));
    }
}
