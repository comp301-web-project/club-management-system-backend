package org.clubmanagementsystem.usermanagementservice.service;


import org.clubmanagementsystem.usermanagementservice.repository.ClubManagerRepository;
import org.clubmanagementsystem.usermanagementservice.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final StudentRepository studentRepository;
    private final ClubManagerRepository clubManagerRepository;

    public LoginService(StudentRepository studentRepository, ClubManagerRepository clubManagerRepository) {
        this.studentRepository = studentRepository;
        this.clubManagerRepository = clubManagerRepository;
    }

    public boolean loginStudent(String email, String password) {
        return studentRepository.existsByEmailAndPassword(email, password);
    }
    public boolean loginClubManager(String email, String password) {
        return clubManagerRepository.existsByEmailAndPassword(email, password);
    }
}
