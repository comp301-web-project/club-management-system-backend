package org.clubmanagementsystem.clubmanagementservice.service;


import jakarta.transaction.Transactional;
import org.clubmanagementsystem.clubmanagementservice.model.Club;
import org.clubmanagementsystem.clubmanagementservice.repository.ClubRepository;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ClubService {

    private ClubRepository clubRepository;

    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public Club createClub(Club club) {
        club.setCreatedDate(new Date());
        return clubRepository.save(club);
    }

    public Club getClubById(Long id) {
        return clubRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Club not found with id: " + id));
    }

    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    public Club updateClub(Long id, Club clubDetails) {
        Club club = getClubById(id);
        club.setName(clubDetails.getName());
        club.setDescription(clubDetails.getDescription());
        return clubRepository.save(club);
    }

    public void deleteClub(Long id) {
        Club club = getClubById(id);
        clubRepository.delete(club);
    }

}
