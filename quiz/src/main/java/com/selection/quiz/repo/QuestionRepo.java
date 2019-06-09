package com.selection.quiz.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.selection.quiz.entity.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

}
