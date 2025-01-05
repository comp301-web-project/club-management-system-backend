package org.clubmanagementsystem.usermanagementservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Data
@Getter
@Setter

public class ClubManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "startDate")
    private Date startDate;

    @Basic
    @Column(name = "endDate")
    private Date endDate;

    @Basic
    @Column(name = "clubId")
    private Long clubId;

    @Basic
    @Column(name = "createdDate")
    private Long createdDate;

    @Basic
    @Column(name = "updatedDate")
    private Long updatedDate;

    // Getter methods
    public Long getEventId() {
        return eventId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Long getClubId() {
        return clubId;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public Long getUpdatedDate() {
        return updatedDate;
    }

    // Setter methods
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setClubId(Long clubId) {
        this.clubId = clubId;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public void setUpdatedDate(Long updatedDate) {
        this.updatedDate = updatedDate;
    }
}
