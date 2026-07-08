package com.example.quizquarry1.entity;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class AttemptAnswer {

    @Id
    @NotNull(message = "Id cannot be null")
    private Long id;

    @NotBlank(message = "Selected option cannot be blank")
    @Size(max = 50)
    private String selectedOption;

    @NotNull(message = "isCorrect cannot be null")
    private Boolean isCorrect;

    public AttemptAnswer() {
    }

    public AttemptAnswer(Long id, String selectedOption, Boolean isCorrect) {
        this.id = id;
        this.selectedOption = selectedOption;
        this.isCorrect = isCorrect;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

}