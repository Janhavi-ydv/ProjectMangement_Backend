package com.projecttrack.model;

import jakarta.persistence.*;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_name", nullable = false)
    private String projectName;

    @Column(nullable = false)
    private String domain;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String synopsis;

    @Column(nullable = false)
    private int year;

    @Column(name = "project_type", nullable = false)
    private String projectType;  // "Mini" or "Final"

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    // Constructors
    public Project() {}

    public Project(String projectName, String domain, String synopsis, int year, String projectType, Department department) {
        this.projectName = projectName;
        this.domain = domain;
        this.synopsis = synopsis;
        this.year = year;
        this.projectType = projectType;
        this.department = department;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
