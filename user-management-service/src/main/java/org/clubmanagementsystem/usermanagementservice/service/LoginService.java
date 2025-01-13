package org.clubmanagementsystem.usermanagementservice.service;


import org.clubmanagementsystem.usermanagementservice.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final StudentRepository studentRepository;

    public LoginService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public boolean login(String email, String password) {
        return studentRepository.existsByEmailAndPassword(email, password);
    }
}
