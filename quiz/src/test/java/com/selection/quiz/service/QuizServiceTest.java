package com.selection.quiz.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.selection.quiz.bean.LoginRequest;
import com.selection.quiz.bean.LoginResponse;
import com.selection.quiz.bean.QuestionAnswerRequest;
import com.selection.quiz.bean.QuestionAnswerResponse;
import com.selection.quiz.entity.Answer;
import com.selection.quiz.entity.Question;
import com.selection.quiz.entity.User;
import com.selection.quiz.entity.UserAnswer;
import com.selection.quiz.repo.AnswerRepo;
import com.selection.quiz.repo.QuestionRepo;
import com.selection.quiz.repo.UserAnswerRepo;
import com.selection.quiz.repo.UserRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuizServiceTest {
	
	@MockBean
	UserRepo userRepo;
	
	@MockBean
	QuestionRepo questionRepo;
	
	@MockBean
	AnswerRepo answerRepo;
	
	@MockBean
	UserAnswerRepo userAnswerRepo;
	
	@Autowired
	QuizService quizService;
	
	@Test
	public void login() {
		User user = new User();
    	user.setUid(1);
    	user.setUserName("tom");
    	user.setPassword("123");
    	user.setRole("Admin");
	    LoginResponse response = new LoginResponse();
	    response.setId("1");
	    Mockito.when(userRepo.getUser("tom")).thenReturn(user);
	    
	    LoginRequest request = new LoginRequest();
	    request.setRequestId("12324");
	    request.setUsername("tom");
	    request.setPassword("123");
	    
	    LoginResponse response2 = quizService.login(request);
	    Assert.assertTrue(response.getId().equalsIgnoreCase(response2.getId()));
	}
	
	@Test
	public void getFirstQuestion() {
		Question question = new Question();
		question.setQid(1);
		question.setQuestion("What is the capital of Karnataka ? ");
    	
    	QuestionAnswerResponse response = QuestionAnswerResponse.builder().requestId("12345").build();
	    Mockito.when(questionRepo.findAll()).thenReturn(Collections.singletonList(question));
	    Mockito.when(userAnswerRepo.save(new UserAnswer())).thenReturn(new UserAnswer());
	    Mockito.when(answerRepo.retrieveAnswersForQuestion(1)).thenReturn((List<Answer>)new ArrayList<Answer> ());
	    
	    QuestionAnswerRequest request = new QuestionAnswerRequest();
	    request.setRequestId("12345");
	    
	    QuestionAnswerResponse response2 = quizService.getFirstQuestionAnswer(request);
	    Assert.assertTrue(response.getRequestId().equalsIgnoreCase(response2.getRequestId()));
	}
}
