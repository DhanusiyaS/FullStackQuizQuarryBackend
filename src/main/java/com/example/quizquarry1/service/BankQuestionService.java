package com.example.quizquarry1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quizquarry1.entity.BankQuestion;
import com.example.quizquarry1.exception.QuizNotFoundException;
import com.example.quizquarry1.repository.BankQuestionRepository;

@Service
public class BankQuestionService {

    @Autowired
    private BankQuestionRepository repo;

    public BankQuestion save(BankQuestion question) {
        return repo.save(question);
    }

    public List<BankQuestion> getAll() {
        return repo.findAll();
    }

    public BankQuestion getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new QuizNotFoundException("BankQuestion not found"));
    }

    public BankQuestion update(Long id, BankQuestion question) {

        BankQuestion existing = repo.findById(id)
                .orElseThrow(() ->
                        new QuizNotFoundException("BankQuestion not found"));

        existing.setQuestionText(question.getQuestionText());
        existing.setOptionsJson(question.getOptionsJson());
        existing.setCorrectAnswer(question.getCorrectAnswer());
        existing.setExplanation(question.getExplanation());
        existing.setDifficultyLevel(question.getDifficultyLevel());
        existing.setIsAiGenerated(question.getIsAiGenerated());

        return repo.save(existing);
    }

    public BankQuestion delete(Long id) {

        BankQuestion existing = repo.findById(id)
                .orElseThrow(() ->
                        new QuizNotFoundException("BankQuestion not found"));

        repo.delete(existing);

        return existing;
    }

    public BankQuestion patch(Long id, BankQuestion question) {

    BankQuestion existing = repo.findById(id)
            .orElseThrow(() ->
                    new QuizNotFoundException("BankQuestion not found"));

    if (question.getQuestionText() != null) {
        existing.setQuestionText(question.getQuestionText());
    }

    if (question.getOptionsJson() != null) {
        existing.setOptionsJson(question.getOptionsJson());
    }

    if (question.getCorrectAnswer() != null) {
        existing.setCorrectAnswer(question.getCorrectAnswer());
    }

    if (question.getExplanation() != null) {
        existing.setExplanation(question.getExplanation());
    }

    if (question.getDifficultyLevel() != null) {
        existing.setDifficultyLevel(question.getDifficultyLevel());
    }

    if (question.getIsAiGenerated() != null) {
        existing.setIsAiGenerated(question.getIsAiGenerated());
    }

    return repo.save(existing);
}
}