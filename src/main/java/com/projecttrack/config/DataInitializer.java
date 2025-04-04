package com.projecttrack.config;

import com.projecttrack.model.Department;
import com.projecttrack.model.Project;
import com.projecttrack.repository.DepartmentRepository;
import com.projecttrack.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(DepartmentRepository departmentRepo, ProjectRepository projectRepo) {
        return args -> initializeData(departmentRepo, projectRepo);
    }

    @Transactional
    public void initializeData(DepartmentRepository departmentRepo, ProjectRepository projectRepo) {
        if (departmentRepo.count() == 0) {
            List<Department> departments = List.of(
                    new Department("Comp"),
                    new Department("IT"),
                    new Department("ENTC"),
                    new Department("AIDS"),
                    new Department("ECE")
            );
            departmentRepo.saveAll(departments);
        }

        List<Department> savedDepartments = departmentRepo.findAll();

        if (projectRepo.count() == 0 && savedDepartments.size() >= 5) {
            projectRepo.saveAll(List.of(
                    new Project("AI-Based Chatbot", "Machine Learning", "A chatbot for college inquiries", 2024, "Final", savedDepartments.get(0)),
                    new Project("Smart Attendance System", "IoT", "Uses RFID for marking attendance", 2024, "Mini", savedDepartments.get(1)),
                    new Project("Automated Irrigation", "Embedded Systems", "Uses sensors for soil moisture", 2023, "Final", savedDepartments.get(2)),
                    new Project("Disease Detection", "AI", "Identifies plant diseases", 2024, "Mini", savedDepartments.get(3)),
                    new Project("Smart Traffic Light", "IoT", "Uses AI to manage traffic signals", 2023, "Final", savedDepartments.get(4))
            ));
        }
    }
}
