package com.example.authservice.config;

import com.example.authservice.entity.User;
import com.example.authservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbTestRunner {

    @Bean
    CommandLineRunner testDb(UserRepository userRepository) {
        return args -> {

            User user = new User();
            user.setName("Sanjana");
            user.setEmail("sanjana@test.com");
            user.setPassword("12345");
            user.setRole("USER");

            userRepository.save(user);

            System.out.println("User saved successfully!");

            System.out.println("Total users in DB: " + userRepository.count());
        };
    }
}
