package com.example.quizquarry1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.quizquarry1.entity.QuestionBank;
import com.example.quizquarry1.service.QuestionBankService;

import jakarta.validation.Valid;

@RestController
public class QuestionBankController {

    @Autowired
    private QuestionBankService service;

    @PostMapping("/postQuestionBank")
    public QuestionBank save(@Valid @RequestBody QuestionBank questionBank) {
        return service.save(questionBank);
    }

    @GetMapping("/getQuestionBanks")
    public List<QuestionBank> getAll() {
        return service.getAll();
    }

    @GetMapping("/getQuestionBank/{id}")
    public QuestionBank getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/putQuestionBank/{id}")
    public QuestionBank update(@PathVariable Long id,
                               @RequestBody QuestionBank questionBank) {

        return service.update(id, questionBank);
    }

    @DeleteMapping("/deleteQuestionBank/{id}")
    public QuestionBank delete(@PathVariable Long id) {

        return service.delete(id);
    }

    @PatchMapping("/patchQuestionBank/{id}")
public QuestionBank patch(@PathVariable Long id,
                          @RequestBody QuestionBank bank) {

    return service.patch(id, bank);
}
}