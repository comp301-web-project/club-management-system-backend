package org.clubmanagementsystem.clubmanagementservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Clubs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clubId;

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
    @Column(name = "updatedDate")
    private Date updatedDate;

}
