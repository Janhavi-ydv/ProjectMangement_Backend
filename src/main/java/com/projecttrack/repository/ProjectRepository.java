package com.projecttrack.repository;

import com.projecttrack.model.Department;
import com.projecttrack.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {
    List<Project> findByYear(int year);
    List<Project> findByProjectType(String projectType);
    List<Project> findByYearAndProjectTypeAndDepartment(int year, String projectType, Department department);
}
