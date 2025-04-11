package com.projecttrack.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.projecttrack.DTO.LoginRequest;
import com.projecttrack.DTO.SignupRequest;
import com.projecttrack.model.User;
import com.projecttrack.repository.UserRepository;
import com.projecttrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173") // frontend URL
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // Signup API
    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest request) {
        Optional<User> existing = userRepository.findByEmailAndRole(request.getEmail(),request.getRole());
        if (existing.isPresent()) {
            return "Email already exists";
        }

        User user = new User(request.getEmail(), request.getPassword(), request.getRole());
        try {
            userService.registerUser(user, request.getAdminKey());
        } catch (IllegalArgumentException e) {
            return e.getMessage(); // "Invalid Admin Key!"
        }

        return "Signup successful as " + user.getRole();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<User> user = userRepository.findByEmailAndRole(request.getEmail(), request.getRole());

        if (user.isPresent() && user.get().getPassword().equals(request.getPassword())) {
            String role = user.get().getRole();

            String redirectUrl = "";
            if ("Admin".equalsIgnoreCase(request.getRole())) {
                redirectUrl = "/admindashboard";
            } else if ("Student".equalsIgnoreCase(request.getRole())) {
                redirectUrl = "/Home";
            }

            return ResponseEntity.ok(Map.of(
                    "user", user.get(),
                    "redirect", redirectUrl
            ));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email, password or role");
        }
    }


}
