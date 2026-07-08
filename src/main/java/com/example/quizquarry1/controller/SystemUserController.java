package com.example.quizquarry1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.quizquarry1.entity.SystemUser;
import com.example.quizquarry1.service.SystemUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class SystemUserController {

    @Autowired
    private SystemUserService service;

    @PostMapping("/add")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public SystemUser saveUser(@Valid @RequestBody SystemUser user) {
        return service.saveUser(user);
    }

    @GetMapping("/all")
    public List<SystemUser> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public SystemUser getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    @PutMapping("/update/{id}")
    public SystemUser updateUser(@PathVariable Long id,
            @RequestBody SystemUser user) {

        return service.updateUser(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {

        service.deleteUser(id);

        return ResponseEntity.ok("User deleted successfully");
    }

    @PatchMapping("/patchUser/{id}")
    public SystemUser patchUser(@PathVariable Long id,
            @RequestBody SystemUser user) {
        return service.patchUser(id, user);
    }

}