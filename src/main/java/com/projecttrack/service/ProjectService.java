package com.projecttrack.service;

import com.projecttrack.model.Department;
import com.projecttrack.model.Project;
import com.projecttrack.repository.ProjectRepository;
import com.projecttrack.specs.ProjectSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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

    public List<Project> getProjectsByYearTypeAndDepartment(int year, String type, Department department) {
        return projectRepository.findByYearAndProjectTypeAndDepartment(year, type, department);
    }

    // 🔥 New Method: Dynamic Filtering with Optional Parameters
    public List<Project> getProjectsByFilters(Integer year, String type, Department department, String domain) {
        Specification<Project> spec = Specification.where(ProjectSpecifications.hasYear(year))
                .and(ProjectSpecifications.hasType(type))
                .and(ProjectSpecifications.hasDepartment(department))
                .and(ProjectSpecifications.hasDomain(domain)); // ← add this line

        return projectRepository.findAll(spec);
    }

}
