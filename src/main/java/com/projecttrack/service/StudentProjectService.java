package com.projecttrack.service;

import com.projecttrack.model.StudentProject;
import com.projecttrack.repository.StudentProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class StudentProjectService {

    @Autowired
    private StudentProjectRepository repository;

    private final String uploadDir = "uploads/";

    public StudentProject saveProject(String title, String domain, String description,
                                      String projectLink, String studentName, String studentEmail,
                                      String studentDeptId, MultipartFile image, MultipartFile document)
            throws IOException {

        // Save files with unique names
        String imageFileName = null;
        String documentFileName = null;

        if (!image.isEmpty()) {
            imageFileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
            image.transferTo(new File(uploadDir + imageFileName));
        }

        if (!document.isEmpty()) {
            documentFileName = UUID.randomUUID() + "_" + document.getOriginalFilename();
            document.transferTo(new File(uploadDir + documentFileName));
        }

        StudentProject project = new StudentProject();
        project.setTitle(title);
        project.setDomain(domain);
        project.setDescription(description);
        project.setProjectLink(projectLink);
        project.setStudentName(studentName);
        project.setStudentEmail(studentEmail);
        project.setStudentDept(studentDeptId);
        project.setImagePath(imageFileName);
        project.setDocumentPath(documentFileName);

        return repository.save(project);
    }
}
