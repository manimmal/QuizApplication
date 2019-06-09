package com.selection.quiz.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.selection.quiz.entity.Answer;

@Repository
public interface AnswerRepo extends JpaRepository<Answer, Integer> {
	
	@Query(value = "select t from Answer t where t.qid=:qid")
	public List<Answer> retrieveAnswersForQuestion(@Param ("qid") Integer qid);

}
