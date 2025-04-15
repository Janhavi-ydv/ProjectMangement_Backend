package com.projecttrack.controller;

import com.projecttrack.model.StudentProject;
import com.projecttrack.service.StudentProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = "*")
public class StudentProjectController {

    @Autowired
    private StudentProjectService service;

    @PostMapping("/projects")
    public ResponseEntity<?> submitProject(
            @RequestParam("title") String title,
            @RequestParam("domain") String domain,
            @RequestParam("description") String description,
            @RequestParam("projectLink") String projectLink,
            @RequestParam("studentName") String studentName,
            @RequestParam("studentEmail") String studentEmail,
            @RequestParam("studentDeptId") String studentDeptId,
            @RequestParam("image") MultipartFile image,
            @RequestParam("document") MultipartFile document
    ) {
        try {
            StudentProject saved = service.saveProject(title, domain, description, projectLink,
                    studentName, studentEmail, studentDeptId, image, document);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error saving project: " + e.getMessage());
        }
    }
}

