package com.example.quizquarry1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class BankQuestion {

    @Id
    @NotNull(message = "Id cannot be null")
    private Long id;

    @NotBlank(message = "Question text cannot be empty")
    private String questionText;

    @NotBlank(message = "Options cannot be empty")
    private String optionsJson;

    @NotBlank(message = "Correct answer cannot be empty")
    private String correctAnswer;

    @NotBlank(message = "Explanation cannot be empty")
    private String explanation;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Difficulty level cannot be null")
    private Difficulty difficultyLevel;

    private Boolean isAiGenerated = false;

    public enum Difficulty {
        EASY,
        MEDIUM,
        HARD
    }

    public BankQuestion() {
    }

    public BankQuestion(Long id, String questionText, String optionsJson,
            String correctAnswer, String explanation,
            Difficulty difficultyLevel, Boolean isAiGenerated) {

        this.id = id;
        this.questionText = questionText;
        this.optionsJson = optionsJson;
        this.correctAnswer = correctAnswer;
        this.explanation = explanation;
        this.difficultyLevel = difficultyLevel;
        this.isAiGenerated = isAiGenerated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getOptionsJson() {
        return optionsJson;
    }

    public void setOptionsJson(String optionsJson) {
        this.optionsJson = optionsJson;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public Difficulty getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(Difficulty difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Boolean getIsAiGenerated() {
        return isAiGenerated;
    }

    public void setIsAiGenerated(Boolean isAiGenerated) {
        this.isAiGenerated = isAiGenerated;
    }
}