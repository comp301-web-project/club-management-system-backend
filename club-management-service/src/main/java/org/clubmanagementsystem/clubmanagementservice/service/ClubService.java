package org.clubmanagementsystem.clubmanagementservice.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.clubmanagementsystem.clubmanagementservice.model.Clubs;
import org.clubmanagementsystem.clubmanagementservice.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service

@Transactional
public class ClubService {


    private ClubRepository clubRepository;
    @Autowired
    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public Clubs createClub(Clubs club) {
        club.setCreatedDate(new Date());
        club.setUpdatedDate(new Date());
        return clubRepository.save(club);
    }

    public Clubs getClubById(Long id) {
        return clubRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Club not found with id: " + id));
    }

    public List<Clubs> getAllClubs() {
        return clubRepository.findAll();
    }

    public Clubs updateClub(Long id, Clubs clubDetails) {
        Clubs club = getClubById(id);
        club.setName(clubDetails.getName());
        club.setDescription(clubDetails.getDescription());
        club.setUpdatedDate(new Date());
        return clubRepository.save(club);
    }

    public void deleteClub(Long id) {
        Clubs club = getClubById(id);
        clubRepository.delete(club);
    }

}
