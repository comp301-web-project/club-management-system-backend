package org.clubmanagementsystem.clubmanagementservice.service;


import jakarta.transaction.Transactional;
import org.clubmanagementsystem.clubmanagementservice.dto.ClubManagerDTO;
import org.clubmanagementsystem.clubmanagementservice.dto.StudentDTO;
import org.clubmanagementsystem.clubmanagementservice.model.Club;
import org.clubmanagementsystem.clubmanagementservice.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClubService {

    private ClubRepository clubRepository;
    private RestTemplate restTemplate;

    public ClubService(ClubRepository clubRepository, RestTemplate restTemplate) {
        this.clubRepository = clubRepository;
        this.restTemplate = restTemplate;
    }

    @Value("${user.management.service.url}")
    private String userManagementServiceUrl;

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

    public Optional<Club> getClubByStudentId(Long id) {
        return clubRepository.findById(id);
    }

    public Club getClubByClubManagerId(Long clubManagerId) {
        try {
            String url = userManagementServiceUrl + "/api/clubManagers/" + clubManagerId;
            ResponseEntity<ClubManagerDTO> response = restTemplate.getForEntity(url, ClubManagerDTO.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                ClubManagerDTO clubManagerDTO = response.getBody();
                if (clubManagerDTO != null && clubManagerDTO.getClubId() != null) {
                    return getClubById(clubManagerDTO.getClubId());
                } else {
                    throw new RuntimeException("Club Manager has no associated club");
                }
            } else {
                throw new RuntimeException("Error fetching Club Manager with id: " + clubManagerId);
            }
        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("Club Manager not found with id: " + clubManagerId);
        } catch (Exception e) {
            throw new RuntimeException("Error getting club by club manager id: " + clubManagerId, e);
        }
    }

    public List<Club> getClubsByStudentId(Long studentId) {
        try {
            String url = userManagementServiceUrl + "/api/student/" + studentId;
            ResponseEntity<StudentDTO> response = restTemplate.getForEntity(url, StudentDTO.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                StudentDTO studentDTO = response.getBody();
                if (studentDTO != null && studentDTO.getClubs() != null) {
                    List<Long> clubIds = studentDTO.getClubs();
                    return clubIds.stream()
                            .map(this::ggetClubById)
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .collect(Collectors.toList());
                } else {
                    return new ArrayList<>();
                }
            } else {
                throw new RuntimeException("Error fetching Student with id: " + studentId);
            }
        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("Student not found with id: " + studentId);
        } catch (Exception e) {
            throw new RuntimeException("Error getting clubs by student id: " + studentId, e);
        }
    }

    private Optional<Club> ggetClubById(Long clubId) {
        try {
            return Optional.of(clubRepository.findById(clubId)
                    .orElseThrow(() -> new RuntimeException("Club not found with id: " + clubId)));
        } catch (Exception e) {
            // Log the error and return an empty Optional
            System.err.println("Error fetching club with id: " + clubId + ". Error: " + e.getMessage());
            return Optional.empty();
        }
    }
}
