package org.clubmanagementsystem.usermanagementservice.model;

import jakarta.persistence.*;

import java.util.List;
@Table(name = "Student")
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "year")
    private String year;

    @Basic
    @Column(name = "address")
    private String address;

    @ElementCollection
    @Column(name = "clubs")
    private List<Long> clubs;


    // Getter and Setter methods

    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
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
