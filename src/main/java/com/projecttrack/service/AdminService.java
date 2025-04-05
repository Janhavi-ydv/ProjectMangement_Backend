package com.projecttrack.service;

import com.projecttrack.model.Admin;
import com.projecttrack.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin register(Admin admin) {
        return adminRepository.save(admin);
    }

    public boolean login(String email, String password) {
        Optional<Admin> optionalAdmin = adminRepository.findByEmail(email);
        return optionalAdmin.isPresent() && optionalAdmin.get().getPassword().equals(password);
    }
}
