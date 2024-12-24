package org.clubmanagementsystem.clubmanagementservice.controller;


import lombok.RequiredArgsConstructor;
import org.clubmanagementsystem.clubmanagementservice.service.ClubService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/clubs")
@RequiredArgsConstructor
@RestController
public class ClubController {

    private final ClubService clubService;


}
