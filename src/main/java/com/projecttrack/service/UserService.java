package com.projecttrack.service;

import com.projecttrack.model.User;
import com.projecttrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Value("${admin.secret.key}")
    private String adminSecretKey;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user, String providedAdminKey) {
        // Check if trying to register as admin
        if ("ADMIN".equalsIgnoreCase(user.getRole())) {
            if (!adminSecretKey.equals(providedAdminKey)) {
                throw new IllegalArgumentException("Invalid Admin Key!");
            }
        }

        // Save user if valid (student or valid admin)
        return userRepository.save(user);
    }
}
