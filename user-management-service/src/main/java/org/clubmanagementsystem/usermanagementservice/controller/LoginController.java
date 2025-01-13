package org.clubmanagementsystem.usermanagementservice.controller;

import org.clubmanagementsystem.usermanagementservice.request.LoginRequest;
import org.clubmanagementsystem.usermanagementservice.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final LoginService loginService;
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login/student")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        boolean isValid = loginService.loginStudent(loginRequest.getEmail(), loginRequest.getPassword());
        if (isValid) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
    }
    @PostMapping("/login/clubManager")
    public ResponseEntity<?> loginStudent(@RequestBody LoginRequest loginRequest) {
        boolean isValid = loginService.loginClubManager(loginRequest.getEmail(), loginRequest.getPassword());
        if (isValid) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
    }
}
