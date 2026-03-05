package com.example.authservice.dto;

public class AuthResponse {

    private String status;
    private String message;
    private String token;

    public AuthResponse(String status, String message, String token) {
        this.status = status;
        this.message = message;
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }
}
