package org.clubmanagementsystem.clubmanagementservice.dto;

import java.util.List;

public class StudentDTO {
    private Long id;
    private String name;
    private List<Long> clubs;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getClubs() {
        return clubs;
    }

    public void setClubs(List<Long> clubs) {
        this.clubs = clubs;
    }
}