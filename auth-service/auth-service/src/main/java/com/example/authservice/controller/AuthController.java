package com.example.authservice.controller;

import com.example.authservice.config.JwtService;
import com.example.authservice.dto.RegisterRequest;
import com.example.authservice.dto.AuthResponse;
import com.example.authservice.dto.LoginRequest;
import com.example.authservice.entity.User;
import com.example.authservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder, 
                        JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService=jwtService;
    }

    @PostMapping("/register")
public AuthResponse register(@RequestBody RegisterRequest request) {

    if (userRepository.findByEmail(request.getEmail()).isPresent()) {
        return new AuthResponse("error", "Email already exists", null);
    }

    User user = new User();
    user.setName(request.getName());
    user.setEmail(request.getEmail());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRole("USER");

    userRepository.save(user);

    return new AuthResponse("success", "User registered successfully", null);
}

   @PostMapping("/login")
public AuthResponse login(@RequestBody LoginRequest request) {

    var userOptional = userRepository.findByEmail(request.getEmail());

    if (userOptional.isEmpty()) {
        return new AuthResponse("error", "User not found", null);
    }

    User user = userOptional.get();

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        return new AuthResponse("error", "Invalid credentials", null);
    }

    String token = jwtService.generateToken(user.getEmail(), user.getRole());

    return new AuthResponse("success", "Login successful", token);
}

@GetMapping("/profile")
public String profile() {
    return "This is a protected profile endpoint";
}
@GetMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public String adminEndpoint() {
    return "Welcome Admin!";
}


}
