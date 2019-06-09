package com.selection.quiz.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.selection.quiz.entity.UserAnswer;

@Repository
public interface UserAnswerRepo extends JpaRepository<UserAnswer, Integer> {

	@Transactional
	@Modifying
	@Query(value = "delete from UserAnswer t where t.uid=:userId")
	public void deleteUserData(@Param ("userId") Integer userId);
	
	@Query(value = "select t from UserAnswer t where t.uid=:userId and orderNum=:orderNum")
	public UserAnswer retrieveQuestion(@Param ("userId") Integer userId, @Param ("orderNum") Integer orderNum);
	
	@Query(value = "select count(t.uaid) from UserAnswer t, Answer a where a.aid = t.aid and t.uid=:userId and a.correct='Y'")
	public Integer CountCorrectAnswers(@Param ("userId") Integer userId);
}
