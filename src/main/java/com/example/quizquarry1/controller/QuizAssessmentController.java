package com.example.quizquarry1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.quizquarry1.entity.QuizAssessment;
import com.example.quizquarry1.service.QuizAssessmentService;

import jakarta.validation.Valid;

@RestController
public class QuizAssessmentController {

    @Autowired
    private QuizAssessmentService service;

    @PostMapping("/postQuizAssessment")
    public QuizAssessment save(@Valid @RequestBody QuizAssessment quizAssessment) {
        return service.save(quizAssessment);
    }

    @GetMapping("/getQuizAssessments")
    public List<QuizAssessment> getAll() {
        return service.getAll();
    }

    @GetMapping("/getQuizAssessment/{id}")
    public QuizAssessment get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/putQuizAssessment/{id}")
    public QuizAssessment update(@PathVariable Long id,
                                 @RequestBody QuizAssessment quizAssessment) {

        return service.update(id, quizAssessment);
    }

    @DeleteMapping("/deleteQuizAssessment/{id}")
    public QuizAssessment delete(@PathVariable Long id) {

        return service.delete(id);
    }

     @PatchMapping("/patchQuizAssessment/{id}")
public QuizAssessment patch(@PathVariable Long id,
                            @RequestBody QuizAssessment quiz) {

    return service.patch(id, quiz);
}
}