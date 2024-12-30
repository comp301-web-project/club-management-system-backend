package org.clubmanagementsystem.eventmanagementservice.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Event {

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

}
