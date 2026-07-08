package com.example.quizquarry1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.quizquarry1.entity.AttemptAnswer;
import com.example.quizquarry1.service.AttemptAnswerService;

import jakarta.validation.Valid;

@RestController
public class AttemptAnswerController {

    @Autowired
    private AttemptAnswerService service;

    @PostMapping("/postAttemptAnswer")
    public AttemptAnswer save(@Valid @RequestBody AttemptAnswer answer) {
        return service.saveAnswer(answer);
    }

    @GetMapping("/getAttemptAnswers")
    public List<AttemptAnswer> getAll() {
        return service.getAllAnswers();
    }

    @GetMapping("/getAttemptAnswer/{id}")
    public AttemptAnswer getById(@PathVariable Long id) {
        return service.getAnswerById(id);
    }

    @PutMapping("/putAttemptAnswer/{id}")
    public AttemptAnswer update(@PathVariable Long id,
            @RequestBody AttemptAnswer answer) {

        return service.updateAnswer(id, answer);
    }

    @DeleteMapping("/deleteAttemptAnswer/{id}")
    public AttemptAnswer delete(@PathVariable Long id) {

        return service.deleteAnswer(id);
    }

    @PatchMapping("/patchAttemptAnswer/{id}")
    public AttemptAnswer patch(@PathVariable Long id,
            @RequestBody AttemptAnswer answer) {

        return service.patch(id, answer);
    }
}