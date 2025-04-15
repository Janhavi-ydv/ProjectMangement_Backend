package com.projecttrack.repository;

import com.projecttrack.model.StudentProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentProjectRepository extends JpaRepository<StudentProject, Long> {
}