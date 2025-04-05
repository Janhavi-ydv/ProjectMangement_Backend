package com.projecttrack.service;

import com.projecttrack.model.Project;
import com.projecttrack.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllProjects() {

        return projectRepository.findAll();
    }

    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getProjectsByYear(int year) {
        return projectRepository.findByYear(year);
    }

    public List<Project> getProjectsByType(String type) {
        return projectRepository.findByProjectType(type);
    }
}