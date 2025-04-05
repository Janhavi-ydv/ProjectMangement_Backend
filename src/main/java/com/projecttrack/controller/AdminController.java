package com.projecttrack.controller;

import com.projecttrack.model.Admin;
import com.projecttrack.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/signup")
    public Admin signup(@RequestBody Admin admin) {
        return adminService.register(admin);
    }

    @PostMapping("/login")
    public String login(@RequestBody Admin admin) {
        boolean success = adminService.login(admin.getEmail(), admin.getPassword());
        return success ? "Login successful" : "Invalid credentials";
    }
}
