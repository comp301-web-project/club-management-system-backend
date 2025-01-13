package org.clubmanagementsystem.clubmanagementservice.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity

public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "createdDate")
    private Date createdDate;

    @Basic
    @Column(name = "club_manager_id")
    private Long clubManagerId;


    public Long getId() { return Id; }
    public void setId(Long Id) { this.Id = this.Id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }

    public Long getClubManagerId() { return clubManagerId; }
    public void setClubManagerId(Long clubManagerId) { this.clubManagerId = clubManagerId; }

}
