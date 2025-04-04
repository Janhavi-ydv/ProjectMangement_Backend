package com.projecttrack.controller;

import com.projecttrack.model.Project;
import com.projecttrack.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @PostMapping
    public Project addProject(@RequestBody Project project) {
        return projectService.addProject(project);
    }

    @GetMapping("/year/{year}")
    public List<Project> getProjectsByYear(@PathVariable int year) {
        return projectService.getProjectsByYear(year);
    }

    @GetMapping("/type/{type}")
    public List<Project> getProjectsByType(@PathVariable String type) {
        return projectService.getProjectsByType(type);
    }
}
