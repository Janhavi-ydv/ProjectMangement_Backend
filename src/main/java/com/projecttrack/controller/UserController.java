package com.projecttrack.controller;

import com.projecttrack.DTO.LoginRequest;
import com.projecttrack.DTO.SignupRequest;
import com.projecttrack.model.User;
import com.projecttrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000") // frontend URL
public class UserController {

    private static final String SECRET_ADMIN_KEY = "PICT9876";

    @Autowired
    private UserRepository userRepository;

    // Signup API
    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest request) {
        Optional<User> existing = userRepository.findByEmail(request.getEmail());
        if (existing.isPresent()) {
            return "Email already exists";
        }

        if ("admin".equalsIgnoreCase(request.getRole())) {
            if (!SECRET_ADMIN_KEY.equals(request.getAdminKey())) {
                return "Invalid Admin Key";
            }
        }

        User newUser = new User(request.getEmail(), request.getPassword(), request.getRole());
        userRepository.save(newUser);
        return "Signup successful as " + request.getRole();
    }

    // Login API
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isPresent() && user.get().getPassword().equals(request.getPassword())) {
            return "Login successful as " + user.get().getRole();
        } else {
            return "Invalid email or password";
        }
    }
}
