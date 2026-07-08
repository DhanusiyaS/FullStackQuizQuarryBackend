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
public class StudentAttempt {

    @Id
    @NotNull(message = "Id cannot be null")
    private Long id;

    @NotBlank(message = "Attempt number cannot be empty")
    @Size(max = 20)
    private String attemptNumber;

    @NotBlank(message = "Score cannot be empty")
    @Size(max = 10)
    private String score;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status cannot be null")
    private Status status = Status.IN_PROGRESS;

    @NotBlank(message = "Started time cannot be empty")
    private String startedAt;

    @NotBlank(message = "Submitted time cannot be empty")
    private String submittedAt;

    public enum Status {
        IN_PROGRESS,
        SUBMITTED
    }

    public StudentAttempt() {
    }

    public StudentAttempt(Long id, String attemptNumber, String score,
            Status status, String startedAt, String submittedAt) {

        this.id = id;
        this.attemptNumber = attemptNumber;
        this.score = score;
        this.status = status;
        this.startedAt = startedAt;
        this.submittedAt = submittedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttemptNumber() {
        return attemptNumber;
    }

    public void setAttemptNumber(String attemptNumber) {
        this.attemptNumber = attemptNumber;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    public String getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(String submittedAt) {
        this.submittedAt = submittedAt;
    }
}