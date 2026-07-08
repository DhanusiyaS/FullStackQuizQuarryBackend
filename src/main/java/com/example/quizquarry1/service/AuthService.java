package com.example.quizquarry1.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.quizquarry1.entity.SystemUser;
import com.example.quizquarry1.repository.SystemUserRepository;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final SystemUserRepository repository;

    public AuthService(PasswordEncoder passwordEncoder,
            SystemUserRepository repository) {

        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    public SystemUser register(SystemUser user) {

        user.setPasswordHash(
                passwordEncoder.encode(user.getPasswordHash()));

        return repository.save(user);
    }

    public String getRole(String email) {

        SystemUser user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getRole().name();
    }

    public String getFullName(String email) {

        SystemUser user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getFullname();
    }

    public boolean verifyPassword(String email, String password) {

        SystemUser user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return passwordEncoder.matches(password,
                user.getPasswordHash());
    }

}