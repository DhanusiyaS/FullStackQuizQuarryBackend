package com.example.quizquarry1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quizquarry1.entity.QuestionBank;
import com.example.quizquarry1.exception.QuizNotFoundException;
import com.example.quizquarry1.repository.QuestionBankRepository;

@Service
public class QuestionBankService {

    @Autowired
    private QuestionBankRepository repo;

    public QuestionBank save(QuestionBank questionBank) {
        return repo.save(questionBank);
    }

    public List<QuestionBank> getAll() {
        return repo.findAll();
    }

    public QuestionBank getById(Long id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new QuizNotFoundException("QuestionBank not found"));
    }

    public QuestionBank update(Long id, QuestionBank questionBank) {

        QuestionBank existing = repo.findById(id)
                .orElseThrow(() ->
                        new QuizNotFoundException("QuestionBank not found"));

        existing.setTitle(questionBank.getTitle());
        existing.setSubjectArea(questionBank.getSubjectArea());

        return repo.save(existing);
    }

    public QuestionBank delete(Long id) {

        QuestionBank existing = repo.findById(id)
                .orElseThrow(() ->
                        new QuizNotFoundException("QuestionBank not found"));

        repo.delete(existing);

        return existing;
    }
    public QuestionBank patch(Long id, QuestionBank bank) {

    QuestionBank existing = repo.findById(id)
            .orElseThrow(() ->
                    new QuizNotFoundException("QuestionBank not found"));

    if (bank.getTitle() != null) {
        existing.setTitle(bank.getTitle());
    }

    if (bank.getSubjectArea() != null) {
        existing.setSubjectArea(bank.getSubjectArea());
    }

    return repo.save(existing);
}
}