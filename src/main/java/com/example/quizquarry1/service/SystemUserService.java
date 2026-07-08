package com.example.quizquarry1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.quizquarry1.entity.SystemUser;
import com.example.quizquarry1.exception.QuizNotFoundException;
import com.example.quizquarry1.repository.SystemUserRepository;

@Service
public class SystemUserService {

    @Autowired
    private SystemUserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public SystemUser saveUser(SystemUser user) {

        user.setPasswordHash(
                passwordEncoder.encode(user.getPasswordHash()));

        return repository.save(user);
    }

    public List<SystemUser> getAllUsers() {
        return repository.findAll();
    }

    public SystemUser getUserById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new QuizNotFoundException("User not found"));
    }

    public SystemUser updateUser(Long id, SystemUser user) {

        SystemUser existing = repository.findById(id)
                .orElseThrow(() -> new QuizNotFoundException("User not found"));

        existing.setFullname(user.getFullname());
        existing.setEmail(user.getEmail());

        if (user.getPasswordHash() != null &&
                !user.getPasswordHash().isBlank()) {

            existing.setPasswordHash(
                    passwordEncoder.encode(user.getPasswordHash()));
        }

        existing.setRole(user.getRole());

        return repository.save(existing);
    }

    public SystemUser deleteUser(Long id) {

        SystemUser existing = repository.findById(id)
                .orElseThrow(() -> new QuizNotFoundException("User not found"));

        repository.delete(existing);

        return existing;
    }

    public SystemUser patchUser(Long id, SystemUser user) {

        SystemUser existing = repository.findById(id)
                .orElseThrow(() -> new QuizNotFoundException("User not found"));

        if (user.getFullname() != null) {
            existing.setFullname(user.getFullname());
        }

        if (user.getEmail() != null) {
            existing.setEmail(user.getEmail());
        }

        if (user.getRole() != null) {
            existing.setRole(user.getRole());
        }

        if (user.getPasswordHash() != null &&
                !user.getPasswordHash().isBlank()) {

            existing.setPasswordHash(
                    passwordEncoder.encode(user.getPasswordHash()));
        }

        return repository.save(existing);
    }

}