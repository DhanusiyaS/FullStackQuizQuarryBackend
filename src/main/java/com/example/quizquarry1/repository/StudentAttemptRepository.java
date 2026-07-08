package com.example.quizquarry1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quizquarry1.entity.StudentAttempt;
@Repository
public interface StudentAttemptRepository extends JpaRepository<StudentAttempt, Long> {
}