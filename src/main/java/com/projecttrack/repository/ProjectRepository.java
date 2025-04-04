package com.projecttrack.repository;

import com.projecttrack.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByYear(int year);
    List<Project> findByProjectType(String projectType);
}
