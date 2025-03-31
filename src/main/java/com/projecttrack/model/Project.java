package com.projecttrack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "projects")
@NoArgsConstructor
@AllArgsConstructor

public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "domain")
    private String domain;

    @Column(name = "year")
    private String academicYear;

    @Column(name = "members")
    private String members;

    @Column(name = "final_year_project", nullable = false)
    private boolean finalYearProject;  // Removed "is" prefix

    @Column(name = "synopsis", columnDefinition = "TEXT")
    private String synopsis;
}
