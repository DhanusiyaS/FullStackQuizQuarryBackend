package com.example.quizquarry1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quizquarry1.entity.QuizAssessment;
import com.example.quizquarry1.exception.QuizNotFoundException;
import com.example.quizquarry1.repository.QuizAssessmentRepository;

@Service
public class QuizAssessmentService {

    @Autowired
    private QuizAssessmentRepository repo;

    public QuizAssessment save(QuizAssessment quizAssessment) {
        return repo.save(quizAssessment);
    }

    public List<QuizAssessment> getAll() {
        return repo.findAll();
    }

    public QuizAssessment getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new QuizNotFoundException("QuizAssessment not found"));
    }

    public QuizAssessment update(Long id, QuizAssessment quizAssessment) {

        QuizAssessment existing = repo.findById(id)
                .orElseThrow(() ->
                        new QuizNotFoundException("QuizAssessment not found"));

        existing.setTitle(quizAssessment.getTitle());
        existing.setTimeLimitMinutes(quizAssessment.getTimeLimitMinutes());
        existing.setStatus(quizAssessment.getStatus());

        return repo.save(existing);
    }

    public QuizAssessment delete(Long id) {

        QuizAssessment existing = repo.findById(id)
                .orElseThrow(() ->
                        new QuizNotFoundException("QuizAssessment not found"));

        repo.delete(existing);

        return existing;
    }
    public QuizAssessment patch(Long id, QuizAssessment quiz) {

    QuizAssessment existing = repo.findById(id)
            .orElseThrow(() ->
                    new QuizNotFoundException("QuizAssessment not found"));

    if (quiz.getTitle() != null) {
        existing.setTitle(quiz.getTitle());
    }

    if (quiz.getTimeLimitMinutes() != null) {
        existing.setTimeLimitMinutes(quiz.getTimeLimitMinutes());
    }

    if (quiz.getStatus() != null) {
        existing.setStatus(quiz.getStatus());
    }

    return repo.save(existing);
}
}