package com.projecttrack.controller;

import com.projecttrack.model.Department;
import com.projecttrack.model.Project;
import com.projecttrack.service.DepartmentService;
import com.projecttrack.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/check")
    public String test() {
        return "CHECK";
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

    // 📁 CSV Upload Endpoint
    @PostMapping("/upload")
    public ResponseEntity<String> uploadCSV(
            @RequestParam("file") MultipartFile file,
            @RequestParam("year") int year,
            @RequestParam("type") String type,
            @RequestParam("department") String deptName
    ) {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
            String line;
            boolean isFirstLine = true;

            Department department = departmentService.getOrCreateByName(deptName);

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // skip header
                    continue;
                }

                String[] data = line.split(",");

                // CSV format: projectName,domain,synopsis
                if (data.length < 3) continue;

                String projectName = data[0].trim();
                String domain = data[1].trim();
                String synopsis = data[2].trim();

                Project project = new Project(projectName, domain, synopsis, year, type, department);
                projectService.addProject(project);
            }

            return ResponseEntity.ok("CSV Uploaded Successfully!");

        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed: " + e.getMessage());
        }
    }

}