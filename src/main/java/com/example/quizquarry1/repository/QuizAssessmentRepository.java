package com.example.quizquarry1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quizquarry1.entity.QuizAssessment;
@Repository
public interface QuizAssessmentRepository extends JpaRepository<QuizAssessment, Long> {
}