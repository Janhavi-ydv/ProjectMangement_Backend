package com.projecttrack.service;

import com.projecttrack.model.Department;
import com.projecttrack.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department getOrCreateByName(String name) {
        return departmentRepository.findByName(name)
                .orElseGet(() -> departmentRepository.save(new Department(name)));
}

}