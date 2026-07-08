package com.example.quizquarry1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.quizquarry1.entity.BankQuestion;
import com.example.quizquarry1.service.BankQuestionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bankquestion")
public class BankQuestionController {

    @Autowired
    private BankQuestionService service;

    @PostMapping("/post")
    public BankQuestion save(@Valid @RequestBody BankQuestion question) {
        return service.save(question);
    }

    @GetMapping("/getAll")
    public List<BankQuestion> getAll() {
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public BankQuestion get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/update/{id}")
    public BankQuestion update(@PathVariable Long id,
                               @RequestBody BankQuestion question) {
        return service.update(id, question);
    }

    @DeleteMapping("/delete/{id}")
    public BankQuestion delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @PatchMapping("/patchBankQuestion/{id}")
public BankQuestion patch(@PathVariable Long id,
                          @RequestBody BankQuestion question) {

    return service.patch(id, question);
}
}