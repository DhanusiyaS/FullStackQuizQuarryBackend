package com.example.quizquarry1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quizquarry1.entity.StudentAttempt;
import com.example.quizquarry1.exception.QuizNotFoundException;
import com.example.quizquarry1.repository.StudentAttemptRepository;

@Service
public class StudentAttemptService {

    @Autowired
    private StudentAttemptRepository repo;

    public StudentAttempt saveAttempt(StudentAttempt attempt) {
        return repo.save(attempt);
    }

    public List<StudentAttempt> getAllAttempts() {
        return repo.findAll();
    }

    public StudentAttempt getAttemptById(Long id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new QuizNotFoundException("StudentAttempt not found"));
    }

    public StudentAttempt updateAttempt(Long id, StudentAttempt attempt) {

        StudentAttempt existing = repo.findById(id)
                .orElseThrow(() ->
                        new QuizNotFoundException("StudentAttempt not found"));

        existing.setAttemptNumber(attempt.getAttemptNumber());
        existing.setScore(attempt.getScore());
        existing.setStatus(attempt.getStatus());
        existing.setStartedAt(attempt.getStartedAt());
        existing.setSubmittedAt(attempt.getSubmittedAt());

        return repo.save(existing);
    }

    public StudentAttempt deleteAttempt(Long id) {

        StudentAttempt existing = repo.findById(id)
                .orElseThrow(() ->
                        new QuizNotFoundException("StudentAttempt not found"));

        repo.delete(existing);

        return existing;
    }

    public StudentAttempt patch(Long id, StudentAttempt attempt) {

    StudentAttempt existing = repo.findById(id)
            .orElseThrow(() ->
                    new QuizNotFoundException("StudentAttempt not found"));

    if (attempt.getAttemptNumber() != null) {
        existing.setAttemptNumber(attempt.getAttemptNumber());
    }

    if (attempt.getScore() != null) {
        existing.setScore(attempt.getScore());
    }

    if (attempt.getStatus() != null) {
        existing.setStatus(attempt.getStatus());
    }

    if (attempt.getStartedAt() != null) {
        existing.setStartedAt(attempt.getStartedAt());
    }

    if (attempt.getSubmittedAt() != null) {
        existing.setSubmittedAt(attempt.getSubmittedAt());
    }

    return repo.save(existing);
}
}