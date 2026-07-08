package com.example.quizquarry1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quizquarry1.entity.AttemptAnswer;
import com.example.quizquarry1.exception.QuizNotFoundException;
import com.example.quizquarry1.repository.AttemptAnswerRepository;

@Service
public class AttemptAnswerService {

    @Autowired
    private AttemptAnswerRepository repo;

    public AttemptAnswer saveAnswer(AttemptAnswer answer) {
        return repo.save(answer);
    }

    public List<AttemptAnswer> getAllAnswers() {
        return repo.findAll();
    }

    public AttemptAnswer getAnswerById(Long id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new QuizNotFoundException("Answer not found"));
    }

    public AttemptAnswer updateAnswer(Long id, AttemptAnswer answer) {

        AttemptAnswer existing = repo.findById(id)
                .orElseThrow(() ->
                        new QuizNotFoundException("Answer not found"));

        existing.setSelectedOption(answer.getSelectedOption());
        existing.setIsCorrect(answer.getIsCorrect());

        return repo.save(existing);
    }

    public AttemptAnswer deleteAnswer(Long id) {

        AttemptAnswer existing = repo.findById(id)
                .orElseThrow(() ->
                        new QuizNotFoundException("Answer not found"));

        repo.delete(existing);

        return existing;
    }

    public AttemptAnswer patch(Long id, AttemptAnswer answer) {

    AttemptAnswer existing = repo.findById(id)
            .orElseThrow(() ->
                    new QuizNotFoundException("Answer not found"));

    if (answer.getSelectedOption() != null) {
        existing.setSelectedOption(answer.getSelectedOption());
    }

    if (answer.getIsCorrect() != null) {
        existing.setIsCorrect(answer.getIsCorrect());
    }

    return repo.save(existing);
}
}