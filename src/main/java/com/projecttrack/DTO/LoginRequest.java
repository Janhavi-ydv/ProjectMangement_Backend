package com.projecttrack.DTO;

public class LoginRequest {
    private String email;
    private String password;
    private String role;


    public LoginRequest() {
    }

    public String getEmail() {
        return email;
    }
    public String getRole() {
        return role;
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
}
