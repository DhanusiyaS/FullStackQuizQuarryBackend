package com.example.quizquarry1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.quizquarry1.entity.StudentAttempt;
import com.example.quizquarry1.service.StudentAttemptService;

import jakarta.validation.Valid;

@RestController
public class StudentAttemptController {

    @Autowired
    private StudentAttemptService service;

    @PostMapping("/postStudentAttempt")
    public StudentAttempt save(@Valid @RequestBody StudentAttempt attempt) {
        return service.saveAttempt(attempt);
    }

    @GetMapping("/getStudentAttempts")
    public List<StudentAttempt> getAll() {
        return service.getAllAttempts();
    }

    @GetMapping("/getStudentAttempt/{id}")
    public StudentAttempt getById(@PathVariable Long id) {
        return service.getAttemptById(id);
    }

    @PutMapping("/putStudentAttempt/{id}")
    public StudentAttempt update(@PathVariable Long id,
                                 @RequestBody StudentAttempt attempt) {

        return service.updateAttempt(id, attempt);
    }

    @DeleteMapping("/deleteStudentAttempt/{id}")
    public StudentAttempt delete(@PathVariable Long id) {

        return service.deleteAttempt(id);
    }
    
    @PatchMapping("/patchStudentAttempt/{id}")
public StudentAttempt patch(@PathVariable Long id,
                            @RequestBody StudentAttempt attempt) {

    return service.patch(id, attempt);
}
}