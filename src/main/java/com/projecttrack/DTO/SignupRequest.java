package com.projecttrack.DTO;

public class SignupRequest {
    private String email;
    private String password;
    private String role;
    private String adminKey;

    public SignupRequest() {
        // default constructor
    }

    public SignupRequest(String email, String password, String role, String adminKey) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.adminKey = adminKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAdminKey() {
        return adminKey;
    }

    public void setAdminKey(String adminKey) {
        this.adminKey = adminKey;
    }
}
