package com.example.quizquarry1.entity;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class QuizAssessment {

    @Id
    @NotNull(message = "Id cannot be null")
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    @Size(max = 200, message = "Title must be under 200 characters")
    private String title;

    @NotBlank(message = "Time limit cannot be empty")
    @Size(max = 10, message = "Time limit must be in minutes")
    private String timeLimitMinutes;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status cannot be null")
    private Status status = Status.DRAFT;

    public enum Status {
        DRAFT,
        PUBLISHED
    }

    public QuizAssessment() {
    }

    public QuizAssessment(Long id, String title, String timeLimitMinutes, Status status) {
        this.id = id;
        this.title = title;
        this.timeLimitMinutes = timeLimitMinutes;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimeLimitMinutes() {
        return timeLimitMinutes;
    }

    public void setTimeLimitMinutes(String timeLimitMinutes) {
        this.timeLimitMinutes = timeLimitMinutes;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}