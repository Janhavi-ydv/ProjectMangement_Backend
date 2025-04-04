package com.projecttrack.controller;

import com.projecttrack.model.Department;
import com.projecttrack.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/get")
    public List<Department> getDepartments() {
        return departmentService.getAllDepartments();
    }

    @PostMapping("/insert")
    public Department addDepartment(@RequestBody Department department) {
        return departmentService.addDepartment(department);
    }


}
