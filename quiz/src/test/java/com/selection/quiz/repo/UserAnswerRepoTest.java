package com.selection.quiz.repo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.selection.quiz.entity.UserAnswer;

@RunWith(SpringRunner.class)
@WebMvcTest(UserRepo.class)
public class UserAnswerRepoTest {

	@MockBean
    private UserAnswerRepo userAnswerRepo;

    @Test
    public void retrieveQuestion() throws Exception {
    	UserAnswer answer = new UserAnswer();
    	answer.setAid(1);
    	answer.setOrderNum(1);
    	answer.setQid(1);
    	answer.setUid(1);
    	Mockito.when(userAnswerRepo.retrieveQuestion(answer.getUid(), answer.getOrderNum())).thenReturn(answer);
    	UserAnswer answer2 = userAnswerRepo.retrieveQuestion(answer.getUid(), answer.getOrderNum());
    	Assert.assertTrue(answer.getQid() == answer2.getUid());
    }
    
    @Test
    public void CountCorrectAnswers() throws Exception {
    	UserAnswer answer = new UserAnswer();
    	answer.setAid(1);
    	answer.setOrderNum(1);
    	answer.setQid(1);
    	answer.setUid(1);
    	Mockito.when(userAnswerRepo.CountCorrectAnswers(answer.getUid())).thenReturn(10);
    	Integer answer2 = userAnswerRepo.CountCorrectAnswers(answer.getUid());
    	Assert.assertTrue(10 == answer2);
    }
}